package com.hiworld.threadcoordinate;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.google.common.collect.Lists;

/**
 * 闭锁
 * 一个线程等待一组线程执行完成再执行
 * 不可重用(如果想下次复用,必须重新创建)
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月26日 下午2:00:04
 */
public class MutiThreadCountDownLatch {

    public static void main(String[] args) {
        int runThreadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(runThreadCount);
        
        Thread waitThread = new Thread(new WaitThread(countDownLatch));
        
        List<Thread> runThreads = Lists.newArrayListWithExpectedSize(runThreadCount);
        for(int i=0; i<runThreadCount; ++i) {
            runThreads.add(new Thread(new RunThread(countDownLatch, i+1)));
        }
        
        waitThread.start();
        for (Thread runThread : runThreads) {
            runThread.start();
        }
        
        try {
            waitThread.join();
            for (Thread runThread : runThreads) {
                runThread.join();
            }
        } catch (InterruptedException e) {
        }
        
        System.out.println("over...");
        
    }
}

class WaitThread implements Runnable {
    
    private CountDownLatch countDownLatch;
    
    public WaitThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    
    @Override
    public void run() {
        
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        
        System.out.println("All condition is ready, i can run...");
    }
}

class RunThread implements Runnable {
    
    private CountDownLatch countDownLatch;
    
    private int threadId;
    
    public RunThread(CountDownLatch countDownLatch, int threadId) {
        this.countDownLatch = countDownLatch;
        this.threadId = threadId;
    }
    
    @Override
    public void run() {
        System.out.println("thread id : " + this.threadId + " run over...");
        countDownLatch.countDown();
    }
}