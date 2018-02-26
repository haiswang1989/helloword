package com.hiworld.jdk.lock;

import java.util.concurrent.TimeUnit;

/**
 * jstack {pid}
 * 
 * sleep的线程处于TIMED_WAITING
 * 等待OBJECT对象锁的线程处于"BLOCKED(on object monitor)"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月26日 上午11:19:32
 */
public class JstackThreadBlockedAndTimedWaitingCondition {

    private static final Object OBJECT = new Object();
    
    static class GetObjectMonitor_1 implements Runnable {
        @Override
        public void run() {
            synchronized (OBJECT) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class GetObjectMonitor_2 implements Runnable {
        @Override
        public void run() {
            synchronized (OBJECT) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(new GetObjectMonitor_1(),"GetObjectMonitorThread-1").start();
        new Thread(new GetObjectMonitor_2(),"GetObjectMonitorThread-2").start();
    }

}
