package com.hiworld.jdk.grammar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 何时刷新 主存和线程的私有内存
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月21日 上午9:22:41
 */
public class Index_2 {
    static Lock lock = new ReentrantLock();
    static Integer state = 0;
    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    
                    lock.lock(); //这边有锁也会刷新主存,A和B会交替打印
                    try { 
                        if(state % 2 == 0) {
                            System.out.println("A");
                            state++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        },"Print-a");
        
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    lock.lock();
                    try {
                        if(state % 2 == 1) {
                            System.out.println("B");
                            state++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "Print-b");
        
        one.start();
        two.start();
        
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
        }
    }
}
