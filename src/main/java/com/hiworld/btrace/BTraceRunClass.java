package com.hiworld.btrace;

import java.util.concurrent.TimeUnit;

/**
 * 运行的代码
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月6日 上午11:13:00
 */
public class BTraceRunClass {

    public static void main(String[] args) {
        BTraceRunClass btraceRunClass = new BTraceRunClass();
        while(true) {
            btraceRunClass.funcCallSystemGC();
            btraceRunClass.slowFunc1Second();
            btraceRunClass.slowFunc2Second();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
        }
    }
    
    /**
     * call system GC的方法
     */
    public void funcCallSystemGC() {
        System.gc();
    }
    
    /**
     * 慢方法,sleep 1s
     */
    public void slowFunc1Second() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    /**
     * 慢方法,sleep 2s
     */
    public void slowFunc2Second() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
    }
}
