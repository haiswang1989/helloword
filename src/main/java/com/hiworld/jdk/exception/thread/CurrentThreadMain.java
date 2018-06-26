package com.hiworld.jdk.exception.thread;

/**
 * 测试当前线程的异常处理
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月17日 上午9:40:10
 */
public class CurrentThreadMain {

    public static void main(String[] args) {
        
        Thread t1 = new Thread(new CurrThrowExceptionThread());
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
