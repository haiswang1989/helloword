package com.hiworld.lintcode;

import com.hiworld.lintcode.common.ListNode;

public class ListsAdd {
    
    
    public static void main(String[] args) {
        
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(5);
        
        node1.next = node2;
        node2.next = node3;
        
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(2);
        
        node4.next = node5;
        node5.next = node6;
        
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
            if(hasCarry) {
                sum = sum + 1;
            }
            
            int nodeValue = sum % 10;
            
            if(sum / 10 == 0) {
                hasCarry = false;
            } else {
                hasCarry = true;
                nodeValue = 0;
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
        
        if(null != l1Temp) {
            addSingleList(l1Temp, beforeNode, hasCarry);
        }
        
        if(null != l2Temp) {
            addSingleList(l2Temp, beforeNode, hasCarry);
        }
        
        return newHeadNode;
    }
    
    /**
     * 
     * @param node
     * @param beforeNode
     * @param hasCarry
     */
    public void addSingleList(ListNode node, ListNode beforeNode, boolean hasCarry) {
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
                nodeValue = 0;
            }
            
            ListNode currNode = new ListNode(nodeValue);
            if(beforeNode != null) {
                beforeNode.next = currNode;
            }
            
            beforeNode = currNode;
            node = node.next;
        }
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
