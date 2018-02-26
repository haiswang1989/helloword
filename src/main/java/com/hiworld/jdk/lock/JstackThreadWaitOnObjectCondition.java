package com.hiworld.jdk.lock;

/**
 * jstack {pid}
 * 
 * 线程处于"WAITING(on object monitor)"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月26日 上午10:58:15
 */
public class JstackThreadWaitOnObjectCondition {
    
    private static final Object OBJECT = new Object();
    
    static class WaitOnObjectThread implements Runnable {
        @Override
        public void run() {
            synchronized (OBJECT) {
                try {
                    OBJECT.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(new WaitOnObjectThread(),"WaitOnObjectCondition").start();
    }

}
