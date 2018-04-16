package com.for4.mt.everyday;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月16日 上午9:17:35
 */
public class D0413 {

    public static void main(String[] args) {
        
        int[] arrForFastsort = {11,7,18,3,5,4,10,9};
        Fastsort0413 fastsort = new Fastsort0413(arrForFastsort);
        fastsort.sort();
        System.out.println(Arrays.toString(arrForFastsort));
        
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0413 heapsort = new Heapsort0413(arrForHeapsort);
        heapsort.sort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        TopnByHeapsort0413 topnByHeapsort = new TopnByHeapsort0413(arrForTopn, 5);
        int[] topnArr = topnByHeapsort.topn();
        System.out.println(Arrays.toString(topnArr));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        Fastsort0413 fs = new Fastsort0413(arrKLargest);
        fs.sort();
        System.out.println(Arrays.toString(arrKLargest));
        
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargestByFastsort0413 kLargestByFastsort = new KLargestByFastsort0413(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargest(0, arrKLargest_1.length-1) + ", ");
        }
        System.out.println();
        
        int[] arr = {0,11,2,33,14,56,26,97,81,90};
        int n = 10;
        GetNFromM0413 getNFromM0413 = new GetNFromM0413(arr, n);
        int[] retArr = getNFromM0413.get();
        System.out.println(Arrays.toString(retArr));
    }

}

/**
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月16日 上午9:16:45
 */
class Fastsort0413 {
    
    private int[] arr;
    
    public Fastsort0413(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    public void sort() {
        sort(arr, 0, arr.length-1);;
    }
    
    /**
     * 
     * @param arr
     * @param fromIndex
     * @param endIndex
     */
    private void sort(int[] arr, int fromIndex, int endIndex) {
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
            sort(arr, fromIndex, baseIndex-1);
            sort(arr, baseIndex+1, endIndex);
        }
    }
}

/**
 * 堆排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月16日 上午9:17:13
 */
class Heapsort0413 {
    
    private int[] arr;
    
    public Heapsort0413(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    /**
     * 
     */
    public void sort() {
        
        int arrLength = arr.length;
        int adjustIndex = arrLength / 2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(arr, i, arrLength);
        }
        
        for(int j=arrLength-1; j>=0; j--) {
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
    private void adjustHeap(int[] arr, int adjustIndex, int length) {
        int tmp = arr[adjustIndex];
        int leftChildIndex = adjustIndex * 2 + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            
            if(rightChildIndex < length && arr[leftChildIndex] < arr[rightChildIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] > tmp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = adjustIndex * 2 + 1;
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
 * TOPN
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月12日 上午11:27:24
 */
class TopnByHeapsort0413 {
    
    private int[] arr;
    private int topn;
    
    public TopnByHeapsort0413(int[] arrArgs, int topnArgs) {
        this.arr = arrArgs;
        this.topn = topnArgs;
    }
    
    /**
     * 
     * @return
     */
    public int[] topn() {
        int[] topnArr = new int[topn];
        System.arraycopy(arr, 0, topnArr, 0, topn);
        
        int topnArrLength = topnArr.length;
        int adjustIndex = topnArrLength / 2 - 1;
        for(int i=adjustIndex; i>=0; i--) {
            adjustIndex(topnArr, i, topnArrLength);
        }
        
        for(int j=topn; j<arr.length; j++) {
            if(arr[j] > topnArr[0]) {
                topnArr[0] = arr[j];
                adjustIndex(topnArr, 0, topnArrLength);
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
    public void adjustIndex(int[] arr, int adjustIndex, int length) {
        
        int tmp = arr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && arr[leftChildIndex] > arr[rightChildIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] < tmp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        arr[adjustIndex] = tmp;
    }
    
    
}

/**
 * 第"K"大元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月12日 上午11:27:28
 */
class KLargestByFastsort0413 {
    
    //使用快速排序的阈值
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int kIndex;
    
    
    public KLargestByFastsort0413(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.kIndex = this.k - 1;
    }
    
    /**
     * 
     * @param fromIndex
     * @param endIndex
     * @return
     */
    public int kLargest(int fromIndex, int endIndex) {
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
        if(baseIndex == k) {
            return base;
        } else if(baseIndex > k) { //左半边
            return kLargest(fromIndex, baseIndex - 1);
        } else { //右半边
            return kLargest(baseIndex + 1, endIndex);
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
 * @date 2018年4月12日 上午11:27:32
 */
class GetNFromM0413 {
    
    private int[] arr;
    
    private int n;
    
    private Random random = new Random();
    
    public GetNFromM0413(int[] arrArgs, int nArgs) {
        this.arr = arrArgs;
        this.n = nArgs;
    }
    
    /**
     * 
     */
    public int[] get() {
        int[] arrRet = new int[n];
        int arrLength = arr.length;
        for(int i=0; i<n; i++) {
            int randomInt = random.nextInt(arrLength - i);
            swap(randomInt, arrLength - i - 1);
            arrRet[i] = arr[arrLength - i - 1];
        }
        
        return arrRet;
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