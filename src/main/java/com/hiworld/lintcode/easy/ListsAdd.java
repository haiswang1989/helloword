package com.hiworld.lintcode.easy;

import com.hiworld.lintcode.common.ListNode;

/**
 * 链表求和
 * 
 * 给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null
 * 
 * 注意：
 * 1：进位
 * 2：链表不一样长
 * 3：结点的值 为 num % 10 ,结点是否有进位num / 10 == 0 没有进位 如果等于1有进位
 * 4：处理最后一个结点考虑进位
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月5日 上午11:52:23
 */
public class ListsAdd {
    
    
    public static void main(String[] args) {
        
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(2);
        
        node1.next = node2;
        node2.next = node3;
        
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(2);
        
        node4.next = node5;
//        node5.next = node6;
        
        ListsAdd listsAdd = new ListsAdd();
        listsAdd.printList(node1);
        listsAdd.printList(node4);
        
        ListNode head = listsAdd.addLists(node1, node4);
        listsAdd.printList(head);
    }
    
    /**
     * 
     * @param l1
     * @param l2
     * @return
     */
    ListNode addLists(ListNode l1, ListNode l2) {
        ListNode l1Temp = l1;
        ListNode l2Temp = l2;
        
        boolean hasCarry = false;
        int sum = 0;
        
        ListNode beforeNode = null;
        ListNode newHeadNode = null;
        
        while(null!=l1Temp && null!=l2Temp) {
            
            int l1CurrValue = l1Temp.val;
            int l2CurrValue = l2Temp.val;
            
            sum = l1CurrValue + l2CurrValue;
            if(hasCarry) { //这边如果有进位,把进位的1线合并过来
                sum = sum + 1;
            }
            
            int nodeValue = sum % 10; //取余就是该node的值
            
            if(sum / 10 == 0) { //如果值大于10那么就会有进位
                hasCarry = false;
            } else {
                hasCarry = true;
            }
            
            ListNode currNode = new ListNode(nodeValue);
            if(beforeNode!=null) {
                beforeNode.next = currNode;
                beforeNode = currNode;
            } else {
                beforeNode = currNode;
                newHeadNode = beforeNode;
            }
            
            l1Temp = l1Temp.next;
            l2Temp = l2Temp.next;
        }
        
        if(null != l1Temp) { //两个链表不是一样长的时候,计算完成之后,还要继续合并
            hasCarry = addSingleList(l1Temp, beforeNode, hasCarry);
        }
        
        if(null != l2Temp) {
            hasCarry = addSingleList(l2Temp, beforeNode, hasCarry);
        }
        
        if(hasCarry) { //注意这边最后相加完成后,可能还有进位
            ListNode endNode = new ListNode(1);
            beforeNode.next = endNode;
        }
        
        return newHeadNode;
    }
    
    /**
     * 
     * @param node
     * @param beforeNode
     * @param hasCarry
     */
    public boolean addSingleList(ListNode node, ListNode beforeNode, boolean hasCarry) {
        while(null!=node) {
            int currValue = node.val;
            
            if(hasCarry) {
                currValue = currValue + 1;
            }
            
            int nodeValue = currValue % 10;
            if(nodeValue / 10 == 0) {
                hasCarry = false;
            } else {
                hasCarry = true;
            }
            
            ListNode currNode = new ListNode(nodeValue);
            if(beforeNode != null) {
                beforeNode.next = currNode;
            }
            
            beforeNode = currNode;
            node = node.next;
        }
        
        return hasCarry;
    }
    
    public void printList(ListNode head) {
        ListNode beginNode = head;
        while(beginNode.next != null) {
            System.out.print(beginNode.val + "->");
            beginNode = beginNode.next;
        }
        System.out.println(beginNode.val);
    }
}
