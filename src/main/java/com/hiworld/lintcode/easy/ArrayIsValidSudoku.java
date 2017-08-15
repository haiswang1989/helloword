package com.hiworld.lintcode.easy;

/**
 * 问题：判断数独是否合法
 * 描述：请判定一个数独是否有效。该数独可能只填充了部分数字，其中缺少的数字用 . 表示
 * 
 * 
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月15日 上午11:59:09
 */
public class ArrayIsValidSudoku {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    /*
     * 
     * @param board: the board
     * @return: whether the Sudoku is valid
     */
    public boolean isValidSudoku(char[][] board) {
        // write your code here
        if(null == board || board.length != 9 || board[0].length != 9) {
            return false;
        }
        
        for(int i=0; i<9; ++i) {
            for(int j=0; j<9; ++j) {
                
            }
        }
        
        return true;
    }

}
