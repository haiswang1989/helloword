package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月19日 上午10:29:30
 */
public class D0419 {

    public static void main(String[] args) {
        
        int[] arrForFastsort = {11,7,18,3,5,4,10,9};
        Fastsort0419 fastsort = new Fastsort0419(arrForFastsort);
        fastsort.sort();
        System.out.println(Arrays.toString(arrForFastsort));
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0419 heapsort = new Heapsort0419(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        TopnByHeapsort0419 topn = new TopnByHeapsort0419(arrForTopn, 5);
        int[] topnArr = topn.topn();
        System.out.println(Arrays.toString(topnArr));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        Fastsort0413 fs = new Fastsort0413(arrKLargest);
        fs.sort();
        System.out.println(Arrays.toString(arrKLargest));
        
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargestByFastsort0419 kLargestByFastsort = new KLargestByFastsort0419(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest() + ", ");
        }
    }

}

/**
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月19日 上午9:16:09
 */
class Fastsort0419 {
    
    private int[] arr;
    
    public Fastsort0419(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void sort() {
        fastsort(0, arr.length-1);
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
 * @date 2018年4月19日 上午9:16:21
 */
class Heapsort0419 {
    
    private int[] arr;
    
    public Heapsort0419(int[] arrArgs) {
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
     * @param arr
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int adjustIndex, int length) {
        int tmp = arr[adjustIndex];
        int leftChindIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChindIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChindIndex + 1;
            
            if(rightChildIndex < length && arr[rightChildIndex] < arr[leftChindIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] < tmp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChindIndex = 2 * adjustIndex + 1;
        }
        
        arr[adjustIndex] = tmp;
    }
    
    /**
     * 
     * @param swapIndex1
     * @param swapIndex2
     */
    private void swap(int swapIndex1, int swapIndex2) {
        int tmp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = tmp;
    }
}

/**
 * 通过堆排序获取TOPN
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月19日 上午9:16:39
 */
class TopnByHeapsort0419 {
    
    private int[] arr;
    private int topn;
    
    public TopnByHeapsort0419(int[] arrArgs, int topnArgs) {
        this.arr = arrArgs;
        this.topn = topnArgs;
    }
    
    public int[] topn() {
        int[] topnArr = new int[topn];
        System.arraycopy(arr, 0, topnArr, 0, topn);
        
        int adjustIndex = topn / 2 - 1;
        doAdjust(topnArr, adjustIndex, topn);
        
        for(int i=topn; i<arr.length; i++) {
            if(arr[i] > topnArr[0]) {
                topnArr[0] = arr[i];
                doAdjust(topnArr, 0, topn);
            }
        }
        
        return topnArr;
    }
    
    /**
     * 
     * @param arr
     * @param adjustIndex
     * @param length
     */
    private void doAdjust(int[] arr, int adjustIndex, int length) {
        for(int i=adjustIndex; i>=0; i--) {
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
        int tmp = arr[adjustIndex];
        int leftChindIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChindIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChindIndex + 1;
            
            if(rightChildIndex < length && arr[rightChildIndex] < arr[leftChindIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] < tmp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChindIndex = 2 * adjustIndex + 1;
        }
        
        arr[adjustIndex] = tmp;
    }
}

/**
 * 第K大元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月19日 上午9:16:52
 */
class KLargestByFastsort0419 {
    
    //快排的阈值
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargestByFastsort0419(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    public int kLargest() {
        return kLargestByHalfFastsort(0, arr.length-1);
    }
    
    /**
     * 
     * @param fromIndex
     * @param endIndex
     * @return
     */
    private int kLargestByHalfFastsort(int fromIndex, int endIndex) {
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
            return kLargestByHalfFastsort(fromIndex, baseIndex-1);
        } else { //右半边
            return kLargestByHalfFastsort(baseIndex+1, endIndex);
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
