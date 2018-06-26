package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月14日 上午8:41:32
 */
public class D0514 {

    public static void main(String[] args) {
        
        int[] arrForFastsort = {11,7,18,3,5,4,10,9};
        Fastsort0515 fastsort = new Fastsort0515(arrForFastsort);
        fastsort.sort();
        System.out.println(Arrays.toString(arrForFastsort));
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0514 heapsort = new Heapsort0514(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargest0516 kLargestByFastsort = new KLargest0516(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
        System.out.println();
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0517 topn = new Topn0517(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
    }

}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月18日 上午8:41:54
 */
class Topn0517 {
    
    private int[] arr;
    private int n;
    
    public Topn0517(int[] arrArgs, int nArgs) {
        this.arr = arrArgs;
        this.n = nArgs;
    }
    
    public int[] topn() {
        
        int[] retArr = new int[n];
        System.arraycopy(arr, 0, retArr, 0, n);
        
        doAdjust(retArr);
        
        for(int i=n; i<arr.length; i++) {
            if(arr[i] > retArr[0]) {
                retArr[0] = arr[i];
                doAdjust(retArr);
            }
        }
        
        return retArr;
    }
    
    /**
     * 
     * @param arr
     * @param adjustIndex
     * @param length
     */
    private void doAdjust(int[] arr) {
        int length = arr.length;
        int adjustIndex = length / 2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(arr, i, length);
        }
    }
    
    private void adjustHeap(int[] arr, int adjustIndex, int length) {
        
        int temp = arr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && arr[rightChildIndex] < arr[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] < temp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        arr[adjustIndex] = temp;
    }
}

/**
 * 第K大元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月16日 上午8:38:11
 */
class KLargest0516 {
    
    private static final int FASTSORT_THRESHOLD = 10;
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0516(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    public int kLargest() {
        return halfFastsort(0, arr.length-1);
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
            fastsort(fromIndex, baseIndex - 1);
            fastsort(baseIndex + 1, endIndex);
        }
    }
}

/**
 * 堆排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月14日 上午8:41:21
 */
class Heapsort0514 {
    
    private int[] arr;
    
    public Heapsort0514(int[] arrArgs) {
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
    
    private void adjustHeap(int adjustIndex, int length) {
        
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
        
        arr[adjustIndex] = temp;
    }
    
    private void swap(int swapIndex1, int swapIndex2) {
        int temp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = temp;
    }
    
}

/**
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月15日 上午9:21:59
 */
class Fastsort0515 {
    int[] arr;
    
    public Fastsort0515(int[] arrArgs) {
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
