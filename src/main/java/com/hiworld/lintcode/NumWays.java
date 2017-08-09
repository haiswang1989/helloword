package com.hiworld.lintcode;

/**
 * 问题：栅栏染色
 * 描述：我们有一个栅栏，它有n个柱子，现在要给柱子染色，有k种颜色可以染。必须保证不存在超过2个相邻的柱子颜色相同，求有多少种染色方案。
 * 
 * 前提：n和k都是非负整数
 * 
 * 样例：n = 3, k = 2, return 6
 * post-1 post-2 post-3
 * 0        0       1
 * 0        1       0
 * 0        1       1
 * 1        1       0
 * 1        0       0
 * 1        0       1
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月9日 上午10:27:18
 */
public class NumWays {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        NumWays numWays = new NumWays();
        int ret = numWays.numWays(10, 3);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param n non-negative integer, n posts
     * @param k non-negative integer, k colors
     * @return an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        return dp(n, k);
    }
    
    /**
     * 动态规划
     * 
     * dp[i]表示i柱子的染色方案
     * 
     * 分两种情况：
     * 1：最后两个柱子的颜色相同
     *  避免连续三个柱子的颜色一样,那么dp[i] = dp[i-2] * (k-1)
     * 
     * 2：最后两个柱子的颜色不相同
     *  dp[i] = dp[i-1] * (k-1)
     *  
     *  所以状态转移方程：
     *  dp[i] = dp[i-1] * (k-1) + dp[i-2] * (k-1)
     * 
     * @param n
     * @param k
     * @return
     */
    public int dp(int n, int k) {
        int[] results = {0,k,k*k};
        if(n<=2) {
            return results[n];
        }
        
        for(int i=3; i<=n; ++i) {
            int beforeTwo = results[1];
            int beforeOne = results[2];
            
            int curr = beforeTwo * (k-1) + beforeOne * (k-1);
            
            results[1] = beforeOne;
            results[2] = curr;
        }
        
        return results[2];
    }
}
