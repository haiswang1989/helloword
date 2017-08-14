package com.hiworld.lintcode;

/**
 * 问题：字符串查找
 * 描述：对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
 * 
 * 样例：
 * 如果 source = "source" 和 target = "target"，返回 -1。
 * 如果 source = "abcdabcdefg" 和 target = "bcd"，返回 1
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月14日 上午11:39:34
 */
public class StringFindString {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StringFindString stringFindString = new StringFindString();
        int ret = stringFindString.strStr("abcdabcdefg", "g");
        System.out.println("ret : " + ret);
    }
    
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here
        if(null==source || null==target) {
            return -1;
        }
        
        int sourceLength = source.length();
        int targetLength = target.length();
        if(0==targetLength) {
            return 0;
        }
        
        if(sourceLength < targetLength) {
            return -1;
        }
        
        return mySolutionWithOutRecusion(source, target);
        
        //return mySolutionWithRecusion(source, target, 0);
    }
    
    /**
     * 非递归的方式
     * 
     * @param source
     * @param target
     * @return
     */
    public int mySolutionWithOutRecusion(String source, String target) {
        int sourceLength = source.length();
        int targetLength = target.length();
        for(int i=0; i<sourceLength;) {
            int sourceIndex = i;
            if(sourceLength-i < targetLength) {
                return -1;
            }
            
            int j=0;
            for(; j<targetLength;j++) {
                if(source.charAt(sourceIndex++) != target.charAt(j)) {
                    break;
                }
            }
            
            if(j == targetLength) {
                return i;
            } else {
                i++;
            }
        }
        
        return -1;
    }
    
    
    /**
     * 
     * @param source
     * @param target
     * @return
     */
    public int mySolutionWithRecusion(String source, String target, int sourceStartIndex) {
        int targetLength = target.length();
        int sourceLength = source.length();
        //如果从开始比较的index开始,source的长度已经比target的长度下了
        //那么不需要比较,直接返回-1没找到
        if(sourceLength - sourceStartIndex < targetLength) { 
            return -1;
        }
        
        int sourceIndex = sourceStartIndex;
        int i = 0;
        for(; i<targetLength; i++) {
            if(target.charAt(i) != source.charAt(sourceIndex++)) { //不相等直接break出去
                break;
            } 
        }
        
        //如果target字符全部被比完,那么表示target在source中已经被找到
        if(i == targetLength) { 
            return sourceStartIndex;
        } else {
            //否则从下一个字符开始匹配
            return mySolutionWithRecusion(source, target, sourceStartIndex+1);
        }
    }
    
    /**
     * 
     * @param source
     * @param target
     * @return
     */
    public int KMP(String source, String target) {
        //TODO
        //http://www.cnblogs.com/c-cloud/p/3224788.html
        return -1;
    }
}
