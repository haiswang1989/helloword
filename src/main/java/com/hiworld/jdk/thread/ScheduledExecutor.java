package com.hiworld.jdk.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月23日 下午2:34:43
 */
public class ScheduledExecutor {

    public static void main(String[] args) {
        ScheduledTask task = new ScheduledTask(); 
        ScheduledExecutorService scheduledExcutor = Executors.newScheduledThreadPool(5);
        
        //时间延迟的开始时间是任务执行的开始时间,5s就会跑
        scheduledExcutor.scheduleAtFixedRate(task, 0L, 5L, TimeUnit.SECONDS);
        
        //时间延迟的开始时间是任务执行的结束时间,9s才会跑(task睡眠4s + 5s的延迟)
        scheduledExcutor.scheduleWithFixedDelay(task, 0L, 5L, TimeUnit.SECONDS);
    }
}

class ScheduledTask implements Runnable {
    @Override
    public void run() {
        System.out.println("ScheduledTask!!!");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
        }
    }
}
