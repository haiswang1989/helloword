package com.hiworld.threadcoordinate;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        
        Semaphore semaphore = new Semaphore(3);
        
        semaphore.acquire();
        System.out.println("1");
        
        semaphore.acquire();
        System.out.println("2");
        
        semaphore.acquire();
        System.out.println("3");
        
        semaphore.release();
        
        semaphore.acquire();
        System.out.println("4");
    }

}
