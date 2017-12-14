package com.hiworld.exception.thread;

/**
 * 局部线程"RuntimeException"的handler
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午6:11:08
 */
public class CurrThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Curr handler, thread : " + t + ", e : " + e);
    }
}
