package com.hiworld.lintcode;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：在O(1)时间复杂度,删除链表的结点
 * 
 * 样例：
 * 有List : 11->12->13->14->null 删除值为13的node, 删除后的结点 11->12->14->null
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月13日 上午10:33:05
 */
public class ListDelNode {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ListNode node11 = new ListNode(11);
        ListNode node12 = new ListNode(12);
        ListNode node13 = new ListNode(13);
        ListNode node14 = new ListNode(14);
        
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        
        ListDelNode listDelNode = new ListDelNode();
        listDelNode.deleteNode(node13);
        
        System.out.println(ListNode.print(node11));
    }
    
    /**
     * 实现思路： 当前结点和当前结点的next进行互换(值),然后删除下一个结点
     * 不需要去删除结点,只需要把下一个结点的值,放到当前结点
     * 当前结点的next变成下一个结点的next
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val; 
        node.next = node.next.next;
    }
    
}
