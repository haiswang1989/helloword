package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题：组合
 * 描述：组给出两个整数n和k，返回从1......n中选出的k个数的组合。
 * 
 * 样例：
 * 例如 n = 4 且 k = 2
 * [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月24日 下午1:50:19
 */
public class ArrayCombine {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayCombine arrayCombine = new ArrayCombine();
        List<List<Integer>> sulutions = arrayCombine.combine(4, 2);
        for (List<Integer> sulution : sulutions) {
            System.out.println(Arrays.toString(sulution.toArray()));
        }
    }
    
    /*
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        List<List<Integer>> sulutions = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();
        recusionTrace(1, n, k, 0, sulutions, solution);
        return sulutions;
    }
    
    /**
     * 递归回溯
     * @param fromValue 开始值
     * @param endValue 结束值
     * @param count 元素个数
     * @param currCount 当前元素个数
     * @param sulutions 所以的组合结果
     * @param solution 单个结果
     */
    public void recusionTrace(int fromValue, int endValue, int count, int currCount, List<List<Integer>> sulutions, ArrayList<Integer> solution) {
        if(count == currCount) {
            sulutions.add(new ArrayList<>(solution));
            return;
        } else if(fromValue > endValue) {
            return;
        }
        
        for(int i=fromValue; i<=endValue; i++) {
            solution.add(i);
            currCount++;
            recusionTrace(i+1, endValue, count, currCount, sulutions, solution);
            solution.remove(solution.size()-1);
            currCount--;
        }
    }
}
