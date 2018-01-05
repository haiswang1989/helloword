package com.hiworld.jdk.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Wait for singel-1.");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                }
                System.out.println("go go go - 1.");
            }
        }).start();
        
        Thread.sleep(100l);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Wait for singel-2.");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                }
                System.out.println("go go go - 2.");
            }
        }).start();
        
        Thread.sleep(100l);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Count down...");
                countDownLatch.countDown();
            }
        }).start();
        
        Thread.sleep(100l);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Count down...");
                countDownLatch.countDown();
            }
        }).start();
        
        Thread.sleep(100l);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Count down...");
                countDownLatch.countDown();
            }
        }).start();
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        
    }

}
