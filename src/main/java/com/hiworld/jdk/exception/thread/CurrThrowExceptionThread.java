package com.hiworld.jdk.exception.thread;

/**
 * 一个抛出"非检查"异常的线程
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午3:25:37
 */
public class CurrThrowExceptionThread implements Runnable {
    @Override
    public void run() {
        //可以给线程独立的设置"RuntimeException"的handler
        Thread.currentThread().setUncaughtExceptionHandler(new CurrThreadExceptionHandler());
        throw new RuntimeException("Curr thread.");
    }
}
