package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：数字组合 
 * 描述：给出一组候选数字(C)和目标数字(T),找到C中所有的组合，使找出的数字和为T。C中的数字可以无限制重复被选取。
 * 
 * 样例：
 * 给出候选数组[2,3,6,7]和目标数字7
 * 返回[[7],[2,2,3]]
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月23日 下午1:47:17
 */
public class ArrayCombinationSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        
        int[] dp = new int[target + 1];
        dp[0] = 0;
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        
        int length = candidates.length;
        
        List<List<Integer>> rets = new ArrayList<>();
        
        for(int i=1; i<=target; i++) {
            for(int j=0; i<length; i++) {
                if(candidates[j] == candidates[i]) { //
                    List<Integer> ret = new ArrayList<>();
                    
                } else if(candidates[j] < target) {
                    
                } else {
                    //
                }
            }
        }
        
        
        return null;
    }
}
