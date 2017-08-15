package com.hiworld.lintcode.easy;

/**
 * 爬楼梯
 * 
 * 问题描述：假设你正在爬楼梯，需要n步你才能到达顶部。但每次你只能爬一步或者两步，你能有多少种不同的方法爬到楼顶部
 * 
 * 样例：
 * 比如n=3:1+1+1=1+2=2+1=3,共有3种不同的方式
 * 返回3
 * 
 * 这个其实就是一个 斐波那契数列 ,解题和Fibonacci.java一样
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月11日 上午9:34:51
 */
public class ClimbStairs {
    
    public static void main(String[] args) {
        
        ClimbStairs climbStairs = new ClimbStairs();
        
        System.out.println(climbStairs.climbStairs(44));
    }
    
    public int climbStairs(int n) {
        // write your code here
        if(n == 0) {
            return 1;
        }
        
        return climbStairsWithOutRecursion(n);
    }
    
    /**
     * 该方式的时间复杂度有点高,lintcode平台过不了
     * @param n
     * @return
     */
    public int climbStairsWithRecursion(int n) {
        if(n == 1) { //当只有一个阶梯的时候,只有一种走法
            return 1;
        } else if(n == 2) { //当还剩下两个阶梯的时候,有两个解法
            return 2;
        } else {
            return climbStairsWithRecursion(n-1) + climbStairsWithRecursion(n-2); //使用递归的方式来实现
        }
    }
    
    /**
     * 非递归的方式
     * @param n
     * @return
     */
    public int climbStairsWithOutRecursion(int n) {
        if(n == 1) {
            return 1;
        } else if(n == 2) {
            return 2;
        }
        
        int count = 2;
        int beforeOne = 1;
        int beforeTwo = 2;
        int temp = -1;
        while(++count <= n) {
            temp = beforeOne + beforeTwo;
            beforeOne = beforeTwo;
            beforeTwo = temp;
        }
        
        return beforeTwo;
    }
}
