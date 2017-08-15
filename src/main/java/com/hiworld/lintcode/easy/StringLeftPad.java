package com.hiworld.lintcode.easy;

/**
 * 问题：字符串的左填充
 * 
 * 样例：
 * leftpad("foo", 5) ---> "  foo"
 * leftpad("foobar", 6) ---> "foobar"
 * leftpad("1", 2, "0") ---> "01"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月26日 下午4:28:05
 */
public class StringLeftPad {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("---" + leftPad("1", 2, '0') + "---");
    }
    
    static public String leftPad(String originalStr, int size) {
        // Write your code here
        return leftPad(originalStr, size, ' ');
    }

    /**
     * 
     * @param originalStr the string we want to append to
     * @param size the target length of the string
     * @param padChar the character to pad to the left side of the string
     * @return a string
     */
    static public String leftPad(String originalStr, int size, char padChar) { 
        // Write your code here
        int length = originalStr.length();
        if(length >= size) {
            return originalStr;
        } else {
            char[] ret = new char[size];
            System.arraycopy(originalStr.toCharArray(), 0, ret, size - length, length);
            for(int i=0; i<size - length; ++i) {
                ret[i] = padChar;
            }
            
            return new String(ret);
        }
    }

}
