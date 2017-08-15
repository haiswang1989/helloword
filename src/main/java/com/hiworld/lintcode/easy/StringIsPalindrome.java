package com.hiworld.lintcode.easy;

import java.util.HashSet;

/**
 * 问题：有效回文串 
 * 描述：给定一个字符串，判断其是否为一个回文串。只包含字母和数字，忽略大小写。
 * 
 * 注意事项：
 * 你是否考虑过，字符串有可能是空字符串？这是面试过程中，面试官常常会问的问题。
 * 在这个题目中，我们将空字符串判定为有效回文。
 * 
 * 样例：
 * "A man, a plan, a canal: Panama" 是一个回文。
 * "race a car" 不是一个回文。
 * 
 * 挑战：
 * O(n) 时间复杂度，且不占用额外空间。
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月14日 上午10:01:45
 */
public class StringIsPalindrome {
    
    HashSet<Character> validChars = new HashSet<>();
    
    {
        for(char c='0'; c<='9'; c++) {
            validChars.add(c);
        }
        
        for(char c='a'; c<='z'; c++) {
            validChars.add(c);
        }
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str = "race a car";
        
        StringIsPalindrome stringIsPalindrome = new StringIsPalindrome();
        boolean ret = stringIsPalindrome.isPalindrome(str);
        System.out.println("ret : " + ret);
    }
    
    /*
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        if(null==s || 0==s.length()) {
            return true;
        }
        
        String sTrim = s.trim();
        if(0 == sTrim.length()) {
            return true;
        }
        
        String sTrimLowerCase = sTrim.toLowerCase();
        int length = sTrimLowerCase.length();
        
        for(int i=0,j=length-1; i<j;) {
            char before = sTrimLowerCase.charAt(i);
            char end = sTrimLowerCase.charAt(j);
            
            if(!isValid(before) && !isValid(end)) {
                i++;
                j--;
                continue;
            } else if(!isValid(before) && isValid(end)) {
                i++;
                continue;
            } else if(isValid(before) && !isValid(end)) {
                j--;
                continue;
            } else {
                i++;
                j--;
                if(before != end) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * 是否是有效字符
     * @param c
     * @return
     */
    public boolean isValid(Character c) {
        return validChars.contains(c);
    }
}
