package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 问题：三数之和 
 * 描述：给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
 * 
 * 注意事项：
 * 在三元组(a, b, c)，要求a <= b <= c。
 * 结果不能包含重复的三元组。
 * 
 * 样例：
 * 如S = {-1 0 1 2 -1 -4}, 你需要返回的三元组集合的是：
 * (-1, 0, 1)
 * (-1, -1, 2)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月15日 下午2:51:08
 */
public class ArrayThreeSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] numbers = {2,7,11,15};
        ArrayThreeSum arrayThreeSum = new ArrayThreeSum();
        ArrayList<ArrayList<Integer>> rets = arrayThreeSum.threeSum(numbers);
        
        for (ArrayList<Integer> arrayList : rets) {
            System.out.println(Arrays.toString(arrayList.toArray()));
        }
    }
    
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        // write your code here
        if(null==numbers || numbers.length < 3) {
            return null;
        }
        
        return solution_2(numbers);
    }
    
    
    /**********************解法一************************/
    /**
     * 时间复杂度比较高
     * 先确定两个数,然后第三个数使用Hash的方式获取
     * O(n*longn) + O(n) + O(n*n)
     * @param numbers
     * @return
     */
    public ArrayList<ArrayList<Integer>> solution_1(int[] numbers) {
        sort(numbers, 0, numbers.length-1); //先做个排序,O(n*logn) 
        int length = numbers.length;
        
        HashMap<Integer, Integer> valueIndex = new HashMap<>();
        for(int i=0; i<length; i++) { //O(n)
            valueIndex.put(numbers[i], i);
        }
        
        ArrayList<ArrayList<Integer>> rets = new ArrayList<>();
        
        //O(n*n)
        for(int i=0; i<length; i++) { //双层嵌套循环,确定第一和第二个数 
            for(int j=i+1; j<length; j++) {
                int twoSum = numbers[i] + numbers[j];
                int needNumber = 0 - twoSum;
                //第三个数使用Hash来确定
                if(valueIndex.containsKey(needNumber)) { 
                    int index = valueIndex.get(needNumber);
                    if(index > j) {
                        ArrayList<Integer> ret = new ArrayList<>();
                        ret.add(numbers[i]);
                        ret.add(numbers[j]);
                        ret.add(needNumber);
                        if(!rets.contains(ret)) {
                            rets.add(ret);
                        }
                        
                    }
                }
            }
        }
        
        return rets;
    }
    
    /******************************解法二*****************************/
    /**
     * 先确定两个数,然后第三个数使用二分查找的方式
     * O(n*longn) + O(n*n*logn)
     * @param numbers
     * @return
     */
    public ArrayList<ArrayList<Integer>> solution_2(int[] numbers) {
        
        sort(numbers, 0, numbers.length-1); //先做个排序,O(n*logn) 
        int length = numbers.length;
        
        ArrayList<ArrayList<Integer>> rets = new ArrayList<>();
        
        for(int i=0; i<length; i++) { //双层嵌套循环,确定第一和第二个数 
            for(int j=i+1; j<length; j++) {
                int twoSum = numbers[i] + numbers[j];
                int needNumber = 0 - twoSum;
                if(j+1 > length-1) { //查找的开始索引大于结束索引,那么就不要进行查找了
                    continue;
                }
                //这边第三个数也可以使用二分查找来进行,这样就避免上面的那个O(n)
                int ret = binarySearch(needNumber, numbers, j+1, length-1);
                if(-1 != ret) {
                    ArrayList<Integer> retList = new ArrayList<>();
                    retList.add(numbers[i]);
                    retList.add(numbers[j]);
                    retList.add(needNumber);
                    if(!rets.contains(retList)) {
                        rets.add(retList);
                    }
                }
            }
        }
        
        return rets;
    }
    
    /************************解法三**********************/
    /**
     * 确定一个数,然后让第二个数和第三个数,进行变化
     * 
     * 第二个数从左往右,第三个数从右往左
     * 
     * O(n*logn)  + O(n*n)
     * 
     * @param numbers
     * @return
     */
    public ArrayList<ArrayList<Integer>> solution_3(int[] numbers) {
        sort(numbers, 0, numbers.length-1); //先做个排序,O(n*logn) 
        int length = numbers.length;
        
        ArrayList<ArrayList<Integer>> rets = new ArrayList<>();
        
        for(int i=0; i<length; i++) {
            int number = numbers[i];
            int needTwoSum = 0 - number; //还需要的两个数的和
            
            int fromIndex = i+1;
            int endIndex = length-1;
            
            for(int m=fromIndex,n=endIndex; m<n; ) {
                int mVal = numbers[m];
                int nVal = numbers[n];
                if(mVal+nVal == needTwoSum) { //当两数之和==需要的值,那么找到了
                    ArrayList<Integer> retList = new ArrayList<>();
                    retList.add(numbers[i]);
                    retList.add(mVal);
                    retList.add(nVal);
                    if(!rets.contains(retList)) {
                        rets.add(retList);
                    }
                    m++;
                    n--;
                } else if(mVal+nVal < needTwoSum) { //如果小于,左边的数需要往右移
                    m++;
                } else { //如果大于,右边的数往左移
                    n--;
                }
            }
        }
        
        return rets;
    }
    
    
    /**
     * 二分查找
     * @param target
     * @param numbers
     * @param fromIndex
     * @param endIndex
     * @return
     */
    public int binarySearch(int target, int[] numbers, int fromIndex, int endIndex) {
       if(fromIndex == endIndex || fromIndex+1 == endIndex) { //开始index和结束index相等或者相差1,那么就可以直接比较进行退出了
            if(numbers[fromIndex] == target) {
                return fromIndex;
            } else if(numbers[endIndex] == target) {
                return endIndex;
            } else {
                return -1;
            }
        } 
        
        int middleIndex = (endIndex + fromIndex) / 2;
        int middleValue = numbers[middleIndex];
        if(middleValue < target) {
            fromIndex = middleIndex;
        } else if(middleValue == target) {
            return middleIndex;
        } else {
            endIndex = middleIndex;
        }
        
        return binarySearch(target, numbers, fromIndex, endIndex); //递归调用
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
