package com.hiworld.lintcode;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：
 * 给定一棵二叉查找树和一个新的树节点，将节点插入到树中,你需要保证该树仍然是一棵二叉查找树。
 *    
 * 样例：
 * 在原来的一个二叉查找树上插入一个6
 *          2                  2
 *       1     4   ---->    1     4
 *           3                  3   6
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月19日 下午1:51:58
 */
public class BinarySearchTreeInsertNode {

    public static void main(String[] args) {
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        
        TreeNode node6 = new TreeNode(6);
        
        node2.left = node1;
        node2.right = node4;
        node4.left = node3;
        
        BinarySearchTreeInsertNode binarySearchTreeInsertNode = new BinarySearchTreeInsertNode();
        binarySearchTreeInsertNode.insertNode(node2, node6);
        
        System.out.println(node2);
    }
    
    /**
     * 
     * @param root
     * @param node
     * @return
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if(null == node) {
            return root;
        }
        
        if(null == root) {
            return node;
        }
        
        withRecusion(root, node);
        
        return root;
    }
    
    /**
     * 递归方式
     * @param rootNode
     * @param addNode
     */
    public void withRecusion(TreeNode rootNode, TreeNode addNode) {
        int rootVal = rootNode.val;
        int addVal = addNode.val;
        
        TreeNode leftTree = rootNode.left;
        TreeNode rightTree = rootNode.right;
        
        if(addVal < rootVal) {
            if(null == leftTree) { //所有的插入都只会插入到叶子结点
                rootNode.left = addNode;
            } else {
                withRecusion(leftTree, addNode);
            }
        } else {
            if(null == rightTree) { //所有的插入都只会插入到叶子结点
                rootNode.right = addNode;
            } else {
                withRecusion(rightTree, addNode);
            }
        }
    }
    
    /**
     * 非递归方式
     * @param rootNode
     * @param addNode
     */
    public void withOutRecusion(TreeNode rootNode, TreeNode addNode) {
        //TODO
    }
}
