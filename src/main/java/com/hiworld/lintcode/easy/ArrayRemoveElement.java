package com.hiworld.lintcode.easy;

import java.util.Arrays;

/**
 * 问题：删除元素
 * 描述：给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。元素的顺序可以改变，并且对新的数组不会有影响。
 * 
 * 样例：
 * 给出一个数组 [0,4,4,0,0,2,4,4]，和值 4
 * 返回 4 并且4个元素的新数组为[0,0,0,2]
 * 
 * 
 * @author Administrator
 *
 */
public class ArrayRemoveElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		int[] B = {0,0,0,0};
//		int[] C = {1,1,1,1};
//		
//		System.arraycopy(C, 0, B, 0, C.length);
//		System.out.println(Arrays.toString(B));
		
		
		int[] A = {0,4,4,0,4,4,4,0,2};
		ArrayRemoveElement arrayRemoveElement = new ArrayRemoveElement();
		int length = arrayRemoveElement.removeElement(A, 4);
		System.out.println(Arrays.toString(A));
		System.out.println("length:" + length);
	}
	
	
	
	/** 
     *@param A: A list of integers
     *@param elem: An integer
     *@return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
    	int length = -1;
    	if(null==A || 0==(length=A.length)) {
    		return 0;
    	}
    	
    	int sameIndexBegin = -1;
    	int sameIndexEnd = -1;
    	
    	int delCount = 0;
    	
    	for(int i=0; i<(length-delCount);++i) {
    		int currNum = A[i];
    		if(currNum == elem) {
    			if(sameIndexBegin == -1) {
    				sameIndexBegin = i; //第一次设置begin和end为同一个值
    				sameIndexEnd = i;
    			} else {
    				sameIndexEnd = i; //如果连续找到指定值,调整end值就ok
    			}
    		} else {
    			if(sameIndexBegin!=-1) {
    				if(sameIndexEnd != A.length-1) { //最后一个元素就不需
    					delCount += copyArray(A, sameIndexBegin, sameIndexEnd);
    					i = sameIndexBegin-1; //重新设置i的位置位sameIndexBegin的前面一个元素,马上就要进行++i操作
        				sameIndexBegin = -1;
        				sameIndexEnd = -1;
    				} 
    			} 
    		}
    	}
    	
    	if(sameIndexBegin != -1) { //如果查找元素在数组的最后,需要调整删除元素个数
    		delCount += (sameIndexEnd - sameIndexBegin + 1);
    	}
    	
    	return A.length - delCount;
    }
    
    public int copyArray(int[] A, int delIndexBegin, int delIndexEnd) {
    	System.arraycopy(A, delIndexEnd+1, A, delIndexBegin, A.length - delIndexEnd - 1);
    	return delIndexEnd - delIndexBegin + 1;
    }

}
