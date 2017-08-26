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
		int[] A = {-3,-2,-1};
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
    	//记录历史最大的和,以及beginIndex和endIndex
    	int historyMaxFromIndex = -1;
    	int historyMaxEndIndex = -1;
    	int historyMax =  Integer.MIN_VALUE;
    	
    	//记录该次循环的最大值和对应的index
    	int fromIndex = -1;
    	int endIndex = -1;
    	int currMax = Integer.MIN_VALUE;
    	int sum = 0;
    	
    	int length = A.length;
    	for(int i=0; i<length; i++) {
    		sum += A[i];
    		if(sum >= 0) { //如果和大于0,那么就可以继续往下累加
    			if(sum > currMax) {
    				if(fromIndex == -1) {
    					fromIndex = i;
    				}
    				
    				endIndex = i;
    				currMax = sum;
    			} 
    		} else { //出现sum小于0,继续往下加就不符合条件了
    			if(sum > historyMax && fromIndex == -1) { //这部分是对全负数的处理,如果直接是负数,就记录最小的负数
    				historyMaxFromIndex = i;
    				historyMaxEndIndex = i;
    				historyMax = sum;
    			} else if(currMax > historyMax) { //如果这次循环的最大值大于历史的最大值,那么就更新历史最大值的index和max值
    				historyMaxFromIndex = fromIndex;
    				historyMaxEndIndex = endIndex;
    				historyMax = currMax;
    			}
    			
    			//清除当前循环的状态
    			fromIndex = -1;
    			endIndex = -1;
    			sum = 0; 
    			currMax = Integer.MIN_VALUE;
    		}
    	}
    	
    	if(currMax > historyMax) { //退出来以后,要处理一下最后的情况
			historyMaxFromIndex = fromIndex;
			historyMaxEndIndex = endIndex;
			historyMax = currMax;
    	}
    	
    	ArrayList<Integer> ret = new ArrayList<>();
    	ret.add(historyMaxFromIndex);
    	ret.add(historyMaxEndIndex);
    	return ret;
    }

}
