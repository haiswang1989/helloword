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
    	int target = 7;
    	
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
     * 样例：nums = [1, 2, 4], target = 4
     * 
     * index： 0    1   2   3   4   5   6   7
     * value： 1    1   2   3   6   10  18  31
     * 
     * 状态：dp[i]
     * 
     * 状态转换方程：
     * foreach nums as num
     * if(num <= target) {
     *  dp[i] += dp[i-num]
     * }
     * 
     * @param nums
     * @param target
     * @return
     */
    public int dp(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i=1; i<=target; i++) {
            for(int j=0; j<nums.length; j++) {
                if(nums[j] <= i) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        
        return dp[target];
    }
    
        /**
         * 递归的方式(指数级增长),复杂度太高
         * 无法被AC
         * @param nums
         * @param target
         * @return
         */
        public int recusion(int[] nums, int target) {
        	int length = nums.length;
        	int count = 0;
        	for(int i=0; i<length; i++) {
        		int currNum = nums[i];
        		if(target-currNum == 0) {
        			count += 1;
        		} else if(target-currNum > 0) {
        			count += recusion(nums, target-currNum);
        		}
        	}
        	
        	return count;
        }
}
