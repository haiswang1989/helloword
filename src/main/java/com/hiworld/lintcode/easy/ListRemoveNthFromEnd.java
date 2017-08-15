package com.hiworld.lintcode.easy;

import java.util.HashMap;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：删除链表中倒数第n个节点
 * 
 * 样例：
 * List : 1->2->3->4->5->null
 * N : 2
 * 
 * 返回：
 * 1->2->3->5->null
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月13日 上午10:48:47
 */
public class ListRemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        
        ListRemoveNthFromEnd listRemoveNthFromEnd = new ListRemoveNthFromEnd();
        node1 = listRemoveNthFromEnd.removeNthFromEnd(node1, 5);
        ListNode.print(node1);
    }   
    
    /**
     * O(N)时间复杂度
     * 遍历List一遍,然后将每个结点存储下来,定位需要删除的结点
     * 再使用O(1)时间复杂度删除需要删除的结点
     * 
     * @param head
     * @param n
     * @return
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if(head == null) { //如果list为null,直接返回null
            return null;
        }
        
        if(n <= 0) { //如果n小于等于0,直接返回list
            return head;
        }
        
        HashMap<Integer, ListNode> nodes = new HashMap<>();
        ListNode loopNode = head;
        int index = 1;
        while(null!=loopNode) {
            nodes.put(index++, loopNode);
            loopNode = loopNode.next;
        }
        
        int listSize = nodes.size();
        
        if(listSize < n) { //如果n>list的长度,也不需要进行删除,直接返回list
            return head;
        }
        
        int indexFromBegin = listSize - n + 1;
        //删除第一个结点和删除最后一个结点,需要做特殊处理
        if(indexFromBegin == 1) { //需要删除的是第一个结点
            head = head.next;
        } else if(indexFromBegin == listSize) { //需要删除的是最后一个结点
            ListNode beforeDeleteNode = nodes.get(indexFromBegin - 1);
            beforeDeleteNode.next = null;
        } else { //中间结点
            ListNode deleteNode = nodes.get(indexFromBegin);
            deleteNode.val = deleteNode.next.val;
            deleteNode.next = deleteNode.next.next;
        }
        
        return head;
    }
}