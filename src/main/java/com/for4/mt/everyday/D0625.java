package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月26日 上午9:22:03
 */
public class D0625 {

    public static void main(String[] args) {
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0625 heapsort = new Heapsort0625(arrForHeapsort);
        heapsort.dosort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0626 topn = new Topn0626(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
    }

}

/**
 * 堆排序
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月25日 上午9:32:11
 */
class Heapsort0625 {
    
    private int[] arr;
    
    public Heapsort0625(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void dosort() {
    
        int length = arr.length;
        int adjustIndex = length / 2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(i, length);
        }
        
        for(int j=length-1; j>=0; j--) {
            swap(0, j);
            adjustHeap(0, j);
        }
        
        
    }
    
    private void adjustHeap(int adjustIndex, int length) {
        
        int temp = arr[adjustIndex];
        int leftChildIndex = 2*adjustIndex + 1;
        
        for(int i=leftChildIndex; i<length; i=2*i+1) {
            int rightChildIndex = leftChildIndex + 1;
            
            if(rightChildIndex < length && arr[rightChildIndex] > arr[leftChildIndex]) {
                i = rightChildIndex;
            }
            
            if(arr[i] > temp) {
                arr[adjustIndex] = arr[i];
                adjustIndex = i;
            } else {
                break;
            }
            
            leftChildIndex = 2*adjustIndex + 1;
        }
        
        arr[adjustIndex] = temp;
    }
    
    private void swap(int swapIndex1, int swapIndex2) {
        int temp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = temp;
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月26日 上午9:22:36
 */
class Topn0626 {
    
    private int[] arr;
    private int n;
    
    public Topn0626(int[] arrArgs, int nArgs) {
        this.arr = arrArgs;
        this.n = nArgs;
    }
    
    /**
     * 
     * @return
     */
    public int[] topn() {
        
        int[] ret = new int[n];
        System.arraycopy(arr, 0, ret, 0, n);
        doAdjust(ret);
        
        for(int i=n; i<arr.length; i++) {
            if(arr[i] > ret[0]) {
                ret[0] = arr[i];
                doAdjust(ret);
            }
        }
        
        return ret;
    }
    
    private void doAdjust(int[] ret) {
        int length = ret.length;
        int adjustIndex = length / 2 - 1;
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(ret, i, length);
        }
    }
    
    private void adjustHeap(int[] ret, int adjustIndex, int length) {
        int temp = ret[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            
            if(rightChildIndex < length && ret[rightChildIndex] < ret[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(ret[k] < ret[adjustIndex]) {
                ret[adjustIndex] = ret[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        ret[adjustIndex] = temp;
    }
}
