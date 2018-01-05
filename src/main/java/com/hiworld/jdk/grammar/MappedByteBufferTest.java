package com.hiworld.jdk.grammar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * 这个直接内存快的飞起,100w次读写 就使用了7ms
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年2月14日 上午10:15:45
 */
public class MappedByteBufferTest {

	public static void main(String[] args) {
		
		File shareFile = new File("e:\\sharefile.txt");
		
		RandomAccessFile randomFile = null;
		
		try {
			randomFile = new RandomAccessFile(shareFile,"rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
//		Thread writerThread = new Thread(new Writer(randomFile));
//		Thread readerThread = new Thread(new Reader(randomFile));
		
		Thread readAndWriter = new Thread(new ReadAndWriter(randomFile));
		
//		writerThread.start();
//		readerThread.start();
		
		readAndWriter.start();
		
		try {
//			writerThread.join();
//			readerThread.join();
			
			readAndWriter.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年2月13日 下午6:43:59
 */
class Writer implements Runnable {
	
	RandomAccessFile randomFile = null;
	
	MappedByteBuffer mapBuf = null;
	
	public Writer(RandomAccessFile randomFile) {
		this.randomFile = randomFile;
	}
	
	public void prepare() throws IOException {
		FileChannel fileChannel = randomFile.getChannel();
		int size = 64;
		mapBuf = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,size);
	}
	
	@Override
	public void run() {
		
		try {
			prepare();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		long i = 1l;
		
		long startTime = System.currentTimeMillis();
		
		while(i < 1000000) {
//			System.out.println("Write msg : " + ());
			mapBuf.putLong(0, ++i);
			
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("use : " + (endTime - startTime));
	}
	
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年2月13日 下午6:44:04
 */
class Reader implements Runnable {
	
	RandomAccessFile randomFile = null;
	
	MappedByteBuffer mapBuf = null;
	
	public void prepare() throws IOException {
		FileChannel fileChannel = randomFile.getChannel();
		int size = 64;
		mapBuf = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,size);
	}
	
	public Reader(RandomAccessFile randomFile) {
		this.randomFile = randomFile;
	}
	
	@Override
	public void run() {
		
		try {
			prepare();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while(true) {
			long msgId = mapBuf.getLong(0);
			System.out.println("read msg id : " + msgId);
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年2月14日 上午10:16:34
 */
class ReadAndWriter implements Runnable {
	
	RandomAccessFile randomFile = null;
	
	MappedByteBuffer mapBuf = null;
	
	public ReadAndWriter(RandomAccessFile randomFile) {
		this.randomFile = randomFile;
	}
	
	public void prepare() throws IOException {
		FileChannel fileChannel = randomFile.getChannel();
		int size = 64;
		mapBuf = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,size);
	}
	
	public void run() {
		
		try {
			prepare();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		long i = 1l;
		
		long startTime = System.currentTimeMillis();
		
		while(i < 1000000) {
			mapBuf.getLong(0);
			mapBuf.putLong(0, ++i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("use : " + (endTime - startTime));
	}
} 


