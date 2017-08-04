package com.hiworld.lintcode;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：链表划分
 * 描述：给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。你应该保留两部分内链表节点原有的相对顺序
 * 样例：
 * 给定链表 1->4->3->2->5->2->null，并且 x=3
 * 返回 1->2->2->4->3->5->null
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月3日 下午5:59:56
 */
public class ListPartition {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ListNode node1 = new ListNode(1);
        ListNode node2_1 = new ListNode(2);
        ListNode node2_2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node4;
        node4.next = node3;
        node3.next = node2_1;
        node2_1.next = node5;
        node5.next = node2_2;
        
        
        ListPartition listPartition = new ListPartition();
        ListNode head = listPartition.partition(node1, 3);  
        ListNode.print(head);
    }
    
    /**
     * 
     * nodeBefore      nodeAfter
     * --------------------------
     * 
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode 
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if(head == null || head.next == null)
            return head;
        
        ListNode leftHead = null;
        ListNode left = leftHead;
        
        ListNode rightHead = null;
        ListNode right = rightHead;
        
        ListNode loopNode = head;
        while(null!=loopNode) {
            ListNode loopNodeNext = loopNode.next;
            loopNode.next = null;
            if(loopNode.val < x) {
                if(null==left) {
                    left = loopNode;
                    leftHead = left;
                } else {
                    left.next = loopNode;
                    left=loopNode;
                }
            } else {
                if(null==right) {
                    right = loopNode;
                    rightHead = right;
                } else {
                    right.next = loopNode;
                    right = loopNode;
                }
            }
            
            loopNode = loopNodeNext;
        }
        
        if(null==leftHead) {
            return rightHead;
        }
        
        left.next = rightHead;
        
        return leftHead;
        
    }
}
