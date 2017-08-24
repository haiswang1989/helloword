package com.hiworld.lintcode.medium;

/**
 * 问题：硬币排成线 II
 * 描述：有 n 个不同价值的硬币排成一条线。两个参赛者轮流从左边依次拿走 1 或 2 个硬币，直到没有硬币为止。计算两个人分别拿到的硬币总价值，价值高的人获胜。
 * 请判定 第一个玩家 是输还是赢？
 * 
 * 样例：
 * 给定数组 A = [1,2,2], 返回 true.
 * 给定数组 A = [1,2,4], 返回 false.
 * 
 * @author Administrator
 *
 */
public class CoinFirstWinII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
	    int[] values = {1,2,4,8};
	    CoinFirstWinII coinFirstWinII = new CoinFirstWinII();
	    boolean success = coinFirstWinII.dp(values);
	    System.out.println(success);
	}
	
	/*
	 * 
	 * 
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
    	return dp(values);
    }
    
    /**
     * 
     * 状态：dp[i] - 表示从i~end能取到的最大值
     * 
     * 情况1：
     *      我若取values[i]
     *          对方取values[i+1]
     *              我们只能从i+2到end内取，我们所取得最大值是dp[i+2]
     *          
     *          对方取values[i+1] + values[i+2]
     *              我们只能从i+3到end内取，我们所取得最大值是dp[i+3]
     * 
     *      因此：
     *      dp[i] = values[i] + min(dp[i+2],dp[i+3]) //因为对方所选的结果一定是使我们以后选取的值最小
     * 
     * 情况2：
     *      我若取values[i] + values[i+1]
     *          对方取values[i+2]
     *              我们只能从i+3到end内取，我们所取得最大值是dp[i+3]
     *              
     *          对方取values[i+2] + values[i+3]
     *              我们只能从i+4到end内取，我们所取得最大值是dp[i+4]
     *              
     *      因此：
     *      dp[i] = values[i] + values[i+1] + min(dp[i+3],dp[i+4]) //因为对方所选的结果一定是使我们以后选取的值最小
     *              
     * @param values
     * @return
     */
    public boolean dp(int[] values) {
        int length = values.length;
        if(length <= 2) { //如果只有1或者2个硬币,直接取了就赢了
            return true;
        }
        
        int[] dp = new int[length + 1];
        
        dp[length] = 0; // 
        dp[length-1] = values[length-1]; //只有一个
        dp[length-2] = values[length-1] + values[length-2]; //只有两个
        
        /**
         * 情况1：dp[i] = values[i] + min(dp[i+2],dp[i+3])
         * 情况2：dp[i] = values[i] + values[i+1] + min(dp[i+3],dp[i+4])
         * 
         * 结果：dp[i] = Math.max(情况1, 情况2)
         * 
         * 情况1：
         * dp[length-3] = values[length-3] + min(values[length-1],0) = values[length-3] + 0
         * 
         * 情况2:
         * dp[length-3] = values[length-3] + values[i-2] + min(dp[length], dp[length+1]) =  values[length-3] + values[i-2] + min(0, dp[length+1])
         *              = values[length-3] + values[length-2]
         *              
         * dp[i] = Math.max(values[length-3], values[length-3] + values[length-2]) = values[length-3] + values[length-2];
         *        
         */
        dp[length-3] = values[length-3] + values[length-2];
        
        for(int i=length-4; i>=0; --i) {
            int one = values[i] + Math.min(dp[i+2], dp[i+3]); //我先拿一个的情况
            int two = values[i] + values[i+1] + Math.min(dp[i+3],dp[i+4]); //我先拿两个的情况
            dp[i] = Math.max(one, two);
        }
        
        int sum = 0;
        for(int value : values) {
            sum += value;
        }
            
        return dp[0] > sum - dp[0];
    }
}
