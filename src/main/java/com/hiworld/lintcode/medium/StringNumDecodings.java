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
		int ret = stringNumDecodings.numDecodings("11111878787676172120121101212918291829819");
		System.out.println("ret : " + ret);
	}
	
	/*
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        // write your code here
    	int length = 0;
    	if(null==s || (length=s.length())==0 || '0'==s.charAt(0)) {
    		return 0;
    	}
//    	return withRecusion(s, 0, length-1);
//    	return withDP(s, length);
    	return dp(s);
    }
    
    /****************************自己能理解的dp实现****************************/
    /**
     * 
     * @param s
     * @return
     */
    public int dp(String s) {
        int[] dp = new int[s.length() + 1];
        
        dp[0] = 1; //
        dp[1] = 1; //第一个字符,能进这个方法,s.charAt(0)!='0'
        int dpIndex = 2; //dp数组的开始位置从2开始
        for(int i=1; i<s.length(); i++,dpIndex++) {
            char curr = s.charAt(i); //当前字符
            char before = s.charAt(i - 1); //前面一个字符
            
            if(curr == '0') { //如果当前字符是0
                if(before=='1' || before=='2') { //如果前面的字符是'1' 或者 '2'那么 当前字符和前面一个字符必须合起来解码
                    dp[dpIndex] = dp[dpIndex-2];
                } else { //如果前面的字符不是'1' 或者 '2' 那么就无法解码了
                    return 0;
                }
            } else { //如果不是'0'
                if(before=='1' || (before=='2' && curr<='6')) { //且和前面一个字符可以合起来解码或者分开解码
                    dp[dpIndex] = dp[dpIndex-1] + dp[dpIndex-2];
                } else { //和前面的字符只能分开解码
                    dp[dpIndex] = dp[dpIndex-1];
                }
            }
        }
        
        return dp[s.length()];
    }
    
    /**************************网上的dp实现**************************/
    /**
     * 状态：dp[i] 表示前i个字符的解码方式
     * 状态转移方程：
     * dp[i] = dp[i-1] 
     * dp[i] = dp[i-1] + dp[i-2]
     * 动态规划
     * @param s
     * @param fromIndex
     * @param endIndex
     * @return
     */
    public int withDP(String s, int length) {
        int[] dp = new int[length + 1];
        dp[0] = 1;
        
        for(int i=1; i<dp.length; ++i) {
            dp[i] = s.charAt(i-1) == '0' ? 0 : dp[i-1];
            if(i>=2 && (s.charAt(i-2)=='1' || (s.charAt(i - 2) == '2') && s.charAt(i - 1) <= '6')) {
                dp[i] += dp[i-2];
            }
        }
        
        return dp[length];
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
        } else if((first == '1' && second=='0') || (first == '2' && second=='0')) {
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
