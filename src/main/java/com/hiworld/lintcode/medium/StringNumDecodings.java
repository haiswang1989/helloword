package com.hiworld.lintcode.medium;

/**
 * 问题：解码方法 
 * 描述：有一个消息包含A-Z通过以下规则编码
 * 
 * 'A' -> 1
 * 'B' -> 2
 * .
 * .
 * .
 * 'Z' -> 26
 * 
 * 现在给你一个加密过后的消息，问有几种解码的方式
 * 
 * 样例：
 * 给你的消息为12，有两种方式解码 AB(12) 或者 L(12). 所以返回 2
 * 
 * @author Administrator
 *
 */
public class StringNumDecodings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StringNumDecodings stringNumDecodings = new StringNumDecodings();
		int ret = stringNumDecodings.numDecodings("2789011203");
		System.out.println("ret : " + ret);
	}
	
	/*
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        // write your code here
    	int length = 0;
    	if(null==s || (length=s.length())==0) {
    		return 0;
    	}
    	
    	return withRecusion(s, 0, length-1);
    }
    
    /**
     * 动态规划
     * @param s
     * @param fromIndex
     * @param endIndex
     * @return
     */
    public int withDP(String s, int fromIndex, int endIndex) {
    	return 0;
    }
    
    /***************************我自己的方法递归(不能被AC)**************************/
    /**
     * 执行超时了
     * @param s
     * @param fromIndex
     * @param endIndex
     * @return
     */
    public int withRecusion(String s, int fromIndex, int endIndex) {
    	if(fromIndex >= endIndex) {
    		return 1;
    	}
    	
    	char first = s.charAt(fromIndex);
    	char second = s.charAt(fromIndex + 1);
    	
    	if((first == '1' && second!='0') || (first == '2' && second <= '6' && second!='0')) {
    		//对于这样的情况可以一个位一位的解码,也可以2位合起来一起解码
    		return withRecusion(s, fromIndex + 1, endIndex) + withRecusion(s, fromIndex + 2, endIndex);
    	} else if((first == '1' && second=='0') || (first == '2' && second <= '6' && second=='0')) {
    		//"10","20" 这样的情况只能两位一起解码
    		//如果分开解码就会出现'0'没法解
    	    return withRecusion(s, fromIndex + 2, endIndex);
    	} else {
    		//如果出现first为'0',那么就无法解码了
    		if(first=='0') {
    			return 0;
    		} else {
    			//否则一位一位的解
    			return withRecusion(s, fromIndex + 1, endIndex);
    		}
    	}
    }

}
