package com.hiworld.lintcode.easy;


import com.hiworld.lintcode.common.ListNode;

/**
 * 问题：用插入排序对链表排序
 * 样例：
 * 排序前：1->3->2->0->null
 * 排序后：0->1->2->3->null 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月19日 下午4:31:25
 */
public class SortListByInsert {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        
        node3.next = node4;
        node4.next = node1;
        
        SortListByInsert sortListByInsert = new SortListByInsert();
        ListNode head = sortListByInsert.insertionSortList(node3);
        
        ListNode.print(head);
    }
    
    /**
     * 
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        // write your code here
        ListNode node = head;
        if(null==node) {
            return node;
        }
        
        //新链表的头
        ListNode newListHead = null;
        
        ListNode loopNode = head; //
        ListNode nextLoopNode = null;
        while(null!=loopNode) {
            nextLoopNode = loopNode.next; //下一个处理结点
            loopNode.next = null; //把当前处理结点的next设置为null,避免到新的list中乱了
            
            if(null!=newListHead) {
                ListNode newListLoop = newListHead;
                ListNode before = null;
                boolean isInsert = false;
                while(null!=newListLoop) { //将比较结点,插入到新的list中
                    if(loopNode.val > newListLoop.val) {
                        before = newListLoop;
                        newListLoop = newListLoop.next;
                    } else {
                        if(null!=before) { //把比较结点,插入到新的结点的中间
                            before.next = loopNode;
                            loopNode.next = newListLoop;
                        } else { //把比较结点,插入到结点的头部
                            loopNode.next = newListLoop;
                            newListHead = loopNode; //更新新结点的head结点
                        }
                        isInsert = true; 
                        break;
                    }
                }
                
                if(!isInsert) { //如果比较结点遍历到最后没有找到存储的位置,那么就是该结点在新的list中最大,直接存储到最后
                    before.next = loopNode;
                }
            } else { //如果新List为空,那么认为当前结点就是新List的head结点
                newListHead = loopNode; 
            }
            
            loopNode = nextLoopNode; //重新赋值新的遍历结点
        }
        
        return newListHead;
    }
}
