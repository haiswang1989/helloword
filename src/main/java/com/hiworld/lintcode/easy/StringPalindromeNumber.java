package com.hiworld.lintcode.easy;

/**
 * 问题：回文数
 * 描述：判断一个正整数是不是回文数。回文数的定义是，将这个数反转之后，得到的数仍然是同一个数。
 * 注意：给的数一定保证是32位正整数，但是反转之后的数就未必了。
 * 
 * 样例：11, 121, 1, 12321 这些是回文数。23, 32, 1232 这些不是回文数。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月2日 下午5:13:12
 */
public class StringPalindromeNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        StringPalindromeNumber stringPalindromeNumber = new StringPalindromeNumber();
        boolean ret = stringPalindromeNumber.palindromeNumber(1232);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param num a positive number
     * @return true if it's a palindrome or false
     */
    public boolean palindromeNumber(int num) {
        // Write your code here
        return isPalindrome(String.valueOf(num));
    }
    
    /**
     * 测试一个字符串是不是回文
     * @param str
     * @return
     */
    public boolean isPalindrome(String str) {
        int length = str.length();
        if(1 == length) {
            return true;
        }
        
        int i = 0;
        int j = length-1;
        
        while(i < j) {
            if(str.charAt(i++) != str.charAt(j--)) {
                return false;
            } 
        }
        
        return true;
    }
    

}
