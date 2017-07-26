package com.hiworld.lintcode;

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
        
        //TODO 代码测试不过
        
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
            System.out.println(entry.getKey() + ":" + entry.getValue());
            int count = entry.getValue();
            if(count % 2 == 0) {
                evenNumCount += count;
            } else {
                maxUnevenNum = Math.max(maxUnevenNum, count);
            }
        }
        
        
        return evenNumCount + maxUnevenNum;
    }

}
