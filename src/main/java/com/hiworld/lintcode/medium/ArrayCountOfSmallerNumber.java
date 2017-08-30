package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题：统计比给定整数小的数的个数
 * 描述：给定一个整数数组 （下标由 0 到 n-1，其中 n 表示数组的规模，数值范围由 0 到 10000），以及一个 查询列表。对于每一个查询，将会给你一个整数，请你返回该数组中小于给定整数的元素的数量
 * 
 * 样例：
 * 对于数组 [1,2,7,8,5] ，查询 [1,8,5]，返回 [0,4,2]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月29日 下午2:30:36
 */
public class ArrayCountOfSmallerNumber {

    public static void main(String[] args) {
        int[] A = {32,67}; 
        int[] queries = {65,50}; 
        ArrayCountOfSmallerNumber arrayCountOfSmallerNumber = new ArrayCountOfSmallerNumber();
        List<Integer> rets = arrayCountOfSmallerNumber.countOfSmallerNumber(A, queries);
        System.out.println(Arrays.toString(rets.toArray()));
    }
    
    /*
     * @param A: An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        if(null==queries || 0==queries.length) {
            return new ArrayList<>();
        }
        
        if(null==A || 0==A.length) {
            List<Integer> rets = new ArrayList<>();
            int length = queries.length;
            while(length--!=0) {
                rets.add(0);
            }
            return rets;
        }
        
        return bySortAndMap(A, queries);
    }
    
    /*****************************实际想考察的方式(构造线段树)*****************************/
    //这种方式不推荐,比较复杂
    public void bySegmentTree() {
        
    }
    
    /*************************方法二:用一个快排+二分查找(这个方式有个缺点,就是对于每个元素就需要进行二分查找)*************************/
    public List<Integer> byBinarySearch(int[] A, int[] queries) {
        return null;
    }
    
    /*************************方法一:用一个快排+2个Map来完成(该方式被AC了)*************************/
    public List<Integer> bySortAndMap(int[] A, int[] queries) {
        // write your code here
        quickSort(A, 0, A.length-1); //先做一个快速排序
        
        int min = A[0]; //最小值
        int max = A[A.length-1]; //最大值
        
        int count = 0;
        //这边有个比较坑爹的地方
        //就是结果给出一个KEY=V,要的结果是小于V的元素的个数,是不包含V的元素的个数的
        //所以这边需要知道每个元素的个数
        Map<Integer, Integer> number2Count = new HashMap<>(); //按照A中的元素,构造小于或等于每个元素的个数的MAP
        Map<Integer, Integer> numCount = new HashMap<>(); //按照A中的元素,构造每个元素的个数的MAP
        for (int a : A) {
            number2Count.put(a, ++count);
            if(numCount.containsKey(a)) {
                numCount.put(a, numCount.get(a) + 1);
            } else {
                numCount.put(a, 1);
            }
        }
        
        List<Integer> rets = new ArrayList<>();
        for (int i : queries) {
            Integer val = number2Count.get(i);
            if(null==val) {
                if(i < min) { //小于最小元素,所以直接返回0
                    val = 0;
                } else if(i > max) { //包含全部元素
                    val = A.length;
                } else {
                    int key = minNear(A, i, 0, A.length-1); //找个包含该元素的最小区间,然后返回left key
                    val = number2Count.get(key);
                }
                rets.add(val);
            } else {
                rets.add(val - numCount.get(i)); //如果包含这个元素,那么获取出来的结果要减去这个元素的个数
            }
        }
        
        return rets;
    }
    
    /**
     * 
     * @param A
     * @param query
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public int minNear(int[] A, int query, int beginIndex, int endIndex) {
        if(beginIndex + 1 == endIndex) {
            return A[beginIndex];
        }
        
        int middleIndex = (beginIndex + endIndex) / 2;
        int middle = A[middleIndex];
        
        if(middle > query) {
            return minNear(A, query, beginIndex, middleIndex);
        } else {
            return minNear(A, query, middleIndex, endIndex);
        }
    }
    
    public void quickSort(int[] A, int beginIndex, int endIndex) {
        if(beginIndex < endIndex) {
            int i = beginIndex;
            int j = endIndex;
            int base = A[i];
            int baseIndex = i;
            while(i < j) {
                while(A[j] >= base && i < j) {
                    j--;
                }
                
                if(i < j) {
                    A[baseIndex] = A[j];
                    baseIndex = j;
                }
                
                while(A[i] < base && i < j) {
                    i++;
                }
                
                if(i < j) {
                    A[baseIndex] = A[i];
                    baseIndex = i;
                }
            }
            
            A[baseIndex] = base;
            quickSort(A, beginIndex, baseIndex-1);
            quickSort(A, baseIndex+1, endIndex);
        }
    }
}
