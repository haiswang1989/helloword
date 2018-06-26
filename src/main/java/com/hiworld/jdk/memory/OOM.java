package com.hiworld.jdk.memory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 创建的线程太多,会抛出如下错误
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * 
 * -Xss指定的是Java线程的"栈"的大小,线程的栈空间不再-Xmx指定的内存中,用的是OS的内存,当OS的内存不够的时候会导致这样的异常
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月28日 上午10:17:24
 */
public class OOM {

    public static void main(String[] args) {
        
        int newThreadCnt = Integer.parseInt(args[0]);
        
        ArrayList<Thread> allThreads = new ArrayList<>();
        for(int i=0; i<newThreadCnt; i++) {
            Thread runningThread = new Thread(new OOMThread());
            runningThread.start();
            allThreads.add(runningThread);
        }
        
        System.out.println("over...");
    }
}



/**
 * 创建线程
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月28日 下午2:29:15
 */
class OOMThread implements Runnable {
    
    public OOMThread() {
    }
    
    @Override
    public void run() {
        useStack(0);
    }
    
    public void useStack(int count) {
        if(count < 5000) {
            useStack(++count);
        } else {
            try {
                TimeUnit.SECONDS.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} 
