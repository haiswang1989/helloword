package com.hiworld.lintcode.medium;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：前序遍历和中序遍历树构造二叉树 
 * 描述：根据前序遍历和中序遍历树构造二叉树.
 * 
 * 样例：
 * 给出中序遍历：[1,2,3]和前序遍历：[2,1,3]. 返回如下的树:
 * 
 * 		2
 *    *   *
 * 	1		3
 * 
 * 
 * @author Administrator
 *
 */
public class BinaryTreeBuildByInPreOrderAndInOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] preorder = {1,2,3,4,5};
		int[] inorder = {5,4,3,2,1};
		
		BinaryTreeBuildByInPreOrderAndInOrder binaryTreeBuildByInPreOrderAndInOrder = new BinaryTreeBuildByInPreOrderAndInOrder();
		TreeNode root = binaryTreeBuildByInPreOrderAndInOrder.buildTree(preorder, inorder);
		System.out.println(root);
	}
	
	/**
	 * 
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
    	if(null==preorder || null==inorder) {
    		return null;
    	}
    	
    	if(preorder.length != inorder.length || inorder.length == 0) {
    		return null;
    	}
    	
    	return buildWithRecusion(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    
    /**
     * 
     * @param preorder
     * @param preorderBeginIndex
     * @param preorderEndIndex
     * @param inorder
     * @param inorderBeginIndex
     * @param inorderEndIndex
     * @return
     */
    public TreeNode buildWithRecusion(int[] preorder, int preorderBeginIndex, int preorderEndIndex, int[] inorder, int inorderBeginIndex, int inorderEndIndex) {
    	int length = preorderEndIndex - preorderBeginIndex + 1;
    	
    	TreeNode root = new TreeNode(preorder[preorderBeginIndex]);
    	if(1==length) {
    		return root;
    	} else if(2==length) {
    		if(preorder[preorderBeginIndex] == inorder[inorderBeginIndex]) {
    			TreeNode rightChildNode = new TreeNode(preorder[preorderEndIndex]);
    			root.right = rightChildNode;
    		} else {
    			TreeNode leftChildNode = new TreeNode(preorder[preorderEndIndex]);
    			root.left = leftChildNode;
    		}
    		return root;
    	} else if(3==length) {
    		handleThreeNode(preorder, preorderBeginIndex, preorderEndIndex, inorder, inorderBeginIndex, inorderEndIndex, root);
    		return root;
    	}
    	
    	int count = 0;
    	for(int i=inorderBeginIndex; i<=inorderEndIndex; i++) {
    		count++;
    		if(inorder[i] == preorder[preorderBeginIndex]) {
    			break;
    		}
    	}
    	
    	int leftCount = count - 1;
    	int rightCount = length - count;
    	
    	if(leftCount != 0) {
    		TreeNode leftNode = buildWithRecusion(preorder, preorderBeginIndex + 1, preorderBeginIndex + leftCount, inorder, inorderBeginIndex, inorderBeginIndex + leftCount - 1);
    		root.left = leftNode;
    	}
    	
    	if(rightCount != 0) {
    		TreeNode rightNode = buildWithRecusion(preorder, preorderEndIndex - rightCount + 1, preorderEndIndex, inorder, inorderEndIndex - rightCount + 1, inorderEndIndex);
    		root.right = rightNode;
    	}
    	
    	return root;
    }
    
    /**
     * 处理还剩下三个结点的情况,这个情况比较复杂
     * @param preorder
     * @param preorderBeginIndex
     * @param preorderEndIndex
     * @param inorder
     * @param inorderBeginIndex
     * @param inorderEndIndex
     * @param root
     */
    public void handleThreeNode(int[] preorder, int preorderBeginIndex, int preorderEndIndex , int[] inorder, int inorderBeginIndex, int inorderEndIndex, TreeNode root) {
        if(preorder[preorderEndIndex] == inorder[inorderEndIndex]) {
            if(preorder[preorderBeginIndex] == inorder[inorderBeginIndex]) {
            	TreeNode rightChildNode = new TreeNode(preorder[preorderBeginIndex+1]);
            	TreeNode rightChildRightChildNode = new TreeNode(preorder[preorderBeginIndex+2]);
            	root.right = rightChildNode;
            	rightChildNode.right = rightChildRightChildNode;
            } else {
            	TreeNode leftChildNode = new TreeNode(preorder[preorderBeginIndex+1]);
            	TreeNode rightChildNode = new TreeNode(preorder[preorderBeginIndex+2]);
            	root.left = leftChildNode;
            	root.right = rightChildNode;
            }
        } else if (preorder[preorderBeginIndex] == inorder[inorderEndIndex]) { 
            if(preorder[preorderEndIndex] == inorder[inorderBeginIndex]) {
            	TreeNode leftChildNode = new TreeNode(preorder[preorderBeginIndex+1]);
            	TreeNode leftChildLeftChildNode = new TreeNode(preorder[preorderBeginIndex+2]);
            	root.left = leftChildNode;
            	leftChildNode.left = leftChildLeftChildNode;
            } else {
            	TreeNode leftChildNode = new TreeNode(preorder[preorderBeginIndex+1]);
            	TreeNode leftChildRightChildNode = new TreeNode(preorder[preorderBeginIndex+2]);
            	root.left = leftChildNode;
            	leftChildNode.right = leftChildRightChildNode;
            }
        } else {
        	TreeNode rightChildNode = new TreeNode(preorder[preorderBeginIndex+1]);
        	TreeNode rightChildLeftChildNode = new TreeNode(preorder[preorderBeginIndex+2]);
        	root.right = rightChildNode;
        	rightChildNode.left = rightChildLeftChildNode;
        }
    }
}
