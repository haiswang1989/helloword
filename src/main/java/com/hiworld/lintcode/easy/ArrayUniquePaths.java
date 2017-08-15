package com.hiworld.lintcode.easy;

import java.math.BigInteger;

/**
 * 问题：不同的路径 
 * 描述：有一个机器人的位于一个 m × n 个网格左上角。
 *       机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
 *       问有多少条不同的路径？
 *       
 * 样例：
 * 给出 m = 3 和 n = 3, 返回 6.
 * 给出 m = 4 和 n = 5, 返回 35.
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月14日 下午4:34:30
 */
public class ArrayUniquePaths {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayUniquePaths arrayUniquePaths = new ArrayUniquePaths();
        
        int ret = arrayUniquePaths.uniquePaths(2, 62);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if(n < 1 || m < 1) {
            return 0;
        }
        
//        return dp(m, n);
//        return math(m, n);
        
        return dpNew(m, n);
    }
    
    /**
     * dp[i][j] = dp[i][j-1] + dp[i-1][j]
     * 第一行和第一列都是1
     * 然后就遍历数组,来进行填充,直到最后一个dp[m][n],然后返回该值
     * 
     * 1    1   1   1   1   1   1
     * 1    2   3   4   5   6   7
     * 1    3   6   10  15  21  28
     *  
     * @param m
     * @param n
     * @return
     */
    public int dpNew(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }
        
        for(int j=1; j<m; j++) {
            dp[j][0] = 1;
        }
        
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        
        return dp[m-1][n-1];
    }
    
    /*****************************这种使用数学的方式,由于在进行C(n,m)计算的时候很容易出现溢出,使用了BigInteger,本地测试通过但是lintcode直接编译一直卡着********************************/
    /**
     * 数学方式
     * 
     * C(m+n-2,m-1) = C(m+n-2,n-1)
     * 
     * c(4,2) = 4 * 3 / 1 * 2
     * c(7,3) = 7 * 6 * 5 / 1 * 2 *3
     * 
     * m*n的网格,从左上角走到右下角一共要走 m+n-2步
     * 问题的答案就是,从这么多步中,走m-1步的走法,或者走n-1步的走法
     * 
     * @param m
     * @param n
     * @return
     */
    public int math(int m, int n) {
        int totleStep = m + n -2;
        int downStep = n - 1;
        
        BigInteger divisor = divisor(totleStep, downStep);
        BigInteger dividend = dividend(downStep);
        
        return divisor.divide(dividend).intValue();
    }
    
    /**
     * c(4,2)
     * 除数 4 * 3
     * 
     * @param totleStep
     * @param downStep
     * @return
     */
    public BigInteger divisor(int totleStep, int downStep) {
        BigInteger divisor = new BigInteger("1");
        for(int i=1; i<=downStep; i++) {
            divisor = divisor.multiply(new BigInteger(totleStep+""));
            totleStep--;
        }
        return divisor;
    }
    
    /**
     * c(4,2)
     * 被除数 1 * 2
     * @param downStep
     * @return
     */
    public BigInteger dividend(int downStep) { //这边全是使用BigInteger,其他类型很容易溢出
        BigInteger dividend = new BigInteger("1");
        for(int i=1; i<=downStep; i++) {
            dividend = dividend.multiply(new BigInteger(i+""));
        }
        
        return dividend;
    }
    
    /**********************************最初使用的额动态规划,递归的方式实现,但是不能AC,出现超时*********************************/
    /**
     * 这个方法会出现超时,不能AC
     * @param m
     * @param n
     * @return
     */
    public int dp(int m, int n) {
        if(m==1) {
            return 1;
        } else if(n==1) {
            return 1;
        } else {
            return dp(m, n-1) + dp(m-1, n);
        }
    }
}
