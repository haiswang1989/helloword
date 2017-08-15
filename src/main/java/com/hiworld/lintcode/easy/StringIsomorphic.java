package com.hiworld.lintcode.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 问题：同态字符串
 * 描述：给定两个字符串s和t，确定它们是否是同构的。如果s中的字符可以被替换得到t，则两个字符串是同构的。
 * 字符的所有出现必须用另一个字符代替，同时保留字符的顺序。 没有两个字符可能映射到同一个字符，但一个字符可能映射到自己。
 * 
 * 样例： 
 * Given s = "egg", t = "add", return true.
 * Given s = "foo", t = "bar", return false.
 * Given s = "paper", t = "title", return true.
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月10日 下午4:02:59
 */
public class StringIsomorphic {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str1 = "ab";
        String str2 = "cc";
        
        StringIsomorphic stringIsomorphic = new StringIsomorphic();
        boolean ret = stringIsomorphic.isIsomorphic(str1, str2);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 
     * @param s a string
     * @param t a string
     * @return true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // Write your code here
        int sLength = -1;
        int tLength = -1;
        
        if(null==s && null==t) {
            return true;
        } else if(null==s && null!=t) {
            return false;
        } else if(null!=s && null==t) {
            return false;
        } else if((sLength=s.length()) != (tLength=t.length())) {
            return false;
        }
        
        
        return withHashMap(s, t, sLength, tLength);
    }
    
    /**
     * 用一个HashMap来实现
     * @param s
     * @param t
     * @param sLength
     * @param tLength
     * @return
     */
    public boolean withHashMap(String s, String t, int sLength, int tLength) {
        HashMap<Character, Character> map = new HashMap<>(); //收集 s的char 和 t的char的映射
        HashSet<Character> values = new HashSet<>(); //收集所有的以及放入map的t的char
        for(int i=0; i<sLength; ++i) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if(map.containsKey(sc)) {
                char c = map.get(sc);
                if(c != tc) {
                    return false;
                }
            } else {
                if(!values.contains(tc)) { //注意这边,针对 ab 和 cc这样的字符串
                    map.put(sc, tc);
                    values.add(tc);
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }
}
