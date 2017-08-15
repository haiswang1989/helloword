package com.hiworld.lintcode.easy;

/**
 * 问题：数字三角形 
 * 描述：给定一个数字三角形，找到从顶部到底部的最小路径和。每一步可以移动到下面一行的相邻数字上。  
 * 注意：如果你只用额外空间复杂度O(n)的条件下完成可以获得加分，其中n是数字三角形的总行数。
 * 
 * 样例：
 * [
 *  [2],
 *  [3,4],
 *  [6,5,7],
 *  [4,1,8,3]
 * ]
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月11日 下午5:11:36
 */
public class ArrayMinimumTotal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] input = {{2},{3,4},{6,5,7},{4,1,8,3}};
        
        ArrayMinimumTotal arrayMinimumTotal = new ArrayMinimumTotal();
        int ret = arrayMinimumTotal.minimumTotal(input);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if(null==triangle || 0==triangle.length) {
            return 0;
        }
        
        int firstLevel = triangle[0][0];
        firstLevel += dp(triangle, 0, 1, 1);
        
        return firstLevel;
    }
    
    /**
     * 动态规划：
     * 
     * 状态：
     * 状态转移方程： 
     * @param triangle
     * @param yStartIndex
     * @return
     */
    public int dp(int[][] triangle, int leftIndex, int rightIndex, int level) {
        int yLength = triangle.length;
        if(level == yLength) {
            return 0;
        } 
        
        int leftSum = triangle[level][leftIndex] + dp(triangle,leftIndex,leftIndex+1,level+1);
        int rightSum = triangle[level][rightIndex] + dp(triangle, rightIndex, rightIndex+1, level+1);
        
        return Math.min(leftSum, rightSum);
    }
}
