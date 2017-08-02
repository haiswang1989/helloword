package com.hiworld.algorithm.dp;

import java.util.Arrays;

/**
 * 硬币问题：我们有面值为1元、3元和5元的硬币若干枚，如何用最少的硬币凑够11元
 * 动态规划:
 * dp(i) 表示凑够i元需要的硬币数
 * dp(0) = 0
 * dp(1) = dp(1-1) + 1 = dp(0) + 1 = 1
 * dp(2) = dp(2-1) + 1 = dp(1) + 1 = 1 + 1 = 2
 * dp(3) = dp(3-3) + 1 = dp(0) + 1 = 1 OR dp(3) = dp(3-1) + 1 = dp(2) + 1 = 2 + 1 = 3
 * 
 * 动态规划的两个重要概念：
 * 状态：dp(i)
 * 状态转移方程： dp(i) ---> dp(3) = min{dp(3-1)+1,dp(3-3)+1}
 * dp(i) = min(d(i-vj)) + 1
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月2日 上午10:24:50
 */
public class CoinProblem {

    public static void main(String[] args) {
        
        int coin[] = {1,3,5}; //硬币的面额
        int sum = 11; //需要的总面额
        int dp[] = new int[sum+1];
        dp[0] = 0;
        for(int i=1; i<=sum; ++i) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        for(int i=1; i<=sum; ++i) { //总面额
            for(int j=0; j<coin.length; ++j) { //硬币的面额数
                if(coin[j]<=i && dp[i-coin[j]]+1 < dp[i]) { //与当前的数量进行比较,比当前的数量下,那么就替换
                    dp[i] = dp[i-coin[j]]+1;
                }
            }
        }
        
        System.out.println(Arrays.toString(dp));
    }
    
}
