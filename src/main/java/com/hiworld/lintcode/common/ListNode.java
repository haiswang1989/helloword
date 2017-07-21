package com.hiworld.lintcode.common;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    
    @Override
    public String toString() {
        return val + "";
    }
    
    public static void print(ListNode node) {
        if(null == node) {
            System.out.println("NULL");
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        node = node.next;
        while(null!=node) {
            sb.append("->");
            sb.append(node.val);
            node = node.next;
        }
        
        System.out.println(sb.toString());
    }
}
