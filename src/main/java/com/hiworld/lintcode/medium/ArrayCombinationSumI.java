package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
public class ArrayCombinationSumI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	int[] candidates = {2,3,6,7};
    	int target = 7;
    	
    	ArrayCombinationSumI arrayCombinationSum = new ArrayCombinationSumI();
    	List<List<Integer>> rets = arrayCombinationSum.combinationSum(candidates, target);
    	for (List<Integer> list : rets) {
    		System.out.println(Arrays.toString(list.toArray()));
		}
    	
    }
    
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	if(null==candidates || 0==candidates.length) {
    		return new ArrayList<>();
    	}
    	
    	/***************回溯法解决**************/
    	ArrayList<List<Integer>> allSolutions = new ArrayList<>();
    	ArrayList<Integer> singleSolution = new ArrayList<>();
    	
    	recusionAndTrace(candidates, target, allSolutions, singleSolution, 0);
    	return removeDuplicate(allSolutions);
    	/*************动态规划(递推)************/
//    	return likeDp(candidates, target);
    }
    
    /**
     * 回溯探测实现方式
     * 思路：进行递归和回溯
     * 触发条件：sum==target 或者 sum>target
     * @param candidates
     * @param target
     * @return
     */
    public void recusionAndTrace(int[] candidates, int target, List<List<Integer>> allSolutions, ArrayList<Integer> singleSolution, int sum) {
        if(sum == target) { //这边相等了就表示找到了一个满足条件的组合
            ArrayList<Integer> onoSolution = new ArrayList<>(singleSolution);
            allSolutions.add(onoSolution);
            return;
        } else if(sum > target) { //如果大于了,就要进行"回溯",然后尝试其他方式
            return;
        }
        
        int length  = candidates.length;
        for(int i=0; i<length; i++) {
            singleSolution.add(candidates[i]);
            sum += candidates[i];
            recusionAndTrace(candidates, target, allSolutions, singleSolution, sum); //递归往下探测
            Integer lastInteger = singleSolution.remove(singleSolution.size()-1); //遇到(sum<target)的条件的时候进行回溯
            sum -= lastInteger;
        }
    }
    
    /*********************以下是我自己的实现方式***********************/
    /**
     * 类似于动态规划(递推)方式
     * 
     * dp[i] 表示 target为i的组合情况
     * 
     * dp[i] = foreach(dp[i - candidates[j]]) + candidates[j]
     * 
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> likeDp(int[] candidates, int target) {
        // write your code here
        int[] dp = new int[target + 1];
        dp[0] = 0;
        
        int length = candidates.length;
        Map<Integer, List<List<Integer>>> rets = new HashMap<>(); //存储1-target中每个数的组合
        
        for(int i=1; i<=target; i++) {
            for(int j=0; j<length; j++) {
                if(candidates[j] == i) { //直接等于,那么单个数就是一个解决方案
                    List<Integer> list = new ArrayList<>();
                    list.add(candidates[j]);
                    List<List<Integer>> ret = null;
                    if(rets.containsKey(i)) {
                    	ret = rets.get(i);
                    } else {
                    	ret = new ArrayList<>();
                    }
                    ret.add(list);
                    rets.put(i, ret);
                } else if(candidates[j] < i) {
                    int key = i - candidates[j];
                    List<List<Integer>> values = rets.get(key);
                    List<List<Integer>> ret = null;
                    if(rets.containsKey(i)) {
                    	ret = rets.get(i); 
                    } else {
                    	ret = new ArrayList<>();
                    }
                    
                    if(null!=values) { //dp[i - candidates[j]]
                    	for (List<Integer> value : values) {
    						List<Integer> list = new ArrayList<>(value);
    						list.add(candidates[j]);
    						ret.add(list);
    					}
                        rets.put(i, ret);
                    }
                } else {
                    //如果值大于target,那么就不满足条件
                }
            }
        }
        
        List<List<Integer>> ret = rets.get(target);
        return null==ret?null:removeDuplicate(ret);
    }
    
    /**
     * 去除重复的
     * @param list
     * @return
     */
    public List<List<Integer>> removeDuplicate(List<List<Integer>> list) {
        HashSet<Map<Integer, Integer>> hashSet = new HashSet<>();
        List<List<Integer>> rets = new ArrayList<>();
        for (List<Integer> l : list) {
        	HashMap<Integer, Integer> map = new HashMap<>();
			for (Integer i : l) {
				if(map.containsKey(i)) {
					int count = map.get(i);
					map.put(i, count+1);
				} else {
					map.put(i, 1);
				}
			}
			
			if(!hashSet.contains(map)) {
				rets.add(l);
				hashSet.add(map);
			} 
		}
        
        return rets;
    }
}
