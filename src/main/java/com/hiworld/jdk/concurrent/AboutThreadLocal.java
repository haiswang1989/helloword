package com.hiworld.jdk.concurrent;

import java.util.ArrayList;
import java.util.List;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年10月16日 下午3:40:30
 */
public class AboutThreadLocal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        List<Thread> threadList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            /*
            Thread t1 = new Thread(new JavaThreadLocal());
            threadList.add(t1);
            */
            
            NettyFastThreadLocal t1 = new NettyFastThreadLocal();
            threadList.add(t1);
        }
        
        for (Thread thread : threadList) {
            thread.start();
        }
        
        try {
            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("over...");
    }
}

/**
 * JDK自带的ThreadLocal
 * 
 * JDK内部的ThreadLocal是使用Map来存储  ThreadLocal的值
 * 这样如果hash函数实现不好,会出现冲突,这样在获取值的时候,就需要对链表进行遍历
 * 
 * JDK的ThreadLocal的Key是WeakReference(防止内存膨胀),这样就可能被GC回收,当Key被回收以后
 * 配合get()的逻辑,就可以清除该ThreadLocal对于的Value
 * 
 * JDK原生的ThreadLocal在get()的时候,会清除被GC回收的Key的(变成null)的value
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年10月16日 下午4:33:54
 */
class JavaThreadLocal implements Runnable {
    
    ThreadLocal<Integer> local = new ThreadLocal<>();
    
    public Integer getLocal() {
        Integer localValue = local.get();
        //如果该ThreadLoca的值没有初始化,那么给ThreadLoca赋值
        if(null==localValue) {
            localValue = 0;
            local.set(localValue);
        }
        
        return localValue;
    }
    
    @Override
    public void run() {
        int localValue = getLocal();
        System.out.println("local value : " + localValue);
    }
}

/**
 * Netty的FastThreadLocal和FastThreadLocalThread
 * 
 * FastThreadLocal内部是使用的一个数组存储值的
 * 每一个FastThreadLocal在线程中有一个对应的Index(该Index是自增序列)
 * 这样在get()的时候就是一个O(1)复杂度
 * 
 * 这两个一定要组合使用
 * 如果只是单纯的使用FastThreadLocal + JDK的Thread,那么就会退化成JDK自带的ThreadLocal
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年10月16日 下午4:39:47
 */
class NettyFastThreadLocal extends FastThreadLocalThread {
    
    FastThreadLocal<Integer> local = new FastThreadLocal<>();
    
    public Integer getLocal() {
        Integer localValue = local.get();
        if(null==localValue) {
            localValue = 0;
            local.set(localValue);
        }
        return localValue;
    }
    
    @Override
    public void run() {
        int localValue = getLocal();
        System.out.println("local value : " + localValue);
    }
} 


