package com.hiworld.jdk.queue;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson.JSONObject;

/**
 * 带有持久化功能的Queue
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月27日 上午11:27:10
 * @param <E>
 */
public class PersistenceQueue<E> implements Queue<E> {
    
    private static final long LOG_MAP_SIZE = 1024 * 1024 * 1024;
    private static final long INDEX_MAP_SIZE = 1024;
    
    ReentrantLock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    
    //持久化路径
    private String persistencePath;
    
    private File persistencePathFile;
    private File readIndexFile = null;
    private File writeIndexFile = null;
    private File[] allFiles;
    
    //读文件
    public File currReadFile;
    private RandomAccessFile currReadRandomAccessFile;
    private MappedByteBuffer readMappedBuffer;
    //读取的索引位置
    public int readIndex = 0;
    private RandomAccessFile readIndexAccessFile;
    private MappedByteBuffer readIndexBuffer;
    
    //写文件
    public File currWriteFile;
    private RandomAccessFile currWriteRandomAccessFile;
    private MappedByteBuffer writeMappedBuffer;
    //写入的索引位置
    public int writeIndex = 0;
    private RandomAccessFile writeIndexAccessFile;
    private MappedByteBuffer writeIndexBuffer;
    
    private Class<E> eClass;
    
    public PersistenceQueue(String persistencePath, Class<E> clazz) {
        this.persistencePath = persistencePath;
        this.eClass = clazz;
        readIndexFile = new File(this.persistencePath + File.separator + "readindex.log");
        writeIndexFile = new File(this.persistencePath + File.separator + "writeindex.log");
        persistencePathFile = new File(persistencePath);
    }
    
    /**
     * @throws IOException 
     * 
     */
    public void doPrepare() throws IOException {
        
        if(!persistencePathFile.exists()) {
            persistencePathFile.mkdirs();
        }
        
        if(!readIndexFile.exists()) {
            readIndexFile.createNewFile();
        }
        
        if(!writeIndexFile.exists()) {
            writeIndexFile.createNewFile();
        }
        
        createLogFileIfHasNoLogFile();
        allFiles = persistencePathFile.listFiles();
        
        currReadFile = getReadLogFile();
        currWriteFile = getWriteLogFile();
        
        currReadRandomAccessFile = new RandomAccessFile(currReadFile, "rw");
        currWriteRandomAccessFile = new RandomAccessFile(currWriteFile, "rw");
        
        readIndexAccessFile = new RandomAccessFile(readIndexFile, "rw");
        writeIndexAccessFile = new RandomAccessFile(writeIndexFile, "rw");
        
        readIndexBuffer = readIndexAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, INDEX_MAP_SIZE);
        writeIndexBuffer = writeIndexAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, INDEX_MAP_SIZE);
        
        readIndex = getReadIndex();
        writeIndex = getWriteIndex();
        
        readMappedBuffer = currReadRandomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, LOG_MAP_SIZE);
        writeMappedBuffer = currWriteRandomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, LOG_MAP_SIZE);
        
        readMappedBuffer.position(readIndex);
        writeMappedBuffer.position(writeIndex);
    }
    
    /**
     * 插入
     * @param e
     * @return
     * @throws IOException 
     * @throws InterruptedException 
     */
    public void offer(E e) throws IOException, InterruptedException {
        byte[] bytes = serialize(e);
        int length = bytes.length;
        int capacity = 4 + length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        byteBuffer.putInt(length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            writeMappedBuffer.put(byteBuffer);
            //修改write的索引
            writeIndex += capacity;
            //持久化
            storeIndex(writeIndex, writeIndexBuffer);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    } 
    
    /**
     * 获取但不移除
     * @return
     * @throws IOException 
     * @throws InterruptedException 
     * @throws ClassNotFoundException 
     */
    public E peek() throws IOException, InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            boolean isOk = checkIndexIsOk();
            if(!isOk) {
                notEmpty.await();
            }
            
            readMappedBuffer.mark();
            int messageLength = readMappedBuffer.getInt(readIndex);
            byte[] allMessage = new byte[messageLength + 4];
            readMappedBuffer.get(allMessage);
            readMappedBuffer.reset();
            byte[] messageBytes = new byte[messageLength];
            System.arraycopy(allMessage, 4, messageBytes, 0, messageBytes.length);
            E e = deSerialize(messageBytes);
            return e;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 获取且移除
     * @return
     * @throws IOException 
     * @throws InterruptedException 
     * @throws ClassNotFoundException 
     */
    public E poll() throws IOException, InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            boolean isOk = checkIndexIsOk();
            if(!isOk) {
                notEmpty.await();
            }
            int messageLength = readMappedBuffer.getInt(readIndex);
            byte[] allMessage = new byte[messageLength + 4];
            readMappedBuffer.get(allMessage);
            byte[] messageBytes = new byte[messageLength];
            System.arraycopy(allMessage, 4, messageBytes, 0, messageBytes.length);
            E e = deSerialize(messageBytes);
            readIndex += (messageLength + 4);
            //持久化readIndex
            storeIndex(readIndex, readIndexBuffer);
            return e; 
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 
     * @return
     */
    public File getReadLogFile() {
        return allFiles[0];
    }
    
    /**
     * 
     * @return
     */
    public File getWriteLogFile() {
        return allFiles[allFiles.length-3];
    }
    
    /**
     * 
     * @return
     */
    private int getReadIndex() {
        int readIndex = 0;
        try {
            readIndex = readIndexBuffer.getInt();
        } catch(IndexOutOfBoundsException e) {
        }
        
        return readIndex;
    }
    
    /**
     * 
     * @return
     */
    private int getWriteIndex() {
        int writeIndex = 0;
        try {
            writeIndex = writeIndexBuffer.getInt();
        } catch(IndexOutOfBoundsException e) {
        }
        
        return writeIndex;
    }
    
    /**
     * 
     * @param e
     * @return
     * @throws IOException 
     */
    private byte[] serialize(E e) throws IOException {
        return JSONObject.toJSONBytes(e);
    }
    
    /**
     * 
     * @param messageBytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private E deSerialize(byte[] messageBytes) throws IOException {
        return JSONObject.parseObject(new String(messageBytes), eClass);
    }
    
    /**
     * 
     * @return
     */
    private boolean checkIndexIsOk() {
        if(currReadFile.getName().equals(currWriteFile.getName())) {
            if(readIndex >= writeIndex) {
                return false;
            }
        } 
        
        return true;
    }
    
    /**
     * 
     * @param index
     * @param buffer
     */
    private void storeIndex(int index, MappedByteBuffer buffer) {
        buffer.putInt(0, index);
    }
    
    /**
     * 
     * @throws IOException
     */
    public void createLogFileIfHasNoLogFile() throws IOException {
        int length = persistencePathFile.listFiles().length;
        if(2==length) {
            File currFile = new File(persistencePath + File.separator + getLogFileName());
            currFile.createNewFile();
        }
    }
    
    /**
     * 
     * @return
     */
    public String getLogFileName() {
        return "000001.log";
    }
}

