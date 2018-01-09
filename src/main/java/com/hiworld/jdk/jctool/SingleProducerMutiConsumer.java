package com.hiworld.jdk.jctool;

import org.jctools.queues.SpmcArrayQueue;

/**
 * 单生产者多消费者的队列
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月9日 上午11:19:10
 */
public class SingleProducerMutiConsumer {

    public static void main(String[] args) {
        
        SpmcArrayQueue<Integer> spmcArrayQueue = new SpmcArrayQueue<>(1024);
        //内部没有用锁或者CAS处理同步
        spmcArrayQueue.offer(1);
        //内部使用CAS保证了"同步"
        int value = spmcArrayQueue.poll();
        System.out.println(value);
        //不存在SpmcLinkedQueue
    }

}
