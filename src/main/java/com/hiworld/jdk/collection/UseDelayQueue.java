package com.hiworld.jdk.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.Getter;

/**
 * 使用场景：
 * 1：重试机制,比如处理某个消息失败,希望10s以后对消息做重试处理
 * 2：ScheduledThreadPoolExecutor中的DelayedWorkQueue是对其的优化使用
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月24日 上午9:44:15
 */
public class UseDelayQueue {

    public static void main(String[] args) throws InterruptedException {
        
        Message message5 = new Message("5");
        DelayedMessage delayedMessage5 = new DelayedMessage(5000L, message5);
        
        Message message10 = new Message("10");
        DelayedMessage delayedMessage10 = new DelayedMessage(10000L, message10);
        
        Message message15 = new Message("15");
        DelayedMessage delayedMessage15 = new DelayedMessage(15000L, message15);
        
        //DelayQueue的实现原理
        //DelayQueue内部其实就是一个PriorityQueue(优先级)队列,优先级就是元素的delay时间
        DelayQueue<DelayedMessage> delayQueue = new DelayQueue<>();
        delayQueue.offer(delayedMessage5);
        delayQueue.offer(delayedMessage10);
        delayQueue.offer(delayedMessage15);
        System.out.println("Put over...");
        
        //take的实现原理：
        //由于队列中的元素是按照"到期时间的先后顺序"排序的,所以对头的第一个元素,是最先应该被take出来的
        //所以获取第一个元素,查看"到期时间"通过getDelay()获取多长时间以后,该元素可以获取
        //查看leader对象是否为null(是否有现在该线程前面获取元素),如果为null,设置当前线程为leader,然后await delay时间
        //如果不为null,那么直接await当前线程,等待leader获取到元素以后再signal自己
        DelayedMessage delayedMessage = delayQueue.take();
        System.out.println(delayedMessage.getData().getData());
        
        delayedMessage = delayQueue.take();
        System.out.println(delayedMessage.getData().getData());
        
        delayedMessage = delayQueue.take();
        System.out.println(delayedMessage.getData().getData());
        System.out.println("Get over...");
    }

}

/**
 * 所有放入DelayQueue的对象都必须实现Delayed接口
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月24日 上午9:46:48
 */
class DelayedMessage implements Delayed {
    
    private final long delayTime;
    private final long expire;
    
    @Getter
    private Message data;
    
    public DelayedMessage(long delay, Message message) {
        this.delayTime = delay;
        this.expire = System.currentTimeMillis() + delayTime;
        this.data = message;
    }
    
    /**
     * 获取超时时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
    
    /**
     * 
     */
    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}

/**
 * 消息对象
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月24日 上午9:49:44
 */
class Message {
    
    @Getter
    private String data;
    
    public Message(String data) {
        this.data = data;
    }
}
