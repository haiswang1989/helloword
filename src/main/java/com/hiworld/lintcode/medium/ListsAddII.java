package com.hiworld.lintcode.medium;

import java.util.Stack;

import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：链表求和 II
 * 描述：假定用一个链表表示两个数，其中每个节点仅包含一个数字。假设这两个数的数字顺序排列，请设计一种方法将两个数相加，并将其结果表现为链表的形式。
 * 
 * 样例：
 * 给出 6->1->7 + 2->9->5。即，617 + 295。
 * 返回 9->1->2。即，912 。
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月15日 下午5:54:46
 */
public class ListsAddII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListNode node6 = new ListNode(3);
        ListNode node1 = new ListNode(1);
        ListNode node7 = new ListNode(2);
        
        node6.next = node1;
        node1.next = node7;
        
        ListNode node2 = new ListNode(5);
        ListNode node9 = new ListNode(9);
        ListNode node5 = new ListNode(5);
        ListNode node21 = new ListNode(1);
        
        node2.next = node9;
        node9.next = node5;
        node5.next = node21;
        
        ListsAddII listsAddII = new ListsAddII();
        
        ListNode ret = listsAddII.addLists2(node6, node2);
        ListNode.print(ret);
    }
    
    /*
     * @param l1: The first list.
     * @param l2: The second list.
     * @return: the sum list of l1 and l2.
     */
    public ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        if(null==l1) {
            return l2;
        } else if(null==l2) {
            return l1;
        }
        
