package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.Iterator;

import com.hiworld.lintcode.common.Interval;

/**
 * 问题：
 * 
 * 给出一个无重叠的按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 
 * 样例：
 * 
 * 插入区间[2, 5] 到 [[1,2], [5,9]]，我们得到 [[1,9]]。
 * 插入区间[3, 4] 到 [[1,2], [5,9]]，我们得到 [[1,2], [3,4], [5,9]]。
 * 
 * 理论分析：
 * lintcode理论/区间插入
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月18日 上午11:22:46
 */
public class IntervalInsert {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Interval i1 = new Interval(1, 5);
        Interval i2 = new Interval(7, 8);
        Interval i3 = new Interval(10, 13);
        
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        
        Interval newI = new Interval(8, 9);
        
        IntervalInsert intervalInsert = new IntervalInsert();
        ArrayList<Interval> ret = intervalInsert.insert(intervals, newI);
        System.out.println(ret.size());
    }
    
    /**
     * 比较复杂(情况比较多,容易遗漏)
     * @param intervals
     * @param newInterval
     * @return
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        // write your code here
        if(null==intervals || 0==intervals.size()) {
            result.add(newInterval);
            return result;
        }
        
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        boolean isAdd = false; //标记这个新的区间有没有被合并进来
        
        Iterator<Interval> iter = intervals.iterator();
        while(iter.hasNext()) {
            Interval interval = iter.next();
            int intervalStart = interval.start;
            int intervalEnd = interval.end;
            
            int min = min(newStart, intervalStart, intervalEnd);
            int max = max(newEnd, intervalStart, intervalEnd);
            
            if(min == newStart) { 
                if(max == intervalEnd) {
                    if(newEnd < intervalStart) {
                        result.add(newInterval);
                        result.add(interval);
                    } else { //newEnd == intervalStart || (newEnd > intervalStart && newEnd < intervalEnd) || newEnd == intervalEnd
                        Interval combineInterval = new Interval(newStart, intervalEnd);
                        result.add(combineInterval);
                    }
                    isAdd = true; //这边new的interval已经加入到了ret list中
                    break;
                } else { //newInterval包含了该interval,那么改interval就不需要管了,跳过
                    continue;
                }
            } else { //min == intervalStart
                if(max == intervalEnd) { //该interval包含了newinterval
                    result.add(interval);
                    isAdd = true; //注意这边,interval完全包含newinterval,所以也相当于newinterval被加入进来了
                    break;
                } else if(max == newEnd) {
                    if(newStart <= intervalEnd) { //构造一个新的interval,继续下面的遍历
                        newStart = intervalStart; 
                        newInterval = new Interval(newStart, newEnd);
                    } else { //
                        result.add(interval);
                    }
                }
            }
        }
        
        if(!isAdd) { //可能出现,NEW区间就是在最后的情况,所以这边加一个额外的判断
            result.add(newInterval);
        }
        
        while(iter.hasNext()) {
            result.add(iter.next());
        }
        
        return result;
    }
    
    public int max(int input1, int input2, int input3) {
        int max = Math.max(input1, input2);
        return Math.max(max, input3);
    }
    
    public int min(int input1, int input2, int input3) {
        int min = Math.min(input1, input2);
        return Math.min(min, input3);
    }
    
}
