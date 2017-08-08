package com.hiworld.lintcode;

import java.util.Arrays;

/**
 * 问题：空格替换
 * 描述：设计一种方法，将一个字符串中的所有空格替换成 %20 。你可以假设该字符串有足够的空间来加入新的字符，且你得到的是“真实的”字符长度。
 * 返回值：你的程序还需要返回被替换后的字符串的长度。
 * 
 * 样例：
 * 对于字符串"Mr John Smith", 长度为 13
 * 替换空格之后，参数中的字符串需要变为"Mr%20John%20Smith"，并且把新长度 17 作为结果返回。
 * 
 * 挑战：在原字符串(字符数组)中完成替换，不适用额外空间
 * 
 * 
 * @author Administrator
 *
 */
public class ArrayReplaceBlank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayReplaceBlank arrayReplaceBlank = new ArrayReplaceBlank();
		String str = " Mr John Smith ";
		char[] strChars = str.toCharArray();
		
		char[] chars = new char[strChars.length + 2*4];
		System.arraycopy(strChars, 0, chars, 0, strChars.length);
		
		int ret = arrayReplaceBlank.replaceBlank(chars, 15);
		System.out.println("ret ： " + ret);
		System.out.println(Arrays.toString(chars));
	}
	
	/*
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
        // write your code here
    	int emptyCount = 0;
    	int endIndex = length - 1;
    	
    	int copyCharLength = 0;
    	
    	for(int i=endIndex; i>=0; --i) { //才有从后往前遍历的方式来处理
    		char c = string[i];
    		if(c == ' ') { //遇到空格
    			System.arraycopy(string, i+1, string, i + 3, copyCharLength); //就把当前元素后面一个元素开始,往后移动2个位置空格的地方放'%',往后移动的两个位置分别放'2','0'
    			string[i] = '%';
    			string[i+1] = '2';
    			string[i+2] = '0';
    			copyCharLength += 3;  //这边长度添加了3 1个空格 变成了'%' 多出来的'2','0'
    			emptyCount++; //空格多少多一个
    		} else {
    			copyCharLength++;
    		}
    	}
    	
    	return length + 2 * emptyCount;
    }

}
