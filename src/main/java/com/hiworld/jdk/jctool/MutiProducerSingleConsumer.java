package com.hiworld.jdk.jctool;

import org.jctools.queues.MpscArrayQueue;

/**
 * 多生产者单消费者的队列
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月9日 上午11:18:58
 */
public class MutiProducerSingleConsumer {

    public static void main(String[] args) {
        
        MpscArrayQueue<Integer> mpscArrayQueue = new MpscArrayQueue<>(1024);
        //通过CAS来处理"同步"
        mpscArrayQueue.offer(1);
        //内部没有用锁或者CAS处理同步
        int value = mpscArrayQueue.poll();
        System.out.println(value);
    }
}
