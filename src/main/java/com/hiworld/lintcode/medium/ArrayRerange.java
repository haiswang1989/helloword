package com.hiworld.lintcode.medium;

import java.util.Arrays;

/**
 * 问题：交错正负数
 * 描述：给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
 * 样例：给出数组[-1, -2, -3, 4, 5, 6]，重新排序之后，变成[-1, 5, -2, 4, -3, 6]或者其他任何满足要求的答案
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月11日 下午2:27:55
 */
public class ArrayRerange {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A = {28,2,-22,-27,2,9,-33,-4,-18,26,25,34,-35,-17,2,-2,32,35,-8};
        
        ArrayRerange arrayRerange = new ArrayRerange();
        arrayRerange.rerange(A);
        System.out.println(Arrays.toString(A));
    }
    
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) { 
        // write your code here
        int length = -1;
        if(null==A || (length=A.length)<=2) {
            return;
        }
        
        quickSort(A, 0, length-1); //先排序
        int fromIndex = 0;
        int endIndex = length - 1;
        
        while(fromIndex < endIndex) { //然后对数组 进行 前后元素(跳跃)对调
            swap(A, fromIndex, endIndex);
            fromIndex += 2;
            endIndex -= 2;
        }
        
        boolean isOdd = length % 2 == 1 ? true : false; //如果元素的个数是奇数,那么中间会出现连续的正数或者负数
        int middleIndex = length / 2;
        if(isOdd) {
            int middle = A[middleIndex];
            if(middle < 0) { //负数,放到前面
                System.arraycopy(A, 0, A, 1, middleIndex);
                A[0] = middle;
            } else { //正数,放到后面
                System.arraycopy(A, middleIndex+1, A, middleIndex, A.length-middleIndex-1);
                A[A.length-1] = middle;
            }
        }
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
            int baseIndex = i;
            int base = A[i];
            
            while(i < j) {
                while(i < j && A[j] >= base) {
                    j--;
                }
                
                if(i < j) {
                    A[baseIndex] = A[j];
                    baseIndex = j;
                }
                
                while(i < j && A[i] < base) {
                    i++;
                }
                
                if(i < j) {
                    A[baseIndex] = A[i];
                    baseIndex = i;
                }
            }
            
            A[baseIndex] = base;
            quickSort(A, fromIndex, baseIndex-1);
            quickSort(A, baseIndex+1, endIndex);
        }
    }
    
    /**
     * 
     * @param A
     * @param fromIndex
     * @param endIndex
     */
    public void swap(int[] A, int fromIndex, int endIndex) {
        int temp = A[fromIndex];
        A[fromIndex] = A[endIndex];
        A[endIndex] = temp;
    }
}
