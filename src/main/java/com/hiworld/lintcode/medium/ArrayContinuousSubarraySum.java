package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 问题：连续子数组求和
 * 描述：给定一个整数数组，请找出一个连续子数组，使得该子数组的和最大。输出答案时，请分别返回第一个数字和最后一个数字的下标。（如果两个相同的答案，请返回其中任意一个）
 * 
 * 样例：
 * 给定 [-3, 1, 3, -3, 4], 返回[1,4].
 * 
 * @author Administrator
 *
 */
public class ArrayContinuousSubarraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {-3, 1, 3, -3, 4};
		ArrayContinuousSubarraySum arrayContinuousSubarraySum = new ArrayContinuousSubarraySum();
		ArrayList<Integer> rets = arrayContinuousSubarraySum.continuousSubarraySum(A);
		System.out.println(Arrays.toString(rets.toArray()));
	}
	
	/**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
    	int maxFromIndex = 0;
    	int maxEndIndex = 0;
    	int max =  A[0];
    	
    	int fromIndex = 0;
    	int endIndex = 0;
    	int currMax = A[0];
    	int sum = A[0];
    	
    	int length = A.length;
    	
    	for(int i=1; i<length; i++) {
    		if(sum > 0) {
    			if(sum > currMax) {
    				endIndex = i;
    				currMax = sum;
    			} 
    			
    			sum += A[i];
    		} else { //出现sum小于0,那么这边需要更新开始索引
    			maxFromIndex = fromIndex;
    			maxEndIndex = endIndex;
    			
    			fromIndex = i;
    			endIndex = i;
    			
    			sum = A[i]; 
    		}
    	}
    	
    	if(currMax > max) {
    		maxFromIndex = fromIndex;
			maxEndIndex = endIndex;
    	}
    	
    	ArrayList<Integer> ret = new ArrayList<>();
    	ret.add(maxFromIndex);
    	ret.add(maxEndIndex);
    	return ret;
    }

}
