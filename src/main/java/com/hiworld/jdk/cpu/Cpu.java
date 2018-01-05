package com.hiworld.jdk.cpu;

import java.util.concurrent.TimeUnit;

public class Cpu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Thread t1 = new Thread(new CalculateThread());
        t1.start();
        
        Thread t2 = new Thread(new SleepThread());
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class CalculateThread implements Runnable {
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            int count = 0;
            for(int i=0; i<100000; i++) {
                count += i;
            }
            System.out.println(count);
        }
    }
}

class SleepThread implements Runnable {
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} 
