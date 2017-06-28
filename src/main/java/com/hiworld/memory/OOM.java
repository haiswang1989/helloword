package com.hiworld.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 创建的线程太多,会抛出如下错误
 * 创建的线程多少，貌似与环境关系很大，主要就是OS的可用内存(64位)
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月28日 上午10:17:24
 */
public class OOM {

    public static void main(String[] args) {
        
        int threadCount = Integer.parseInt(args[0]);
        System.out.println("threadCount : " + threadCount);
        List<Thread> oomTheads = new ArrayList<>();
        for(int i=0; i<threadCount; ++i) {
            oomTheads.add(new Thread(new OOMThread(i)));
        }
        
        for (Thread oomThead : oomTheads) {
            oomThead.start();
        }
        
        try {
            for (Thread oomThead : oomTheads) {
                oomThead.join();
            }
        } catch (InterruptedException e) {
        }
        
        System.out.println("over...");
    }

}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月28日 下午2:29:15
 */
class OOMThread implements Runnable {
    
    private int threadId;
    
    public OOMThread(int threadId) {
        this.threadId = threadId;
    }
    
    @Override
    public void run() {
        System.out.println("threadId : " + threadId + " created...");
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(100000);
            } catch (InterruptedException e) {
            }
        }
    }
} 
