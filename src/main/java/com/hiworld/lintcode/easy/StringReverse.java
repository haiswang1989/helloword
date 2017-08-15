package com.hiworld.lintcode.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题：翻转字符串 
 * 描述：给定一个字符串，逐个翻转字符串中的每个单词。
 * 
 * 说明：
 * 1：单词的构成：无空格字母构成一个单词
 * 2：输入字符串是否包括前导或者尾随空格？可以包括，但是反转后的字符不能包括
 * 3：如何处理两个单词间的多个空格？在反转字符串中间空格减少到只含一个
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月8日 下午6:12:31
 */
public class StringReverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
	    StringReverse reverse = new StringReverse();
	    String str = "   I    AM   WANG  HAI   SHENG    ";
	    String ret = reverse.reverseWords(str);
	    System.out.println("ret : ---" + ret + "----");
	}
	
	/**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        // write your code
        List<String> words = new LinkedList<>();
        char[] chars = s.toCharArray();
        int length = chars.length;
        int wordBegin = -1;
        int wordEnd = -1;
        
        int i=length-1;
        for(;i>=0;--i) { //从后往前遍历,获取一个一个的单词
            char c = chars[i];
            if(c == ' ') { //如果发现了空格
                if(wordEnd == -1) { //连续空格就不管了
                    continue; 
                } else { //如果不是,那么这个时候,走出了一个单词
                    wordBegin = i+1;
                    words.add(getWord(s, wordBegin, wordEnd + 1));
                    wordBegin = -1;
                    wordEnd = -1;
                }
            } else {
                if(wordEnd == -1) { //如果是第一个非空字符,那么这个就是单词的开始
                    wordEnd = i;
                    wordBegin = i;
                } else {
                    wordBegin = i;
                }
            }
        }
        
        if(wordEnd!=-1) {
            words.add(getWord(s, wordBegin, wordEnd + 1));
        }
        
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String word : words) { //通过所有的word,构造返回值
            if(isFirst) {
                sb.append(word); 
                isFirst = false;
            } else {
                sb.append(" ").append(word);
            }
        }
        
    	return sb.toString();
    }
    
    /**
     * 获取单词
     * @param s
     * @param beginIndex
     * @param endIndex
     * @return
     */
    String getWord(String s, int beginIndex, int endIndex) {
        return s.substring(beginIndex, endIndex);
    }
}
