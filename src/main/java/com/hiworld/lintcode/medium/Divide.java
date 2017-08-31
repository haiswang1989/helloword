package com.hiworld.lintcode.medium;

/**
 * 问题：两个整数相除 
 * 描述：将两个整数相除，要求不使用乘法、除法和 mod 运算符。
 * 如果溢出，返回 2147483647 。
 * 
 * 样例：
 * 给定被除数 = 100 ，除数 = 9，返回 11。
 * 
 * @author Administrator
 *
 */
public class Divide {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dividend = 1;
		int divisor = 1;
		
		Divide divide = new Divide();
		int ret = divide.divide(dividend, divisor);
		System.out.println("ret : " + ret);
	}
	
	/*
     * @param dividend: the dividend
     * @param divisor: the divisor
     * @return: the result
     */
    public int divide(int dividend, int divisor) {
        // write your code here
    	//divisor 除数
    	//dividend 被除数
    	
    	if(dividend < 0) {
    	}
    	
    	if(dividend < divisor) {
    		return 0;
    	}
    	
    	return bySubtraction(dividend, divisor);
    }
    
    /**
     * 通过减法实现
     * @param dividend
     * @param divisor
     * @return
     */
    public int bySubtraction(int dividend, int divisor) {
    	int count = 0;
    	while(dividend >= divisor) {
    		count++;
    		dividend -= divisor;
    		if(count < 0) {
    			return 2147483647;
    		}
    	}
    	
    	return count;
    }
}
