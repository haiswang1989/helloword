package com.hiworld.lintcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题：最长回文串
 * 描述：给出一个包含大小写字母的字符串。求出由这些字母构成的最长的回文串的长度是多少。数据是大小写敏感的，也就是说，"Aa" 并不会被认为是一个回文串。
 * 
 * 样例：
 * 给出 s = "abccccdd" 返回 7
 * 一种可以构建出来的最长回文串方案是 "dccaccd"。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月26日 下午5:22:39
 */
public class StringLongestPalindrome {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //NTrQdQGgwtxqRTSBOitAXUkwGLgUHtQOmYMwZlUxqZysKpZxRoehgirdMUgy
        String s = "NTrQdQGgwtxqRTSBOitAXUkwGLgUHtQOmYMwZlUxqZysKpZxRoehgirdMUgy";
        StringLongestPalindrome stringLongestPalindrome = new StringLongestPalindrome();
        System.out.println(stringLongestPalindrome.longestPalindrome(s));
    }
    
    /**
     * @param s a string which consists of lowercase or uppercase letters
     * @return the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // Write your code here
        if(null==s || 0==s.length()) {
            return 0;
        }
        
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            if(charCount.containsKey(c)) {
                charCount.put(c, charCount.get(c) + 1);
            } else {
                charCount.put(c, 1);
            }
        }
        
        int evenNumCount = 0; //偶数的个数
        int maxUnevenNum = 0; //最大的奇数
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            int count = entry.getValue();
            if(count % 2 == 0) { //如果是偶数可以直接用来构造回文串
                evenNumCount += count;
            } else { 
                if(maxUnevenNum < count) { //如果是奇数,那么可以把当前的奇数-1后的count,来构造回文
                    if(0!=maxUnevenNum) { //可以留下一个最长的奇数,然后其他小于或等于该最长的奇数,统一减去1来构造回文
                        evenNumCount += (maxUnevenNum-1);
                    }
                    maxUnevenNum = count;
                } else {
                    evenNumCount += (count-1);
                }
            }
        }
        
        
        return evenNumCount + maxUnevenNum; //加上最长奇数
    }

}
