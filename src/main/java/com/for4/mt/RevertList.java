package com.for4.mt;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月12日 下午2:10:51
 */
public class RevertList {

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
        node5.next = null;
        
        RevertList rl = new RevertList();
        ListNode newHead = rl.revertList(node1);
        System.out.println(newHead);
    }
    
    public ListNode revertList(ListNode head) {
        if(null == head) {
            return head;
        }
        
        ListNode beforeNode = null;
        ListNode currNode = head;
        ListNode next = currNode.next;
        
        ListNode nextNext = null;
        while(null!=next) {
            nextNext = next.next;
            if(null!=beforeNode) {
                currNode.next = beforeNode;
            } else {
                //最后一行元素,更新next为Null,避免出现死循环
                currNode.next = null;
            }
            
            beforeNode = currNode;
            next.next = currNode;
            currNode = next;
            next = nextNext;
        }
        
        if(null!=beforeNode) {
            currNode.next = beforeNode;
        }
        
        return currNode;
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月12日 上午11:40:34
 */
class ListNode {
    
    public int val;
    
    public ListNode next;
    
    public ListNode(int val) {
        this.val = val;
    }
    
    @Override
    public String toString() {
        return val + "";
    }
}
