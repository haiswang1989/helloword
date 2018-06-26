package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月28日 上午8:30:22
 */
public class D0528 {

    public static void main(String[] args) {
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0528 heapsort = new Heapsort0528(arrForHeapsort);
        heapsort.dosort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0530 topn = new Topn0530(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargest0601 kLargestByFastsort = new KLargest0601(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
        System.out.println();
    }

}


/**
 * 
 * 第K大
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月1日 上午8:25:51
 */
class KLargest0601 {
    
    //使用快排的阈值
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0601(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    /**
     * 
     * @return
     */
    public int kLargest() {
        return halfFastsort(0, arr.length-1);
    }
    
    /**
     * 半快排
     * @param fromIndex
     * @param endIndex
     * @return
     */
    private int halfFastsort(int fromIndex, int endIndex) {
        if(endIndex - fromIndex <= FASTSORT_THRESHOLD) {
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
        } else if(baseIndex > kIndex) {
            return halfFastsort(fromIndex, baseIndex-1);
        } else {
            return halfFastsort(baseIndex+1, endIndex);
        }
    }
    
    /**
     * 快速排序
     * @param fromIndex
     * @param endIndx
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

/**
 * topn 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月30日 上午8:23:47
 */
class Topn0530 {
    
    private int[] arr;
    private int n;
    
    public Topn0530(int[] arrArgs, int nArgs) {
        this.arr= arrArgs;
        this.n = nArgs;
    }
    
    /**
     * 
     * @return
     */
    public int[] topn() {
        int length = arr.length;
        int[] ret = new int[n];
        System.arraycopy(arr, 0, ret, 0, n);
        doAdjust(ret);
        
        for(int j=n; j<length; j++) {
            if(arr[j] > ret[0]) {
                ret[0] = arr[j];
                doAdjust(ret);
            }
        }
        
        return ret;
    }
    
    /**
     * 
     * @param ret
     */
    private void doAdjust(int[] ret) {
        int length = ret.length;
        int adjustIndex  = length / 2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(ret, i, length);
        }
    }
    
    /**
     * 
     * @param ret
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int[] ret, int adjustIndex, int length) {
        
        int temp = ret[adjustIndex];
        
        int leftChildIndex = 2*adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex<length && ret[leftChildIndex] > ret[rightChildIndex]) {
                k = rightChildIndex;
            }
            
            if(ret[k] < temp) {
                ret[adjustIndex] = ret[k];
                adjustIndex = k;
            } else  {
                break;
            }
            
            leftChildIndex = 2*adjustIndex + 1;
        }
        
        ret[adjustIndex] = temp;
    }
}

/**
 * 堆排序
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月28日 上午8:36:47
 */
class Heapsort0528 {
    
    private int[] arr;
    
    public Heapsort0528(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    /**
     * 
     */
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
    
    /**
     * 
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int adjustIndex, int length) {
        int temp = arr[adjustIndex];
        
        int leftChildIndex = 2*adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            
            if(rightChildIndex<length && arr[rightChildIndex]>arr[leftChildIndex]) {
                k = rightChildIndex;
            } 
            
            if(arr[k] > temp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2*adjustIndex + 1;
        }
        
        arr[adjustIndex] = temp;
    }
    
    /**
     * 
     * @param fromIndex1
     * @param fromIndex2
     */
    private void swap(int fromIndex1, int fromIndex2) {
        int temp = arr[fromIndex1];
        arr[fromIndex1] = arr[fromIndex2];
        arr[fromIndex2] = temp;
    }
}
