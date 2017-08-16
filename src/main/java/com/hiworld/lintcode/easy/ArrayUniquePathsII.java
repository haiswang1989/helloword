package com.hiworld.lintcode.easy;

/**
 * 问题：不同的路径 II 
 * 
 * 描述："不同的路径" 的跟进问题：现在考虑网格中有障碍物，那样将会有多少条不同的路径？网格中的障碍和空位置分别用 1 和 0 来表示。
 * 
 * 样例：如下所示在3x3的网格中有一个障碍物：
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * 
 * 一共有2条不同的路径从左上角到右下角
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月15日 上午10:55:42
 */
public class ArrayUniquePathsII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        
        ArrayUniquePathsII arrayUniquePathsII = new ArrayUniquePathsII();
        int ret = arrayUniquePathsII.uniquePathsWithObstacles(obstacleGrid);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        return dp(obstacleGrid);
    }
    
    /**
     * 
     * @param obstacleGrid
     * @return
     */
    public int dp(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) { //如果左上角或右下角元素就是障碍物,那么就0
            return 0;
        }
        
        boolean hasObstacles = false; //是否遇到障碍物
        for(int i=0; i<n; i++) { // 第一排
            if(!hasObstacles) {
                if(obstacleGrid[0][i] == 0) {
                    obstacleGrid[0][i] = 1;
                } else { 
                    obstacleGrid[0][i] = 0;
                    hasObstacles = true;
                }
            } else { //如果横向出现障碍物,那么后面的结点都不可达
                obstacleGrid[0][i] = 0;
            }
        }
        
        hasObstacles = false;//是否遇到障碍物
        for(int j=1; j<m; j++) { //第一列
            if(!hasObstacles) {
                if(obstacleGrid[j][0] == 0) {
                    obstacleGrid[j][0] = 1;
                } else {
                    obstacleGrid[j][0] = 0;
                    hasObstacles = true;
                }
            } else { //如果纵向出现障碍物,那么下面的结点都不可达
                obstacleGrid[j][0] = 0;
            }
        }
        
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(obstacleGrid[i][j] == 1) { //如果遇到填充的结点值为1,那么表示该结点是一个障碍物,那也就是这个点不可达
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i][j-1] + obstacleGrid[i-1][j];
                }
            }
        }
        
        return obstacleGrid[m-1][n-1];
    }
}
