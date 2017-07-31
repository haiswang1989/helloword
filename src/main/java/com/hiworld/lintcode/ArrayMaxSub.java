package com.hiworld.lintcode;

import java.util.HashMap;

/**
 * 问题：最大子数组
 * 描述：给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。
 * 
 * 样例：给出数组[-2,2,-3,4,-1,2,1,-5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月28日 上午11:49:50
 */
public class ArrayMaxSub {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = {-1};
        ArrayMaxSub arrayMaxSub = new ArrayMaxSub();
        int ret = arrayMaxSub.maxSubArray(input);
        System.out.println("ret : " + ret);
    }
    
    public int maxSubArray(int[] nums) {
        if(null==nums || 0==nums.length) {
            return 0;
        }
        
        return divideAndConquer(nums, 0, nums.length-1);
    }
    
    /***********************这是暴力法,我自己的解法*************************/
    /**
     * 该种方式是的时间复杂度O(n2),所以在执行的时候直接报错了
     * @param nums
     * @return
     */
    public int violence(int[] nums) {
        int length = nums.length;
        
        HashMap<Integer, Integer> sums = new HashMap<>();
        
        int max = Integer.MIN_VALUE; //记录最大值
        
        for(int i=0; i<length; i++) {
            int currentVal = nums[i];
            sums.put(i, currentVal);
            if(currentVal > max) {
                max = currentVal;
            }
            
            for(int j=0; j<i; ++j) { //每个元素,会和在它之前的元素都进行累加,然后和max值进行比较,并且放到Map中
                Integer lastVal = sums.get(j);
                Integer sum = lastVal+currentVal;
                sums.put(j, sum);
                if(sum > max) {
                    max = sum;
                }
            }
        }
        
        return max;
    }
    
    /*************************分治法*************************/
    /**
     * 
     * 这个分治法的时间复杂度 O(nlogn)
     * @param nums
     * @param startIndex
     * @param endIndex
     * @return
     */
    public int divideAndConquer(int[] nums, int startIndex, int endIndex) {
        
        if(startIndex == endIndex) {
            /*
            if(nums[startIndex] > 0) {
                return  nums[startIndex];
            } else {
                return 0;
            }
            */
            return  nums[startIndex];
        }
        
        //最大子串,横跨左右两边,包含中间元素
        int medianIndex = (startIndex + endIndex) / 2; //中间元素
        
        int maxLeftBorderSum = Integer.MIN_VALUE; //获取左边最大值 
        int leftBorderSum = 0;
        for(int i=medianIndex; i>=startIndex; --i) {
            leftBorderSum += nums[i];
            if(leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }
        
        int maxRightBorderSum = Integer.MIN_VALUE; //获取右边最大值
        int rightBorderSum = 0;
        for(int i=medianIndex+1; i<=endIndex; ++i) {
            rightBorderSum += nums[i];
            if(rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        
        
        int maxLeftSum = divideAndConquer(nums, startIndex, medianIndex); //最大子串在左边
        int maxRightSum = divideAndConquer(nums, medianIndex+1, endIndex); //最大子串在右边
        
        return Math.max(Math.max(maxLeftSum, maxRightSum), maxRightBorderSum + maxLeftBorderSum); //3个中的最大值,就是
    }
    
    /*************************O(n)的解决方案，动态规划**************************/
    public int dynamicProgramming() {
        return 0;
    }
}
