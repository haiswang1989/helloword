package com.hiworld.lintcode;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：两两交换链表中的节点 
 * 描述：给一个链表，两两交换其中的节点，然后返回交换后的链表。   
 * 样例：
 * 给出 1->2->3->4, 你应该返回的链表是 2->1->4->3。
 * 挑战：你的算法只能使用常数的额外空间，并且不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月11日 下午2:07:20
 */
public class ListSwapPairs {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        
        ListNode.print(node1);
        
        ListSwapPairs listSwapPairs = new ListSwapPairs();
        ListNode head = listSwapPairs.swapPairs(node1);
        
        ListNode.print(head);
    }
    
    /**
     * @param head a ListNode
     * @return a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // Write your code here
        if(null == head) {
            return head;
        }
        
        ListNode before = null;
        ListNode swapNode = head;
        ListNode swapNodeAfter = null;
        while(null!=swapNode) {
            swapNodeAfter = swapNode.next;
            if(null==swapNodeAfter) {
                return head;
            }
            
            if(null==before) {
                swapNode.next = swapNodeAfter.next;
                swapNodeAfter.next = swapNode; 
                head = swapNodeAfter; //更新head
            } else {
                before.next = swapNodeAfter;
                swapNode.next = swapNodeAfter.next;
                swapNodeAfter.next = swapNode;
            }
            
            before = swapNode;
            swapNode = swapNode.next;
        }
        
        return head;
    }

}
