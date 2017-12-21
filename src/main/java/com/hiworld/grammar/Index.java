package com.hiworld.grammar;

/**
 * 关于指令重排序
 * 理论上出现 0:1 1:0 0:0 1:1 都是有可能的 
 * 但是实际跑了30分钟 1:1的情况没有出现
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月21日 上午9:21:22
 */
public class Index {
    
    int a = 0;
    int b = 0;
    int x = 0;
    int y = 0;
    
    public static void main(String[] args) {
        while(true) {
            test();
        }
    }
    
    public static boolean test() {
        
        final Index index = new Index();
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                index.a = 1;
                index.x = index.b;
            }
        });
        
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                index.b = 1;
                index.y = index.a;
            }
        });
        
        one.start();
        two.start();
        
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
        }
        
        if(index.x==1 && index.y==1) {
            System.out.println(index.x + " : " + index.y);
            return true;
        } else if(index.x==0 && index.y==0) {
            System.out.println(index.x + " : " + index.y);
            return true;
        }
        
        return false;
    }
}
