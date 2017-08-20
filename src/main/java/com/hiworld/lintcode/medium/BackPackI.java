package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：背包问题
 * 描述：在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
 * 
 * 注意：你不可以将物品进行切割。
 * 
 * 样例：
 * 如果有4个物品[2, 3, 5, 7]
 * 如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。
 * 如果背包的大小为12，可以选择[2, 3, 7]装入背包，最多可以装满12的空间。
 * 函数需要返回最多能装满的空间大小。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月16日 下午5:00:18
 */
public class BackPackI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A = {2, 3, 5, 7};
        BackPackI backPackI = new BackPackI();
        int ret = backPackI.backPack(11, A);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        return dp(m, A);
    }
    
    /**
     * 
     * 状态转移方程：dp[i] = max{dp[i],dp[i-A[i]] + A[i]}
     * @param m
     * @param LA
     * @return
     */
    public int dp(int m, int[] A) {
    	int dp[] = new int[m+1];
    	int length = A.length;
    	
    	for(int i=0; i<length; i++) {
    		for(int j=m; j>0; j--) {
    			if(j >= A[i]) {
    				dp[j] = Math.max(dp[j], dp[j-A[i]] + A[i]);
    			}
    		}
    	}
    	
        return dp[m];
    }

}
