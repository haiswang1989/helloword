package com.for4.mt.everyday;

import java.util.Arrays;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月10日 上午9:27:31
 */
public class D0410 {

    public static void main(String[] args) {
        
        int[] arr = {11,7,18,3,5,4,10,9};
        Fastsort fastsort = new Fastsort(arr);
        fastsort.doSort();
        fastsort.print();
        
        Heapsort heapsort = new Heapsort(arr);
        heapsort.doSort();
        heapsort.print();
        
        int topn = 5;
        TopnByHeapsort tonByHeapsort = new TopnByHeapsort(arr, topn);
        int[] topnRet = tonByHeapsort.topn();
        System.out.println(Arrays.toString(topnRet));
        
        int[] arrKLargest = {11,7,18,3,5,4,10,9,1,2,20,25,12,13,55,66,33,99,78,89,55};
        KLargest kLargest = new KLargest(arrKLargest, 5);
        int k = kLargest.getKLargest();
        System.out.print(k);
    }

}

/**
 * 第K大
 * 
 * 使用类似于快排的方式解决
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月10日 上午10:25:32
 */
class KLargest {
    
    //使用快排的阈值(fromindex和endindex差值)
    private static final int FASTSORT_THRESHOLD = 10;
    
    private int[] arr;
    private int k;
    private int shoudIndex;
    
    public KLargest(int[] arrArgs, int kArgs) {
        this.arr = arrArgs;
        this.k = kArgs;
        this.shoudIndex = this.k - 1;
    }
    
    public int getKLargest() {
        return halfFastsort(0, arr.length-1);
    }
    
    public int halfFastsort(int fromIndex, int endIndex) {
        if(endIndex - fromIndex <= FASTSORT_THRESHOLD) {
            fastsort(fromIndex, endIndex);
            return arr[shoudIndex];
        } else {
            
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
            if(baseIndex == shoudIndex) {
                return arr[shoudIndex];
            } else if(baseIndex < shoudIndex) { //右半边
                return halfFastsort(baseIndex+1, endIndex);
            } else { //左半边
                return halfFastsort(fromIndex, baseIndex-1);
            }
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
 * 快速排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月10日 上午9:28:16
 */
class Fastsort {
    
    private int[] arr;
    
    public Fastsort(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    /**
     * 
     */
    public void doSort() {
        sort(arr, 0, arr.length-1);
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
    
    public void print() {
        System.out.println(Arrays.toString(arr));
    }
    
}

/**
 * 堆排序
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月10日 上午9:28:24
 */
class Heapsort {
    
    private int[] arr;
    
    /**
     * 
     */
    public Heapsort(int[] arrArgs) {
        this.arr = arrArgs;
    }
    
    /**
     * 
     */
    public void doSort() {
        
        int adjustIndex = (arr.length/2) - 1;
        
        for(int i=adjustIndex; i>=0; i--) {
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
    public void adjustHeap(int arr[], int adjustIndex, int length) {
        
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
    
    public void print() {
        System.out.println(Arrays.toString(arr));
    }
}

/**
 * 通过"小根堆" 解决最大TOPN问题
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月10日 上午9:28:35
 */
class TopnByHeapsort {
    
    private int[] arr;
    private int topn;
    
    public TopnByHeapsort(int[] arrArgs, int topnArgs) {
        this.arr = arrArgs;
        this.topn = topnArgs;
    }
    
    public int[] topn() {
        int[] topnArr = new int[topn];
        System.arraycopy(arr, 0, topnArr, 0, topn);
        
        int adjustIndex = (topnArr.length/2) - 1;
        doAdjust(topnArr, adjustIndex, topn);
        
        for(int i=topn; i<arr.length; i++) {
            if(arr[i] > topnArr[0]) {
                topnArr[0] = arr[i];
                doAdjust(topnArr, 0, topnArr.length);
            } 
        }
        
        return topnArr;
    }
    
    private void doAdjust(int[] topnArr, int adjustIndex, int length) {
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(topnArr, i, length);
        }
    }
    
    /**
     * 调整堆
     * @param topnArr
     * @param adjustIndex
     * @param length
     */
    private void adjustHeap(int[] topnArr, int adjustIndex, int length) {
        
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
