package com.for4.mt;

/**
 * 反转句子中的字符
 * 如:
 * "ABCDEFGH IJKLMN  OPQRST   UVWXYZ"
 * "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月8日 上午9:49:07
 */
public class ReverseCharsInSentence {

    public static void main(String[] args) {
        
        String str = "ABCDEFGH IJKLMN  OPQRST   UVWXYZ";
        ReverseCharsInSentence reverseCharsInSentence = new ReverseCharsInSentence();
        System.out.println(reverseCharsInSentence.reverseCharsInSentence(str));
        
        str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println(reverseCharsInSentence.reverseCharsInSentence(str));
    }
    
    /**
     * 
     * @param sentence
     * @return
     */
    public String reverseCharsInSentence(String sentence) {
        int sentenceLength = sentence.length();
        if(sentenceLength <= 1) {
            return sentence;
        }
        
        char[] sentenceChars = sentence.toCharArray();
        for(int i=0,j=sentenceLength-1; i<j; i++,j--) {
            swap(sentenceChars, i, j);
        }
        
        return new String(sentenceChars);
    }
    
    /**
     * 
     * @param sentenceChars
     * @param index1
     * @param index2
     */
    public void swap(char[] sentenceChars, int index1, int index2) {
        char tmp = sentenceChars[index1];
        sentenceChars[index1] = sentenceChars[index2];
        sentenceChars[index2] = tmp;
    }
}
