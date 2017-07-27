package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 问题：最长单词 
 * 描述：给一个词典，找出其中所有最长的单词
 * 
 * 样例：
 * {"dog","google","facebook","internationalization","blabla"}中, 最长的单词集合为 ["internationalization"]
 * 
 * {"like","love","hate","yes"}中，最长的单词集合为 ["like", "love", "hate"]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月27日 上午9:51:26
 */
public class ArrayLongestWords {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        String[] dictionary = {"dog",
                "google",
                "facebook",
                "internationalization",
                "blabla"};
        
        ArrayLongestWords arrayLongestWords = new ArrayLongestWords();
        System.out.println(Arrays.toString(arrayLongestWords.longestWords(dictionary).toArray()));
    }
    
    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    ArrayList<String> longestWords(String[] dictionary) {
        // write your code here
        if(null==dictionary || 0==dictionary.length) {
            return new ArrayList<>();
        }
        
        ArrayList<String> ret = new ArrayList<>();
        int maxLength = 0; //记录最长单词的长度
        for (String string : dictionary) {
            int wordLength = string.length(); //当前单纯的长度
            if(maxLength == wordLength) {
                ret.add(string);
            } else if(maxLength < wordLength) { //如果当前的单词的长度比现在的大
                ret.clear(); //清空单词列表
                maxLength = wordLength; //重置单词长度
                ret.add(string); //放入列表
            } else {
                //do nothing
            }
        }
        
        return ret;
    }

}
