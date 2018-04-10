package com.hiworld.jdk.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadPoolExecutorSource {
    
    private static final String THREAD_POOL_NAME = "test_threadpool";
    
    public static void main(String[] args) {
        
        //池中保存的线程数,包括空闲线程
        int corePoolSize = 2;
        //池中允许的最大线程数
        int maximumPoolSize = 3;
        //当线程数大于核心线程数量时(corePoolSize),终止多余的空闲线程等待新任务的时间
        long keepAliveTime = 30L;
        //********************************************************************************************************************
        //当使用LinkedTransferQueue队列的时候和LinkedBlockingQueue的效果一致
        //但是应该传输效率会更快一点
        //LinkedTransferQueue<Runnable> workQueue = new LinkedTransferQueue<>();
        //********************************************************************************************************************
        //如果使用的SynchronousQueue,那么就直接使用"maximumPoolSize"线程数来处理任务
        //从jconsole来看,就是加入一个任务,就会启动一个"执行线程"处理,直到"执行线程"数等于maximumPoolSize就不会再上涨了
        //原因是SynchronousQueue是一个"传递"队列,不能存放元素,只能又有take的时候才能put(有人put的时候才能take)
        SynchronousQueue<Runnable> workQueue = new SynchronousQueue<>();
        //********************************************************************************************************************
        //如果使用的是ArrayBlockingQueue
        //当提交的任务数小于等于corePoolSize时,来一个执行一个
        //当提交的任务大于corePoolSize时,任务就会存放到workQueue中
        //当workQueue中午饭容纳更多的元素的时候,就会继续创建线程来处理新的任务,直到线程池中"执行线程"大于maximumPoolSize时抛出异常
        //ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1);
        //********************************************************************************************************************
        //当使用LinkedBlockingQueue这个队列的时候
        //理论上讲,当使用LinkedBlockingQueue队列的时候,maximumPoolSize参数基本就没啥用了,"执行线程只会"等于corePoolSize设置的大小
        //当使用LinkedBlockingQueue队列的时候,RejectedExecutionHandler的设置也基本没啥用了,不太可能把LinkedBlockingQueue放满
        //LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        //********************************************************************************************************************
        //创建执行线程的工厂
        ThreadFactory threadFactory = newGenericThreadFactory(THREAD_POOL_NAME);
        
        //关于RejectedExecutionHandler: 由于超出线程范围和队列容量而使执行被堵塞时所使用的处理程序
        //ThreadPoolExecutor中现有的RejectedExecutionHandler的实现
        //new ThreadPoolExecutor.CallerRunsPolicy() //在调用线程中,执行thread,直接调用Thread的run()
        //new ThreadPoolExecutor.AbortPolicy() //直接抛出异常
        //new ThreadPoolExecutor.DiscardPolicy() //直接把异常吃了,这个任务就丢了
        //new ThreadPoolExecutor.DiscardOldestPolicy() //直接吧队列中最老的任务丢弃,将该任务加入到队列
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                //Executor + Runnable的异常处理结果
                //采用这样的方式获取异常异常好像拿不全,输出如下
                //Runnable : java.util.concurrent.FutureTask@388436ec, Throwable : null
                //这边获取到的异常,好像是被包装后的Runnable对象
                System.out.println("Runnable : " + r + ", Throwable : " + t);
            }
        };
        
//        executor.submit(new MyCallable());
        //采用Future.get()的方式,才能正确的拿到Exception
//        Future<?> future = executor.submit(new ThrowExceptionThread());
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            System.out.println("Exception-1 : " + e);
//        } catch (ExecutionException e) {
//            System.out.println("Exception-2 : " + e);
//        }
        
        executor.submit(new MyThread("0001"));
        executor.submit(new MyThread("0002"));
        executor.submit(new MyThread("0003"));
        executor.submit(new MyThread("0004"));
        executor.submit(new MyThread("0005"));
        
        
//        executor.submit(new MyThread("0006"));
//        executor.submit(new MyThread("0007"));
//        executor.submit(new MyThread("0008"));
//        executor.submit(new MyThread("0009"));
//        executor.submit(new MyThread("0010"));
//        executor.submit(new MyThread("0011"));
//        executor.submit(new MyThread("0012"));
        
//        checkThreadFactoryExceptionHandler();
        
    }
    
    /**
     * 只有这样的直接调用,创建 ThreadFactory设置的异常处理才有效
     */
    private static void checkThreadFactoryExceptionHandler() {
        ThrowExceptionThread throwException = new ThrowExceptionThread();
        ThreadFactory threadFactory = newGenericThreadFactory("check-exception-handler");
        Thread throwExceptionThread = threadFactory.newThread(throwException);
        throwExceptionThread.start();
        try {
            throwExceptionThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 线程池创建新线程时使用的工程
     * 
     * 用该ThreadFactory配合ThreadPoolExcutor来使用,这个定义的uncaughtExceptionHandler是不可达的
     * 因为ThreadPoolExcutor + Runnable的组合方式,异常处理不再是通过Thread.setDefaultUncaughtExceptionHandler来设置了
     * 
     * 需要通过submit以后返回的Future的get()方法获取
     * 或者
     * 通过重写ThreadPoolExector的afterExecute方法来获取
     *
     * @param processName
     * @return
     */
    private static ThreadFactory newGenericThreadFactory(String processName) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //这边的异常处理,啥时候能到？
                System.out.println("Thread : " + t + ", Exception : " + e);
            }
        };
        
        return new ThreadFactoryBuilder()
                .setNameFormat(processName + "-%d")
                .setDaemon(true) //如果设置"执行线程"为非守护线程,任务跑完了,整个程序是不会退出的
                //.setDaemon(false) //如果设置"执行线程"为守护线程,那么当任务跑完,整个程序就直接退出了
                .setUncaughtExceptionHandler(uncaughtExceptionHandler) //uncaughtExceptionHandler好像不可达
                .build();
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月17日 下午3:57:29
 */
class MyCallable implements Callable<Void> {
    
    @Override
    public Void call() throws Exception {
        throw new RuntimeException("Callable |= Error!!!");
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月16日 下午2:18:49
 */
class MyThread implements Runnable {
    
    private String threadId;
    
    public MyThread(String threadId) {
        this.threadId = threadId;
    }
    
    @Override
    public void run() {
        System.out.println("submit thread, id : " + threadId);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
        }
        System.out.println("thread run over, id : " + threadId);
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月16日 下午4:04:02
 */
class ThrowExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Runnable |= Error!!!");
    }
}
