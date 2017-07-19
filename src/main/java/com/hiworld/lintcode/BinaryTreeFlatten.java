package com.hiworld.lintcode;

import java.util.ArrayList;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：将一棵二叉树按照前序遍历拆解成为一个假链表。所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
 * 
 * 样例：
 *          1
 *       2      5
 *     3   4  #    6
 * 转换成：
 *      1
 *        2
 *          3
 *            4
 *              5
 *                6
 *      
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月13日 下午5:34:44
 */
public class BinaryTreeFlatten {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        
        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.right = node6;
        
        BinaryTreeFlatten binaryTreeFlatten = new BinaryTreeFlatten();
        binaryTreeFlatten.flatten(node1);
        System.out.println("lintcode...");
    }
    
    /**
     * 
     * @param root
     */
    public void flatten(TreeNode root) {
        // write your code here
        if(null == root) {
            return;
        }
        
        //TODO
        
    }
    
    public void doFlatten(TreeNode node) {
        
    }
    
    /**
     * 判断是不是叶子结点
     * @param node
     * @return
     */
    public boolean isLeaf(TreeNode node) {
        if(null==node.left && null==node.right) {
            return true;
        }
        
        return false;
    }
}
