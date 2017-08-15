package com.hiworld.lintcode.easy;

import java.util.Arrays;


/**
 * 排序整数数组(O(n2)的排序)
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月4日 上午11:10:20
 */
public class SortIntegers {

    public static void main(String[] args) {
        int[] A = new int[]{3, 2, 1, 4, 5};
        SortIntegers sortIntegers = new SortIntegers();
//        sortIntegers.bubbleSort(A);
//        sortIntegers.selectionSort(A);
        sortIntegers.insertionSort(A);
        System.out.println(Arrays.toString(A));
    }
    
    /**
     * 选择排序
     * 选择排序就是每次选择最小(或者最大)的元素,把这个元素放到"部分有序"的最前面(最后面)
     * 该方式相对于冒泡排序交换的次数会减少很多次
     * @param A
     */
    public void selectionSort(int[] A) {
        int arrayLength = A.length;
        int min = -1;
        int minIndex = -1;
        for(int i=0; i<arrayLength; ++i) {
            min = A[i];
            minIndex = i;
            for(int j=i+1; j<arrayLength; ++j) {
                if(min > A[j]) {
                    min = A[j];
                    minIndex = j;
                }
            }
            
            swap(A, i, minIndex);
        }
    }
    
    /**
     * 冒泡排序
     * @param A
     */
    public void bubbleSort(int[] A) {
        int arrayLength = A.length;
        for(int i=0; i<arrayLength; ++i) {
            for(int j=i+1; j<arrayLength; ++j) {
                if(A[i] > A[j]) {
                    swap(A, i, j);
                }
            }
        }
    } 
    
    /**
     * 插入排序
     * 会存在大量的元素移动，但是现在 System.arraycopy()还是很快的
     * @param A
     */
    public void insertionSort(int[] A) {
        int arrayLength = A.length;
        if(arrayLength == 0 || arrayLength == 1) {
            return;
        }
        
        int sortedBeginIndex = 0;
        int sortedEndIndex = 0;
        for(int i=1; i<arrayLength; ++i) {
            int currVal = A[i];
            for(int j=sortedBeginIndex; j<=sortedEndIndex; ++j) {
                if(currVal < A[j]) {
                    System.arraycopy(A, j, A, j+1, i - j);
                    A[j] = currVal;
                    break;
                }
            }
            
            sortedEndIndex++;
        }
    }
    
    /**
     * 交换元素
     * @param A
     * @param indexA
     * @param indexB
     */
    public void swap(int[] A, int indexA, int indexB) {
        int temp = A[indexA];
        A[indexA] = A[indexB];
        A[indexB] = temp;
    }
}
