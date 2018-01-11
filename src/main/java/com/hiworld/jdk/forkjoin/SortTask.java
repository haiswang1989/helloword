package com.hiworld.jdk.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * 
 * 排序的任务
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月11日 上午10:49:08
 */
public class SortTask extends RecursiveAction {
    
    private static final long serialVersionUID = 1L;
    
    private int[] anArray;
    private int startIndex;
    private int endIndex;
    private int maxGap;
    
    public SortTask(int[] anArray, int startIndex, int endIndex, int maxGap) {
        this.anArray = anArray;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.maxGap = maxGap;
    }
    
    @Override
    protected void compute() {
        
        int fromIndex = startIndex;
        int toIndex = endIndex;
        
        if(toIndex - fromIndex <= maxGap) {
            fastSort(anArray, fromIndex, toIndex);
        } else {
            SortTask[] sortTasks = cutTask(anArray, fromIndex, toIndex, maxGap);
            invokeAll(sortTasks[0], sortTasks[1]);
        }
    }
    
    /**
     * 
     * @param anArray
     * @param startIndex
     * @param endIndex
     * @param maxGap
     * @return
     */
    public SortTask[] cutTask(int[] anArray, int fromIndex, int toIndex, int maxGap) {
        
        int i = fromIndex;
        int j = toIndex;
        int baseIndex = i;
        int base = anArray[baseIndex];
        
        while(i < j) {
            while(i<j && anArray[j] > base) {
                --j;
            }
            
            if(i < j) {
                anArray[baseIndex] = anArray[j];
                baseIndex = j;
            }
            
            //关于这个快速排序的"等号"放到上面一个while和当前while中的任何一个就ok
            while(i<j && anArray[i] <= base) { 
                ++i;
            }
            
            if(i < j) {
                anArray[baseIndex] = anArray[i];
                baseIndex = i;
            }
        }
        
        anArray[baseIndex] = base;
        //将快排的左右两个部分,拆分成一个新的任务
        SortTask sortTaskOne = new SortTask(anArray, fromIndex, baseIndex-1, maxGap);
        SortTask sortTaskTwo = new SortTask(anArray, baseIndex+1, toIndex, maxGap);
        return new SortTask[]{sortTaskOne, sortTaskTwo};
    }
    
    /**
     * 快速排序
     * @param anArray
     * @param fromIndex
     * @param toIndex
     */
    public void fastSort(int[] anArray, int fromIndex, int toIndex) {
        if(fromIndex < toIndex) {
            int i = fromIndex;
            int j = toIndex;
            int baseIndex = i;
            int base = anArray[baseIndex];
            
            while(i < j) {
                while(i<j && anArray[j] > base) {
                    --j;
                }
                
                if(i < j) {
                    anArray[baseIndex] = anArray[j];
                    baseIndex = j;
                }
                
                //关于这个快速排序的"等号"放到上面一个while和当前while中的任何一个就ok
                while(i<j && anArray[i] <= base) { 
                    ++i;
                }
                
                if(i < j) {
                    anArray[baseIndex] = anArray[i];
                    baseIndex = i;
                }
            }
            
            anArray[baseIndex] = base;
            fastSort(anArray, fromIndex, baseIndex-1);
            fastSort(anArray, baseIndex+1, toIndex);
        }
    }
}