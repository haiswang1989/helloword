package com.hiworld.thread;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.google.common.collect.Lists;

/**
 * 栅栏
 * 多线程之间相互协调
 * 一组线程相互等待,到达某个屏障点后,相互等待的线程再次执行
 * 可重用
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月26日 下午2:00:10
 */
public class MutiThreadCyclicBarrier {

    public static void main(String[] args) {
        
        int threadCount = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, new ActionBeforeOpenBarrier());
        List<Thread> runWaitThreads = Lists.newArrayListWithExpectedSize(threadCount);
        
        for(int i=0; i<threadCount; ++i) {
            runWaitThreads.add(new Thread(new RunWaitThread(cyclicBarrier, i+1)));
        }
        
        for (Thread runWaitThread : runWaitThreads) {
            runWaitThread.start();
        }
        
        for (Thread runWaitThread : runWaitThreads) {
            try {
                runWaitThread.join();
            } catch (InterruptedException e) {
            }
        }
        
        System.out.println("over...");
    }
}

class ActionBeforeOpenBarrier implements Runnable {
    
    @Override
    public void run() {
        System.out.println("Action before open barrier : let's go..." );
    }
}

class RunWaitThread implements Runnable {
    
    private CyclicBarrier cyclicBarrier;
    
    private int threadId;
    
    public RunWaitThread(CyclicBarrier cyclicBarrier, int threadId) {
        this.cyclicBarrier = cyclicBarrier;
        this.threadId = threadId;
    }
    
    @Override
    public void run() {
        System.out.println("thread id : " + threadId + ", wait for other thread...");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
        }
        
        System.out.println("thread id : " + threadId + ", every thread is ready, i will go...");
    }
}
