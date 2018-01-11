package com.hiworld.jdk.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * 等计算量上来以后,使用Fork/Join框架的速度还是有优势的
 * 
 * 对于多线程的切换,如果计算很快就可以完成,就没有必要用这个并行框架了
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月11日 上午10:15:05
 */
public class SumTaskMain {

    public static void main(String[] args) {
        int start = 0;
        int end = 200000000;
        
        Long sum = 0L;
        long t1 = System.currentTimeMillis();
        for(int i=start; i<=end; ++i) {
            sum += i;
        }
        long t2 = System.currentTimeMillis();
        System.out.println("result : " + sum + ", use : " + (t2-t1));
        
        int maxGap = 90000000;
        SumTask sumTask = new SumTask(start, end, maxGap);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        long t3 = System.currentTimeMillis();
        Future<Long> future = forkJoinPool.submit(sumTask);
        try {
            long result = future.get();
            long t4 = System.currentTimeMillis();
            System.out.println("result : " + result + " ,use : " + (t4-t3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
