package com.for4.mt.everyday;

import java.util.Arrays;

public class D0411 {

    public static void main(String[] args) {
        
        int[] arrForFastsort = {11,7,18,3,5,4,10,9};
        Fastsort0411 fastsort = new Fastsort0411(arrForFastsort);
        fastsort.doSort(0, arrForFastsort.length-1);
        System.out.println(Arrays.toString(arrForFastsort));
        
        int[] arrForHeapsort = {11,7,18,3,5,4,10,9};
        Heapsort0411 heapsort = new Heapsort0411(arrForHeapsort);
        heapsort.doSort();
        System.out.println(Arrays.toString(arrForHeapsort));
        
        int[] arrForTopn = {11,7,18,3,5,4,10,9};
        TopnByHeapsort0411 topnByHeapsort = new TopnByHeapsort0411(arrForTopn,5);
        int[] topArr = topnByHeapsort.topn();
        System.out.println(Arrays.toString(topArr));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        Fastsort0411 fs = new Fastsort0411(arrKLargest);
        fs.doSort(0, arrKLargest.length-1);
        System.out.println(Arrays.toString(arrKLargest));
        
        for(int i=1; i<=arrKLargest.length; i++) {
            int[] arrKLargest_1 = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
            KLargestByFastsort0411 kLargestByFastsort = new KLargestByFastsort0411(arrKLargest_1, i);
            System.out.print(kLargestByFastsort.kLargestByHalfFastsort(0, arrKLargest_1.length-1) + ", ");
        }
    }

}

/**
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午9:11:22
 */
class Fastsort0411 {
    
    private int[] arr;
    
    public Fastsort0411(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    /**
     * 
     * @param fromIndex
     * @param endIndex
     */
    public void doSort(int fromIndex, int endIndex) {
        
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
            doSort(fromIndex, baseIndex-1);
            doSort(baseIndex+1, endIndex);
        }
    }
}

/**
 * 堆排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午9:11:32
 */
class Heapsort0411 {
    
    private int[] arr;
    
    public Heapsort0411(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    /**
     * 
     */
    public void doSort() {
        int arrLength = arr.length;
        int adjustIndex = arrLength/2 - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(arr, i, arrLength);
        }
        
        for(int j=arrLength-1; j>=0; j--) {
            swap(0, j);
            adjustHeap(arr, 0, j);
        }
    }
    
    /**
     * 调整堆
     * @param arr
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int[] arr, int adjustIndex, int length) {
        int tmp = arr[adjustIndex];
        
        int leftChildIndex = 2 * adjustIndex + 1;
        
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && arr[rightChildIndex] > arr[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(arr[k] > tmp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
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
 * 通过"小根堆"获取topn大元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午9:12:11
 */
class TopnByHeapsort0411 {
    
    private int[] arr;
    private int topn;
    
    public TopnByHeapsort0411(int[] arrArgs, int topnArgs) {
        this.arr = arrArgs;
        this.topn = topnArgs;
    }
    
    public int[] topn() {
        int[] topnArr = new int[topn];
        System.arraycopy(arr, 0, topnArr, 0, topn);
        
        int adjustIndex = topnArr.length/2 - 1;
        doAdjust(topnArr, adjustIndex, topnArr.length);
        
        for(int i=topn; i<arr.length; i++) {
            if(arr[i] > topnArr[0]) {
                topnArr[0] = arr[i];
                doAdjust(topnArr, 0, topn);
            }
        }
        
        return topnArr;
    }
    
    public void doAdjust(int[] topnArr, int adjustIndex, int length) {
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(topnArr, i, length);
        }
    }
    
    public void adjustHeap(int[] topnArr, int adjustIndex, int length) {
        int tmp = topnArr[adjustIndex];
        int leftChildIndex = 2 * adjustIndex + 1;
        for(int k=leftChildIndex; k<length; k=2*k+1) {
            int rightChildIndex = leftChildIndex + 1;
            if(rightChildIndex < length && topnArr[rightChildIndex] < topnArr[leftChildIndex]) {
                k = rightChildIndex;
            }
            
            if(topnArr[k] < tmp) {
                topnArr[adjustIndex] = topnArr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = 2 * adjustIndex + 1;
        }
        
        topnArr[adjustIndex] = tmp;
    }
    
    
}

/**
 * 通过"快速排序"获取第"K"大元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午9:12:29
 */
class KLargestByFastsort0411 {
    
    //fromindex 和 endindex 相差多少时使用快速排序
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int kLargest;
    private int kLargestIndex;
    
    public KLargestByFastsort0411(int[] arrArgs, int kLargestArgs) {
        this.arr = arrArgs;
        this.kLargest = kLargestArgs;
        kLargestIndex = this.kLargest - 1;
    }
    
    /**
     * 
     */
    public int kLargestByHalfFastsort(int fromIndex, int endIndex) {
        if(endIndex - fromIndex <= FASTSORT_THRESHOLD) {
            fastsort(fromIndex, endIndex);
            return arr[kLargestIndex];
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
        if(baseIndex == kLargestIndex) {
            return base;
        } else if(baseIndex > kLargestIndex) { //左半边
            return kLargestByHalfFastsort(fromIndex, baseIndex-1);
        } else { //右半边
            return kLargestByHalfFastsort(baseIndex+1, endIndex);
        }
    }
    
    /**
     * 
     * @param fromIndex
     * @param endIndex
     */
    public void fastsort(int fromIndex, int endIndex) {
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


