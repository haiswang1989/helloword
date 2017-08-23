package com.hiworld.lintcode.medium;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：二叉树中的最大路径和
 * 描述：给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束（路径和为两个节点之间所在路径上的节点权值之和）
 * 
 * 样例：
 * 		1
 *    *   *
 *   2     3
 *   
 *   返回：6
 * 
 * 
 * @author Administrator
 *
 */
public class BinaryTreeMaxPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	    
	    BinaryTreeMaxPathSum binaryTreeMaxPathSum = new BinaryTreeMaxPathSum();
	    int ret = binaryTreeMaxPathSum.maxPathSum(node1);
	    System.out.println("ret : " + ret);
	}
	
	/*
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public int maxPathSum(TreeNode root) {
        // write your code here
        if(null==root) {
            return 0;
        } else if(isLeaf(root)) {
            return root.val;
        }
        
        int leftMaxPath = 0;
        TreeNode leftTree = root.left;
        if(null!=leftTree) {
            leftMaxPath += maxPathWithRecusion(leftTree);
        }
        
        int rightMaxPath = 0;
        TreeNode rightTree = root.right;
        if(null!=rightTree) {
            rightMaxPath += maxPathWithRecusion(rightTree);
        }
        
        
        
    	return leftMaxPath+rightMaxPath+root.val;
    }
    
    /**
     * 递归方式实现
     * @param treeHead
     * @return
     */
    public int maxPathWithRecusion(TreeNode treeHead) {
        
        if(isLeaf(treeHead)) {
            return treeHead.val;
        }
        
        int leftTreeMaxLength = treeHead.val;
        TreeNode leftTree = treeHead.left;
        if(null!=leftTree) {
            leftTreeMaxLength += maxPathWithRecusion(leftTree);
        }
        
        int rightTreeMaxLength = treeHead.val;
        TreeNode rightTree = treeHead.right;
        if(null!=rightTree) {
            rightTreeMaxLength += maxPathWithRecusion(rightTree);
        }
        
        return max(leftTreeMaxLength, rightTreeMaxLength, treeHead.val);
    }
    
    /**
     * 判断是否是叶子节点
     * @param treeNode
     * @return
     */
    boolean isLeaf(TreeNode treeNode) {
        return null==treeNode.left && null==treeNode.right;
    }
    
    /**
     * 
     * @param input1
     * @param input2
     * @param input3
     * @return
     */
    public int max(int input1, int input2, int input3) {
        return Math.max(input1, Math.max(input2, input3));
    }
}
