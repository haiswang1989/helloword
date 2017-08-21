package com.hiworld.lintcode.medium;

/**
 * 问题：买卖股票的最佳时机 II 
 * 描述：
 * 假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。设计一个算法来找到最大的利润。
 * 你可以完成尽可能多的交易(多次买卖股票)。然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。
 * 
 * 样例：
 * 给出一个数组样例[2,1,2,0,1], 返回 2
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月21日 下午3:59:04
 */
public class MaxProfitII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] prices = {2,1,2,0,1};
        MaxProfitII maxProfitII = new MaxProfitII();
        int ret = maxProfitII.maxProfit(prices);
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
        
        return solution_1(prices);
    }
    
    /**
     * 
     * @param prices
     * @return
     */
    public int solution_1(int[] prices) {
        int length = prices.length;
        int buy = prices[0]; //买入价
        int earningsSum = 0; //总收益
        
        for(int i=1; i<length; i++) {
            int price = prices[i]; //卖出价
            int earnings = price - buy; //收益
            if(earnings > 0) { //大于0就进行买入卖出
                earningsSum += earnings; //总收益
            } 
            
            buy = price; //不管是否有收益,以当天再作为买入价
        }
        
        return earningsSum;
    }
}
