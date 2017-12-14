package com.hiworld.exception.callable;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午6:21:55
 */
public class CallableThread implements Callable<Boolean> {
    
    boolean flag;
    
    public CallableThread(boolean flagArg) {
        this.flag = flagArg;
    }
    
    @Override
    public Boolean call() throws Exception {
        //设置当前线程的非捕获异常的处理handler
        Thread.currentThread().setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //这边没有捕获到call()抛出的异常
                System.out.println("thread : " + t + ", exception : " + e);
            }
        });
        
        if(flag) {
            throw new RuntimeException("Callbale call() runtime exception.");
        }
        return null;
    }
}
