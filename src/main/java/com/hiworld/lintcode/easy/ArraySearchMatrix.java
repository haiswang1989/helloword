package com.hiworld.lintcode.easy;

/**
 * 问题：搜索二维矩阵 
 * 描述：写出一个高效的算法来搜索 m × n矩阵中的值
 *     这个矩阵具有以下特性：
 *     1：每行中的整数从左到右是排序的。
 *     2：每行的第一个数大于上一行的最后一个整数。
 * 
 * 样例：
 * [
 * 	[1, 3, 5, 7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 50]
 * ]
 * 
 * 给出target=3，返回true
 * 
 * 挑战：O(log(n) + log(m)) 时间复杂度
 * 
 * 注意:该复杂度就是一个二分查找
 *   
 * @author Administrator
 *
 */
public class ArraySearchMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] matrix = {{1, 3, 5, 7},{10, 11, 16, 20},{23, 30, 34, 50}};
		
		ArraySearchMatrix arraySearchMatrix = new ArraySearchMatrix();
		boolean isExist = arraySearchMatrix.searchMatrix(matrix,7);
		System.out.println("IsExist : " + isExist);
	}
	
	/**
	 * 
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
    	int y = -1;
    	int x = -1;
    	if(null==matrix || 0==(y=matrix.length) || 0==(x=matrix[0].length)) {
    		return false;
    	}
    	
    	int yBeginIndex = 0;
    	int yEndIndex = y - 1;
    	
    	//先找到在在第几行
    	int targetYIndex = -1;
    	while(true) {
    		int yMiddleIndex = (yEndIndex + yBeginIndex) / 2;
	    	int yMiddle = matrix[yMiddleIndex][0];
    		
    		if(yMiddle == target) {
    			return true;
    		} else if(yMiddle > target) {
    			yEndIndex = yMiddleIndex;
    		} else {
    			yBeginIndex = yMiddleIndex;
    		}
    		
    		if(yBeginIndex == yEndIndex || (yBeginIndex+1) == yEndIndex) { //这边yBeginIndex == yEndIndex,解决了如果只有一行数据的死循环问题
    			int yEnd = matrix[yEndIndex][0];
    			if(target >= yEnd) {
    				targetYIndex = yEndIndex;
    			} else {
    				targetYIndex = yBeginIndex;
    			}
    			break;
    		} 
    	}
    	
    	//行找到了,那么就二分查找列
    	int xBeginIndex = 0;
    	int xEndIndex = x-1;
    	while(true) { //二分查找
    		int xMiddleIndex = (xEndIndex + xBeginIndex) / 2;
    		if(matrix[targetYIndex][xMiddleIndex] == target) {
    			return true;
    		} else if(matrix[targetYIndex][xMiddleIndex] < target) { //后半部
    			xBeginIndex = xMiddleIndex;
    		} else { //前半部
    			xEndIndex = xMiddleIndex;
    		}
    		
    		if(xBeginIndex == xEndIndex || xEndIndex == (xBeginIndex+1)) { //这边xBeginIndex == xEndIndex,解决了只有一列数据的死循环问题
    			if(matrix[targetYIndex][xBeginIndex]==target || matrix[targetYIndex][xEndIndex]==target) {
    				return true;
    			}
    			
    			break;
    		}
    	}
    	
    	return false;
    }
}
