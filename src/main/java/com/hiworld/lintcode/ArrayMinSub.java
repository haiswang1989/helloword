package com.hiworld.lintcode;

import java.util.ArrayList;

/**
 * 问题：最小子数组
 * 描述：给定一个整数数组，找到一个具有最小和的子数组。返回其最小和。
 * 样例：给出数组[1, -1, -2, 1]，返回 -3
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月3日 下午2:36:15
 */
public class ArrayMinSub {

    public static void main(String[] args) {
        
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(-1);
        nums.add(-2);
        nums.add(1);
        
        ArrayMinSub arrayMinSub = new ArrayMinSub();
        int ret = arrayMinSub.minSubArray(nums);
        
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        int length = -1;
        if(null==nums || 0==(length=nums.size())) {
            return 0;
        }
        
        if(1 == length) {
            return nums.get(0);
        }
        
        return divideAndConquer(nums, 0, nums.size() - 1);
    }
    
    /**
     * 分而治之
     * @param nums
     * @param startIndex
     * @param endIndex
     * @return
     */
    public int divideAndConquer(ArrayList<Integer> nums, int startIndex, int endIndex) {
        if(startIndex == endIndex) {
            return nums.get(startIndex);
        }
        
        int middleIndex = (startIndex + endIndex) / 2;
        
        //横跨左右
        //左半边
        int leftBorderMin = Integer.MAX_VALUE;
        int leftBorderSum = 0;
        for(int i=middleIndex; i>=startIndex; --i) { //注意边界,这边做半边包含中间位置,相连两个数的中间是前面一个数,所以在左半部分的时候包含中间元素
            leftBorderSum += nums.get(i);
            if(leftBorderSum < leftBorderMin) {
                leftBorderMin = leftBorderSum;
            }
        }
        
        //右半边
        int rightBorderMin = Integer.MAX_VALUE;
        int rightBorderSum = 0;
        for(int i=middleIndex+1; i<=endIndex; ++i) { //右半边不包含中间位置
            rightBorderSum += nums.get(i);
            if(rightBorderSum < rightBorderMin) {
                rightBorderMin = rightBorderSum;
            }
        }
        
        int middleMin = leftBorderMin + rightBorderMin;
        //左边
        int leftMin = divideAndConquer(nums, startIndex, middleIndex); //左侧包含中间位置
        //右边
        int rightMin = divideAndConquer(nums, middleIndex+1, endIndex); //右侧不包含中间位置
        
        return Math.min(Math.min(leftMin, rightMin), middleMin);
    }
}
