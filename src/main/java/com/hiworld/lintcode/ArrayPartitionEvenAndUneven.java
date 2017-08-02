package com.hiworld.lintcode;

import java.util.Arrays;

/**
 * 问题：奇偶分割数组 
 * 描述：分割一个整数数组，使得奇数在前偶数在后。
 * 样例：给定 [1, 2, 3, 4]，返回 [1, 3, 2, 4]。
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月2日 下午5:49:23
 */
public class ArrayPartitionEvenAndUneven {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int nums[] = {1, 2, 3, 4};
        ArrayPartitionEvenAndUneven arrayPartitionEvenAndUneven = new ArrayPartitionEvenAndUneven();
        arrayPartitionEvenAndUneven.partitionArray(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    /**
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        // write your code here;
        int length = -1;
        if(null==nums||0==(length=nums.length)) {
            return ;
        }
        
        boolean isFoundUneven = false; //是否在找奇数
        int evenIndex = -1; //偶数的索引位置
        for(int i=0; i<length; ++i) {
            if(isEven(nums[i])) { //偶数
                if(!isFoundUneven) {
                    evenIndex = i;
                    isFoundUneven = true;
                }
            } else { //奇数
                if(isFoundUneven) { 
                    swap(nums, evenIndex, i);
                    i = evenIndex;
                    isFoundUneven = false;
                }
            }
        }
    }
    
    /**
     * 偶数
     * @param num
     * @return
     */
    public boolean isEven(int num) {
        return  num % 2 == 0;
    }
    
    /**
     * 交换元素
     * @param nums
     * @param index1
     * @param index2
     */
    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
