package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 问题：数字组合 II 
 * 描述：给出一组候选数字(C)和目标数字(T),找出C中所有的组合，使组合中数字的和为T。C中每个数字在每个组合中只能使用一次。
 * 
 * 样例：
 * 给出一个例子，候选数字集合为[10,1,6,7,2,1,5] 和目标数字 8 
 * [[1,7],[1,2,5],[2,6],[1,1,6]]
 * 
 * 
 * @author Administrator
 *
 */
public class ArrayCombinationSumII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    int[] num = {2,3,6,7};
	    int target = 7;
	    ArrayCombinationSumII arrayCombinationSumII = new ArrayCombinationSumII();
	    List<List<Integer>> rets = arrayCombinationSumII.combinationSum2(num, target);
	    for (List<Integer> ret : rets) {
            System.out.println(Arrays.toString(ret.toArray()));
        }
	}
	
	/**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        int length = -1;
        if(null==num || 0==(length=num.length)) {
            return new ArrayList<>();
        }
        
        quickSort(num, 0, length-1);
        
        List<List<Integer>> solutions = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();
        recusionTrace(num, 0, length, target, 0, solutions, solution);
        
        return null==solutions?null : removeDuplicate(solutions);
    }
    
    /**
     * 递归探测
     * @param num 数组
     * @param fromIndex 数组可探测的开始索引位置(避免重复)
     * @param numLength 数组的大小
     * @param target 目标值(sum)
     * @param currSum 当前值(sum)
     * @param solutions 所有的解决方案
     * @param solution 存储解决方案的临时集合
     * @return
     */
    public void recusionTrace(int[] num, int fromIndex, int numLength, int target, int currSum, List<List<Integer>> solutions, ArrayList<Integer> solution) {
        if(currSum == target) {
            solutions.add(new ArrayList<>(solution));
            return;
        } else if(currSum > target) {
            return;
        }
        
        for(int i=fromIndex; i<numLength; ++i) { //注意这边遍历的开始位置,不再是"数字组合 I"中的从0开始,由于不变一个元素不能使用多次,所以只能从下一个元素开始
            solution.add(num[i]);
            currSum += num[i];
            recusionTrace(num, i+1, numLength, target, currSum, solutions, solution); //这边的fromIndex要从当前位置的下一个位置开始
            int remove = solution.remove(solution.size()-1);
            currSum -= remove;
        }
    }
    
    
    /**
     * 快速排序
     * @param num
     * @param beginIndex
     * @param endIndex
     */
    public void quickSort(int[] num, int beginIndex, int endIndex) {
        if(beginIndex < endIndex) {
            int i = beginIndex;
            int j = endIndex;
            
            int base = num[i];
            int baseIndex = i;
            
            while(i < j) {
                while(num[j]>base && i<j) {
                    j--;
                }
                if(i<j) {
                    num[baseIndex] = num[j];
                    baseIndex = j;
                }
                
                while(num[i]<=base && i<j) {
                    i++;
                }
                if(i<j) {
                    num[baseIndex] = num[i];
                    baseIndex = i;
                }
            }
            
            num[baseIndex] = base;
            quickSort(num, beginIndex, baseIndex-1);
            quickSort(num, baseIndex+1, endIndex);
        }
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
