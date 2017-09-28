package com.hiworld.concurrent;

import java.util.ArrayList;

/**
 * 金融产品交易场景
 * 
 * 约束：
 * 1：同一个金融产品的交易必须按顺序处理
 * 2：不同产品之间的交易互不干扰
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月28日 上午10:02:59
 */
public class SampleOne {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        final Datastructure<Event> dt = new Datastructure<>();
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<65535; i++) {
                    dt.put(new Event(i));
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<65535; i++) {
                    dt.put(new Event(i));
                }
            }
        });
        
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<65535; i++) {
                    dt.put(new Event(i));
                }
            }
        });
        
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<65535; i++) {
                    dt.put(new Event(i));
                }
            }
        });
        
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<65535; i++) {
                    dt.put(new Event(i));
                }
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("check...");
        
        for(int i=0; i<65535; i++) {
            ArrayList<Event> list = dt.get();
            if(list.size()!=5) {
                System.out.println("fuck...");
            }
        }
        
        System.out.println("over...");
    }

}