package com.hiworld.lintcode;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：删除排序链表中的重复元素
 * 描述：给定一个排序链表，删除所有重复的元素每个元素只留下一个。
 * 
 * 样例：
 * 给出 1->1->2->null，返回 1->2->null
 * 给出 1->1->2->3->3->null，返回 1->2->3->null
 * 
 * 
 * @author Administrator
 *
 */
public class ListDeleteDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		ListNode node1_1 = new ListNode(1);
//		ListNode node1_2 = new ListNode(1);
//		ListNode node2 = new ListNode(2);
//		
//		node1_1.next = node1_2;
//		node1_2.next = node2;
		
		ListNode node1_1 = new ListNode(1);
		ListNode node1_2 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3_1 = new ListNode(3);
		ListNode node3_2 = new ListNode(3);
		
		node1_1.next = node1_2;
		node1_2.next = node2;
		node2.next = node3_1;
		node3_1.next = node3_2;
		
		
		ListNode.print(node1_1);
		
		ListNode head = ListDeleteDuplicates.deleteDuplicates(node1_1);
		ListNode.print(head);
		
	}
	
	/**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) { 
        // write your code here
    	if(null==head) {
    		return head;
    	}
    	
    	ListNode loopNode = head;
    	ListNode beforeNode = loopNode; //前面一个元素,删除元素时使用
    	
    	while(null!=(loopNode=loopNode.next)) {
    		if(beforeNode.val == loopNode.val) { //如果两个元素相等,那么就需要删除元素
    			beforeNode.next = loopNode.next; //删除loopnode
    			loopNode = beforeNode; //基本把loopnode设置为beforeNode,这样就会就绪处理下一个元素
    		} else {
    			beforeNode = loopNode;
    		}
    	}
    	
    	return head;
    } 
}
