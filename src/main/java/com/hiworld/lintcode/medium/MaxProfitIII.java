package com.hiworld.lintcode.medium;

/**
 * 问题：买卖股票的最佳时机 III
 * 描述：假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。
 * 
 * 样例：给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月21日 下午4:15:21
 */
public class MaxProfitIII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] prices = {1,2,4,2,5,7,2,4,9,0};
        MaxProfitIII maxProfitIII = new MaxProfitIII();
        int ret = maxProfitIII.maxProfit(prices);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        if(null==prices || prices.length<=1) {
            return 0;
        }
        
        return dp(prices);
    }
    
    /**
     * 双向动态规划
     * 
     * 以index=i为分割点
     * 左边的最大收益：dpLeft[i] 表示 0-i的最大收益,dpLeft[i] = max{dpLeft[i-1], prices[i]-leftMin}
     * 右边的最大收益：dpRight[i] 表示i-length的最大收益,dpRight[i] = max{dpRight[i+1], rightMax - prices[i]}
     * 
     * 最后结果：max{dpLeft[i] + dpRight[i]}
     * 
     * @param prices
     * @return
     */
    public int dp(int[] prices) {
        int length = prices.length;
        
        int[] left = new int[length];
        int[] right = new int[length];
        
        int leftMin = prices[0];
        for(int i=1; i<length; i++) { //从前往后遍历,走一遍MaxProfitI的逻辑
            int curr = prices[i];
            int earnings = curr - leftMin;
            leftMin = Math.min(curr, leftMin);
            left[i] = Math.max(earnings, left[i-1]);
        }
        
        int rightMax = prices[length-1];
        for(int i=length-2; i>=0; i--) { //从后往前遍历,走一遍MaxProfitI的逻辑
            int curr = prices[i];
            int earnings = rightMax - curr;
            rightMax = Math.max(rightMax, curr);
            right[i] = Math.max(earnings, right[i+1]);
        }
        
        int maxSum = Integer.MIN_VALUE; 
        for(int i=0; i<prices.length; i++) { //计算总和的最大收益
            int sum = left[i] + right[i];
            if(sum > maxSum) {
                maxSum = sum;
            }
        }
        
        return maxSum;
    }
}
