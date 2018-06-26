package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月7日 上午8:21:37
 */
public class D0605 {

    public static void main(String[] args) {
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0605 heapsort = new Heapsort0605(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargest0606 kLargestByFastsort = new KLargest0606(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
        System.out.println();
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0607 topn = new Topn0607(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
    }

}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月7日 上午8:23:42
 */
class Topn0607 {
    
    private int[] arr;
    private int n;
    
    public Topn0607(int[] arrArgs, int nArgs) {
        this.arr = arrArgs;
        this.n = nArgs;
    }
    
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
    
    public void doAdjust(int[] ret) {
        int length = ret.length;
        int adjustIndex = length / 2 - 1;
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
    public void adjustHeap(int[] ret, int adjustIndex, int length) {
        
        int temp = ret[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex<length && ret[rightChildIndex] < ret[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(ret[k] < temp) {
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

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月6日 上午8:37:40
 */
class KLargest0606 {
    
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0606(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    public int kLargest() {
        return halfFastsort(0, arr.length - 1);
    }
    
    /**
     * 
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
        if(baseIndex > kIndex) {
            return halfFastsort(fromIndex, baseIndex-1);
        } else if(baseIndex < kIndex) {
            return halfFastsort(baseIndex+1, endIndex);
        } else {
            return base;
        }
    }
    
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
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月5日 上午8:37:18
 */
class Heapsort0605 {
    
    private int[] arr;
    
    public Heapsort0605(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void sort() {
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
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
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
     * @param fromIndex1
     * @param fromIndex2
     */
    private void swap(int fromIndex1, int fromIndex2) {
        int temp = arr[fromIndex1];
        arr[fromIndex1] = arr[fromIndex2];
        arr[fromIndex2] = temp;
    }
    
}
