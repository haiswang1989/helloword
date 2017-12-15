package com.hiworld.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsAppendFrequently {

    /**
     * @param args
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        
        Configuration config = new Configuration();
        Path createFile = new Path("hdfs://slcd000hnn001.stubcorp.com:8020/tmp/HdfsAppendFrequently.nb");
        FileSystem fileSystem = null;
        try {
            fileSystem = createFile.getFileSystem(config);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        FSDataOutputStream dos = null;
        System.out.println("start....0");
        
        try {
            dos = fileSystem.append(createFile);        
            dos.writeUTF("90000000000000000000000000 \n");
            //调用hsync,就会直接落盘,这边得直接FSDataOutputStream的对象调用,如果是包装了以后调用都没有效果
            dos.hsync();
            System.out.println("start....1");
            
            Thread.currentThread().sleep(10000l);
            
            dos.writeUTF("1000000000000000000000000 \n");
            //同上
            dos.hsync();
            System.out.println("start....2");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
     
        System.out.println("-----------------------------------");
        
        try {
            Thread.currentThread().sleep(20000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
