package com.hiworld.jdk.thread;

import java.util.concurrent.Executors;

public class ThreadPool {

    public static void main(String[] args) {
        
        //根据需要而创建执行线程的线程池,如果以前的执行线程可用,那么就重用他们
        //ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
        Executors.newCachedThreadPool();
        
        //创建一个有"固定"执行线程的线程池,以"共享的无界队列"来运行这些线程
        //ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        Executors.newFixedThreadPool(1);
        
        //该线程池可以安排"执行任务"在指定延迟之后或者定期的执行
        //new ScheduledThreadPoolExecutor(corePoolSize)
        //super(corePoolSize, Integer.MAX_VALUE, 0, TimeUnit.NANOSECONDS, new DelayedWorkQueue())
        Executors.newScheduledThreadPool(1);
        
        //创建一个"单执行线程"的线程池,以无界队列的方式运行这些线程
        //new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()))
        Executors.newSingleThreadExecutor();
        
        //创建一个"单执行线程"的线程池,可以安排"执行任务"在指定延迟之后或者定期的执行
        //new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1))
        Executors.newSingleThreadScheduledExecutor();
    }
}
