package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月21日 上午8:43:14
 */
public class D0521 {

    public static void main(String[] args) {
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0521 heapsort = new Heapsort0521(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0522 topn = new Topn0522(arrForTopn, 5);
        int[] ret = topn.topn();
        System.out.println(Arrays.toString(ret));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargest0523 kLargestByFastsort = new KLargest0523(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
        System.out.println();
    }

}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月23日 上午8:27:48
 */
class KLargest0523 {
    
    /*快排的阈值*/ 
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0523(int[] arrArgs, int kArgs) {
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
     * 半排序的方式
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
        } else if(baseIndex > kIndex) { //左半边
            return halfFastsort(fromIndex, baseIndex-1);
        } else  { //右半边
            return halfFastsort(baseIndex+1, endIndex);
        }
    }
    
    /**
     * 快速排序
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

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月22日 上午8:39:09
 */
class Topn0522 {
    
   private int[] arr;
   private int n;
   
   public Topn0522(int[] arrArgs, int nArgs) {
       this.arr = arrArgs;
       this.n = nArgs;
   }
   
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
   
   /**
    * 
    * @param ret
    */
   private void doAdjust(int[] ret) {
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

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月21日 上午8:43:11
 */
class Heapsort0521 {
    
    private int[] arr;
    
    public Heapsort0521(int[] arrArgs) {
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
