package com.hiworld.jdk.exception.thread;

/**
 * 一个抛出"非检查"异常的线程
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午3:25:37
 */
public class GlobalThrowExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Global thread.");
    }
}
