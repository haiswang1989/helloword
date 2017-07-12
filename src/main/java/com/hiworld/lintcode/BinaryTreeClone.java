package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.List;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 克隆二叉树
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月11日 上午9:53:21
 */
public class BinaryTreeClone {

    public static void main(String[] args) {
        
    }
    
    /**
     * clone二叉树
     * @param root
     * @return
     */
    public TreeNode cloneTree(TreeNode root) {
        // Write your code here
        return cloneTreeWithRecursion(root);
    }
    
    
    /**
     * 递归的方式克隆二叉树
     * @param root
     * @return
     */
    public TreeNode cloneTreeWithRecursion(TreeNode node) {
        if(null == node) {
            return null;
        }
        
        TreeNode cloneNode = new TreeNode(node.val);
        TreeNode leftTree = node.left;
        TreeNode rightTree = node.right;
        
        TreeNode leftTreeClone = cloneTreeWithRecursion(leftTree);
        TreeNode rightTreeClone = cloneTreeWithRecursion(rightTree);
        
        cloneNode.left = leftTreeClone;
        cloneNode.right = rightTreeClone;
        
        return cloneNode;
    }
    
    /**
     * 使用非递归的方式克隆二叉树
     * @param node
     * @return
     */
    public TreeNode cloneTreeWithOutRecursion(TreeNode node) {
        //TODO
        if(null == node) {
            return null;
        }
        
        List<TreeNode> beforeTwoLevel = new ArrayList<>(); //父
        List<TreeNode> beforeOneLevel = new ArrayList<>(); //子
        boolean beforeOneLevelIsAllNull = false;
        
        beforeTwoLevel.add(node);
        
        while(!beforeOneLevelIsAllNull) {
            
        }
        
        return null;
    }
    
    /**
     * 
     * @param node
     * @return
     */
    public TreeNode cloneNode(TreeNode node) {
        TreeNode treeNode = new TreeNode(node.val);
        return treeNode;
    }
}
