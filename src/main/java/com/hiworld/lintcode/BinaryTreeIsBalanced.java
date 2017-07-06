package com.hiworld.lintcode;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 判断一个二叉树是否"高度"平衡
 * 
 * 定义：一个二叉树的两个子树的深度相差不会超过1
 *       3                 3
 *    9     20                 20
 *        15   7             15   7
 *       A                 B
 *       
 * A是高度平衡,B不是高度平衡
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月5日 下午1:05:35
 */
public class BinaryTreeIsBalanced {

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        
        node20.left = node15;
        node20.right = node7;
        node3.left = node9;
        node3.right = node20;
        
        BinaryTreeIsBalanced binaryTreeIsBalanced = new BinaryTreeIsBalanced();
        System.out.println(binaryTreeIsBalanced.isBalanced(node3));
        
        node3.left = null;
        System.out.println(binaryTreeIsBalanced.isBalanced(node3));
    }
    
    /**
     * 
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        
        if(null == root) { //这边需要注意,如果开始传过来的node为null,那么这棵树也是"高度"平衡的
            return true;
        }
        
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        
        //如果左结点和右结点只要有一个为null,那么就可以判定这棵树是不是"高度"平衡
        //只要另一个非null的结点,存在任意一个孩子结点,那么这棵树就不是"高度"平衡
        if(null==leftNode || null==rightNode) { 
            if(null!=leftNode) {
                return !(null!=leftNode.left || null!=leftNode.right);
            } else if(null!=rightNode) {
                return !(null!=rightNode.left || null!=rightNode.right);
            } else { //需要注意的是,如果左右孩子结点都是null,那么这颗树也是"高度"平衡
                return true;
            }
        }
        
        return isBalanced(leftNode) && isBalanced(rightNode); //递归调用,看左右子树是否是"高度"平衡
    }
}
