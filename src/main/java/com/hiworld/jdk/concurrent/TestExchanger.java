package com.hiworld.jdk.concurrent;

import java.util.concurrent.Exchanger;

public class TestExchanger {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Exchanger<String> exchanger = new Exchanger<>();
        Thread carThread = new Thread(new Car(exchanger));
        Thread bikeThread = new Thread(new Bike(exchanger));
        carThread.start();
        bikeThread.start();
        
        try {
            carThread.join();
            bikeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月11日 下午3:59:11
 */
class Car implements Runnable {
    
    Exchanger<String> exchangerDesc;
    
    public Car(Exchanger<String> exchangerDesc) {
        this.exchangerDesc = exchangerDesc;
    }
    
    @Override
    public void run() {
        System.out.println("Car thread.");
        try {
            System.out.println("Car thread get : " + exchangerDesc.exchange("i'am car"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Bike implements Runnable {
    
    Exchanger<String> exchangerDesc;
    
    public Bike(Exchanger<String> exchangerDesc) {
        this.exchangerDesc = exchangerDesc;
    }
    
    @Override
    public void run() {
        System.out.println("Bike thread.");
        try {
            System.out.println("Bike thread get : " + exchangerDesc.exchange("i'am bike"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
