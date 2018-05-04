package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月4日 上午10:47:12
 */
public class D0504 {

    public static void main(String[] args) {
        int[] arrForFastsort = {11,7,18,3,5,4,10,9};
        Fastsort0504 fastsort = new Fastsort0504(arrForFastsort);
        fastsort.sort();
        System.out.println(Arrays.toString(arrForFastsort));
        
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0504 heapsort = new Heapsort0504(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0504 topn = new Topn0504(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargest0504 kLargestByFastsort = new KLargest0504(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
    }

}

/**
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月4日 上午8:39:00
 */
class Fastsort0504 {
    
    private int[] arr;
    
    public Fastsort0504(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void sort() {
        fastsort(0, arr.length-1);
    }
    
    private void fastsort(int fromIndex, int endIndex) {
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
                
                while(i < j && arr[i] <= base) {
                    i++;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[i];
                    baseIndex = i;
                }
            }
            
            arr[baseIndex] = base;
            fastsort(fromIndex, baseIndex-1);
            fastsort(baseIndex+1, endIndex);
        }
    }
}

/**
 * 堆排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月4日 上午8:39:35
 */
class Heapsort0504 {
    
    private int[] arr;
    
    public Heapsort0504(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void sort() {
        
        int arrLength = arr.length;
        int adjustIndex = arrLength / 2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(i, arrLength);
        }
        
        for(int j=arrLength-1; j>=0; j--) {
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
        int temp = arr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        for(int k=leftChildIndex; k<length; k++) {
            int rightChildIndex = leftChildIndex + 1;
            
            if(rightChildIndex < length && arr[rightChildIndex] > arr[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] > temp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        arr[adjustIndex] = temp;
    }
    
    /**
     * 
     * @param swapIndex1
     * @param swapIndex2
     */
    private void swap(int swapIndex1, int swapIndex2) {
        int temp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = temp;
    }
}

/**
 * topn
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月4日 上午10:09:13
 */
class Topn0504 {
    
    private int[] arr;
    private int n;
    
    public Topn0504(int[] arrArgs, int nArgs) {
        this.arr = arrArgs;
        this.n = nArgs;
    }
    
    public int[] topn() {
        int[] topnArr = new int[n];
        System.arraycopy(arr, 0, topnArr, 0, n);
        doAdjust(topnArr);
        
        for(int i=n; i<arr.length; i++) {
            if(arr[i] > topnArr[0]) {
                topnArr[0] = arr[i];
                doAdjust(topnArr);
            }
        }
        
        return topnArr;
    }
    
    private void doAdjust(int[] adjustArr) {
        int adjustIndex = adjustArr.length / 2 - 1;
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(adjustArr, i, adjustArr.length);
        }
    }
    
    private void adjustHeap(int[] adjustArr, int adjustIndex, int length) {
        int temp = adjustArr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && adjustArr[rightChildIndex] < adjustArr[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(adjustArr[k] < temp) {
                adjustArr[adjustIndex] = adjustArr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        adjustArr[adjustIndex] = temp;
    }
}

/**
 * 第K大元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月4日 上午10:09:19
 */
class KLargest0504 {
    
    //快排阈值
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0504(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    public int kLargest() {
        return halfFastsort(0, arr.length-1);
    }
    
    private int halfFastsort(int fromIndex, int endIndex) {
        if(endIndex - fromIndex < FASTSORT_THRESHOLD) {
            fastsort(fromIndex, endIndex);
            return arr[kIndex];
        }
        
        int i = fromIndex;
        int j = endIndex;
        
        int baseIndex = i;
        int base = arr[baseIndex];
        
        while(i < j) {
            while(i < j && arr[j] < base) {
                j--;
            }
            
            if(i < j) {
                arr[baseIndex] = arr[j];
                baseIndex = j;
            }
            
            while(i < j && arr[i] >= base) {
                i++;
            }
            
            if(i < j) {
                arr[baseIndex] = arr[i];
                baseIndex = i;
            }
        }
        
        arr[baseIndex] = base;
        if(baseIndex == kIndex) {
            return base;
        } else if(baseIndex > kIndex) { //左半边
            return halfFastsort(fromIndex, baseIndex-1);
        } else { //右半边
            return halfFastsort(baseIndex+1, endIndex);
        }
    }
    
    /**
     * 从大到小排序
     * @param fromIndex
     * @param endIndex
     */
    private void fastsort(int fromIndex, int endIndex) {
        if(fromIndex < endIndex) {
            int i = fromIndex;
            int j = endIndex;
            
            int baseIndex = i;
            int base = arr[baseIndex];
            while(i < j) {
                while(i < j && arr[j] < base) {
                    j--;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[j];
                    baseIndex = j;
                }
                
                while(i < j && arr[i] >= base) {
                    i++;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[i];
                    baseIndex = i;
                }
            }
            
            arr[baseIndex] = base;
            fastsort(fromIndex, baseIndex-1);
            fastsort(baseIndex+1, endIndex);
        }
    }
}
