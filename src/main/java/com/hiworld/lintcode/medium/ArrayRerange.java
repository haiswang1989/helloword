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
        int[] A = {-1, -2, -3, 4, 5, 6};
        
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
        if(null==A || A.length<=2) {
            return;
        }
        
        int length = A.length;
        boolean isPositive = false;
        if(A[0] >= 0) {
            isPositive = true;
        }
        
        int fromIndex = 1;
        while(fromIndex < length) {
            
        }
        
        
    }
}
