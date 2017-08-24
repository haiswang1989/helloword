package com.hiworld.lintcode.medium;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：中序遍历和后序遍历树构造二叉树
 * 
 * 样例：
 * 给出树的中序遍历： [1,2,3] 和后序遍历： [1,3,2]
 * 
 * 返回如下树：
 *      2
 *      
 *   1      3
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月24日 下午2:14:56
 */
public class BinaryTreeBuildByInOrderAndPostOrder {
    
    public static void main(String[] args) {
        int[] inorder = {5,4,3,2,1};
        int[] postorder = {5,4,3,2,1};
        
        BinaryTreeBuildByInOrderAndPostOrder binaryTreeBuildByInOrderAndPostOrder = new BinaryTreeBuildByInOrderAndPostOrder();
        TreeNode root = binaryTreeBuildByInOrderAndPostOrder.buildTree(inorder, postorder);
        System.out.println(root);
    }
    
    /**
     *@param inorder : A list of integers that inorder traversal of a tree
     *@param postorder : A list of integers that postorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // write your code here
        if(null==inorder || null==postorder) {
            return null;
        }
        
        if(inorder.length != postorder.length || inorder.length == 0) {
            return null;
        }
        
        return buildWithRecusion(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    /**
     * 
     * 
     * @param inorder
     * @param inorderBeginIndex
     * @param inorderEndIndex
     * @param postorder
     * @param postorderBeginIndex
     * @param postorderEndIndex
     * @return
     */
    public TreeNode buildWithRecusion(int[] inorder, int inorderBeginIndex, int inorderEndIndex , int[] postorder, int postorderBeginIndex, int postorderEndIndex) {
        int length = postorderEndIndex - postorderBeginIndex + 1;
        TreeNode root = new TreeNode(postorder[postorderEndIndex]);
        if(length == 1) {
            return root;
        } else if(length == 2) {
            int rootVal = postorder[postorderEndIndex];
            if(inorder[inorderBeginIndex] == rootVal) { //右子树
                TreeNode rightNode = new TreeNode(postorder[postorderBeginIndex]);
                root.right = rightNode;
            } else { //左子树
                TreeNode leftNode = new TreeNode(postorder[postorderBeginIndex]);
                root.left = leftNode;
            }
            return root;
        } else if(length == 3) {
            handleThreeNode(inorder, inorderBeginIndex, inorderEndIndex, postorder, postorderBeginIndex, postorderEndIndex, root);
            return root;
        }
        
        int count = 0;
        for(int i=inorderBeginIndex; i<=inorderEndIndex; i++) {
            count++;
            if(inorder[i] == postorder[postorderEndIndex]) {
                break;
            }
        }
        
        int leftEndIndex = inorderBeginIndex + count - 1;
        if(inorderBeginIndex <= leftEndIndex) {
            TreeNode leftTree = buildWithRecusion(inorder, inorderBeginIndex, leftEndIndex, postorder, postorderBeginIndex, leftEndIndex);
            root.left = leftTree;
        }
        
        
        int rightBeginIndex = inorderBeginIndex + count + 1;
        if(rightBeginIndex <= inorderEndIndex) {
            TreeNode rightTree = buildWithRecusion(inorder, rightBeginIndex, inorderEndIndex, postorder, rightBeginIndex, postorderEndIndex);
            root.right = rightTree;
        }
        
        return root;
    }
    
    /**
     * 处理还剩下三个结点的情况,这个情况比较复杂
     * @param inorder
     * @param inorderBeginIndex
     * @param inorderEndIndex
     * @param postorder
     * @param postorderBeginIndex
     * @param postorderEndIndex
     * @param root
     */
    public void handleThreeNode(int[] inorder, int inorderBeginIndex, int inorderEndIndex , int[] postorder, int postorderBeginIndex, int postorderEndIndex, TreeNode root) {
        if(inorder[(inorderBeginIndex + inorderEndIndex)/2] == postorder[postorderEndIndex]) {
            TreeNode leftNode = new TreeNode(postorder[postorderBeginIndex]);
            TreeNode rightNode = new TreeNode(postorder[postorderBeginIndex+1]);
            root.left = leftNode;
            root.right = rightNode;
        } else if (inorder[inorderEndIndex] ==  postorder[postorderEndIndex]) { 
            if(inorder[inorderBeginIndex] == postorder[postorderBeginIndex]) {
                TreeNode leftChildNode = new TreeNode(postorder[postorderEndIndex-1]);
                TreeNode leftChildLeftChildNode = new TreeNode(postorder[postorderEndIndex-2]);
                root.left = leftChildNode;
                leftChildNode.left = leftChildLeftChildNode;
            } else {
                TreeNode leftChildNode = new TreeNode(postorder[postorderBeginIndex+1]);
                TreeNode leftChildRightChildNode = new TreeNode(postorder[postorderBeginIndex]);
                root.left = leftChildNode;
                leftChildNode.right = leftChildRightChildNode;
            }
        } else {
            if(inorder[inorderBeginIndex+1] == postorder[postorderBeginIndex+1]) {
                TreeNode rightChildNode = new TreeNode(inorder[inorderBeginIndex+1]);
                TreeNode rightChildRightChildNode = new TreeNode(inorder[inorderBeginIndex+2]);
                root.right = rightChildNode;
                rightChildNode.right = rightChildRightChildNode;
            } else {
                TreeNode rightChildNode = new TreeNode(inorder[inorderBeginIndex+2]);
                TreeNode rightChildLeftChildNode = new TreeNode(inorder[inorderBeginIndex+1]);
                root.right = rightChildNode;
                rightChildNode.left = rightChildLeftChildNode;
            }
        }
    }
}
