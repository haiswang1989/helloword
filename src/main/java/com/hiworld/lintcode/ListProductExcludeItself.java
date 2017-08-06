package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 问题：数组剔除元素后的乘积
 * 描述：给定一个整数数组A。定义B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]， 计算B的时候请不要使用除法。
 * 
 * 样例：给出A=[1, 2, 3]，返回 B为[6, 3, 2]
 * 
 * 分析：就是B[i]值就是A中所有除了A[i]元素的乘积,注意不要使用除法
 * 
 * @author Administrator
 *
 */
public class ListProductExcludeItself {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> A = new ArrayList<>();
		A.add(0);
//		A.add(2);
//		A.add(3);
		
		ListProductExcludeItself listProductExcludeItself = new ListProductExcludeItself();
		ArrayList<Long> ret = listProductExcludeItself.productExcludeItself(A);
		System.out.println("ret : " + Arrays.toString(ret.toArray()));
	}
	
	/**
     * @param A: Given an integers array A
     * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        // write your code
    	int length = -1;
    	if(null==A || 0==(length=A.size())) {
    		return new ArrayList<>();
    	}
    	
    	ArrayList<Long> ret = new ArrayList<>();
    	if(1==length) { //为啥只有一个元素的时候,返回1呢？
    		ret.add(1l);
    		return ret;
    	}
    	
    	Map<Integer, Long> beginToEnd = new HashMap<>();
    	Map<Integer, Long> endToBegin = new HashMap<>();
    	
    	long beginProduct = 1l;
    	long endProduct = 1l; 
    	
    	for(int i=0,j=length-1; i<length; ++i,--j) { //这个做法其实遍历了3次,才会生成最后的结果
    		beginProduct *= A.get(i);
    		endProduct *= A.get(j);
    		
    		beginToEnd.put(i, beginProduct); //从前往后遍历,生成乘积
    		endToBegin.put(j, endProduct); //从后往前遍历,生成乘积
    	}
    	
    	for(int bi=0; bi<length; ++bi) { //具体的B[i] = 从前往后遍历的第i-1个元素 * 从后往前第i+1个元素
    		Long before = beginToEnd.get(bi-1);
    		Long after = endToBegin.get(bi+1);
    		if(null==before && null==after) {
    			System.err.println("fuck,error.");
    		} else if(null==before) {
    			ret.add(after);
    		} else if(null==after) {
    			ret.add(before);
    		} else {
    			ret.add(before*after);
    		}
    	}
    	
    	return ret;
    }
}
