package com.hiworld.lintcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 问题：两数之和
 * 描述：给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
 * 
 * 注意事项：你可以假设只有一组答案。
 * 样例：给出 numbers = [2, 7, 11, 15], target = 9, 返回 [1, 2].
 * 
 * 挑战：
 * O(n) Space, O(nlogn) Time
 * O(n) Space, O(n) Time
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月11日 下午8:22:56
 */
public class ArrayTwoSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        ArrayTwoSum arrayTwoSum = new ArrayTwoSum();
        
        int[] ret = arrayTwoSum.twoSum(numbers, target);
        System.out.println(Arrays.toString(ret));
    }
    
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] ret = new int[2];
        int length = numbers.length;
        Map<Integer, Integer> valIndexMap = new HashMap<>();
        for(int i=0; i<length; ++i) {
            int val = numbers[i];
            int needVal = target - val;
            if(valIndexMap.containsKey(needVal)) {
                int index = valIndexMap.get(needVal);
                ret[0] = index;
                ret[1] = i+1;
                break;
            } else {
                valIndexMap.put(val, i+1);
            }
        }
        
        return ret;
    }

}
