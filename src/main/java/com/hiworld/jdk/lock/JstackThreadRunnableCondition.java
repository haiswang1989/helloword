package com.hiworld.jdk.lock;

/**
 * jstack {pid}
 * 
 * 线程处于RUNNABLE状态
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月26日 上午11:03:49
 */
public class JstackThreadRunnableCondition {
    
    static class RunningThread implements Runnable {
        @Override
        public void run() {
            int count = 0;
            while(true) {
                System.out.println(count++);
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(new RunningThread(), "RunnableConditionThread").start();
    }
}
