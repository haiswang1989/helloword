package com.hiworld.lintcode;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 
 * 问题：给一个排序数组（从小到大），将其转换为一棵高度最小的排序二叉树。
 * 
 * 样例：
 * 给出数组 [1,2,3,4,5,6,7],返回
 *          4
 *        *   *
 *       2     6
 *      * *   * *
 *     1   3 5   7
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月12日 上午9:40:24
 */
public class BinaryTreeConvertSortedArrayToBST {
    
    public static void main(String[] args) {
        
        BinaryTreeConvertSortedArrayToBST binaryTreeConvertSortedArrayToBST = new BinaryTreeConvertSortedArrayToBST();
        int[] A = new int[]{-1,1};
        TreeNode treeNode = binaryTreeConvertSortedArrayToBST.sortedArrayToBST(A);
        System.out.println(treeNode);
    }
    
    public TreeNode sortedArrayToBST(int[] A) {  
        // write your code here
        if(null==A || 0==A.length) {
            return null;
        }
        
        return convertWithRecursion(A, 0, A.length - 1);
    }
    
    /**
     * 采用递归的方式来进行转换
     * @param A
     * @return
     */
    public TreeNode convertWithRecursion(int[] A, int fromIndex, int endIndex) {
        int nodeCount = endIndex - fromIndex + 1;
        
        if(nodeCount == 1) {
            return new TreeNode(A[fromIndex]);
        } else if(nodeCount == 2) {
            TreeNode rootNode = new TreeNode(A[endIndex]);
            TreeNode leftNode = new TreeNode(A[fromIndex]);
            rootNode.left = leftNode;
            return rootNode;
        } else if(nodeCount == 3) {
            TreeNode rootNode = new TreeNode(A[fromIndex+1]);
            TreeNode leftNode = new TreeNode(A[fromIndex]);
            TreeNode rightNode = new TreeNode(A[endIndex]);
            rootNode.left = leftNode;
            rootNode.right = rightNode;
            return rootNode;
        }
        
        int middleIndex = (fromIndex + endIndex) / 2;
        
        TreeNode rootNode = new TreeNode(A[middleIndex]);
        
        TreeNode leftTree = convertWithRecursion(A, fromIndex, middleIndex-1);
        TreeNode rightTree = convertWithRecursion(A, middleIndex+1, endIndex);
        
        rootNode.left = leftTree;
        rootNode.right = rightTree;
        return rootNode;
    }
}
