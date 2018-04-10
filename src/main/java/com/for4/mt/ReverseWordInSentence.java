package com.for4.mt;

import org.apache.commons.lang.StringUtils;

/**
 * 翻转句子中的单词
 * 如:
 * "ABC DEF"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月4日 下午5:59:28
 */
public class ReverseWordInSentence {

    public static void main(String[] args) {
        String sentence = "ABC DEF GHI JKL MNO PQR STU";
        ReverseWordInSentence reverseWordInSentence = new ReverseWordInSentence();
        String newSentence = reverseWordInSentence.reverseWordInSentence(sentence);
        System.out.println("new : " + newSentence);
    }
    
    /**
     * 反转句子中的单词
     * @param sentence
     * @return
     */
    public String reverseWordInSentence(String sentence) {
        
        String[] words = sentence.split(" ");
        if(words.length <= 1) {
            return sentence;
        }
        
        int beginIndex = 0;
        int endIndex = words.length - 1;
        
        for(int i=beginIndex,j=endIndex; i < j; i++,j--) {
            swap(words, i, j);
        }
        
        return StringUtils.join(words, " ");
    }
    
    /**
     * 交换数组单词
     * @param words
     * @param index1
     * @param index2
     */
    private void swap(String[] words, int index1, int index2) {
        String tmp = words[index1];
        words[index1] = words[index2];
        words[index2] = tmp;
    }
}
