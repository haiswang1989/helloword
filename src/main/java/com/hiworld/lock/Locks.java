package com.hiworld.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 实验线程被stop(),可能会导致Lock不会被释放
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月25日 下午4:05:24
 */
public class Locks {
    
    static final ReentrantLock fairLock = new ReentrantLock(true);
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        while(true) {
            isSuccess();
        }
    }
    
    public static boolean isSuccess() {
        
        final Thread t1 = new Thread(new Runnable() {
            
            @Override
            public void run() {
                //这种标准获取获取锁的方式,如果其他的线程stop当前线程,可能会导致死锁
                fairLock.lock();
                System.out.println("Thread-1 Get lock...");
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                }
                
                
                try {
                } finally {
                    System.out.println("Thread-1 release lock...");
                    fairLock.unlock();
                }
                
            }
        }, "thread-1");
        
        t1.start();
        
        try {
            Thread.sleep(500l);
        } catch (InterruptedException e) {
        }
        
        Thread t2 = new Thread(new Runnable() {
            
            @Override
            public void run() {
                System.out.println("Stop...");
                t1.stop();
                fairLock.lock();
                try {
                    System.out.println("Get lock...");
                    System.out.println("---------------------------------------------------");
                } finally {
                    fairLock.unlock();
                }
            }
        }, "thread-2");
        
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        
        return true;
    }
}
