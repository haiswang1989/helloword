package com.hiworld.concurrent;

import java.util.concurrent.Executors;

public class ThreadPool {
    
    public static void main(String[] args) {
        
        //SynchronousQueue
        Executors.newCachedThreadPool();
        
        //LinkedBlockingQueue
        Executors.newFixedThreadPool(5);
        
        //ScheduledThreadPoolExecutor with DelayedWorkQueue
        Executors.newScheduledThreadPool(5);
        
        //LinkedBlockingQueue
        Executors.newSingleThreadExecutor();
        
        //ScheduledThreadPoolExecutor with DelayedWorkQueue
        Executors.newSingleThreadScheduledExecutor();
    }
    
}
