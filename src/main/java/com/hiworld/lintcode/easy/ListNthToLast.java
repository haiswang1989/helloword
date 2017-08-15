package com.hiworld.lintcode.easy;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：链表倒数第n个节点
 * 描述：找到单链表倒数第n个节点，保证链表中节点的最少数量为n。
 * 样例：给出链表 3->2->1->5->null和n = 2，返回倒数第二个节点的值1.
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月2日 下午3:51:36
 */
public class ListNthToLast {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        
        node3.next = node2;
        node2.next = node1;
        node1.next = node5;
        
        ListNthToLast listNthToLast = new ListNthToLast();
        ListNode targetNode = listNthToLast.nthToLast(node3, 2);
        System.out.println("ret : " + targetNode.val);
    }
    
    /**
     * 思想：
     * 一个指针,指向重头开始的第n个位置的元素,一个指针指向第一个元素,然后不停的往下遍历,
     * 直到在前面的指针指向最后一个元素,那么后面这个指针指向的就是要寻找的结点
     * 
     * afternode     beforenode
     * - - - - - - - - - - - -
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: Nth to last node of a singly linked list. 
     */
    ListNode nthToLast(ListNode head, int n) {
        // write your code here
        
        ListNode beforeNode = head;
        ListNode afterNode = head;
        int count = 0;
        while(count++ < n) {
            beforeNode = beforeNode.next;
        }
        
        while(null!=beforeNode) {
            afterNode = afterNode.next;
            beforeNode = beforeNode.next;
        }
        
        return afterNode;
    }
}
