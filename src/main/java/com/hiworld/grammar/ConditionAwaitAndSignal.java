package com.hiworld.grammar;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock condition await signal
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月26日 上午11:51:27
 */
public class ConditionAwaitAndSignal {
    private static Lock LOCK = new ReentrantLock();
    private static Condition CONDITION = LOCK.newCondition(); 
    private static boolean CAN_GO = false;
    public static void main(String[] args) {
        Thread await_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!CAN_GO) {
                    LOCK.lock(); //获取锁
                    try {
                        System.out.println("await...");
                        //堵塞,并且把锁给释放
                        //被唤醒后,重新获取到锁?不需要再手动获取?
                        CONDITION.await(); 
                        System.out.println("Be signaled...");
                    } catch (InterruptedException e) {
                    } finally {
                        LOCK.unlock();
                    }
                }
            }
        });
        Thread notify_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LOCK.lock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("Ready to notify...");
                    CAN_GO = true;
                    CONDITION.signalAll(); //唤醒await的线程
                } catch (InterruptedException e) {
                } finally {
                    LOCK.unlock();
                }
            }
        });
        await_thread.start();
        notify_thread.start();
        try {
            await_thread.join();
            notify_thread.join();
        } catch (InterruptedException e) {
        }
        System.out.println("over...");
    }
}
