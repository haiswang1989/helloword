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
        int[] A = {1};
        
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int ret = firstMissingPositive.firstMissingPositive(A);
        System.out.println("ret : " + ret);
    }
    
    /*
     * 思路：就是把每个元素放到对应的位置上,比如元素的值为1那么就放到数组的index为0的位置上
     * 如果元素的值为n,就放到数组index为(n-1)的位置上
     * 无需移动的情况：
     * index的范围不能小于0且不能大于等于数组的length,如果数组的值就该放到这个位置上index = A[index]-1
     * 
     * 元素迁移完成以后,再次遍历一下数组,如果数组出现index!=A[index]-1那么就返回当前index+1的值
     * 如果元素全部完全一致,那么就直接返回数组的length+1
     *  
     * @param A: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] A) {
        // write your code here
        if(null==A || A.length==0) {
            return 1;
        }
        
        int length = A.length;
        for(int i=0; i<length; ++i) {
            int toIndex = A[i] - 1;
            int fromIndex = i;
            int val = A[fromIndex];
            while(!(toIndex < 0 || toIndex >= length || fromIndex == val-1)) {
                fromIndex = toIndex;
                int temp = setValue(A, toIndex, val);
                toIndex = temp - 1;
                val = temp;
            }
        }
        
        for(int i=0; i<length; ++i) {
            if(i != A[i] - 1) {
                return i + 1;
            }
        }
        
        return length + 1;
    }
    
    /**
     * 
     * @param A
     * @param index
     * @param value
     * @return
     */
    public int setValue(int[] A, int index, int value) {
        int temp = A[index];
        A[index] = value;
        return temp;
    }
}
