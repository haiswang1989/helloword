package com.hiworld.jdk.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的源码
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月5日 下午4:24:46
 */
public class ReentrantLockSource {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        
        lock.lock();
        try {
            
        } finally {
            lock.unlock();
        }
        
    }
}
