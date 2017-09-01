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
		int dividend = 100;
		int divisor = 9;
		
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
        // x(被除数) / y(除数)
        //dividend 被除数
    	//divisor 除数
    	
        //溢出情况
        if(divisor == 0 || (dividend==Integer.MIN_VALUE && divisor==-1)) {
            return Integer.MAX_VALUE;
        }
        
        int sign = (dividend>0) ^ (divisor>0) ? -1 : 1; 
    	
        int result = 0;
        long dividendL = Math.abs((long)dividend);
        long divisorL = Math.abs((long)divisor);
        
        while(dividendL >= divisorL) {
            long sub = divisorL;
            int subR = 1;
            
            while(dividendL >= (sub << 1)) { //当小于出现,那么就一颗跳出该次循环
                sub <<= 1; //除数在2的倍数增加
                subR <<=1; //结果也就成2的倍数的增加
            }
            
            dividendL -= sub; //剩下的结果集
            result += subR; //输出结果集
        }
        
        return sign * result;
    }
}
