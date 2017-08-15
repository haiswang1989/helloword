package com.hiworld.lintcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
//        withRecursion(root, result);
        withOutRecursion(root, result);
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
     * @param node
     * @param result
     */
    public void withOutRecursion(TreeNode node, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        while(stack.size() != 0 || null!=node) {
            if(null!=node) {
                TreeNode rightNode = node.right; 
                result.add(node.val); //打印当前结点
                node = node.left; //遍历当前结点的左结点
                if(null!=rightNode) {//只要有右结点都会push到栈中
                    stack.push(rightNode);
                }
            } else {
                node = stack.pop(); //如果遍历到叶子结点了,那么就从栈中pop出来一个(右)结点进行遍历
            }
        }
    }
}
