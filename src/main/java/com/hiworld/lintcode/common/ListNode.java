package com.hiworld.lintcode.common;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    
    public static String print(ListNode node) {
        if(null == node) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        node = node.next;
        while(null!=node) {
            sb.append("->");
            sb.append(node.val);
            node = node.next;
        }
        
        return sb.toString();
    }
}
