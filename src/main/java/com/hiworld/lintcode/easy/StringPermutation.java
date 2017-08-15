package com.hiworld.lintcode.easy;

/**
 * 问题：字符串置换
 * 描述：给定两个字符串，请设计一个方法来判定其中一个字符串是否为另一个字符串的置换。置换的意思是，通过改变顺序可以使得两个字符串相等。
 * 
 * 样例：
 * "abc" 为 "cba" 的置换。
 * "aabc" 不是 "abcc" 的置换。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月10日 下午2:15:47
 */
public class StringPermutation {

    public static void main(String[] args) {
        String str1 = "aabc";
        String str2 = "abcc";
        
        StringPermutation stringPermutation = new StringPermutation();
        boolean ret = stringPermutation.Permutation(str1, str2);
        System.out.println("ret : " + ret);
    }
    
    /*
     * 
     * @param A: a string
     * @param B: a string
     * @return: a boolean
     */
    public boolean Permutation(String A, String B) {
        // write your code here
        int aLength = -1;
        int bLength = -1;
        if(null==A && null==B) {
            return true;
        } else if(null==A && null!=B) {
            return false;
        } else if(null!=A && null==B) {
            return false;
        } else if((aLength=A.length()) != (bLength=B.length())) {
            return false;
        }
        
        
        return withArray(A, B, aLength, bLength);
    }
    
    /**
     * 
     * @param A
     * @param B
     * @param aLength
     * @param bLength
     * @return
     */
    public boolean withArray(String A, String B, int aLength, int bLength) { 
        int[] aArray = new int[128];
        int[] bArray = new int[128];
        
        for (char c : A.toCharArray()) { //是一个字符串,那么可以映射到ASCII的128个位置的数组
            aArray[c] = aArray[c] + 1;
        }
        
        for (char c : B.toCharArray()) {
            bArray[c] = bArray[c] + 1;
        }
        
        for(int i=0; i<128; ++i) { //比较数组各个位置相等
            if(aArray[i] != bArray[i]) {
                return false;
            }
        }
        
        return true;
    }
}
