package com.hiworld.jdk.grammar;

/**
 * 何时刷新 主存和线程的私有内存
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月21日 上午9:22:47
 */
public class Index_4 {
    static Integer state = 0;
    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    //通过System.out.println这样也可以进行交替打印A和B
                    //原理是System.out.println内部是使用了synchronized的
                    //在进行state % 2 == 0的判定之前就刷新主存和线程的私有内存
                    System.out.println("");  //注意这部分要放在判断之前,不然会有问题
                    if(state % 2 == 0) {
                        System.out.println("A");
                        state++;
                    } 
                }
            }
        },"Print-a");
        
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("");
                    if(state % 2 == 1) {
                        System.out.println("B");
                        state++;
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
