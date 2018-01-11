package com.hiworld.jdk.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和的任务
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月11日 上午10:29:49
 */
public class SumTask extends RecursiveTask<Long> {
    private static final long serialVersionUID = 1L;
    
    public int start;
    public int end;
    //当元素的个数大于这个值的时候就进行切分
    private int maxGap; 
    
    public SumTask(int start, int end, int maxGap) {
        this.start = start;
        this.end = end;
        this.maxGap = maxGap;
    }
    
    @Override
    protected Long compute() {
        int gap = end - start;
        Long sum = 0L;
        if(gap <= maxGap) {
            for(int i=start; i<=end; ++i) {
                sum += i;
            }
            return sum;
        } else {
            int middle = (start+end)/2;
            SumTask childTaskOne = new SumTask(start, middle, maxGap);
            SumTask childTaskTwo = new SumTask(middle+1, end, maxGap);
            //childTaskOne.fork();
            //childTaskTwo.fork();
            invokeAll(childTaskOne, childTaskTwo);
            return childTaskOne.join() + childTaskTwo.join();
        }
    }
}
