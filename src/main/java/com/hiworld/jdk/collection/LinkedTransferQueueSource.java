package com.hiworld.jdk.collection;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月16日 上午10:40:46
 */
public class LinkedTransferQueueSource {

    public static void main(String[] args) {
        
        final LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
        
        Thread transferThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //如果有consumer,直接返回,否则把元素放到队列,然后挂起当前线程
                    linkedTransferQueue.transfer("wanghaisheng");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        transferThread.start();
        
        Thread transferThreadCopy = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //如果有consumer,直接返回,否则把元素放到队列,然后挂起当前线程
                    linkedTransferQueue.transfer("wanghaisheng");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        transferThreadCopy.start();
        
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        
//        Thread takeThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //xfer(null, false, SYNC, 0)
//                    linkedTransferQueue.take();
//                    
//                    //xfer(null, false, NOW, 0)
//                    //linkedTransferQueue.poll();
//                    
//                    //xfer(null, false, TIMED, unit.toNanos(timeout));
//                    //linkedTransferQueue.poll(1L, TimeUnit.SECONDS);
//                    
//                    //
//                    //linkedTransferQueue.peek();
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//        takeThread.start();
        
        try {
//            takeThread.join();
            transferThread.join();
            transferThreadCopy.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        
//        //如果有consumer,直接返回true,否则返回false
//        boolean isSuccess = linkedTransferQueue.tryTransfer("wanghaisheng");
//        
//        try {
//            //
//            isSuccess = linkedTransferQueue.tryTransfer("wanghaisheng", 1L, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        
//        //判断是否存在消费者线程
//        boolean hasWaitingConsumer = linkedTransferQueue.hasWaitingConsumer();
//        
//        //获取所有等待消费者线程的个数
//        int waitConsumerCount = linkedTransferQueue.getWaitingConsumerCount();
//        
//        //检测队列元素的个数,需要一个一个的迭代,可能会出现不准确的结果
//        int size = linkedTransferQueue.size();
    }
}
