package com.hiworld.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class TestReadWriteLock {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        
        ReadLock readLock = rwLock.readLock();
        WriteLock writeLock = rwLock.writeLock();
        
        ReadThread rt1 = new ReadThread(readLock);
        ReadThread rt2 = new ReadThread(readLock);
        
        //多个线程可以同时拿到readLock
        Thread t1 = new Thread(rt1);
        Thread t2 = new Thread(rt2);
        t1.start();
        t2.start();
        
        TimeUnit.SECONDS.sleep(5);
        
        //如果writeLock锁起来,readLock就没法获取
        writeLock.lock();
        try {
            TimeUnit.SECONDS.sleep(5);
        } finally {
            writeLock.unlock();
        }
        
        System.out.println("Main over...");
    }
}

class ReadThread implements Runnable {
    ReadLock rLock = null;
    public ReadThread(ReadLock readLock) {
        this.rLock = readLock;
    }
    @Override
    public void run() {
        int count = 1;
        while(true) {
            this.rLock.lock();
            try {
                System.out.println(count++);
            } finally {
                this.rLock.unlock();
            }
            
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}
