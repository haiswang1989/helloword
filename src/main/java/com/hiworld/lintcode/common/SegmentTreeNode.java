package com.hiworld.lintcode.common;

/**
 * 线段树节点
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月28日 下午2:50:23
 */
public class SegmentTreeNode {
    public int start;
    public int end;
    public int count;
    public SegmentTreeNode left;
    public SegmentTreeNode right;
    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = null; 
        this.right = null;
    }
    
    public SegmentTreeNode(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
        this.left = null; 
        this.right = null;
    }
    
    @Override
    public String toString() {
        return "["+start+","+end+"]";
    }
}
