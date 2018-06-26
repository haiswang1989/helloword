package com.hiworld.jdk.collection;

import java.util.concurrent.SynchronousQueue;

/**
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月24日 上午10:41:05
 */
public class SynchronousQueueSource {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        final SynchronousQueue<String> tansfer = new SynchronousQueue<>(true);
        Thread putThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Do Put.");
                try {
                    tansfer.put("string");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Put Over.");
            }
        });
        putThread.start();
        
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Do Get.");
                try {
                    tansfer.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Get Over.");
            }
        });
        takeThread.start();
        
        try {
            putThread.join();
            takeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Over...");
    }

}
