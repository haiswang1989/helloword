package com.hiworld.lintcode;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：
 * 翻转一颗二叉树
 * 
 * 样例：
 *      1                           1
 *    *    *                      *   *
 *  2        3    -------->     3       2
 *          *                     *
 *         4                       4
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月26日 下午4:17:29
 */
public class BinaryTreeInvert {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        
        
        BinaryTreeInvert binaryTreeInvert = new BinaryTreeInvert();
        binaryTreeInvert.invertBinaryTree(node1);
        System.out.println(node1);
    }
    
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        if(null==root) {
            return;
        }
        
        withRecusion(root);
    }
    
    /**
     * 递归实现
     * @param root
     */
    public void withRecusion(TreeNode node) {
        
        TreeNode leftTree = node.left;
        TreeNode rightTree = node.right;
        
        if(null!=leftTree) {
            withRecusion(leftTree);
        }
        
        if(null!=rightTree) {
            withRecusion(rightTree);
        }
        
        node.left = rightTree;
        node.right = leftTree;
    }
    
    /**
     * 非递归实现
     * @param root
     */
    public void withOutRecusion(TreeNode root) {
        //TODO
    }
    
}
