package com.hiworld.lintcode.medium;

/**
 * 问题：编辑距离
 * 描述：给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
 * 你总共三种操作方法：
 *  插入一个字符
 *  删除一个字符
 *  替换一个字符
 *  
 * 给出 work1="mart" 和 work2="karma"
 * 返回 3
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月1日 上午10:57:50
 */
public class EditDistanceI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String word1 = "mart";
        String word2 = "karma";
        
        EditDistanceI editDistanceI = new EditDistanceI();
        int ret = editDistanceI.minDistance(word1, word2);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 动态规划
     * 
     * 状态：dp[i][j] 表示第一个字符串从0到i 到 第二个字符串的0到j 需要进行多少次变换
     * 
     * 状态转移方程：dp[i][j] = min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1
     * 
     * dp[i][j]    dp[i-1][j-1] word1或者word2换一个
     *             dp[i-1][j]   word1加一个字符(反过来就是减一个)
     *             dp[i][j-1]   word2加一个字符(反过来就是减一个)
     * 
     * 如上,那个小就从哪一个变换过来          
     *   
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        
        int word1Length = word1.length();
        int word2Length = word2.length();
        
        int[][] dp = new int[word1Length+1][word2Length+1];
        
        for(int i=0; i<=word1Length; ++i) {
            dp[i][0] = i; //word1从0-i 变成 ""需要转换的次数 , 其实就是删除所有字符串
        }
        
        for(int j=0; j<=word2Length; ++j) {
            dp[0][j] = j; //word2从0-j 变成""需要转换的次数 , 其实就是删除所有字符串
        }
        
        for(int i=1; i<=word1Length; ++i) {
            for(int j=1; j<=word2Length; ++j) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1]; //两个字符相等 
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }
        
        return dp[word1Length][word2Length];
    }
}
