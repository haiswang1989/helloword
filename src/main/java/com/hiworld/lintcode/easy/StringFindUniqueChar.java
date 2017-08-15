package com.hiworld.lintcode.easy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 问题：找出第一个再字符串中只出现一次的的字符的索引,如果不存在这样的字符返回-1
 * 
 * 样例：
 * s = "lintcode", return 0.
 * s = "lovelintcode", return 2.
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月13日 下午1:53:47
 */
public class StringFindUniqueChar {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        StringFindUniqueChar stringFindUniqueChar = new StringFindUniqueChar();
        String str = "lintcode";
        System.out.println(stringFindUniqueChar.firstUniqChar(str));
        System.out.println(stringFindUniqueChar.solution(str));
        
    }
    
    /**
     * 查看第一个
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        // Write your code here
        if(null == s || "".equals(s)) {
            return -1;
        }
        
        LinkedHashMap<Character, Integer> chars = new LinkedHashMap<>();
        for (char c : s.toCharArray()) { //遍历字符,构造一个linkedhashmap
            if(chars.containsKey(c)) {
                int count = chars.get(c);
                chars.put(c, ++count);
            } else {
                chars.put(c, 1);
            }
        }
        
        boolean hasUniqueChar = false;
        char uniqueChar = ' ';
        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            if(entry.getValue() == 1) { //如果该字符出现的次数为1,那么就跳出来,表示找到了unique的字符
                hasUniqueChar = true;
                uniqueChar = entry.getKey();
                break;
            }
        }
        
        if(hasUniqueChar) {
            return s.indexOf(uniqueChar); //再次查看字符在string中的位置
        }
        
        return -1;
    }
    
    /**
     * 内存占用率为O(1)的解决方案
     */
    public int solution(String s) {
        if(null == s || "".equals(s)) {
            return -1;
        }
        
        
        int[] charCount = new int[256];
        
        char[] cs = s.toCharArray();
        int length = cs.length;
        
        for (char c : cs) {
            ++charCount[c];
        }
        
        for(int i=0; i<length; ++i) {
            if(charCount[cs[i]] == 1) {
                return i;
            }
        }
        
        return -1;
    }
}
