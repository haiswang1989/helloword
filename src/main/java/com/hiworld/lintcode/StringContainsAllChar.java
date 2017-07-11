package com.hiworld.lintcode;

/**
 * 问题：比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是 大写字母
 * 
 * 样例：
 * 给出 A = "ABCD" B = "ACD"，返回 true
 * 给出 A = "ABCD" B = "AABC"， 返回 false
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月11日 下午2:05:08
 */
public class StringContainsAllChar {

    public static void main(String[] args) {
        StringContainsAllChar stringContainsAllChar = new StringContainsAllChar();
        System.out.println(stringContainsAllChar.compareStrings("ABCD", "ACD"));
        System.out.println(stringContainsAllChar.compareStrings("ABCD", "AABC"));
    }
    
    /**
     * 
     * @param A
     * @param B
     * @return
     */
    public boolean compareStrings(String A, String B) {
        
        if(null == B) {
            return true;
        } else if(null == A) {
            return false;
        } 
        
        int[] arrays = new int[26];
        for (char c : A.toCharArray()) {
            int index = c - 'A';
            arrays[index] = ++arrays[index]; 
        }
        
        for (char c : B.toCharArray()) {
            int index = c - 'A';
            int value = arrays[index];
            if(--value < 0) {
                return false;
            }
            
            arrays[index] = value;
        }
        
        return true;
    }

}
