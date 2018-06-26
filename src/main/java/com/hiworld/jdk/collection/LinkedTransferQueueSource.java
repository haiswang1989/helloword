package com.hiworld.jdk.collection;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedTransferQueue
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月16日 上午10:40:46
 */
public class LinkedTransferQueueSource {

    public static void main(String[] args) {
        
        final LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
        
        
        //调用了transfer后,当前线程就会被挂起
        //直到有consumer获取该元素,然后唤醒当前线程
        try {
            linkedTransferQueue.transfer("wanghaisheng");
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        
        ////////////////////////////////////////////////////////////华丽的分割线/////////////////////////////////////////////////////////
        Thread transferThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String putEle = "wanghaisheng";
                System.out.println("Put ele : " + putEle);
                try {
                    linkedTransferQueue.transfer(putEle);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                String ele = null;
                
                try {
                    //获取并移除此队列的头部，在元素变得可用之前一直等待
                    ele = linkedTransferQueue.take();
                    
                    //获取并移除此队列的头部，如果没有可用元素那么直接返回null
                    //ele = linkedTransferQueue.poll();
                    
                    //获取并移除此队列的头部，在指定的等待时间前等待可用的元素
                    //ele = linkedTransferQueue.poll(1L, TimeUnit.SECONDS);
                    
                    //查看(不移除)队列的头部,如果没有可用元素那么直接返回null
                    //ele = linkedTransferQueue.peek();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                
                System.out.println("Get ele : " + ele);
            }
        });
        
        transferThread.start();
        takeThread.start();
        
        try {
            transferThread.join();
            takeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        ////////////////////////////////////////////////////////////华丽的分割线/////////////////////////////////////////////////////////
        /*
        //如果有consumer在等待,那么"生产成功"返回true,否则"生产失败"返回false
        boolean isSuccess = linkedTransferQueue.tryTransfer("wanghaisheng");
        System.out.println("Try transfer result : " + isSuccess);
        
        try {
            //在指定的等待时间前等待Consumer来获取
            isSuccess = linkedTransferQueue.tryTransfer("wanghaisheng", 1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Try transfer in time result : " + isSuccess);
        
        
        //判断是否存在消费者线程
        boolean hasWaitingConsumer = linkedTransferQueue.hasWaitingConsumer();
        System.out.println("Has waitung consumer : " + hasWaitingConsumer);
        
        //获取所有等待消费者线程的个数
        int waitConsumerCount = linkedTransferQueue.getWaitingConsumerCount();
        System.out.println("Wait consumer count : " + waitConsumerCount);
        
        //检测队列元素的个数(Consumer 或者 "等待获取的元素"的个数),需要一个一个的迭代,可能会出现不准确的结果
        int size = linkedTransferQueue.size();
        System.out.println("Wait consumer or element node count : " + size);
        */
    }
}
