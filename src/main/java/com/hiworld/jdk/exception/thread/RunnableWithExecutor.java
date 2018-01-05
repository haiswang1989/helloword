package com.hiworld.jdk.exception.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * 测试Runnable + Executor框架 组合的异常处理情况
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午6:31:19
 */
public class RunnableWithExecutor {
    
    public static void main(String[] args) {
        
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<?> future = es.submit(new RunnableWithExecutorThread());
        
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //这边捕获到了异常
            e.printStackTrace();
        }
    }
    
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午6:30:13
 */
class RunnableWithExecutorThread implements Runnable {
    
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //异常没有到达这边
                System.out.println("thread : " + t + ", exception : " + e);
            }
        });
        
        throw new RuntimeException("Throw in RunnableWithExecutorThread.");
    }
}

