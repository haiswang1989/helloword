package com.hiworld.lintcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hiworld.lintcode.common.Interval;

/**
 * 问题：合并区间
 * 描述：给出若干闭合区间，合并所有重叠的部分。
 * 样例：
 * [[1, 3],[2, 6],[8, 10],[15, 18]]
 * 返回值：
 * [[1, 6],[8, 10],[15, 18]]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月2日 下午6:05:28
 */
public class IntervalMerge {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 6);
        Interval i3 = new Interval(8, 10);
        Interval i4 = new Interval(15, 18);
        
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);
        
        IntervalMerge intervalMerge = new IntervalMerge();
        List<Interval> rets = intervalMerge.merge(intervals);
        
        for (Interval interval : rets) {
            System.out.println(interval.toString());
        }
    }
    
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.st
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        int length = -1;
        if(null==intervals || (length=intervals.size()) <= 1) {
            return intervals;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() { //首先要对Intervals进行排序 
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        
        
        List<Interval> ret = new ArrayList<>();
        
        Interval pre = intervals.get(0);
        for(int i=1; i<length; ++i) { //排序完了进行合并
            Interval curr = intervals.get(i);
            if(pre.end < curr.start) { 
                ret.add(pre); //前面一个interval,与当前的interval没有交集,那么直接加入到返回list
                pre = curr; //继续往下遍历
            } else if(pre.end < curr.end) { //pre.end >= curr.start and pre.end < curr.end
                pre.end = curr.end; //更新下pre的end为curr的end,构成一个新的interval,是由pre和curr合并起来
            } else {
                //pre完全包含curr
            }
        }
        
        ret.add(pre);
        return ret;
    }
}
