package com.hiworld.lintcode.medium;

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
        int[] A = {2, 3, 7, 5};
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
        return dp_1(m, A);
    }
    
    
    /**
     *      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 (重量) 
     * 0    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0     
     * 2    0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2
     * 3    0, 0, 2, 3, 3, 5, 5, 5, 5, 5, 5, 5
     * 5    0, 0, 2, 3, 3, 5, 5, 7, 8, 8, 10, 10
     * 7    0, 0, 2, 3, 3, 5, 5, 7, 8, 9, 10, 10
     * (物品)
     * 
     * 状态：dp[i][j] 
     * 表示：当背包总重量为j且有前i个物品时，背包最多装的空间。 
     * 
     * 状态转移方程：dp[i][j] = max(dp[i-1][j-A[i]] + A[i],dp[i-1][j])
     * 1：为了能装下A[i],那么需要为该物品腾出空间,腾出后空间的背包最多可装的空间为dp[i-1][j-A[i]]
     * 2：如果j<A[i],那么就是表示背包装不下该物品,那么dp[i][j] = dp[i-1][j];
     * 
     * @param m
     * @param A
     * @return
     */
    public int dp_1(int m, int[] A) {
        int[] withZeroA = new int[A.length+1]; //在前面加一个0,避免下面在遍历的时候做太多的判断
        withZeroA[0] = 0;
        System.arraycopy(A, 0, withZeroA, 1, A.length);
        int[][] dp = new int[withZeroA.length][m+1];
        for(int i=1; i<withZeroA.length; i++) {
            for(int j=1; j<=m; j++) {
                if(withZeroA[i] <= j) {
                    dp[i][j] =Math.max(dp[i-1][j-withZeroA[i]] + withZeroA[i], dp[i-1][j]); 
                } else { //如果放不下这个withZeroA[i],那么该位置的值就是他的上面一个元素,也就是dp[i-1][j]
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[withZeroA.length-1][m];
    }
    
    
    /**
     * 上面的二位数组是有优化空间的,可以直接使用一维数组来完成
     * 
     * 状态转移方程：dp[i] = max{dp[i],dp[i-A[i]] + A[i]}
     * 
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
