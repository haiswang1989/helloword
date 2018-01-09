package com.hiworld.jdk.jctool;

import org.jctools.queues.MpmcArrayQueue;

/**
 * 多生产者多消费者的队列
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月9日 上午11:18:45
 */
public class MutiProducerMutiConsumer {

    public static void main(String[] args) {
        MpmcArrayQueue<Integer> mpmcArrayQueue = new MpmcArrayQueue<>(1024);
        //内部使用CAS处理"同步"问题
        mpmcArrayQueue.offer(1);
        //内部使用CAS处理"同步"问题
        int value = mpmcArrayQueue.poll();
        System.out.println(value);
    }

}
