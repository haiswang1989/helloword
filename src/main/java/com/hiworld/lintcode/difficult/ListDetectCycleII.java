package com.hiworld.lintcode.difficult;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：带环链表 II
 * 描述：给定一个链表，如果链表中存在环，则返回到链表中环的起始节点的值，如果没有环，返回null。
 * 
 * 挑战：
 * 不使用额外的空间
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月6日 上午10:16:43
 */
public class ListDetectCycleII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n3;
        
        ListDetectCycleII listDetectCycleII = new ListDetectCycleII();
        ListNode ret = listDetectCycleII.detectCycle(n1);
        System.out.println(ret);
    }
    
    /**
     * 思路：
     *          —— —— —— 
     *         |        |
     *         |        |
     *         |        |
     *         |        |
     * —— —— —— —— —— ——
     * 
     * 利用两个指针从头开始,指针p1一次走一步,指针p2一次走两步
     * 
     * 理论：
     * 1:如果有环两指针必定有重逢之时
     * 2:p1被p2追上的时候他一定没有走完整个环
     * 
     * 假设：
     * 1：从链的起点到环的起点,这段距离为 a
     * 2：环的长度为 c
     * 3：第一次相遇距环的起点距离为p
     * 
     * 相遇的时候
     * p1走了 a + p
     * p2走了 a + p + nc (n是表示p2走了n圈环)
     * 
     * 因为p2的速度是p1速度的2倍
     * 所以：
     * 2a + 2p = a + p + nc
     * 变换为
     * a + p = nc
     * 
     * 所以再走a就到了环的起点,而a恰好又是链表的启动到环的起点的距离,因此可以另起一个指针在链表的起点,另一个指针在原地
     * 同时以速度为1前进,当两个指针相遇的时候,这个就是环的起点
     * 
     * 
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. 
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {  
        // write your code here
        if(null==head || null==head.next) {
            return null;
        }
        
        ListNode p1 = head; //一次一步
        ListNode p2 = head; //一次两步
        
        boolean hasCycle = false;
        
        while(p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            
            if(null == p2) { //无环,到底了
                return null;
            } else if(p1 == p2) { //有环,p1和p2相遇了
                hasCycle = true;
                break;
            }
        }
        
        if(!hasCycle) { //无环
            return null;
        }
        
        ListNode p3 = head;
        while(p3 != p1) {
            p1 = p1.next;
            p3 = p3.next;
        }
        
        return p3;
    }
}
