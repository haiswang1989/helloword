package com.hiworld.grammar;

import java.util.concurrent.TimeUnit;

/**
 * wait和notify
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月26日 上午10:39:27
 */
public class WaitAndNotify {

    private static volatile boolean CONDITION = false;
    
    private static final Object waitObject = new Object();
    
    public static void main(String[] args) {
        
        Thread waitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (waitObject) { //要调用wait,这边必须先获取wait对象的锁
                  //对于wait调用,放到while循环中,防止"虚假唤醒"
                    while(!CONDITION) {
                        System.out.println("wait...");
                        try {
                            waitObject.wait(); //wait调用的时候会释放锁
                        } catch (InterruptedException e) {
                        }
                        
                        System.out.println("Be notified...");
                    }
                }
            }
        });
        
        Thread notifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                }
                
                synchronized (waitObject) { //要调用notify,这边必须获取
                    System.out.println("Ready to notify...");
                    CONDITION = true;
                    //一般直接使用notifyAll来唤醒线程,防止唤醒失败导致死锁
                    //notify唤醒,只是选择一个线程来唤醒,选择是任意性的
                    waitObject.notifyAll(); 
                    
                }
            }
        });
        
        waitThread.start();
        notifyThread.start();
        
        try {
            waitThread.join();
            notifyThread.join();
        } catch (InterruptedException e) {
        }
        
        System.out.println("over...");
    }

}
