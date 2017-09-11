package com.hiworld.lintcode.medium;

/**
 * 问题：打劫房屋
 * 描述：假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。
 * 你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，你最多可以得到多少钱 在不触动报警装置的情况下。
 * 
 * 样例：
 * 给定 [3, 8, 4], 返回 8.
 * 
 * 挑战：
 * O(n) 时间复杂度 且 O(1) 存储
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月11日 上午11:15:06
 */
public class HouseRobberI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A = {1,3,2,1,5};
        
        HouseRobberI houseRobberI = new HouseRobberI();
        long ret = houseRobberI.houseRobber(A);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 动态规划：
     * 
     * 状态：dp[i] 0~i间屋子的最大偷窃金额 
     * 
     * 状态转移方程：
     * dp[dpIndex] = Math.max(dp[dpIndex-2] + A[i],dp[dpIndex-1]);
     * 
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        // write your code here
        if(null==A || 0==A.length) {
            return 0;
        }
        
        long[] dp = new long[A.length + 1];
        dp[0] = 0;
        dp[1] = A[0];
        
        for(int i=1,dpIndex=2; i<A.length; i++,dpIndex++) {
            long value = dp[dpIndex-2] + A[i];
            long before = dp[dpIndex-1];
            dp[dpIndex] = Math.max(value, before);
        }
        
        return dp[A.length];
    }
}
