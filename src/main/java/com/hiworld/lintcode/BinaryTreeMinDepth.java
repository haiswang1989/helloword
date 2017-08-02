package com.hiworld.lintcode;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：二叉树的最小深度
 * 描述：给定一个二叉树，找出其最小深度。二叉树的最小深度为根节点到最近叶子节点的距离。
 * 
 * 样例：
 *          1
 *        *    *  
 *      2        3
 *             *   *
 *            4     5
 *            
 * 这个二叉树的最小深度为2
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月1日 下午4:58:10
 */
public class BinaryTreeMinDepth {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        
        BinaryTreeMinDepth binaryTreeMinDepth = new BinaryTreeMinDepth();
        int ret = binaryTreeMinDepth.minDepth(node1);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if(null == root) {
            return 0;
        }
        
        return withRecusion(root);
    }
    
    /**
     * 递归实现
     * @param node
     * @return
     */
    public int withRecusion(TreeNode node) {
        TreeNode leftTree = node.left;
        TreeNode rightTree = node.right;
        
        if(null==leftTree && null==rightTree) { //注意叶子结点的定义,就是左右孩子都是null,那么就是叶子结点
            return 1;
        } else if(null!=leftTree && null!=rightTree) { //左右孩子都不是null,那么递归计算
            int leftMinDepth = withRecusion(leftTree);
            int rightMinDepth =  withRecusion(rightTree);
            return Math.min(leftMinDepth, rightMinDepth) + 1;
        } else if(null!=leftTree) { //左子树不为null
            return withRecusion(leftTree) + 1; 
        } else { //右子树不为null
            return withRecusion(rightTree) + 1;
        }
    }
}
