package com.hiworld.jdk.threadlocal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalSource {

    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            new Thread(new MyThread()).start();
        }
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class MyThread implements Runnable {
    
    private static final AtomicInteger uniqueId = new AtomicInteger(0);
    
    ThreadLocal<Integer> age = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return uniqueId.getAndIncrement();
        }
    };
    
    @Override
    public void run() {
        Integer actualAge = age.get();
    }
}
