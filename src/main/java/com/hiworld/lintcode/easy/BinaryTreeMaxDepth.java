package com.hiworld.lintcode.easy;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：二叉树的最大深度
 * 描述：给定一个二叉树，找出其最大深度，二叉树的深度为根节点到最远叶子节点的距离。
 * 
 * 样例：
 *      1
 *    *   *
 *   2      3
 *         * *
 *        4   5
 *        
 *  返回：3
 *        
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月28日 上午11:31:47
 */
public class BinaryTreeMaxDepth {

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
        
        BinaryTreeMaxDepth binaryTreeMaxDepth = new BinaryTreeMaxDepth();
        int maxDepth = binaryTreeMaxDepth.maxDepth(node1);
        System.out.println("Max Depth : " + maxDepth);
    }
    
    /**
     * 
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        // write your code here
        if(null==root) {
            return 0;
        }
        
        return withRecusion(root);
    }
    
    /**
     * 非递归的方式
     * @param Node
     * @return
     */
    public int withOutRecusion(TreeNode Node) {
        //TODO
        
        return 0;
    }
    
    /**
     * 递归方式实现
     * @param node
     * @return
     */
    public int withRecusion(TreeNode node) {
        TreeNode leftTree = node.left;
        TreeNode rightTree = node.right;
        if(null!=leftTree && null!=rightTree) {
            return Math.max(withRecusion(leftTree),withRecusion(rightTree)) + 1;
        } else if(null!=leftTree && null==rightTree) {
            return withRecusion(leftTree) + 1;
        } else if(null==leftTree && null!=rightTree) {
            return withRecusion(rightTree) + 1;
        } else {
            return 1;
        }
    }
}
