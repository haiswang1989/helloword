package com.hiworld.lintcode.easy;

import java.util.Arrays;

/**
 * 问题：合并排序数组
 * 描述：合并两个排序的整数数组A和B变成一个新的数组。
 * 样例：给出A=[1,2,3,4]，B=[2,4,5,6]，返回 [1,2,2,3,4,4,5,6] 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月1日 上午11:40:50
 */
public class ArrayMergeSortedArray {

    public static void main(String[] args) {
        
        int[] A = {7};
        int[] B = {5,7}; 
        
        ArrayMergeSortedArray arrayMergeSortedArray = new ArrayMergeSortedArray();
        int[] ret = arrayMergeSortedArray.mergeSortedArray(A, B);
        System.out.println(Arrays.toString(ret));
    }
    
    public int[] mergeSortedArray(int[] a, int[] b) {
        if(null==a && null==b) {
            return null;
        } else if(null==a && null!=b) {
            return b;
        } else if(null!=a && null==b) {
            return a;
        }
        
        int aLength = a.length;
        int bLength = b.length;
        
        int[] newArray = new int[aLength + bLength];
        int index = 0;
        int i=0,j=0;
        for(; i<aLength&&j<bLength;) {
            if(a[i] == b[j]) {
                newArray[index++] = a[i++];
                newArray[index++] = b[j++];
            } else if(a[i] < b[j]) {
                newArray[index++] = a[i++];
            } else {
                newArray[index++] = b[j++]; 
            }
        }
        
        if(i < aLength) {
            System.arraycopy(a, i, newArray, index, aLength - i);
        }
        
        if(j < bLength) {
            System.arraycopy(b, j, newArray, index, bLength - j);
        }
        
        return newArray;
    }
}
