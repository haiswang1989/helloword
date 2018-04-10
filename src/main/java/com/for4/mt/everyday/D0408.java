package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 排序
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月8日 下午2:46:21
 */
public class D0408 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = {11,7,18,3,5,4,10,9};
        
        D0408 sort = new D0408();
//        sort.fastsort(arr, 0, arr.length-1);
        sort.heapsort(arr);
        System.out.println(Arrays.toString(arr));
        
        
    }
    
    /**
     * 快速排序
     * @param arr
     * @param fromIndex
     * @param endIndex
     */
    public void fastsort(int[] arr, int fromIndex, int endIndex) {
        
        if(fromIndex < endIndex) {
            int i = fromIndex;
            int j = endIndex;
            
            int baseIndex = i;
            int base = arr[baseIndex];
            
            while(i < j) {
                while(i < j && arr[j] > base) {
                    j--;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[j];
                    baseIndex = j;
                }
                
                while(i < j && arr[i] < base) {
                    i++;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[i];
                    baseIndex = i;
                }
            }
            
            arr[baseIndex] = base;
            fastsort(arr, fromIndex, baseIndex-1);
            fastsort(arr, baseIndex+1, endIndex);
        }
    }
    
    /**
     * 
     * @param arr
     */
    public void heapsort(int[] arr) {
        
        int length = arr.length;
        
        int adjustIndex = (length / 2) - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            heapsortAdjustHeap(arr, i, length);
        }
        
        for(int j=length-1; j>0; j--) {
            swap(arr, 0, j);
            heapsortAdjustHeap(arr, 0, j);
        }
    }
    
    /**
     * 
     * @param arr
     * @param adjustIndex
     * @param length
     */
    private void heapsortAdjustHeap(int[] arr, int adjustIndex, int length) {
        int tmp = arr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int i=leftChildIndex; i<length; i = 2 * i + 1) {
            
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && arr[leftChildIndex] < arr[rightChildIndex]) {
                i = rightChildIndex;
            }
            
            if(arr[i] > tmp) {
                arr[adjustIndex] = arr[i];
                adjustIndex = i;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        arr[adjustIndex] = tmp;
    }
    
    /**
     * 
     * @param arr
     * @param index1
     * @param index2
     */
    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
