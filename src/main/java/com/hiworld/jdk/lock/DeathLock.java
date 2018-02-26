package com.hiworld.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeathLock {

    static final Lock LOCK1 = new ReentrantLock();
    static final Lock LOCK2 = new ReentrantLock();
    
    static class GetLock1Thread implements Runnable {
        @Override
        public void run() {
            LOCK1.lock();
            try {
                System.out.println("Thread-1 Get lock-1");
                TimeUnit.SECONDS.sleep(5L);
                LOCK2.lock();
                try {
                    System.out.println("Thread-1 Get lock-2");
                } finally {
                    System.out.println("Thread-1 release lock-2");
                    LOCK2.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread-1 release lock-1");
                LOCK1.unlock();
            }
        }
    }
    
    static class GetLock2Thread implements Runnable {
        @Override
        public void run() {

            LOCK2.lock();
            try {
                System.out.println("Thread-2 Get lock-2");
                TimeUnit.SECONDS.sleep(5L);
                LOCK1.lock();
                try {
                    System.out.println("Thread-2 Get lock-1");
                } finally {
                    System.out.println("Thread-2 release lock-1");
                    LOCK1.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread-2 release lock-2");
                LOCK2.unlock();
            }
        }
    }
    
    
    public static void main(String[] args) {
        new Thread(new GetLock1Thread()).start();
        new Thread(new GetLock2Thread()).start();
    }
}
