package com.hiworld.lintcode.medium;

import com.hiworld.lintcode.common.SegmentTreeNode;

/**
 * 问题：线段树的查找
 * 描述：我们可以将一个数组构造成一个"线段树",每个结点额外定义了一个"count"字段存储了[start,end]中元素的个数
 * 设计一个query方法,参数如下,查找[start,end]中的元素的个数
 * 
 * 样例：
 * 
 *                   [0, 3, count=3]
 *                   /             \
 *       [0,1,count=1]             [2,3,count=2]
 *       /         \               /            \
 * [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
 * 
 * query(1, 1), return 0
 * query(1, 2), return 1
 * query(2, 3), return 2
 * query(0, 2), return 2
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月28日 下午3:33:41
 */
public class SegmentTreeQueryII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    /**
     * @param root, start, end: The root of segment tree and an segment / interval
     * @return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(null == root) {
            return 0;
        } 
        
        if(start < root.start) { //调整开始值,当开始值小于区间的开始值的时候,直接将查找开始值赋值为区间的开始值
            start = root.start;
        }
        
        if(end > root.end) { //调整结束值,当结束值大于区间的结束值的时候,直接将查找的结束值更新为区间的结束值
            end = root.end;
        }
        
        if(root.start>=start && root.end<=end) { //如果查找区间[start,end] 包含root,那么直接返回root的结点的个数
            return root.count;
        }
        
        
        SegmentTreeNode node = root;
        while(true) {
            int currNodeBegin = node.start;
            int currNodeEnd = node.end;
            int middle = (currNodeBegin + currNodeEnd) / 2;
            if(start == currNodeBegin) {
            } else if(start <= middle) {
                SegmentTreeNode leftSeg = root.left;
            } else {
                SegmentTreeNode rightSeg = root.right;
            }
        }
        
        return 1;
    }
}