//        ListNode[] l1L2 = balanceList(l1, l2);
//        l1 = l1L2[0];
//        l2 = l1L2[1];
//        
//        return listAdd(l1, l2, false);
        
        return addListByStack(l1, l2);
    }
    
    /*******************************网上的思路,使用栈来实现********************************/
    /**
     * 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addListByStack(ListNode l1, ListNode l2) {
        Stack<ListNode> l1Stack = new Stack<>();
        Stack<ListNode> l2Stack = new Stack<>();
        
        ListNode l1Node = l1;
        while(null!=l1Node) {
            l1Stack.push(l1Node);
            l1Node = l1Node.next;
        }
        
        ListNode l2Node = l2;
        while(null!=l2Node) {
            l2Stack.push(l2Node);
            l2Node = l2Node.next;
        }
        
        boolean hasCarry = false;
        
        ListNode retNode = null;
        
        while(l1Stack.size()!=0 || l2Stack.size()!=0) {
            int l1Val = 0;
            int l2Val = 0;
            
            if(l1Stack.size()!=0) {
                l1Val = l1Stack.pop().val;
            }
            
            if(l2Stack.size()!=0) {
                l2Val = l2Stack.pop().val;
            }
            
            int sum = l1Val + l2Val;
            if(hasCarry) {
                sum += 1;
            }
            
            if(sum >= 10) {
                sum %=10;
                hasCarry = true;
            } else {
                hasCarry = false;
            }
            
            ListNode newReturnNode = new ListNode(sum);
            if(null==retNode) {
                retNode = newReturnNode;
            } else {
                newReturnNode.next = retNode;
                retNode = newReturnNode;
            }
        }
        
        if(hasCarry) {
            ListNode newReturnNode = new ListNode(1);
            newReturnNode.next = retNode;
            retNode = newReturnNode;
        }
        
        return retNode;
    }
    
    
    
    /**********************************如下是我的思路********************************/
    /**
     * 基本思路就是,先平衡两个list,使两个list长度相等,短的在前面补0
     * 然后就是遍历两个List,相加,生成一个sum的list和一个进位的list
     * 如果进位的次数不为0那么循环对sum和进位进行累加
     */
    
    /**
     * 先平衡两个链表,让两个链表一样长,短的在前面补充0
     * @param l1
     * @param l2
     * @return
     */
    public ListNode[] balanceList(ListNode l1, ListNode l2) {
        
        ListNode l1Node = l1;
        ListNode l2Node = l2;
        
        Boolean isL1FirstNull = null;
        ListNode emptyHead = null;
        ListNode endEmptyNode = null;
        while(null!=l1Node || null!=l2Node) {
            if(null==l1Node) {
                isL1FirstNull = true;
                ListNode[] emptyHeadEndNode = addEmptyNode(emptyHead, endEmptyNode);
                emptyHead = emptyHeadEndNode[0];
                endEmptyNode = emptyHeadEndNode[1];
            } else {
                l1Node = l1Node.next;
            }
            
            if(null==l2Node) {
                isL1FirstNull = false;
                ListNode[] emptyHeadEndNode = addEmptyNode(emptyHead, endEmptyNode);
                emptyHead = emptyHeadEndNode[0];
                endEmptyNode = emptyHeadEndNode[1];
            } else {
                l2Node = l2Node.next;
            } 
        }
        
        if(null == isL1FirstNull) {
            return new ListNode[]{l1,l2};
        } 
        
        if(isL1FirstNull) {
            endEmptyNode.next = l1;
            l1 = emptyHead;
        } else {
            endEmptyNode.next = l2;
            l2 = emptyHead;
        }
        
        return new ListNode[]{l1,l2};
    }
    
    /**
     * 
     * @param head
     * @param endEmptyNode
     * @return
     */
    public ListNode[] addEmptyNode(ListNode head, ListNode endEmptyNode) {
        ListNode emptyNode = new ListNode(0);
        if(null==endEmptyNode) {
            endEmptyNode = emptyNode;
            head = endEmptyNode;
        } else {
            endEmptyNode.next = emptyNode;
            endEmptyNode = emptyNode;
        }
        
        return new ListNode[]{head,endEmptyNode};
    }
    
    /**
     * 
     * @param l1
     * @param l2
     * @param l2FromIndex
     * @return
     */
    public ListNode listAdd(ListNode l1, ListNode l2, boolean isCarryAdd) {
        ListNode l1Node = l1;
        ListNode l2Node = l2;
        ListNode returnNode = null; //忽略进位的sum的List
        ListNode returnHead = null;
        
        ListNode carryNode = null; //进位的List
        ListNode carryHead = null;
        
        if(isCarryAdd) { //这个是对进位的特殊处理
            if(l2Node.val==1) {
                ListNode newReturnNode = new ListNode(1);
                returnNode = newReturnNode;
                returnHead = returnNode;
            }
            
            l2Node = l2Node.next;
        }
        
        boolean hasCarry = false; //是不是有进位
        int carryCount = 0; //这个是进位的计数,如果>0表示有进位,需要进行递归,如果为0表示没有进位,计算结束
        while(null!=l1Node || null!=l2Node) {
            int l1Val = 0;
            int l2Val = 0;
            if(null!=l1Node) {
                l1Val = l1Node.val;
                l1Node = l1Node.next;
            }
            
            if(null!=l2Node) {
                l2Val = l2Node.val;
                l2Node = l2Node.next;
            }
            
            int sum = l1Val + l2Val;
            if(sum >= 10) {
                carryCount++;
                hasCarry = true;
                sum %= 10;
            } 
            
            ListNode newReturnNode = new ListNode(sum);
            if(null==returnNode) {
                returnNode = newReturnNode;
                returnHead = returnNode;
            } else {
                returnNode.next = newReturnNode;
                returnNode = newReturnNode;
            }
            
            ListNode newCarryNode = null;
            if(hasCarry) {
                newCarryNode = new ListNode(1);
            } else {
                newCarryNode = new ListNode(0); 
            }
            
            hasCarry = false;
            if(null==carryNode) {
                carryNode = newCarryNode;
                carryHead = carryNode;
            } else {
                carryNode.next = newCarryNode;
                carryNode = newCarryNode;
            }
        }
        
        if(carryCount!=0) { //如果有进位,那么需要对进位进行处理
            return listAdd(returnHead, carryHead, true);
        }
        
        return returnHead;
    }
}

