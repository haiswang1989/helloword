package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题：恢复旋转排序数组
 * 描述：给定一个旋转排序数组，在原地恢复其排序。
 * 说明：原始数组为[1,2,3,4], 则其旋转数组可以是[1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
 * 
 * 样例：[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
 * 
 * 挑战：使用O(1)的额外空间和O(n)时间复杂度
 * 
 * @author Administrator
 *
 */
public class ListRecoverRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> nums = new ArrayList<>();
		nums.add(4);
		nums.add(5);
		nums.add(1);
		nums.add(2);
		nums.add(3);
		
		ListRecoverRotatedSortedArray listRecoverRotatedSortedArray = new ListRecoverRotatedSortedArray();
		listRecoverRotatedSortedArray.recoverRotatedSortedArray(nums);
		
		System.out.println(Arrays.toString(nums.toArray()));
	}
	
	/*
     * @param nums: An integer
     * @return: 
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
    	int length = -1;
    	if(null==nums || 0==(length=nums.size()) || 1==length) {
    		return;
    	}
    	
    	int before = nums.get(0);
    	nums.remove(0);
		nums.add(before);
		
		while(true) {
			int curr = nums.get(0);
			if(curr < before) { //不停得删除第一个元素,加到末尾,直到出现当前值小于前面一个值
				break;
			}
			
			nums.remove(0); 
			nums.add(curr);
			before = curr;
		}
    }

}
