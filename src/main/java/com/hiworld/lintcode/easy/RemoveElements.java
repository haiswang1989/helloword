package com.hiworld.lintcode.easy;

import com.hiworld.lintcode.common.ListNode;

/**
 * 删除链表中的元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月4日 上午11:07:29
 */
public class RemoveElements {
    
    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = null; //新的开始结点不能初始化为head,因为head可能就是要删除的结点
        ListNode currentNode = head;
        ListNode beforeNode = null; //前置结点
        boolean isNewHeadSetted = false; //是否设置过新的head结点
        while(null!=currentNode) {
            if(currentNode.val == val) {
                currentNode = currentNode.next;
                if(null != beforeNode) { //这边需要判断一下beforeNode是否为null,可能最前面几个Node就是要删除的
                    beforeNode.next = currentNode;
                }
            } else {
                beforeNode = currentNode;
                currentNode = currentNode.next;
                if(!isNewHeadSetted) { //新的开始结点只要设置一次
                    newHead = beforeNode;
                    isNewHeadSetted = true;
                }
            }
        }
        
        return newHead;
    }
    
    public void printList(ListNode head) {
        ListNode beginNode = head;
        while(beginNode.next != null) {
            System.out.print(beginNode.val + "->");
            beginNode = beginNode.next;
        }
        System.out.println(beginNode.val);
    }
    
    public static void main(String[] args) {
        
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(3);
        node6.next = node7;
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        
        
        RemoveElements removeElements = new RemoveElements();
        removeElements.printList(node1);
        node1 = removeElements.removeElements(node1, 3);
        removeElements.printList(node1);
    }
}

