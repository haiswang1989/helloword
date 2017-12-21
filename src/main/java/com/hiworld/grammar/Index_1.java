package com.hiworld.grammar;

/**
 * 何时刷新 主存和线程的私有内存
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月21日 上午9:22:05
 */
public class Index_1 {
    static Integer state = 0;
    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    synchronized (state) { //这边有synchronized就会刷新 主存,,A和B会交替打印
                        if(state % 2 == 0) {
                            System.out.println("A");
                            state++;
                        }
                    }
                }
            }
        },"Print-a");
        
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    synchronized (state) {
                        if(state % 2 == 1) {
                            System.out.println("B");
                            state++;
                        }
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
