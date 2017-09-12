package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiworld.lintcode.common.Interval;

/**
 * 问题：区间求和 I 
 * 描述：给定一个整数数组（下标由 0 到 n-1，其中 n 表示数组的规模），以及一个查询列表。每一个查询列表有两个整数 [start, end] 。 
 * 对于每个查询，计算出数组中从下标 start 到 end 之间的数的总和，并返回在结果列表中。
 * 
 * 样例：
 * 对于数组 [1,2,7,8,5]，查询[(1,2),(0,4),(2,4)], 返回 [9,23,20]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月12日 上午9:24:16
 */
public class ArrayIntervalSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ArrayIntervalSum arrayIntervalSum = new ArrayIntervalSum();
        int[] A = {1,2,7,8,5};
        
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(0, 4);
        Interval i3 = new Interval(2, 4);
        List<Interval> queries = new ArrayList<>();
        queries.add(i1);
        queries.add(i2);
        queries.add(i3);
        
        List<Long> rets = arrayIntervalSum.intervalSum(A, queries);
        System.out.println(Arrays.toString(rets.toArray()));
    }
    
    /*
     * @param A: An integer list
     * @param queries: An query list
     * @return: The result list
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        // write your code here
        Map<Integer, Long> index2Sum = new HashMap<>();
        int length = A.length;
        Long sum = 0l;
        index2Sum.put(-1, sum);
        for(int i=0; i<length; i++) {
            sum += A[i];
            index2Sum.put(i, sum);
        }
        
        List<Long> rets = new ArrayList<>();
        for (Interval query : queries) {
            int start = query.start;
            int end = query.end;
            rets.add(index2Sum.get(end) - index2Sum.get(start-1));
        }
        
        return rets;
    }
}
