package com.hiworld.lintcode.medium;

import com.hiworld.lintcode.common.SegmentTreeNode;

/**
 * 问题：线段树的构造
 * 描述：线段树是一棵二叉树，他的每个节点包含了两个额外的属性start和end用于表示该节点所代表的区间。start和end都是整数，并按照如下的方式赋值
 * 1：根节点的 start 和 end 由 build 方法所给出。
 * 2：对于节点 A 的左儿子，有 start=A.left, end=(A.left + A.right) / 2。
 * 3：对于节点 A 的右儿子，有 start=(A.left + A.right) / 2 + 1, end=A.right。
 * 4：如果 start 等于 end, 那么该节点是叶子节点，不再有左右儿子。
 * 实现一个 build 方法，接受 start 和 end 作为参数, 然后构造一个代表区间 [start, end] 的线段树，返回这棵线段树的根。
 * 
 * 样例：
 * 比如给定start=1, end=6，对应的线段树为：
 * 
 *                                      [1,  6]
 *                              **                           **
 *              [1,  3]                                             [4,  6]                  
 *          *           *                                     *                  *
 *      [1, 2]          [3,3]                             [4, 5]                [6,6]
 *   *        *                                         *        *   
 * [1,1]     [2,2]                                    [4,4]     [5,5]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月28日 下午3:12:14
 */
public class SegmentTreeNodeBuildI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        SegmentTreeNodeBuildI segmentTreeNodeBuildI = new SegmentTreeNodeBuildI();
        SegmentTreeNode head = segmentTreeNodeBuildI.build(1, 6);
        System.out.println(head);
    }
    
    /*
     * 
     * @param start: start value.
     * @param end: end value.
     * @return: The root of Segment Tree.
     */
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start > end) {
            return null;
        }
        
        return withRecusion(start, end); 
    }
    
    /**
     * 
     * @param start
     * @param end
     * @return
     */
    public SegmentTreeNode withRecusion(int start, int end) {
        if(start == end) {
            return new SegmentTreeNode(start, end);
        }
        
        SegmentTreeNode head = new SegmentTreeNode(start, end);
        
        int leftChildStart = start;
        int leftChildEnd = (start + end) / 2;
        
        SegmentTreeNode leftChild = withRecusion(leftChildStart, leftChildEnd);
        SegmentTreeNode rightChild = withRecusion(leftChildEnd+1, end);
        
        head.left = leftChild;
        head.right = rightChild;
        return head;
    }
}
