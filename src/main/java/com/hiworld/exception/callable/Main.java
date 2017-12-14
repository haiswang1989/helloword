package com.hiworld.exception.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月13日 下午6:24:10
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Boolean> future = es.submit(new CallableThread(true));
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //对于callable接口的call()方法抛出的异常会在这边被捕获到
            e.printStackTrace();
        }
        
    }

}
