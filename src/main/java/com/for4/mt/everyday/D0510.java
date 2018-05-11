package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月10日 上午8:30:31
 */
public class D0510 {

    public static void main(String[] args) {
        
        int[] arrForFastsort = {11,7,18,3,5,4,10,9};
        Fastsort0510 fastsort = new Fastsort0510(arrForFastsort);
        fastsort.sort();
        System.out.println(Arrays.toString(arrForFastsort));
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0510 heapsort = new Heapsort0510(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargest0510 kLargestByFastsort = new KLargest0510(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
        System.out.println();
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0510 topn = new Topn0510(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
    }

}

/**
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月10日 上午8:34:25
 */
class Fastsort0510 {
    
    private int[] arr;
    
    public Fastsort0510(int[] arrArgs) {
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
                    arr[baseIndex]  = arr[i];
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
 * @date 2018年5月10日 上午8:34:45
 */
class Heapsort0510 {
    
    private int[] arr;
    
    public Heapsort0510(int[] arrArgs) {
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
        int leftChindIndex = adjustIndex * 2 + 1;
        
        for(int k=leftChindIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChindIndex + 1;
            
            if(rightChildIndex < length && arr[rightChildIndex] > arr[leftChindIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] > temp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChindIndex = adjustIndex * 2 + 1;
        }
        
        arr[adjustIndex] = temp;
    }
    
    private void swap(int index_1, int index_2) {
        int temp = arr[index_1];
        arr[index_1] = arr[index_2];
        arr[index_2] = temp;
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月11日 上午9:06:49
 */
class KLargest0510 {
    
    //快排的阈值
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0510(int[] arrArgs, int kArgs) {
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
        if(kIndex == baseIndex) {
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
            fastsort(fromIndex, baseIndex-1);
            fastsort(baseIndex+1, endIndex);
        }
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月11日 上午9:31:02
 */
class Topn0510 {
    
    private int[] arr;
    private int n;
    
    public Topn0510(int[] arrArgs, int nArgs) {
        this.arr = arrArgs;
        this.n = nArgs;
    }
    
    public int[] topn() {
        int[] ret = new int[n];
        System.arraycopy(arr, 0, ret, 0, n);
        doAjust(ret);
        
        for(int i=n; i<arr.length; i++) {
            if(arr[i] > ret[0]) {
                ret[0] = arr[i];
                doAjust(ret);
            }
        }
        
        return ret;
    }
    
    /**
     * 
     * @param arr
     */
    private void doAjust(int[] arr) {
        int length = arr.length;
        int adjustIndex = length / 2 - 1;
        for(int i = adjustIndex; i>=0; i--) {
            adjustHeap(arr, i, length);
        }
    }
    
    /**
     * 
     * @param arr
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int[] arr, int adjustIndex, int length) {
        int temp = arr[adjustIndex];
        
        int leftChildIndex = adjustIndex * 2 + 1;
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && arr[leftChildIndex] > arr[rightChildIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] < temp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = adjustIndex * 2 + 1;
        }
        
        arr[adjustIndex] = temp;
    }
}