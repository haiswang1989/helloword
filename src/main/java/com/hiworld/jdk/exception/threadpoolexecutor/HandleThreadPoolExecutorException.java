package com.hiworld.jdk.exception.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executor(ThreadPoolExecutor) + Runnable 
 * 重写afterExecute方法可以获取到Runnable中run()方法抛出的"RuntimeException"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月14日 上午10:22:57
 */
public class HandleThreadPoolExecutorException {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100)) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                // TODO Auto-generated method stub
                super.afterExecute(r, t);
                //可以在这个方法里面直接处理Exception,这样就不要在Future的get()方法获取异常了
                System.out.println("Fuck, Get exception , thread : " + r + ", exception : " + t);
            }
        };
        
        TPEThread tpeThread = new TPEThread();
        Future<?> future = tpe.submit(tpeThread);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //上面的afterExecute和这边的get()都会捕获到异常
            System.out.println("Futurn Get Exception : " + e);
            e.printStackTrace();
        }
        
    }

}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月14日 上午10:20:25
 */
class TPEThread implements Runnable {
    
    @Override
    public void run() {
        throw new RuntimeException("TPEThread throw exception.");
    }
} 


