package com.hiworld.lintcode.medium;

import java.util.Stack;

/**
 * 问题：Expression Expand 
 * 描述：
 * 
 * 样例：
 * s = abc3[a] return abcaaa
 * s = 3[abc] return abcabcabc
 * s = 4[ac]dy, return acacacacdy
 * s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月31日 下午5:18:57
 */
public class ExpressionExpand {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        // Write your code here
        if(null==s || s.length()==0) {
            return s;
        }
        
        Stack<Character> chars = new Stack<>();
        boolean needExp = false;
        int beginCnt = 0;
        
        for (Character character : s.toCharArray()) {
            chars.push(character);
            if(!needExp) {
                if(character == '[') {
                    needExp = true;
                }
            }
        }
        
        
        
    }

}
