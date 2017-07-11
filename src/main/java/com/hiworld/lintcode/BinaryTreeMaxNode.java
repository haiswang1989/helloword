package com.hiworld.lintcode;

import java.util.Stack;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 在二叉树中寻找值最大的节点并返回
 * 
 * 这个问题其实就是一个二叉树的遍历
 * 随便使用 先序,中序,后续
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月11日 下午2:07:15
 */
public class BinaryTreeMaxNode {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        
        node1.left = node2;
        node1.right = node3;
        
        BinaryTreeMaxNode binaryTreeMaxNode = new BinaryTreeMaxNode();
        TreeNode maxNode = binaryTreeMaxNode.maxNode(node1);
        System.out.println(maxNode.val);
    }
    
    /**
     * 二叉树种最大的结点
     * 这边直接使用了一个先序遍历
     * @param root
     * @return
     */
    public TreeNode maxNode(TreeNode root) {
        // Write your code here
        if(null == root) {
            return null;
        }
        
        int max = root.val;
        TreeNode maxNode = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(stack.size()!=0 || null!=node) {
            if(null!=node) {
                TreeNode leftNode = node.left;
                TreeNode rightNode = node.right;
                int tmpVal = node.val;
                if(tmpVal > max) {
                    max = tmpVal;
                    maxNode = node;
                }
                node = leftNode;
                if(null!=rightNode) {
                    stack.push(rightNode);
                }
            }  else {
                node = stack.pop();
            }
        }
        
        return maxNode;
    }
}
