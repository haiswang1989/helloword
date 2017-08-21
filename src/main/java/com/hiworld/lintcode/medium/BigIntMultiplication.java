package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 问题：Big Integer multiplication(大整数[String表示]的乘法)
 * 描述：使用两个string表示的int类型值,返回两个数的乘积
 * 
 * 前提：
 * 1：两个数的长度都小于110
 * 2：每个数只包含0-9
 * 3：每个数都不会以0开头
 * 
 * 
 * @author Administrator
 *
 */
public class BigIntMultiplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigIntMultiplication bigIntMultiplication = new BigIntMultiplication();
		String ret = bigIntMultiplication.multiply("52345", "22");
		System.out.println("ret : " + ret);
	}
	
	/**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return product of num1 and num2
     */
    public String multiply(String num1, String num2) {
        // Write your code here
    	char[] cNum1 = num1.toCharArray();
    	char[] cNum2 = num2.toCharArray();
    	
    	int cNum2Length = cNum2.length;
    	int endZeroCount = 0;
    	
    	List<char[]> charArrays = new ArrayList<>();
    	
    	for(int i=cNum2Length-1; i>=0; i--) {
    		char[] ret = product(cNum1, cNum2[i], endZeroCount++);
    		charArrays.add(ret);
    	}
    	
    	return doSum(charArrays);
    }
    
    /**
     * 
     * 
     * @param cNum1
     * @param c
     * @param endZeroCount
     * @return
     */
    public char[] product(char[] cNum1, char c, int endZeroCount) {
    	int length = cNum1.length;
    	char[] ret = new char[length + endZeroCount];
    	
    	int retLength = ret.length;
    	int zeroCount = 0;
    	
    	int multiplicand = c - '0';
    	if(multiplicand == 0) {
    		return new char[0];
    	}
    	
    	int carry = 0;
    	
    	for(int fillIndex=retLength-1; fillIndex>=0; fillIndex--) {
    		if(endZeroCount >= ++zeroCount) {
    			ret[fillIndex] = '0'; 
    		} else {
    			int val = (cNum1[fillIndex]-'0') * multiplicand;
    			val += carry;
    			ret[fillIndex] = (char)(val % 10 + '0');
    			carry = val / 10;
    		}
    	}
    	
    	if(carry != 0) {
    		char[] realRet = new char[ret.length + 1];
    		realRet[0] = (char)(carry + '0');
    		System.arraycopy(ret, 0, realRet, 1, ret.length);
    		return realRet;
    	}
    	
    	return ret;
    }
    
    /**
     * 
     * @param rets
     * @return
     */
    public String doSum(List<char[]> rets) {
    	int fromEndIndex = 0;
    	List<char[]> needRemove = new ArrayList<>();
    	
    	Stack<Character> values = new Stack<>();
    	
    	int carry = 0;
    	while(rets.size() != 0) {
    		int sum = 0;
    		for (char[] chars : rets) {
				int index = chars.length-1-fromEndIndex;
				if(index < 0) {
					needRemove.add(chars);
				} else {
					sum += (chars[index]-'0');
				}
			}
    		
    		sum += carry;
    		values.push((char)(sum % 10 + '0'));
    		carry = sum / 10;
    		
    		fromEndIndex++;
    		for (char[] cArray : needRemove) {
    			rets.remove(cArray);
			}
    		needRemove.clear();
    	}
    	
    	if(carry != 0) {
    		values.push((char)(carry + '0'));
    	}
    	
    	while(values.peek() == '0') {
    		values.pop();
    	}
    	
    	char[] ret = new char[values.size()];
    	for(int i=0; i<ret.length; i++) {
    		ret[i] = values.pop();
    	}
    	
    	return new String(ret);
    }

}
