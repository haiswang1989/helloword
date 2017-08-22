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
		String ret = bigIntMultiplication.multiply("1", "912891271261762152615265166721812910291921");
		System.out.println("ret : " + ret);
	}
	
	/**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return product of num1 and num2
     */
    public String multiply(String num1, String num2) {
        // Write your code here
        if("0".equals(num1) || "0".equals(num2)) { //有任何一个为0时,那么结果就是0
            return "0";
        }
        
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
     * 乘数和被乘数的每一位进行乘法计算
     * @param cNum1 乘数
     * @param c 被乘数的一位
     * @param endZeroCount 在末尾填充0的个数
     * @return
     */
    public char[] product(char[] cNum1, char c, int endZeroCount) {
    	int length = cNum1.length;
    	char[] ret = new char[length + endZeroCount + 1]; //乘数 * 被乘数(1位) 那么长度最多就是乘数的长度+1
    	int retLength = ret.length;
    	int zeroCount = 0;
    	
    	int multiplicand = c - '0'; //被乘数
    	int carry = 0; //进位
    	
    	int cNum1FromIndex = cNum1.length - 1; //乘数的开始位置
    	for(int fillIndex=retLength-1; fillIndex>=1; fillIndex--) { //注意这边的fillIndex>=1,0的位置是给最后的进位的
    		if(endZeroCount >= ++zeroCount) { //现在末尾填充'0'
    			ret[fillIndex] = '0'; 
    		} else { //计算该位置的值,以及设置进位
    			int val = (cNum1[cNum1FromIndex--]-'0') * multiplicand;
    			val += carry;
    			ret[fillIndex] = (char)(val % 10 + '0'); //当前位的值
    			carry = val / 10; //进位
    		}
    	}
    	
    	if(carry != 0) { //如果最后还有进位,那么需要进行填充
    	    ret[0] = (char)(carry + '0');
    	} else {
    	    ret[0] = '0';
    	}
    	
    	return ret;
    }
    
    /**
     * 计算被乘数的各位与乘数的乘积的和
     * @param rets
     * @return
     */
    public String doSum(List<char[]> rets) {
    	int fromEndIndex = 0; //从后往前累加
    	List<char[]> needRemove = new ArrayList<>(); //需要删除的某位与乘数的乘积(累加结束)
    	Stack<Character> values = new Stack<>(); //返回值
    	
    	int carry = 0;
    	while(rets.size() != 0) {
    		int sum = 0;
    		for (char[] chars : rets) {
				int index = chars.length-1-fromEndIndex; //最后一个数在当前字符数组的位置
				if(index < 0) { //如果该字符数组已经累加结束,那么这个字符数组就需要删除了
					needRemove.add(chars);
				} else {
					sum += (chars[index]-'0'); //累加到sum上
				}
			}
    		
    		sum += carry; //前面的进位
    		values.push((char)(sum % 10 + '0')); //当前位的值
    		carry = sum / 10; //本轮进位
    		
    		for (char[] cArray : needRemove) {
    			rets.remove(cArray);
			}
    		needRemove.clear();
    		fromEndIndex++;
    	}
    	
    	if(carry != 0) { //如果有进位
    		values.push((char)(carry + '0'));
    	}
    	
    	while(values.size()!=0 && values.peek() == '0') { //pop出前面的0
    		values.pop();
    	}
    	
    	char[] ret = new char[values.size()];
    	for(int i=0; i<ret.length; i++) {
    		ret[i] = values.pop();
    	}
    	
    	return new String(ret);
    }
}
