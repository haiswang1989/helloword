package com.for4.mt.everyday;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午11:11:56
 */
public class D0412 {

    public static void main(String[] args) {
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0412 heapsort = new Heapsort0412(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        TopnByHeapsort0412 topnByHeapsort = new TopnByHeapsort0412(arrForTopn, 5);
        int[] retArr = topnByHeapsort.topn();
        System.out.println(Arrays.toString(retArr));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        Fastsort0411 fs = new Fastsort0411(arrKLargest);
        fs.doSort(0, arrKLargest.length-1);
        System.out.println(Arrays.toString(arrKLargest));
        
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargestByFastsort0412 kLargestByFastsort = new KLargestByFastsort0412(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.get(0, arrKLargest_1.length-1) + ", ");
        }
        System.out.println();
        
        int[] arr = {0,11,2,33,14,56,26,97,81,90};
        int n= 10;
        GetNFromM0412 getNFromM0412 = new GetNFromM0412(arr, n);
        int[] ret = getNFromM0412.get();
        System.out.println(Arrays.toString(ret));
    }

}

/**
 * 堆排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午11:10:31
 */
class Heapsort0412 {
    
    private int[] arr;
    
    public Heapsort0412(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    /**
     * 
     */
    public void sort() {
        
        int adkjustIndex = arr.length/2 - 1;
        for(int i=adkjustIndex; i>=0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        
        for(int j=arr.length-1; j>=0; j--) {
            swap(0, j);
            adjustHeap(arr, 0, j);
        }
        
    }
    
    /**
     * 
     * @param arr
     * @param adjustIndex
     * @param length
     */
    public void adjustHeap(int[] arr, int adjustIndex, int length) {
        int tmp = arr[adjustIndex];
        
        int leftChildIndex = 2*adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            
            int rightChildIndex = leftChildIndex +1;
            
            if(rightChildIndex < length && arr[leftChildIndex] > arr[rightChildIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] < tmp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2*adjustIndex + 1;
        }
        
        arr[adjustIndex] = tmp;
    }
    
    private void swap(int swapIndex1, int swapIndex2) {
        int tmp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = tmp;
    }
    
}

/**
 * TOPN问题
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午11:10:51
 */
class TopnByHeapsort0412 {
    
    private int[] arr;
    private int topn;
    
    /**
     * 
     * @param arrAgs
     * @param topnArgs
     */
    public TopnByHeapsort0412(int[] arrAgs, int topnArgs) {
        this.arr = arrAgs;
        this.topn = topnArgs;
    }
    
    public int[] topn() {
        int[] retArr = new int[topn];
        System.arraycopy(arr, 0, retArr, 0, topn);
        
        int adjustIndex = retArr.length / 2 - 1;
        doAdjust(retArr, adjustIndex, retArr.length);
        
        for(int i=topn; i<arr.length; i++) {
            if(arr[i] > retArr[0]) {
                retArr[0] = arr[i];
                doAdjust(retArr, 0, retArr.length);
            }
        }
        
        return retArr;
    }
    
    public void doAdjust(int[] retArr, int adjustIndex, int length) {
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(retArr, i, length);
        }
    }
    
    public void adjustHeap(int[] retArr, int adjustIndex, int length) {
        
        int tmp = retArr[adjustIndex];
        int leftChindIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChindIndex; k<length; k=2*k+1) {
            
            int rightChildIndex = leftChindIndex + 1;
            if(rightChildIndex<length && retArr[rightChildIndex] < retArr[leftChindIndex]) {
                k = rightChildIndex;
            }
            
            if(retArr[k] < tmp) {
                retArr[adjustIndex] = retArr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChindIndex = 2 * adjustIndex + 1;
        }
        
        retArr[adjustIndex] = tmp;
    }
}

/**
 * 第K大元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午11:11:26
 */
class KLargestByFastsort0412 {
    
    private static final int FASTSORT_THRESHOLD = 10;
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargestByFastsort0412(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k-1;
    }
    
    public int get(int fromIndex, int endIndex) {
        if(endIndex - fromIndex <= FASTSORT_THRESHOLD) {
            fastsort(fromIndex, endIndex);
            return arr[kIndex];
        }
        
        int i = fromIndex;
        int j = endIndex;
        
        int baseIndex = i;
        int base = arr[baseIndex];
        
        while(i < j) {
            while(i<j && arr[j] < base) {
                j--;
            }
            
            if(i < j) {
                arr[baseIndex] = arr[j];
                baseIndex = j;
            }
            
            while(i<j && arr[i] >= base) {
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
            return get(fromIndex, baseIndex-1);
        } else { //右半边
            return get(baseIndex+1, endIndex);
        }
    }
    
    /**
     * 
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
                while(i<j && arr[j] < base) {
                    j--;
                }
                
                if(i < j) {
                    arr[baseIndex] = arr[j];
                    baseIndex = j;
                }
                
                while(i<j && arr[i] >= base) {
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
 * 从M个数中随机取N个数,确保N个数不重复
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午11:19:14
 */
class GetNFromM0412 {
    
    private int[] arr;
    private int length;
    private int n;
    private Random random;
    
    public GetNFromM0412(int[] arrAgs, int nArgs) {
        this.arr = arrAgs;
        this.n = nArgs;
        this.length = this.arr.length;
        this.random = new Random();
        
    }
    
    public int[] get() {
        int[] ret = new int[n];
        for(int i=0; i<n; i++) {
            int randomValue = random.nextInt(length-i);
            swap(randomValue, length - i - 1);
            ret[i] = arr[length - i - 1]; 
        }
        
        return ret;
    }
    
    private void swap(int swapIndex1, int swapIndex2) {
        int tmp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = tmp;
    }
}


