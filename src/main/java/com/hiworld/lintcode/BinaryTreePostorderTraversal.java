package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 二叉树的"后序遍历"(不管是前序,中序,后序都是针对根结点的而言的)
 * 
 * 左子树->右子树->根节点
 * 
 * 样例：
 *      1
 *        *      
 *          2
 *        *
 *      3
 * 
 * 返回:[3,2,1]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月6日 上午11:09:38
 */
public class BinaryTreePostorderTraversal {
    
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        
//        node1.left = node2;
//        node1.right = node3;
        
        node1.right = node2;
        node2.left = node3;
        
        
        BinaryTreePostorderTraversal binaryTreePostorderTraversal = new BinaryTreePostorderTraversal();
        ArrayList<Integer> result = binaryTreePostorderTraversal.postorderTraversal(node1);
        for (Integer nodeVal : result) {
            System.out.print(nodeVal + "->");
        }
        System.out.println("");
    }
    
    /**
     * 
     * @param root
     * @return
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        if(null == root) {
            return null;
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        withRecursion(root, result);
//        withOutRecursion(root, result);
        return result;
    }
    
    /**
     * 递归方式
     * @param node
     * @param result
     */
    public void withRecursion(TreeNode node, ArrayList<Integer> result) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        
        if(null == leftNode && null == rightNode) { //叶子结点,如果是叶子结点,那么直接进行打印,如果这边不打印,那么叶子结点就无法打印
            result.add(node.val);
            return;
        } else {
            if(null != leftNode) { //先遍历左子树
                withRecursion(leftNode, result);
            }
            
            if(null!=rightNode) { //再遍历右子树
                withRecursion(rightNode, result);
            }
            
            result.add(node.val); //再根结点
        }
    }
    
    /**
     * 非递归方式
     * @param node
     * @param result
     * @return
     */
    public void withOutRecursion(TreeNode node, ArrayList<Integer> result) {
        
        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> hasVisit = new HashSet<>(); //存放已经访问过得结点
        
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        
        while(0!=stack.size() || null!=node) {
            if(null!=node) {
                leftNode = node.left; //左结点
                rightNode = node.right; //右结点
                
                if(null == leftNode && null == rightNode) {
                    hasVisit.add(node);
                    result.add(node.val);
                    node = null;
                } else {
                    if(!hasVisit.contains(leftNode) && !hasVisit.contains(rightNode)) { //如果该结点的左右结点都访问过,那么直接访问该结点,不然将当前结点和他的右结点压栈
                        stack.push(node);
                        if(null!=rightNode) {
                            stack.push(rightNode);
                        }
                        node = leftNode;
                    } else {
                        hasVisit.add(node);
                        result.add(node.val);
                        node = null;
                    }
                }
            } else {
                node = stack.pop();
            }
        }
    }
}
