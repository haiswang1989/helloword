package com.hiworld.lintcode;

import java.util.Arrays;

/**
 * 问题：合并排序数组 II
 * 描述：合并两个排序的整数数组A和B变成一个新的数组。
 * 注意事项：你可以假设A具有足够的空间（A数组的大小大于或等于m+n）去添加B中的元素。
 * 
 * 样例：
 * 给出 A = [1, 2, 3, empty, empty], B = [4, 5]合并之后 A 将变成 [1,2,3,4,5]
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月1日 下午3:15:53
 */
public class ArrayMergeSortedArrayII {
    
    public static void main(String[] args) {
        int[] A = new int[]{0,0,0,0,0,0};
        int[] B = new int[]{2,3,4,5,6,10};
        
        ArrayMergeSortedArrayII arrayMergeSortedArrayII = new ArrayMergeSortedArrayII();
        arrayMergeSortedArrayII.mergeSortedArray(A, 0, B, B.length);
        System.out.println(Arrays.toString(A));
    }
    
    /**
     * 解这个题的思路就是,从后往前,这样就避免了大量的数组移动
     * @param A: sorted integer array A which has m elements,but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int endIndex = m + n -1;
        int i=m-1,j=n-1;
        for(; i>=0 && j>=0;) {
            if(A[i] >= B[j]) {
                A[endIndex--] = A[i--];
            } else {
                A[endIndex--] = B[j--];
            }
        }
        
        while(i>=0) {
            A[endIndex--] = A[i--];
        }
        
        while(j>=0) {
            A[endIndex--] = B[j--];
        }
    }
}
