package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.List;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 二叉树的"前序遍历"(不管是前序,中序,后序都是针对根结点的而言的)
 * 
 * 根节点->左子树->右子树
 * 
 * 样例：
 *      1
 *        *      
 *          2
 *        *
 *      3
 * 
 * 返回:[1,2,3]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月6日 上午11:09:38
 */
public class BinaryTreePreorderTraversal {
    
    public static void main(String[] args) {
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        
        node1.left = node2;
        node1.right = node3;
        
        BinaryTreePreorderTraversal binaryTreePreorderTraversal = new BinaryTreePreorderTraversal();
        ArrayList<Integer> result = binaryTreePreorderTraversal.preorderTraversal(node1);
        for (Integer nodeVal : result) {
            System.out.print(nodeVal + "->");
        }
        System.out.println("");
    }
    
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        if(null == root) {
            return null;
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        withRecursion(root, result);
        return result;
    }
    
    /**
     * 递归方式
     * @param node
     * @param result
     */
    public void withRecursion(TreeNode node, List<Integer> result) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        result.add(node.val);
        
        if(null == leftNode && null == rightNode) {
            return;
        }
        
        if(null != leftNode) {
            withRecursion(leftNode, result);
        }
        
        if(null!=rightNode) {
            withRecursion(rightNode, result);
        }
    }
    
    /**
     * 非递归方式
     * @return
     */
    public void withOutRecursion(TreeNode node, List<Integer> result) {
        
    }
}
