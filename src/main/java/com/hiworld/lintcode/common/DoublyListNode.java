package com.hiworld.lintcode.common;

/**
 * 双向链表
 * @author Administrator
 *
 */
public class DoublyListNode {
	public int val;
	public DoublyListNode next, prev;
	
	public DoublyListNode(int val) {
		this.val = val;
		this.next = this.prev = null;
	}
}
