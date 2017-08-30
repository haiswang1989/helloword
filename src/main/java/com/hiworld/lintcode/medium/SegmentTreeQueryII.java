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
        
        SegmentTreeNode node1 = new SegmentTreeNode(0, 3, 3);
        SegmentTreeNode node2 = new SegmentTreeNode(0, 1, 1);
        SegmentTreeNode node3 = new SegmentTreeNode(2, 3, 2);
        SegmentTreeNode node4 = new SegmentTreeNode(0, 0, 1);
        SegmentTreeNode node5 = new SegmentTreeNode(1, 1, 0);
        SegmentTreeNode node6 = new SegmentTreeNode(2, 2, 1);
        SegmentTreeNode node7 = new SegmentTreeNode(3, 3, 1);
        
        node1.left = node2;
        node1.right = node3;
        
        node2.left = node4;
        node2.right = node5;
        
        node3.left = node6;
        node3.right = node7;
        
        
        SegmentTreeQueryII segmentTreeQueryII = new SegmentTreeQueryII();
        int ret = segmentTreeQueryII.query(node1, 2, 3);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param root, start, end: The root of segment tree and an segment / interval
     * @return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(null == root || root.start > end || root.end < start) { 
            return 0;
        } 
        
        if(root.start>=start && root.end<=end) { //如果查找区间包含了结点的区间,那么就直接返回全部的元素的个数
            return root.count;
        }
        
        int middle = (root.start + root.end) / 2;
        if(end <= middle) { //查找区间在left区间
            return query(root.left, start, end);
        } else if(start > middle) { //查找区间在right区间
            return query(root.right, start, end);
        } else {
            return query(root.left, start, middle) + query(root.right, middle, end); //横跨left,right区间,那么就拆分成两个部分
        }
    }
}
