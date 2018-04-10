package com.for4.mt.everyday;

import java.util.Arrays;

public class D0409 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = {11,7,18,3,5,4,10,9};
        Fastsort0409 fastsort = new Fastsort0409(arr);
        System.out.println("befor fast sort : ");
        fastsort.print();
        System.out.println("after fast sort : ");
        fastsort.doSort();
        fastsort.print();
        
        int[] arrNew = {11,7,18,3,5,4,10,9};
        Heapsort0409 heapsort = new Heapsort0409(arrNew);
        System.out.println("befor heap sort : ");
        heapsort.print();
        System.out.println("after heap sort : ");
        heapsort.doSort();
        heapsort.print();
        
    }
}

/**
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月9日 上午8:20:47
 */
class Fastsort0409 {
    
    int[] arr;
    
    public Fastsort0409(int[] arrArg) {
        this.arr = arrArg;
    }
    
    public void doSort() {
        sort(0, arr.length-1);
    }
    
    private void sort(int beginIndex, int endIndex) {
        if(beginIndex < endIndex) {
            
            int i = beginIndex;
            int j = endIndex;
            
            int baseIndex = i;
            int base = arr[baseIndex];
            
            while(i < j) {
                while(i<j && arr[j] > base) {
                    j--;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[j];
                    baseIndex = j;
                }
                
                while(i<j && arr[i] <= base) {
                    i++;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[i];
                    baseIndex = i;
                }
            }
            
            arr[baseIndex] = base;
            sort(beginIndex, baseIndex-1);
            sort(baseIndex+1, endIndex);
        }
    }
    
    public void print() {
        System.out.println(Arrays.toString(arr));
    }
}

/**
 * 堆排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月9日 上午8:20:59
 */
class Heapsort0409 {
    
    private int[] arr;
    
    public Heapsort0409(int[] arrArg) {
        this.arr = arrArg;
    }
    
    public void doSort() {
        
        int arrLength = arr.length;
        int adjustIndex = (arrLength/2) - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(i, arrLength);
        }
        
        for(int j=arrLength-1; j >= 0; j--) {
            swap(0, j);
            adjustHeap(0, j);
        }
    }
    
    /**
     * 
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int adjustIndex, int length) {
        
        int tmp = arr[adjustIndex];
        int adjustLeftChild = 2*adjustIndex + 1;
        
        for(int i=adjustLeftChild; i<length; i=2*i+1) {
            int adjustRightChild = adjustLeftChild + 1;
            if(adjustRightChild < length && arr[adjustLeftChild] < arr[adjustRightChild]) {
                i = adjustRightChild;
            }
            
            if(arr[i] > tmp) {
                arr[adjustIndex] = arr[i];
                adjustIndex = i;
            } else {
                break;
            }
            
            adjustLeftChild = 2*adjustIndex + 1;
        }
        
        arr[adjustIndex] = tmp;
    }
    
    private void swap(int swapIndex1, int swapIndex2) {
        int tmp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = tmp;
    }
    
    public void print() {
        System.out.println(Arrays.toString(arr));
    }
}