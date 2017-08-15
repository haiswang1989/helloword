package com.hiworld.lintcode.easy;

/**
 * 问题：搜索插入位置 
 * 描述：给定一个排序数组和一个目标值，如果在数组中找到目标值则返回索引。如果没有，返回到它将会被按顺序插入的位置。
 * 说明：你可以假设在数组中无重复元素。
 * 
 * 样例：
 * [1,3,5,6]，5 → 2
 * [1,3,5,6]，2 → 1
 * [1,3,5,6]，7 → 4
 * [1,3,5,6]，0 → 0
 * 
 * 时间复杂度：O(log(n)) time
 * 
 * 
 * @author Administrator
 *
 */
public class ArraySearchInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArraySearchInsert arraySearchInsert = new ArraySearchInsert();
		
		int[] A = {1,3,5,6};
		int ret = arraySearchInsert.searchInsert(A, 0);
		System.out.println("ret : " + ret);
	}
	
	/** 
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
    	int length = -1;
    	if(null==A || 0==(length=A.length)) {
    		return 0;
    	}
    	
    	int beginIndex = 0;
    	int endIndex = length-1;
    	
    	while(beginIndex!=endIndex && (beginIndex+1)!=endIndex) { //这边使用二分查找,看数组中是否存在该元素,如果存在则直接返回当前值
    		int middleIndex = (endIndex + beginIndex) / 2;
    		int middleVal = A[middleIndex];
    		if(middleVal == target) {
    			return middleIndex;
    		} else if(middleVal < target) {
    			beginIndex = middleIndex;
    		} else {
    			endIndex = middleIndex;
    		}
    	}
    	
    	int beginVal = A[beginIndex]; //如果不存在,那么就和 begin和end的前中后进行比较,判断插入的为,注意比较和begin和end的比较
    	int endVal = A[endIndex];
    	
    	if(target == beginVal) {
    		return beginIndex;
    	} else if(target < beginVal) {
    		if(beginIndex == 0) { //注意如果在begin的前面,需要判断begin是不是已经为0,如果为0直接返回0
    			return 0;
    		} else {
    			return beginIndex - 1; //否则分会begin的前面一个位置
    		}
    	} 
    	
    	//如果过begin的后面
    	if(target == endVal) { 
    		return endIndex;
    	} else if(target < endVal) { //中间位置,那么就是endIndex的位置
    		return endIndex;
    	} else {
    		return endIndex + 1; //如果是后面,那么就是end的后面一个位置
    	}
    }

}
