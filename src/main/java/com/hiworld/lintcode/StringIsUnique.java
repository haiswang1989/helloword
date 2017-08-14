package com.hiworld.lintcode;

/**
 * 问题：判断字符串是否没有重复字符
 * 描述：实现一个算法确定字符串中的字符是否均唯一出现
 * 
 * 样例：
 * 给出"abc"，返回 true
 * 给出"aab"，返回 false
 * 
 * 挑战：
 * 如果不使用额外的存储空间，你的算法该如何改变？
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月14日 上午9:42:50
 */
public class StringIsUnique {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str = "abc_______";
        StringIsUnique stringIsUnique = new StringIsUnique();
        boolean ret = stringIsUnique.isUnique(str);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param str: a string
     * @return: a boolean
     */
    public boolean isUnique(String str) {
        // write your code here
        if(null==str || 0==str.length()) {
            return true;
        }
        
        return doubleForLoop(str);
    }
    
    /**
     * 双层的for循环
     * 
     * @param str
     * @return
     */
    public boolean doubleForLoop(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        for(int i=0; i<length; ++i) {
            for(int j=i+1; j<length; ++j) {
                if(chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
