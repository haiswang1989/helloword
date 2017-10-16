package com.hiworld.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        int count = 5;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for(int i=0; i<count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " ,wait.");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " ,go.");
                    System.out.println(Thread.currentThread().getName() + " ,fuck wait again.");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " ,go again.");
                }
            }, "Thread-" + i).start();
            
            Thread.sleep(100l);
        }
        
        Thread.sleep(10000l);
    }

}
