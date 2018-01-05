package com.hiworld.jdk.memory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建的线程太多,会抛出如下错误
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月28日 上午10:17:24
 */
public class OOM {

    public static void main(String[] args) {
        int threadCount = Integer.parseInt(args[0]);
        double xmxG = Double.parseDouble(args[1]);
        
        System.out.println("threadCount : " + threadCount);
        System.out.println("Get memory G : " + xmxG);
        
        int getMemoryCount = (int)(xmxG * 1024 / 4);
        List<byte[]> memory = new LinkedList<>();
        for(int i=0; i<getMemoryCount; ++i) {
            memory.add(allocateMemory4M());
        }
        
        List<Thread> oomTheads = new ArrayList<>();
        for(int i=0; i<threadCount; ++i) {
            oomTheads.add(new Thread(new OOMThread(i)));
        }
        
        for (Thread oomThead : oomTheads) {
            try {
                Thread.sleep(20l);
            } catch (InterruptedException e) {
            }
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
    
    public static byte[] allocateMemory4M() {
        return new byte[4 * 1024 * 1024];
    }
}



/**
 * 创建线程
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
        add(0);
    }
    
    /**
     * 使用递归,让线程将-Xss占满
     * @param count
     */
    public void add(int count) {
        if(count == 12000) {
            try {
                Thread.sleep(10000000l);
            } catch (InterruptedException e) {
            }
        } else {
            add(count+1);
        }
    }
} 
