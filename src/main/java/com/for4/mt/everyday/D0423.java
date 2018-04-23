package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月23日 上午9:54:38
 */
public class D0423 {

    public static void main(String[] args) {
        int[] arrForFastsort = {11,7,18,3,5,4,10,9};
        Fastsort0423 fastsort = new Fastsort0423(arrForFastsort);
        fastsort.sort();
        System.out.println(Arrays.toString(arrForFastsort));
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0423 heapsort = new Heapsort0423(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        Topn0423 topn = new Topn0423(arrForTopn, 5);
        int[] topnRet = topn.topn();
        System.out.println(Arrays.toString(topnRet));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
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
 * @date 2018年4月23日 上午9:43:12
 */
class Fastsort0423 {
    
    private int[] arr;
    
    public Fastsort0423(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void sort() {
        fastsort(0, arr.length-1);
    }
    
    private void fastsort(int fromIndex, int endIndex) {
        if(fromIndex < endIndex) {
            int m = fromIndex;
            int n = endIndex;
            
            int baseIndex = m;
            int base = arr[baseIndex];
            
            while(m < n) {
                while(m < n && arr[n] > base) {
                    n--;
                }
                
                if(m < n) {
                    arr[baseIndex] = arr[n];
                    baseIndex = n;
                }
                
                while(m < n && arr[m] <= base) {
                    m++;
                }
                
                if(m < n) {
                    arr[baseIndex] = arr[m];
                    baseIndex = m;
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
 * @date 2018年4月23日 上午9:44:34
 */
class Heapsort0423 {
    
    private int[] arr;
    
    public Heapsort0423(int[] arrArgs) {
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
            if(rightChildIndex<length && arr[rightChildIndex] > arr[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(temp < arr[k]) {
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
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月23日 上午9:46:46
 */
class Topn0423 {
    
    private int[] arr;
    
    private int topn;
    
    public Topn0423(int[] arrArgs, int topnArgs) {
        this.arr = arrArgs;
        this.topn = topnArgs;
    }
    
    public int[] topn() {
        int[] topnArr = new int[topn];
        System.arraycopy(arr, 0, topnArr, 0, topn);
        
        doAdjust(topnArr);
        
        for(int i=topn; i<arr.length; i++) {
            if(arr[i] > topnArr[0]) {
                topnArr[0] = arr[i];
                doAdjust(topnArr);
            }
        }
        
        return topnArr;
    }
    
    /**
     * 
     */
    private void doAdjust(int[] topnArr) {
        int length = topnArr.length;
        int adjustIndex = length / 2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(topnArr, i, length);
        }
    }
    
    /**
     * 
     * @param topnArr
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int[] topnArr, int adjustIndex, int length) {
        int temp = topnArr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int i=leftChildIndex; i<length; i=2*i+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && topnArr[rightChildIndex] < topnArr[leftChildIndex]) {
                i = rightChildIndex;
            }
            
            if(topnArr[i] < temp) {
                topnArr[adjustIndex] = topnArr[i];
                adjustIndex = i;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        topnArr[adjustIndex] = temp;
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月23日 上午9:46:49
 */
class KLargest0423 {
    
    //进行快排的阈值
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    public KLargest0423(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    public int kLarget() {
        return getKLargest(0, arr.length-1);
    }
    
    /**
     * 
     */
    private int getKLargest(int fromIndex, int endIndex) {
        //达到快排的条件
        if(endIndex - fromIndex <= FASTSORT_THRESHOLD) {
            fastsort(fromIndex, endIndex);
            return arr[kIndex];
        }
        
        int m = fromIndex;
        int n = endIndex;
        int baseIndex = m;
        int base = arr[baseIndex];
        
        while(m < n) {
            while(m < n && arr[n] < base) {
                n--;
            }
            
            if(m < n) {
                arr[baseIndex] = arr[n];
                baseIndex = n;
            }
            
            while(m < n && arr[m] > base) {
                m++;
            }
            
            if(m < n) {
                arr[baseIndex] = arr[m];
                baseIndex = m;
            }
        }
        
        arr[baseIndex] = base;
        if(baseIndex == kIndex) {
            return base;
        } else if(baseIndex > kIndex) {
            return getKLargest(fromIndex, baseIndex-1);
        } else {
            return getKLargest(baseIndex+1, endIndex);
        }
    }
    
    /**
     * 从大到小排序
     * 
     * @param fromIndex
     * @param endIndex
     */
    private void fastsort(int fromIndex, int endIndex) {
        if(fromIndex < endIndex) {
            
            int m = fromIndex;
            int n = endIndex;
            
            int baseIndex = m;
            int base = arr[baseIndex];
            
            while(m < n) {
                while(m < n && arr[n] < base) {
                    n--;
                }
                
                if(m < n) {
                    arr[baseIndex] = arr[n];
                    baseIndex = n;
                }
                
                while(m < n && arr[m] >= base) {
                    m++;
                }
                
                if(m < n) {
                    arr[baseIndex] = arr[m];
                    baseIndex = m;
                }
            }
            
            arr[baseIndex] = base;
            fastsort(fromIndex, baseIndex-1);
            fastsort(baseIndex+1, endIndex);
        }
    }
}


