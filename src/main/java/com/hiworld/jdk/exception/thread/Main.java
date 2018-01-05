package com.hiworld.jdk.exception.thread;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午3:37:38
 */
public class Main {

    public static void main(String[] args) {
        //可以设置一个全局的线程的"RuntimeException"的handler
        Thread.setDefaultUncaughtExceptionHandler(new GlobalThreadExceptionHandler());
        
        GlobalThrowExceptionThread globalThrowExceptionThread = new GlobalThrowExceptionThread();
        Thread t1 = new Thread(globalThrowExceptionThread,"global");
        t1.start();
        
        CurrThrowExceptionThread currThrowExceptionThread = new CurrThrowExceptionThread();
        Thread t2 = new Thread(currThrowExceptionThread,"curr");
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
