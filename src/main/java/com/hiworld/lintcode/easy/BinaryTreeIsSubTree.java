package com.hiworld.lintcode.easy;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：子树 
 * 描述：有两个不同大小的二进制树： T1 有上百万的节点； T2 有好几百的节点。请设计一种算法，判定 T2 是否为 T1的子树。
 * 注意事项：若 T1 中存在从节点 n 开始的子树与 T2 相同，我们称 T2 是 T1 的子树。也就是说，如果在 T1 节点 n 处将树砍断，砍断的部分将与 T2 完全相同。
 * 
 * 样例：
 *          1                     3
 *        *    *                *
 *      2        3            4           是的
 *              *
 *            4
 *            
 *          1                   3
 *        *   *                   *
 *      2       3                   4     不是
 *             *
 *            4
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月11日 上午11:36:07
 */
public class BinaryTreeIsSubTree {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TreeNode t11 = new TreeNode(1);
        TreeNode t12 = new TreeNode(2);
        TreeNode t13 = new TreeNode(3);
        TreeNode t14 = new TreeNode(4);
        
        TreeNode t23 = new TreeNode(3);
        TreeNode t24 = new TreeNode(4);
        
        t11.left = t12;
        t11.right = t13;
        t13.left = t14;
        
        t23.right = t24;
        
        BinaryTreeIsSubTree binaryTreeIsSubTree = new BinaryTreeIsSubTree();
        boolean ret = binaryTreeIsSubTree.isSubtree(t11, t23);
        System.out.println("ret : " + ret);
    }
    
    /*
     * @param T1: The roots of binary tree T1.
     * @param T2: The roots of binary tree T2.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        // write your code here
        if(null == T2) {
            return true;
        }
        
        return withRecusion(T1, T2);
    }
    
    /**
     * 递归找相同的头结点
     * @param T1
     * @param T2
     */
    public boolean withRecusion(TreeNode T1, TreeNode T2) {
        if(null==T1 && null==T2) {
            return true;
        } else if(null!=T1 && null==T2) {
            return false;
        } else if(null==T1 && null!=T2) {
            return false;
        }
        
        if(T1.val == T2.val) {
            if(equalsWithRecusion(T1, T2)) { //如果这边返回true,那么表示找到了
                return true;
            } //如果返回false,那么不能直接退出,需要继续往下遍历,找到新的根结点
        } 
            
        return withRecusion(T1.left, T2) || withRecusion(T1.right, T2); //这边只要左右子树,有一个相等就ok
        
    }
    
    /**
     * 递归做equals比较
     * @param T1
     * @param T2
     * @return
     */
    public boolean equalsWithRecusion(TreeNode T1, TreeNode T2) {
        if(null==T1 && null==T2) {
            return true;
        } else if(null!=T1 && null==T2) {
            return false;
        } else if(null==T1 && null!=T2) {
            return false;
        } else if(T1.val != T2.val) { //如果不相等,直接就不是子树
            return false;
        }
        
        return equalsWithRecusion(T1.left, T2.left) && equalsWithRecusion(T1.right,T2.right); //递归比较
    }

}
