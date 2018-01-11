package com.hiworld.jdk.forkjoin;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月11日 下午3:28:04
 */
public class SortTaskMain {

    public static void main(String[] args) {
        int elementCnt = 100000000;
        int[] firstArray = createRandomArray(elementCnt);
        int[] secondArray = new int[elementCnt];
        System.arraycopy(firstArray, 0, secondArray, 0, elementCnt);
        
        long t1 = System.currentTimeMillis();
        fastSort(firstArray, 0, firstArray.length-1); //对1亿条数据直接快速排序,耗时在16s左右
        long t2 = System.currentTimeMillis(); 
        System.out.println("first over, use : " + (t2-t1));
        
        long t3 = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SortTask sortTask = new SortTask(secondArray, 0, secondArray.length-1, 5000000); //使用forkjoin框架,进行并发排序,耗时在6s左右
        Future<Void> future = forkJoinPool.submit(sortTask);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long t4 = System.currentTimeMillis();
        System.out.println("second over, use : " + (t4-t3));
    }
    
    /**
     * 
     * @param cnt
     * @return
     */
    public static int[] createRandomArray(int cnt) {
        Random random = new Random();
        int[] anArray = new int[cnt];
        for(int i=0; i<cnt; i++) {
            anArray[i] = random.nextInt();
        }
        return anArray;
    }
    
    /**
     * 快速排序
     * @param anArray
     * @param fromIndex
     * @param toIndex
     */
    public static void fastSort(int[] anArray, int fromIndex, int toIndex) {
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
