package com.hiworld.lintcode.easy;

import java.util.ArrayList;
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
        String s = "aaaa";
        String p = "a";
        
        StringFindAnagrams stringFindAnagrams = new StringFindAnagrams();
        List<Integer> ret = stringFindAnagrams.findAnagrams(s, p);
        System.out.println(ret.toString());
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
        List<Integer> ret = new ArrayList<>();
        if(null==s) {
            return ret;
        }
        
        int sLength = s.length();
        int pLength = p.length();
        if(sLength < pLength) {
            return ret;
        }
        
        findAnagramsMY(s, p, sLength, pLength, ret);
        return ret;
    }
    
    /**
     * 我的解决方式,使用两个Map来进行比较
     * @param s
     * @param p
     * @param sLength
     * @param pLength
     * @param ret
     */
    public void findAnagramsMY(String s, String p, int sLength, int pLength, List<Integer> ret) {
        HashMap<Character, Integer> cCountMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            if(cCountMap.containsKey(c)) {
                cCountMap.put(c, cCountMap.get(c)+1);
            } else {
                cCountMap.put(c, 1);
            }
            
        }
        
        int sBeginIndex = 0;
        int sEndIndex = 0;
        
        HashMap<Character, Integer> compareMap = new HashMap<>();
        for (sEndIndex=0; sEndIndex<sLength; sEndIndex++) {
            int currLength = (sEndIndex-sBeginIndex) + 1;
            char c = s.charAt(sEndIndex);
            if(compareMap.containsKey(c)) {
                compareMap.put(c, compareMap.get(c)+1);
            } else {
                compareMap.put(c, 1);
            }
            
            if(currLength < pLength) {
                continue;
            } else if(currLength > pLength) {
                char cBegin = s.charAt(sBeginIndex);
                int count = compareMap.get(cBegin);
                if(count == 1) {
                    compareMap.remove(cBegin);
                } else {
                    compareMap.put(cBegin, compareMap.get(cBegin)-1);
                }
                sBeginIndex++;
            }
            
            if(compareMap.equals(cCountMap)) {
                ret.add(sBeginIndex);
            }
        }
    }
}
