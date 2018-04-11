package com.for4.mt;

import java.util.LinkedList;

/**
 * 
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月10日 上午11:16:59
 */
public class TreeNodeAddNext {

    public static void main(String[] args) {
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        
        TreeNodeAddNext treeNodeAddNext = new TreeNodeAddNext();
        treeNodeAddNext.addNext(node1);
        
        System.out.println("111");
        
    }
    
    public void addNext(TreeNode treeNode) {
        LinkedList<TreeNode> currentQueue = new LinkedList<>();
        LinkedList<TreeNode> nextQueue = new LinkedList<>();
        currentQueue.offer(treeNode);
        TreeNode beforeNode = null;
        while(currentQueue.size()!=0) {
            TreeNode node = currentQueue.poll();
            
            if(null!=node.left) {
                nextQueue.offer(node.left);
            }
            
            if(null!=node.right) {
                nextQueue.offer(node.right);
            }
            
            if(null!=beforeNode) {
                beforeNode.next = node;
                
            } 
            
            beforeNode = node;
            if(currentQueue.size()==0) {
                beforeNode = null;
                LinkedList<TreeNode> tmp = currentQueue;
                currentQueue = nextQueue;
                nextQueue = tmp;
            }
        }
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月10日 上午11:19:13
 */
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;
    public TreeNode(int val) {
        this.val = val;
        this.left = null; 
        this.right = null;
        this.next = null;
    }
}
