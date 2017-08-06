package com.hiworld.lintcode;

import java.util.Arrays;

/**
 * 问题：整数排序 II
 * 描述：给一组整数，按照升序排序。使用归并排序，快速排序，堆排序或者任何其他 O(n log n) 的排序算法。
 * 样例：给出 [3, 2, 1, 4, 5], 排序后的结果为 [1, 2, 3, 4, 5]。
 * 
 * @author Administrator
 *
 */
public class ArrayIntegerSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] A = {3,2,1,4,5};
		
		ArrayIntegerSort arrayIntegerSort = new ArrayIntegerSort();
		arrayIntegerSort.sortIntegers2(A);
		System.out.println(Arrays.toString(A));
	}
	
	
	/**
     * @param A an integer array
     * @return void
     */
    public void sortIntegers2(int[] A) {
        // Write your code here
    	quickSort(A, 0, A.length-1);
    }
    
    /**
     * 归并排序
     * @param A
     */
    public void mergeSort(int[] A) {
    	
    }
    
    /**
     * 
     * @param A
     * @param fromIndex
     * @param endIndex
     */
    public void quickSort(int[] A, int fromIndex, int endIndex) {
    	if(fromIndex < endIndex) {
    		
    		int i = fromIndex;
    		int j = endIndex;
    		
    		int cardinalIndex = i;
    		int cardinalNum = A[cardinalIndex];
    				
    		
    		while(i < j) {
    			
    			while(A[j] >= cardinalNum && i<j) { //从后往前是找小的填到前面小的地方
    				j--;
    			}
    			
    			if(i < j) {
    				A[cardinalIndex] = A[j];
    				cardinalIndex = j;
    			}
    			
    			while(A[i] < cardinalNum && i<j) { //从前往后是找大的,填到后面大的地方
    				i++;
    			}
    			
    			if(i < j) {
    				A[cardinalIndex] = A[i];
    				cardinalIndex = i;
    			}
    		}
    		
    		A[cardinalIndex] = cardinalNum;
    		quickSort(A, fromIndex, cardinalIndex-1); //再进行分而治之
    		quickSort(A, cardinalIndex+1, endIndex);
    	}
    }
    
    /**
     * 交换元素
     * @param A
     * @param index1
     * @param index2
     */
    public void swap(int[] A, int index1, int index2) {
    	int temp = A[index1];
    	A[index1] = A[index2];
    	A[index2] = temp;
    }
    
    /**
     * 堆排序
     * @param A
     */
    public void heapSort(int[] A) {
    	
    }
    
}
