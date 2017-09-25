package com.hiworld.lintcode.medium;

/**
 * 问题：交叉字符串
 * 描述：给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
 * 
 * 样例：
 * 比如 s1 = "aabcc" s2 = "dbbca"
 *  - 当 s3 = "aadbbcbcac"，返回  true.
 *  - 当 s3 = "aadbbbaccc"， 返回 false.
 * 
 * 挑战：
 * 要求时间复杂度为O(n^2)或者更好
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月12日 上午10:24:46
 */
public class StringIsInterleave {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";
        
        StringIsInterleave stringIsInterleave = new StringIsInterleave();
        boolean ret = stringIsInterleave.isInterleave(s1, s2, s3);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 
     * 
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        int s1Length = s1.length();
        int s2Length = s2.length();
        int s3Length = s3.length();
        
        if(s1Length + s2Length != s3Length) {
            return false;
        }
        
        return dp(s1, s2, s3);
    }
    
    /**
     * 动态规划
     * dp[i,j] 表示 s1的前i个字符和s2的前j个字符 是否可以构成 s3的前 i + j个字符
     * 状态转换方程：
     * dp[i,j] = (dp[i-1][j] && s1[i-1]==s3[t-1]) || (dp[i][j-1] && s2[j-1]==s3[t-1]);
     * 
     * @param s1
     * @param s2
     * @param s3
     */
    public boolean dp(String s1, String s2, String s3) {
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for(int i=1; i<=s1.length(); i++) { //单s1和s3的匹配情况
            dp[i][0] = dp[i-1][0] && (s1.charAt(i-1)==s3.charAt(i-1));
        }
        
        for(int j=1; j<=s2.length(); j++) { //单s2和s3的匹配情况
            dp[0][j] = dp[0][j-1] && (s2.charAt(j-1)==s3.charAt(j-1));
        }
        
        for(int i=1; i<=s1.length(); i++) { //是s1和s2一起与s3的匹配情况
            for(int j=1; j<=s2.length(); j++) {
                int t = i+j;  
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(t-1))
                        || (dp[i][j-1] && s2.charAt(j-1)==s3.charAt(t-1));
            }
        }
        
        return dp[s1.length()][s2.length()];
    }
}
