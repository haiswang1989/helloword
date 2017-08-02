package com.hiworld.lintcode;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：合并两个排序链表
 * 描述：将两个排序链表合并为一个新的排序链表
 * 
 * 样例：给出 1->3->8->11->15->null，2->null， 返回 1->2->3->8->11->15->null。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月1日 下午2:36:05
 */
public class ListMergeTwoLists {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ListNode node1_1 = new ListNode(1);
        ListNode node1_3 = new ListNode(3);
        ListNode node1_8 = new ListNode(8);
        ListNode node1_11 = new ListNode(11);
        ListNode node1_15 = new ListNode(15);
        node1_1.next = node1_3;
        node1_3.next = node1_8;
        node1_8.next = node1_11;
        node1_11.next = node1_15;
        
        ListNode node2_2 = new ListNode(2);
        
        ListMergeTwoLists listMergeTwoLists = new ListMergeTwoLists();
        ListNode newLn = listMergeTwoLists.mergeTwoLists(node1_1, node2_2);
        ListNode.print(newLn);
        
    }
    
    /**
     * @param ListNode l1 is the head of the linked list
     * @param ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode head = null;
        ListNode node = null;
        
        while(null!=l1 && null!=l2) {
            if(l1.val == l2.val) { //如果两个值相等,那么一起添加到新的list中
                ListNode node1 = new ListNode(l1.val);
                ListNode node2 = new ListNode(l2.val);
                if(null==node) {
                    node = node1;
                    node.next = node2;
                    head = node;
                } else {
                    node.next = node1;
                    node1.next = node2;
                }
                node = node2;
                l1 = l1.next;
                l2 = l2.next;
            } else if(l1.val < l2.val) {
                ListNode node1 = new ListNode(l1.val);
                if(null==node) {
                    node = node1;
                    head = node;
                } else {
                    node.next = node1;
                }
                node = node1;
                l1 = l1.next;
            } else {
                ListNode node2 = new ListNode(l2.val);
                if(null==node) {
                    node = node2;
                    head = node;
                } else {
                    node.next = node2;
                }
                node = node2;
                l2 = l2.next;
            }
        }
        
        while(null!=l1) {
            ListNode node1 = new ListNode(l1.val);
            if(null==node) {
                node = node1;
                head = node;
            } else {
                node.next = node1;
            }
            node = node1;
            l1 = l1.next;
        }
        
        while(null!=l2) {
            ListNode node2 = new ListNode(l2.val);
            if(null==node) {
                node = node2;
                head = node;
            } else {
                node.next = node2;
            }
            node = node2;
            l2 = l2.next;
        }
        
        return head;
    }
}
