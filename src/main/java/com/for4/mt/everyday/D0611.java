package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月11日 上午8:23:35
 */
public class D0611 {

    public static void main(String[] args) {
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0611 heapsort = new Heapsort0611(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0612 topn = new Topn0612(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargest0614 kLargestByFastsort = new KLargest0614(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
        System.out.println();
    }

}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月14日 上午8:28:46
 */
class KLargest0614 {
    
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0614(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    public int kLargest() {
        int fromIndex = 0;
        int endIndex = arr.length - 1;
        return halfFastsort(fromIndex, endIndex);
    }
    
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
            while(i < j && arr[i] > base) {
                i++;
            }
            
            if(i < j) {
                arr[baseIndex] = arr[i];
                baseIndex = i;
            }
            
            while(i < j && arr[j] <= base) {
                j--;
            }
            
            if(i < j) {
                arr[baseIndex] = arr[j];
                baseIndex = j;
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
    
    private void fastsort(int fromIndex, int endIndex) {
        if(fromIndex < endIndex) {
            int i = fromIndex;
            int j = endIndex;
            
            int baseIndex = i;
            int base = arr[baseIndex];
            
            while(i < j) {
                while(i < j && arr[i] > base) {
                    i++;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[i];
                    baseIndex = i;
                }
                
                while(i < j && arr[j] <= base) {
                    j--;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[j];
                    baseIndex = j;
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
 * @date 2018年6月12日 上午8:28:05
 */
class Topn0612 {
    
    private int[] arr;
    private int n;
    
    public Topn0612(int[] arrArgs, int nArgs) {
        this.arr = arrArgs;
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
        
        for(int i=n; i<length; i++) {
            if(ret[0] < arr[i]) {
                ret[0] = arr[i];
                doAdjust(ret);
            }
        }
        
        return ret;
    }
    
    /**
     * 
     * @param ary
     */
    private void doAdjust(int[] ary) {
        int length = ary.length;
        int adjustIndex = length / 2 - 1;
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(ary, i, length);
        }
    }
    
    /**
     * 
     * @param ary
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int[] ary, int adjustIndex, int length) {
        int temp = ary[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex<length && ary[rightChildIndex]<ary[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(ary[k] < temp) {
                ary[adjustIndex] = ary[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        ary[adjustIndex] = temp;
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月12日 上午8:27:15
 */
class Heapsort0611 {
    
    private int[] arr;
    
    public Heapsort0611(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void sort() {
        int length = arr.length;
        int adjustIndex = length / 2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjsutHeap(i, length);
        }
        
        for(int j=length-1; j>=0; j--) {
            swap(0, j);
            adjsutHeap(0, j);
        }
    }
    
    private void adjsutHeap(int adjustIndex, int length) {
        
        int temp = arr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && arr[leftChildIndex] < arr[rightChildIndex]) {
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
        
        arr[adjustIndex] = temp;; 
        
    }
    
    private void swap(int swapIndex1, int swapIndex2) {
        int temp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = temp;
    }
}
