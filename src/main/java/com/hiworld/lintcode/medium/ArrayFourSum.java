package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 问题：四数之和
 * 描述：给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
 * 
 * 注意：
 * 四元组(a, b, c, d)中，需要满足a <= b <= c <= d
 * 答案中不可以包含重复的四元组。
 * 
 * 样例：
 * 例如，对于给定的整数数组S=[1, 0, -1, 0, -2, 2] 和 target=0. 满足要求的四元组集合为：
 * (-1, 0, 0, 1)
 * (-2, -1, 1, 2)
 * (-2, 0, 0, 2)
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月15日 下午5:05:42
 */
public class ArrayFourSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] numbers = {1, 0, -1, 0, -2, 2};
        ArrayFourSum arrayFourSum = new ArrayFourSum();
        ArrayList<ArrayList<Integer>> rets = arrayFourSum.fourSum(numbers, 0);
        
        for (ArrayList<Integer> arrayList : rets) {
            System.out.println(Arrays.toString(arrayList.toArray()));
        }
    }
    
    /**
     * 
     * @param numbers : Give an array numbersbers of n integer
     * @param target : you need to find four elements that's sum of target
     * @return : Find all unique quadruplets in the array which gives the sum of
     *           zero.
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        int length = numbers.length;
        sort(numbers, 0, length-1);
        
        ArrayList<ArrayList<Integer>> rets = new ArrayList<>();
        for(int i=0; i<length; i++) {
            for(int j=i+1; j<length; j++) {
                int beginIndex = j+1;
                int endIndex = length-1;
                
                int twoSum = numbers[i] + numbers[j];
                int needTwoSum = target - twoSum;
                
                for( ; beginIndex<endIndex; ) {
                    int beginVal = numbers[beginIndex];
                    int endVal = numbers[endIndex];
                    int targetTwoSum = beginVal + endVal;
                    
                    if(targetTwoSum == needTwoSum) { //找到了
                        ArrayList<Integer> retList = new ArrayList<>();
                        retList.add(numbers[i]);
                        retList.add(numbers[j]);
                        retList.add(beginVal);
                        retList.add(endVal);
                        if(!rets.contains(retList)) {
                            rets.add(retList);
                        }
                        beginIndex++;
                        endIndex--;
                    } else if(targetTwoSum < needTwoSum) {
                        beginIndex++;
                    } else {
                        endIndex--;
                    }
                }
            }
        }
        return rets;
    }
    
    /**
     * 先进行快排
     * @param numbers
     */
    public void sort(int[] numbers, int fromIndex, int endIndex) {
        if(fromIndex < endIndex) {
            int i = fromIndex;
            int j = endIndex;
            int base = numbers[i]; //基准数
            int fillIndex = i; //需要填充的index
            while(i < j) {
                while(j > i && numbers[j] >= base) { //先从后往前,注意这边或者下面总归要有一个地方处理等于的情况,不然会出现死循环
                    j--;
                }
                
                if(j > i) {
                    numbers[fillIndex] = numbers[j];
                    fillIndex = j;
                }
                
                while(i < j && numbers[i] < base) { //再从前往后
                    i++;
                }
                
                if(i < j) {
                    numbers[fillIndex] = numbers[i];
                    fillIndex = i;
                }
            }
            
            numbers[fillIndex] = base;
            sort(numbers, fromIndex, fillIndex-1);
            sort(numbers, fillIndex+1, endIndex);
        }
    }
}
