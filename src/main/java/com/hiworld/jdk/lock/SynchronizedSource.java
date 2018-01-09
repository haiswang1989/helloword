package com.hiworld.jdk.lock;

/**
 * javac SynchronizedSource.java
 * javap -v SynchronizedSource.class //查看class的字节码(-v参数比较详细) 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月8日 上午8:57:39
 */
public class SynchronizedSource {
    
    private int age = 0;
    
    public void func() {
        synchronized (this) {
            age = age + 1;
        }
    }
    
    public synchronized void syncFunc() {
        age++;
    }
}
