package com.hiworld.lintcode.medium;

/**
 * 问题：丢失的第一个正整数
 * 描述：给出一个无序的正数数组，找出其中没有出现的最小正整数。
 * 
 * 样例：
 * 如果给出 [1,2,0], return 3
 * 如果给出 [3,4,-1,1], return 2
 * 
 * 挑战：
 * 只允许时间复杂度O(n)的算法，并且只能使用常数级别的空间。
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月4日 下午5:15:06
 */
public class FirstMissingPositive {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    /*
     * @param A: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] A) {
        // write your code here
        int length = A.length;
        for(int i=0; i<length; ++i) {
            int fromIndex = i;
            int toIndex = A[i] - 1;
            if(fromIndex == toIndex || toIndex < 0 || toIndex >= length) {
                
            } else {
                
            }
        }
        
        return 0;
    }
    
    public void swap(int[] A, int fromIndex, int toIndex) {
        int temp = A[toIndex];
        A[toIndex] = A[fromIndex];
        A[fromIndex] = temp;
    }
}
