package com.hiworld.lintcode;

/**
 * 问题：反转整数
 * 描述：将一个整数中的数字进行颠倒，当颠倒后的整数溢出时，返回 0 (标记为 32 位整数)。
 * 
 * 样例：
 * 给定 x = 123，返回 321
 * 给定 x = -123，返回 -321
 * 
 * @author Administrator
 *
 */
public class IntegerReverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntegerReverse integerReverse = new IntegerReverse();
		int ret = integerReverse.reverseInteger(-123);
		System.out.println("ret : " + ret);
	}
	
	/**
     * @param n the integer to be reversed
     * @return the reversed integer
     */
    public int reverseInteger(int n) {
        // Write your code here
    	boolean isNegative = false; 
    	if(n < 0) { //如果是负数,首先对负数进行转换
    		n *= -1;
    		isNegative = true;
    	}
    	
    	//然后把数字转换成字符串进行处理,进行字符串的reverse
    	String str = String.valueOf(n);
    	char[] chars = str.toCharArray();
    	int length = chars.length;
    	for(int i=0,j=length-1; i<j; i++,--j) {
    		swap(chars, i, j);
    	}
    	
    	//将转换后的字符串和最大Integer的字符串进行比较
    	String maxInteger = String.valueOf(Integer.MAX_VALUE);
    	String newInteger = new String(chars);
    	//如果长度大于最大Integer的字符串长度
    	//或者长度相等,但是字符串比最大Integer的字符串大,那么就表示溢出了,直接返回0
    	if(length > maxInteger.length() || (length==maxInteger.length() && maxInteger.compareTo(newInteger) < 0)) {
    		return 0;
    	}
    	
    	//如果没有溢出,那么直接使用最后的字符串来构造返回值,注意如果前面是负数,这边需要进行转换
    	Integer ret = new Integer(newInteger);
    	if(isNegative) {
    		return ret * -1;
    	} else {
    		return ret;
    	}
    }
    
    /**
     * 交换字符串
     * @param chars
     * @param index1
     * @param index2
     */
    public void swap(char[] chars, int index1, int index2) {
    	char cTemp = chars[index1];
    	chars[index1] = chars[index2];
    	chars[index2] = cTemp;
    }

}
