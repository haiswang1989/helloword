package com.hiworld.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jstack {pid}
 * 
 * 通过Lock来等待锁的线程状态为WAITING(parking)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月26日 上午11:27:16
 */
public class JstackThreadWaitParkingCondition {
    
    private static final Lock LOCK = new ReentrantLock();
    
    static class GetLockThread_1 implements Runnable {
        @Override
        public void run() {
            LOCK.lock();
            try {
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }
    }
    
    static class GetLockThread_2 implements Runnable {
        @Override
        public void run() {
            LOCK.lock();
            try {
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(new GetLockThread_1(), "GetLockThread-1").start();
        new Thread(new GetLockThread_2(), "GetLockThread-2").start();
    }
}
