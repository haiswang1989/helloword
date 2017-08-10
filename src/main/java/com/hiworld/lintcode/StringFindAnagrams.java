package com.hiworld.lintcode;

import java.util.HashMap;
import java.util.List;

/**
 * 问题：子串字谜
 * 描述：给定一个字符串s和一个非空字符串p，找到p在s中所有的字谜的起始索引。
 * 
 * 字谜：
 * "cba"是"abc"的一个字谜
 * "bac"是"abc"的一个字谜
 * 
 * 样例：
 * s = "cbaebabacd" p = "abc"
 * 返回[0,6]
 * "cba"和"bac"都是abc的字谜
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月10日 下午6:00:39
 */
public class StringFindAnagrams {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 
     * 
     * @param s a string
     * @param p a non-empty string
     * @return a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        
        int pLength = p.length();
        HashMap<Character, Integer> cIndexMap = new HashMap<>();
        int index = 0;
        for (char c : p.toCharArray()) {
            cIndexMap.put(c, index++);
        }
        
        
        char[] sc = s.toCharArray();
        int sLength = sc.length;
        
        for (int i=0; i<sLength; i++) {
            
        }
        
        
        
        
        return null;
    }

}
