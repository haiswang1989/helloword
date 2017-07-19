package com.hiworld.lintcode;

import com.hiworld.lintcode.common.TreeNode;

import sun.org.mozilla.javascript.internal.Node;

/**
 * 
 * 问题：检查两棵二叉树是否等价。等价的意思是说，首先两棵二叉树必须拥有相同的结构，并且每个对应位置上的节点上的数都相等。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月18日 上午9:59:29
 */
public class BinaryTreeIsIdentical {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        BinaryTreeIsIdentical binaryTreeIsIdentical = new BinaryTreeIsIdentical();
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2_1 = new TreeNode(2);
        TreeNode node2_2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        
        node1.left = node2_1;
        node1.right = node2_2;
        node2_1.left = node4;
        
        boolean isSame = binaryTreeIsIdentical.isIdentical(node1, node1);
        System.out.println("是否完全相同 ： " + isSame);
    } 
    
    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        return withRecursion(a, b);
    }
    
    /**
     * 递归实现
     * @param a
     * @param b
     * @return
     */
    public boolean withRecursion(TreeNode a, TreeNode b) {
        if(null==a && b==null) {
            return true;
        } else if(null!=a && null!=b) {
            if(a.val == b.val) {
                TreeNode aLeftTree = a.left;
                TreeNode bLeftTree = b.left;
                
                TreeNode aRightTree = a.right;
                TreeNode bRightTree = b.right;
                return withRecursion(aLeftTree, bLeftTree) && withRecursion(aRightTree, bRightTree);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}   
