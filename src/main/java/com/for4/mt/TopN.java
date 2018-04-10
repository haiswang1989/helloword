package com.for4.mt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * TOPN问题
 * 
 * 大顶堆 小顶堆 Java自带的优先级队列
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月2日 上午11:33:16
 */
public class TopN {
    
    public static void main(String[] args) {
        //Integer[] inputs = new Integer[]{10,8,9,5,6,3,7,2,1,4};
        int[] inputs = new int[]{3,7,2,1,8,9,5,6,1,4};
        int topn = 5;
        
//        TopNByJavaPriorityQueue topNByJavaPriorityQueue = new TopNByJavaPriorityQueue(inputs, topn);
//        Integer[] ret = topNByJavaPriorityQueue.topn();
//        System.out.println(Arrays.toString(ret));
        
//        TopNMaxByHeap topnMaxByHeap = new TopNMaxByHeap(inputs, topn);
//        int[] topnArr = topnMaxByHeap.topn();
//        System.out.println(Arrays.toString(topnArr));
        
        TopNMinByHeap topnMinByHeap = new TopNMinByHeap(inputs, topn);
        int[] topnArr = topnMinByHeap.topn();
        System.out.println(Arrays.toString(topnArr));
    }
}

/**
 * 通过"大顶堆"获取TOPN小元素
 * 
 * 取前N个元素生成一个数组Heap,然后对数组进行adjust,将最大元素调整到堆顶
 * 遍历数组与堆顶元素进行比较,如果小于最大的"堆顶"元素,那么替换堆顶元素,
 * 再进行adjust调整,如果大于最大的"堆顶"元素那么则忽略以此类推
 * 
 * 最后得到的heap就是我们要找的值
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月9日 上午10:51:50
 */
class TopNMinByHeap {
    
    private int[] arr;
    private int topn;
    
    public TopNMinByHeap(int[] arrArg, int topnArg) {
        this.arr = arrArg;
        this.topn = topnArg;
    }
    
    /**
     * 
     */
    public int[] topn() {
        int heapLength = topn;
        //构造一个top的数组
        int[] heap = new int[heapLength];
        System.arraycopy(arr, 0, heap, 0, heapLength);
        
        int adjustIndex = heapLength / 2 - 1;
        doAdjust(heap, adjustIndex, heapLength);
        
        for(int i=topn; i<arr.length; i++) {
            int currEle = arr[i];
            if(currEle < heap[0]) {
                heap[0] = currEle;
                doAdjust(heap, 0, heapLength);
            }
        }
        
        return heap;
    }
    
    /**
     * 
     * @param heap
     * @param adjustIndex
     * @param length
     */
    public void doAdjust(int[] heap, int adjustIndex, int length) {
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(heap, i, length);
        }
    }
    
    /**
     * 把最大值调整到最上面
     * @param heap
     * @param adjustIndex
     * @param length
     */
    public void adjustHeap(int[] heap, int adjustIndex, int length) {
        int tmp = heap[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int i=leftChildIndex; i<length; i=2*i+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && heap[leftChildIndex] < heap[rightChildIndex]) {
                i = rightChildIndex;
            }
            
            if(heap[i] > tmp) {
                heap[adjustIndex] = heap[i];
                adjustIndex = i;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        heap[adjustIndex] = tmp;
    }
}

/**
 * 通过"小顶堆"获取TOPN大元素
 * 
 * 取前n个元素生成一个数组heap,然后对heap进行adjust,将最小的元素调到堆顶
 * 然后遍历数组,如果元素比最小的"堆顶"元素大,那么把堆顶元素替换,然后再
 * 进行adjust,如果元素比最小的"堆顶"元素小,那么就直接忽略,以此类推
 * 最后heap中的元素就是我们要的topn
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月8日 下午2:27:51
 */
class TopNMaxByHeap {
    
    private int[] arr;
    private int topn;
    
    public TopNMaxByHeap(int[] arrArg, int topnArg) {
        this.arr = arrArg;
        this.topn = topnArg;
    }
    
    /**
     * 
     */
    public int[] topn() {
        int heapLength = topn;
        //构造一个top的数组
        int[] heap = new int[heapLength];
        System.arraycopy(arr, 0, heap, 0, heapLength);
        
        int adjustIndex = heapLength / 2 - 1;
        doAdjust(heap, adjustIndex, heapLength);
        
        for(int i=topn; i<arr.length; i++) {
            int currEle = arr[i];
            if(currEle > heap[0]) {
                heap[0] = currEle;
                doAdjust(heap, 0, heapLength);
            }
        }
        
        return heap;
    }
    
    /**
     * 
     * @param heap
     * @param adjustIndex
     * @param length
     */
    public void doAdjust(int[] heap, int adjustIndex, int length) {
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(heap, i, length);
        }
    }
    
    /**
     * 把最小值调整到最上面
     * @param heap
     * @param adjustIndex
     * @param length
     */
    public void adjustHeap(int[] heap, int adjustIndex, int length) {
        int tmp = heap[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int i=leftChildIndex; i<length; i=2*i+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && heap[rightChildIndex] < heap[leftChildIndex]) {
                i = rightChildIndex;
            }
            
            if(heap[i] < tmp) {
                heap[adjustIndex] = heap[i];
                adjustIndex = i;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        heap[adjustIndex] = tmp;
    }
}

/**
 * 通过Java的PriorityQueue来实现"TOPN"问题
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月2日 上午11:34:29
 */
class TopNByJavaPriorityQueue {
    
    private Integer[] inputs;
    private int topn;
    //用于控制PriorityQueue的大小的,当currSize大于topn时,就删除PriorityQueue中的元素
    private int currSize = 0;
    
    PriorityQueue<Integer> priorityQueue;
    
    public TopNByJavaPriorityQueue(Integer[] inputsArgs, int topnArgs) {
        this.inputs = inputsArgs;
        this.topn = topnArgs;
        
        priorityQueue = new PriorityQueue<Integer>(topn + 1, new Comparator<Integer>() {
            
            /**
             * o1:是现有的根
             * o2:是新加入的元素
             * 
             * return 
             * >=0 时 直接就不插入
             * <0  时 插入
             * 
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                //return o1 - o2; //topN大
                return o2 - o1; //topN小
            }
        });
    }
    
    /**
     * 
     * @return
     */
    public Integer[] topn() {
        for (Integer ele : inputs) {
            priorityQueue.add(ele);
            currSize++;
            //控制内存,避免元素太多,导致占用的内存太高
            //如果内存足够大,可以直接不进行删除,因为删除会触发"完全二叉树"的结构的调整
            if(currSize > topn) {
                priorityQueue.remove();
            }
        }
        
        //取出topN
        Integer[] ret = priorityQueue.toArray(new Integer[0]);
        return ret;
    }
}

