package com.hiworld.lintcode.medium;

/**
 * 问题：背包问题 II
 * 描述：给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？
 * 注意事项：A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的m。
 * 
 * 样例：
 * 对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月21日 下午2:38:35
 */
public class BackPackII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    	int[] A = {2, 3, 5, 7};
    	int[] V = {1, 5, 2, 4};
    	int m = 10;
    	
    	BackPackII backPackII = new BackPackII();
    	int ret = backPackII.backPackII(m, A, V);
    	System.out.println("ret : " + ret);
    }
    
    /**
     * 
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        return dp(m, A, V);
    }
    
    /**
     * w	v	0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
     * 0	0	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
     * 2	1	0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1
     * 3	5	0, 0, 1, 5, 5, 6, 6, 6, 6, 6, 6
     * 5	2	0, 0, 1, 5, 5, 6, 6, 6, 7, 7, 8
     * 7	4	0, 0, 1, 5, 5, 6, 6, 6, 7, 7, 9
     * 
     * 状态：dp[i][j] 
     * 表示前i件物品中选择若干件放到承重j的背包中,可以取最大值
     * 状态转移方程：
     * dp[i][j] = 0 if (i==0||j==0)
     * dp[i][j] = dp[i-1][j] if (j<A[i])
     * dp[i][j] = max{dp[i-1][j-A[i-1]] + V[i-1],dp[i-1][j]} 
     * 		
     * @param m
     * @param A
     * @param V
     * @return
     */
    public int dp(int m, int[] A, int V[]) {
    	int aLength = A.length;
    	int[][] dp = new int[aLength+1][m+1];
    	
    	int[] aWithZero = new int[aLength+1];
    	aWithZero[0] = 0;
    	System.arraycopy(A, 0, aWithZero, 1, aLength);
    	
    	int[] vWithZero = new int[aLength+1];
    	vWithZero[0] = 0;
    	System.arraycopy(V, 0, vWithZero, 1, aLength);
    	
    	for(int i=1; i<aWithZero.length; i++) {
    		for(int j=1; j<=m; j++) {
    			if(j < aWithZero[i]) {
    				dp[i][j] = dp[i-1][j];
    			} else {
    				dp[i][j] = Math.max(dp[i-1][j-aWithZero[i]] + vWithZero[i], dp[i-1][j]);
    			}
    		}
    	}
    	
    	return dp[aLength][m];
    }

}
