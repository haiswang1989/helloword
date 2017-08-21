package com.hiworld.lintcode.medium;

/**
 * 问题：背包问题 VI 
 * 描述：给出一个都是正整数的数组 nums，其中没有重复的数。从中找出所有的和为 target 的组合个数。
 * 
 * 样例：
 * 给出 nums = [1, 2, 4], target = 4
 * 可能的所有组合：
 * [1, 1, 1, 1]
 * [1, 1, 2]
 * [1, 2, 1]
 * [2, 1, 1]
 * [2, 2]
 * [4]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月21日 下午2:55:42
 */
public class BackPackVI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	
    	int[] nums = {1, 2, 4};
    	int target = 4;
    	
    	BackPackVI backPackVI = new BackPackVI();
    	
    	int ret = backPackVI.backPackVI(nums, target);
    	System.out.println("ret : " + ret);
    			
    }
    
    /**
     * 
     * 
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackVI(int[] nums, int target) {
        // Write your code here
    	return dp(nums, target);
    }
    
    /**
     * 
     * 
     * @param nums
     * @param target
     * @return
     */
    public int dp(int[] nums, int target) {
    	int length = nums.length;
    	int count = 0;
    	for(int i=0; i<length; i++) {
    		int currNum = nums[i];
    		if(target-currNum == 0) {
    			count += 1;
    		} else if(target-currNum > 0) {
    			count += dp(nums, target-currNum);
    		}
    	}
    	
    	return count;
    }
}
