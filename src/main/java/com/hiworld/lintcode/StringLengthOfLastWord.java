package com.hiworld.lintcode;

/**
 * 问题：最后一个单词的长度
 * 描述：给定一个字符串， 包含大小写字母、空格' '，请返回其最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
 * 
 * 样例：
 * 给定 s = "Hello World"，返回 5
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月26日 下午4:40:23
 */
public class StringLengthOfLastWord {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        String s = "H";
        
        StringLengthOfLastWord stringLengthOfLastWord = new StringLengthOfLastWord();
        System.out.println(stringLengthOfLastWord.lengthOfLastWord(s));
    }
    
    /**
     * @param s A string
     * @return the length of last word
     */
    public int lengthOfLastWord(String s) {
        // Write your code here
        if(s == null) {
            return 0;
        }
        
        String sAfterTrim = s.trim();
        if(null == sAfterTrim || 0==sAfterTrim.length()) {
            return 0;
        }
        
        char[] cArray = sAfterTrim.toCharArray();
        int length = cArray.length;
        
        int lastWordlength = 0;
        for(int i=length-1; i>=0; --i) {
            if(cArray[i] != ' ') {
                lastWordlength++;
            } else {
                break;
            }
        }
        
        
        return lastWordlength;
    }
}
