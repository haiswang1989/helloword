package com.hiworld.lintcode.common;

/**
 * 
 * 间隔，区间
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月18日 上午11:21:40
 */
public class Interval {
    
    public int start;
    public int end;
    
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public String toString() {
        return "["+start+ "," +end+ "]";
    }
}
