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
    	
    	return likeDp(candidates, target);
    }
    
    /**
     * 思路：进行递归和回溯
     * 
     * 触发条件：sum==target 或者 sum>target
     * 
     * 
     * @param candidates
     * @param target
     * @return
     */
    public void recusionAndTrace(int[] candidates, List<List<Integer>> rets, ArrayList<Integer> ret, int target, int sum, int currIndex) {
    	//TODO 回溯探测实现方式
    	
    	
    }
    
    /**
     * 快速排序
     * @param candidates
     * @param fromIndex
     * @param endIndex
     */
    public void quickSort(int[] candidates, int fromIndex, int endIndex) {
    	
    	if(fromIndex < endIndex) {
    		int i = fromIndex;
    		int j = fromIndex;
    		int baseIndex = i; //基数索引
    		int base = candidates[baseIndex]; //基数值
    		while(i < j) {
    			while(candidates[j] < base && i < j) {
    				j--;
    			}
    			if(i < j) {
    				candidates[baseIndex] = candidates[j];
    				baseIndex = j;
    			}
    			
    			while(candidates[i] >= base && i < j) {
    				i++;
    			}
    			if(i < j) {
    				candidates[baseIndex] = candidates[i];
    				baseIndex = i;
    			}
    		}
    		
    		candidates[baseIndex] = base;
    		quickSort(candidates, fromIndex, baseIndex-1);
    		quickSort(candidates, baseIndex+1, endIndex);
    	}
    }
    
    /*********************以下是我自己的实现方式***********************/
    /**
     * 类似于动态规划的方式
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
                if(candidates[j] == i) { //
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
                    
                    if(null!=values) {
                    	for (List<Integer> value : values) {
    						List<Integer> list = new ArrayList<>(value);
    						list.add(candidates[j]);
    						ret.add(list);
    					}
                        
                        rets.put(i, ret);
                    }
                    
                    
                } else {
                    //如果值大于target,那么就不需要了
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
				Collections.sort(l);
				rets.add(l);
				hashSet.add(map);
			} 
		}
        
        return rets;
    }
}
