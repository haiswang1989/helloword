package com.hiworld.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 问题：有效的括号序列 
 * 描述：给定一个字符串所表示的括号序列，包含以下字符： '(', ')', '{', '}', '[' and ']'， 判定是否是有效的括号序列。
 * 
 * 样例：括号必须依照 "()" 顺序表示
 *  "[({(())}[()])]" 是有效的括号
 *  "()[]{}" 是有效的括号
 *  "([])" 是有效的括号
 *  "([)]"则是无效的括号。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月14日 上午10:38:37
 */
public class StringIsValidParentheses {
    
    HashSet<Character> open = new HashSet<>();
    HashSet<Character> close = new HashSet<>();
    HashMap<Character, Character> pairs = new HashMap<>();
    {
        open.add('(');
        open.add('{');
        open.add('[');
        
        close.add(')');
        close.add('}');
        close.add(']');
        
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StringIsValidParentheses stringIsValidParentheses = new StringIsValidParentheses();
        String str = "()[]{}";
        boolean ret = stringIsValidParentheses.isValidParentheses(str);
        System.out.println("ret : " + ret);
    }
    
    /*
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        // write your code here
        int length = -1;
        if(null==s || 0==(length=s.length())) {
            return false;
        }
        
        if((length >> 1 << 1) != length) { //如果字符串的长度都不是偶数,那么肯定不配对
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (Character character : s.toCharArray()) {
            if(!isValid(character)) { //如果存在非合法字符,直接返回false
                return false;
            }
            
            if(isOpen(character)) { //遇到open的,直接压栈
                stack.push(character);
            } else { //遇到close的,需要从栈中pop出来,看是否配对
                //注意这边需要先判断栈的大小,
                //"}}}{{{"对于这样的输入,如果不判断,那么这边在pop一个空的stack的时候会抛出异常
                if(stack.size() == 0) { 
                    return false;
                }
                char openC = stack.pop();
                char needPartner = getPartner(openC);
                if(needPartner != character) {
                    return false;
                }
            }
        }
        
        return stack.size() == 0; //这边如果比较结束了以后,stack中还存在元素,那么就是不匹配
    }
    
    /**
     * 是否是合法字符,要么是open要么是close
     * @param c
     * @return
     */
    boolean isValid(Character c) {
        return open.contains(c) || close.contains(c);
    }
    
    /**
     * 符号的前半部分"(","{","["
     * @param c
     * @return
     */
    boolean isOpen(Character c) {
        return open.contains(c);
    }
    
    /**
     * 获取与之配对的符号
     * @param c
     * @return
     */
    public char getPartner(Character c) {
        return pairs.get(c);
    }
}
