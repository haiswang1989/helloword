package com.for4.mt;

/**
 * 按K步长反转链表
 * 如1,2,3,4,5,6,7,8 当k=3的时候的结果是3,2,1,4,5,6,7,8
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月16日 上午10:49:47
 */
public class RevertFirstKList {

    public static void main(String[] args) {
        
        ListNode0416 node1 = new ListNode0416(1);
        ListNode0416 node2 = new ListNode0416(2);
        ListNode0416 node3 = new ListNode0416(3);
        ListNode0416 node4 = new ListNode0416(4);
        ListNode0416 node5 = new ListNode0416(5);
        ListNode0416 node6 = new ListNode0416(6);
        ListNode0416 node7 = new ListNode0416(7);
        ListNode0416 node8 = new ListNode0416(8);
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        
        RevertFirstKList revertFirstKList = new RevertFirstKList();
        ListNode0416 newHead = revertFirstKList.revertFirstK(node1, 9);
        revertFirstKList.print(newHead);
    }
    
    /**
     * 
     * @param head
     * @param k
     */
    public ListNode0416 revertFirstK(ListNode0416 head, int k) {
        
        if(null == head || k <= 1) {
            return head;
        }
        
        //当前结点
        ListNode0416 currNode = head;
        //当前结点的前一个几点
        ListNode0416 beforeNode = null;
        //当前结点的下一个结点
        ListNode0416 next = currNode.next;
        //当前结点的下一个结点的下一个结点
        ListNode0416 nextNext = null;
        
        int cnt = 1;
        while(null!=next && cnt < k) {
            //下一个结点的下一个结点
            nextNext = next.next;
            //currNode->next ===> next->currNode
            //反转两个结点
            next.next = currNode;
            currNode.next = beforeNode;
            //调整前一个结点
            beforeNode = currNode;
            //调整当前结点
            currNode = next;
            //调整Next结点
            next = nextNext;
            cnt++;
        }
        
        head.next = nextNext;
        return currNode;
    }
    
    public void print(ListNode0416 node) {
        
        ListNode0416 nodeTmp = node;
        
        while(null!=nodeTmp) {
            System.out.print(nodeTmp.val + ",");
            nodeTmp = nodeTmp.next;
        }
    }
}

/**
 * 链表的结点
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月16日 上午10:52:23
 */
class ListNode0416 {
    
    public int val;
    
    public ListNode0416 next;
    
    public ListNode0416(int valArgs) {
        this.val = valArgs;
    }
    
    @Override
    public String toString() {
        return val + "";
    }
}
