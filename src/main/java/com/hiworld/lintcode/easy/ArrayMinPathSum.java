package com.hiworld.lintcode.easy;

/**
 * 问题：最小路径和 
 * 描述：给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径。
 * 注意事项：你在同一时间只能向下或者向右移动一步
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月1日 下午5:26:02
 */
public class ArrayMinPathSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        
        ArrayMinPathSum arrayMinPathSum = new ArrayMinPathSum();
        int ret = arrayMinPathSum.minPathSum(grid);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 动态规划：
     * 
     * dp[0][0] = grid[0][0];
     * 左边的结果：dp[0][1] = dp[0][0] + grid[0][1];
     * 下面的结点：dp[1][0] = dp[0][0] + grid[1][0];
     * 一般的结点：dp[y][x] = min(dp[y-1][x],dp[y][x-1]) + grid[y][x] //正常结点的值为min(dp[左],dp[上]) + 当前结点的元素值
     * 
     * 状态:dp[y][x]
     * 状态转移方程：dp[y][x] = min(dp[y-1][x],dp[y][x-1]) + grid[y][x] 
     * 
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        
        if(null==grid || grid.length==0) {
            return 0;
        }
        
        int y = grid.length;
        int x = grid[0].length;
        
        int[][] pathSum = new int[y][x];
        
        for(int i=0; i<y; ++i) {
            for(int j=0; j<x; ++j) {
                pathSum[i][j] = Integer.MAX_VALUE;
            }
        }
        
        pathSum[0][0] = grid[0][0];
        
        for(int i=0; i<y; ++i) {
            for(int j=0; j<x; ++j) {
                //上面结点
                int upY = i-1;
                int upX = j;
                
                //左边结点
                int leftY = i;
                int leftX = j - 1;
                
                if(isValid(upY, upX) && isValid(leftY, leftX)) { //左边结点和上面结点合法的情况下,当前结点的dp值
                    pathSum[i][j] = Math.min(pathSum[upY][upX], pathSum[leftY][leftX]) + grid[i][j];
                } else if(!isValid(upY, upX) && isValid(leftY, leftX)) { //上面结点不合法,左边结点合法,那么当前结点的dp=dp[左] + grid[][]
                    pathSum[i][j] = pathSum[leftY][leftX] + grid[i][j];
                } else if(isValid(upY, upX) && !isValid(leftY, leftX)) { //上边结点合法,左边结点不合法,那么当前结点的dp=dp[上] + grid[][]
                    pathSum[i][j] = pathSum[upY][upX] + grid[i][j];
                } else { //[0][0]
                    pathSum[i][j] = grid[0][0];
                }
            }
        }
        
        return pathSum[y-1][x-1];
    }
    
    boolean isValid(int y, int x) {
        return y>=0 && x>=0;
    }
}
