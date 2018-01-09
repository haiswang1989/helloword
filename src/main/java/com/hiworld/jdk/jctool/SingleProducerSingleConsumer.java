package com.hiworld.jdk.jctool;

import org.jctools.queues.SpscArrayQueue;
import org.jctools.queues.SpscLinkedQueue;

/**
 * 单生产者单消费者对象
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月9日 上午11:19:25
 */
public class SingleProducerSingleConsumer {

    public static void main(String[] args) {
        //内部没有用锁或者CAS处理同步
        SpscArrayQueue<Integer> spscArrayQueue = new SpscArrayQueue<>(1024);
        spscArrayQueue.offer(1);
        int value = spscArrayQueue.poll();
        System.out.println(value);
        
        //内部没有用锁或者CAS处理同步
        SpscLinkedQueue<Integer> spscLinedQeue = new SpscLinkedQueue<>();
        spscLinedQeue.offer(1);
        value = spscLinedQeue.poll();
        System.out.println(value);
    }
}
