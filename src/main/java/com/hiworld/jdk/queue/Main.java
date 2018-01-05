package com.hiworld.jdk.queue;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        
//        final PersistenceQueue<People> queue = new PersistenceQueue<>("F:\\queue", People.class);
//        try {
//            queue.doPrepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        
        //11839
        
        LinkedBlockingQueue<People> queue = new LinkedBlockingQueue<>();
        
        long t11 = System.currentTimeMillis();
        
        Thread t1 = new Thread(new PutThread(queue));
        Thread t2 = new Thread(new GetThread(queue));
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long t22 = System.currentTimeMillis();
        System.out.println("use : " + (t22-t11));
        
//        People people = new People(100000, "wanghaisheng");
//        
//        try {
//            int i=0;
//            long t1 = System.currentTimeMillis();
//            while(i++!=10000000) {
//                queue.offer(people);
//            }
//            long t2 = System.currentTimeMillis();
//            System.out.println("use : " + (t2-t1));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        try {
//            int count = 0;
//            while((++count) <= 6) {
//                System.out.println(queue.peek());
//                
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}

class PutThread implements Runnable {
    
    //private PersistenceQueue<People> queue;
//    public PutThread(PersistenceQueue<People> queue) {
//        this.queue = queue;
//    }
    
    LinkedBlockingQueue<People> queue;
    
    public PutThread(LinkedBlockingQueue<People> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        for(int i=0; i<=10000000; i++) {
            People p = new People(i, "wanghaisheng");
            //System.out.println("offer : " + p.toString());
//            try {
                queue.offer(p);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class GetThread implements Runnable {
    
//    private PersistenceQueue<People> queue;
//    
//    public GetThread(PersistenceQueue<People> queue) {
//        this.queue = queue;
//    }
    
    LinkedBlockingQueue<People> queue;
    
    public GetThread(LinkedBlockingQueue<People> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                //People p = queue.poll();
                People p = queue.take();
                //System.err.println("poll : " + p.toString());
                if(p.id == 10000000) {
                    break;
                }
//            } catch (IOException e) {
//                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
