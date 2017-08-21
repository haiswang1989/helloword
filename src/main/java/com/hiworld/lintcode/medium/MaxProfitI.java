package com.hiworld.lintcode.medium;

/**
 * 问题：买卖股票的最佳时机
 * 描述：假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润
 * 
 * 样例：给出一个数组样例 [3,2,3,1,2], 返回 1 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月21日 下午3:22:34
 */
public class MaxProfitI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] prices = {3,2,3,1,2};
        MaxProfitI maxProfitI = new MaxProfitI();
        int ret = maxProfitI.maxProfit(prices);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if(null==prices || 0==prices.length) {
            return 0;
        }
        
        int max = solution_1(prices);
        return max <= 0 ? 0 : max;
    }
    
    /**
     * 暴力解决
     * 无法被AC
     * 时间复杂度：O(N*N)
     * @param prices
     * @return
     */
    public int violenceResolve(int[] prices) {
        int length = prices.length;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<length; i++) {
            for(int j=i+1; j<length; j++) {
                int curr = prices[j] - prices[i];
                if(curr > max) {
                    max = curr;
                }
            }
        }
        
        return max;
    }
    
    /**
     * 比较一个数组中的额最大差值,for循环嵌套(O(n*n))的时间复杂度可以变成O(n)
     * 
     * 循环的过程中反复变换 最小的基数(min)
     * 
     * @param prices
     * @return
     */
    public int solution_1(int[] prices) {
        int length = prices.length;
        int min = prices[0]; //记录买入的最小值
        int max = Integer.MIN_VALUE;
        
        for(int i=1; i<length; i++) {
            int earnings = prices[i] - min; //记录收入
            if(earnings > max) { //如果收入比之前的大进行更新
                max = earnings;
            }
            
            if(prices[i] < min) { //如果当前的买入价 比min还小,进行更新
                min = prices[i];
            }
        }
        
        return max;
    }
}
