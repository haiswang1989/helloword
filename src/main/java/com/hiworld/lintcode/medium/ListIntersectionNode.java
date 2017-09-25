package com.hiworld.lintcode.medium;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：两个链表的交叉
 * 描述：请写一个程序，找到两个单链表最开始的交叉节点。
 * 
 * 注意：
 * 1：如果两个链表没有交叉，返回null。
 * 2：在返回结果后，两个链表仍须保持原有的结构。
 * 3：可假定整个链表结构中没有循环。
 * 
 * 样例：
 * A:
 * a1->a2->c1->c2->c3
 * B:
 * b1->b2->b3->c1->c2->c3
 * 
 * 交叉部分：
 * c1->c2->c3
 * 
 * 挑战：
 * 需满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月12日 下午4:15:49
 */
public class ListIntersectionNode {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        
        ListNode node11 = new ListNode(11);
        ListNode node12 = new ListNode(12);
        ListNode node13 = new ListNode(13);
        
        ListNode node111 = new ListNode(111);
        ListNode node112 = new ListNode(112);
        ListNode node113 = new ListNode(113);
        
        node1.next = node2;
        node2.next = node111;
        node111.next = node112;
        node112.next = node113;
        
        node11.next = node12;
        node12.next = node13;
        node13.next = node111;
        
        ListIntersectionNode listIntersectionNode = new ListIntersectionNode();
        ListNode ret = listIntersectionNode.getIntersectionNode(node1, node11);
        System.out.println("ret : " + ret.val);
    }
    
    /**
     * 
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode 
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Write your code here
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        
        int aLength = length(nodeA);
        int bLength = length(nodeB);
        
        nodeA = headA;
        nodeB = headB;
        int abs = Math.abs(aLength - bLength);
        if(aLength > bLength) {
            while(abs > 0) {
                nodeA = nodeA.next;
                --abs;
            }
        } else {
            while(abs > 0) {
                nodeB = nodeB.next;
                --abs;
            }
        }
        
        ListNode sameBeginNode = null;
        while(null!=nodeA) {
            if(nodeA.val == nodeB.val) {
                if(null==sameBeginNode) {
                    sameBeginNode = nodeA;
                }
            } else {
                if(null!=sameBeginNode) {
                    sameBeginNode = null;
                }
            }
            
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        
        return sameBeginNode;
    } 
    
    /**
     * 获取链表的长度
     * @param node
     * @return
     */
    public int length(ListNode node) {
        int cnt = 0;
        while(null!=node) {
            node = node.next;
            ++cnt;
        }
            
        return cnt;
    }
}
