package com.hiworld.lintcode;

/**
 * 问题：给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。（最长上升连续子序列可以定义为从右到左或从左到右的序列。）
 * 样例：
 * 给定 [5, 4, 2, 1, 3], 其最长上升连续子序列（LICS）为 [5, 4, 2, 1], 返回 4.
 * 给定 [5, 1, 2, 3, 4], 其最长上升连续子序列（LICS）为 [1, 2, 3, 4], 返回 4.
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月26日 下午5:01:07
 */
public class ArrayLongestIncreasingContinuousSubsequence {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A = {5, 1, 2, 3, 4};
        ArrayLongestIncreasingContinuousSubsequence instance = new ArrayLongestIncreasingContinuousSubsequence();
        System.out.println(instance.longestIncreasingContinuousSubsequence(A));
    }
    
    /*
     * @param : An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // write your code here
        
        if(null==A || 0==A.length) {
            return 0;
        }
        
        int increaseMax = 0; //历史的增长最大值
        int decreaseMax = 0; //历史的减小最大值
        
        int currentIncreaseMax = 1; //当前循环增长最大值
        int currentDecreaseMax = 1; //当前循环减小最大值
        
        int beforeInt = 0;
        int currentInt = A[0];
        for(int i=1; i<A.length; ++i) {
            beforeInt = currentInt;
            currentInt = A[i];
            if(beforeInt < currentInt) { //如果是递增
                currentIncreaseMax++; //当前循环增长累加1
                if(currentDecreaseMax != 1) { //递减复位
                    if(currentDecreaseMax > decreaseMax) { //复位的时候,如果联系递减大于历史的最大值,那么更新到历史最大值中
                        decreaseMax = currentDecreaseMax;
                    }
                    currentDecreaseMax = 1; //同时把当前循环递减重新赋值为1
                }
            } else { //如果是递减
                currentDecreaseMax++; //当前递减增长累加1
                if(currentIncreaseMax != 1) { //递增复位
                    if(currentIncreaseMax > increaseMax) {
                        increaseMax = currentIncreaseMax;
                    }
                    currentIncreaseMax = 1;
                }
            }
        }
        
        if(currentIncreaseMax > increaseMax) { //循环结束,记得用当前的值和历史值在做一次比较
            increaseMax = currentIncreaseMax;
        }
        
        if(currentDecreaseMax > decreaseMax) {
            decreaseMax = currentDecreaseMax;
        }
        
        return Math.max(increaseMax, decreaseMax);
    }
}
