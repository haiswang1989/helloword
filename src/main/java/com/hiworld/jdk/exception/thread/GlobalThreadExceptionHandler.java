package com.hiworld.jdk.exception.thread;

/**
 * 全局线程线程的"RuntimeException"的handler
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午3:36:01
 */
public class GlobalThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Global handler, thread : " + t + ", e : " + e);
    }
}
