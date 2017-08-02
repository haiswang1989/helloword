package com.hiworld.lintcode;

/**
 * 问题：中位数
 * 描述：给定一个未排序的整数数组，找到其中位数。中位数是排序后数组的中间值，如果数组的个数是偶数个，则返回排序后数组的第N/2个数。
 * 
 * 样例：
 * 给出数组[4, 5, 1, 2, 3]， 返回 3
 * 给出数组[7, 9, 4, 5]，返回 5
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月31日 上午10:31:08
 */
public class ArrayMedianNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        // write your code here
        if(null==nums || 0==nums.length) {
            return 0;
        }
        
        int length = nums.length;
        
        int K = 0;
        if(length % 2 == 0) {
            K = length / 2; 
        } else {
            K = (length / 2) + 1;
        }
        
        return kElement(nums, K);
    }
    
    /**
     * 获取第K大元素
     * @param nums
     * @param K
     * @return
     */
    public int kElement(int[] nums, int K) {
        int[] newNums = new int[K]; 
        return newNums[K];
    }
}
