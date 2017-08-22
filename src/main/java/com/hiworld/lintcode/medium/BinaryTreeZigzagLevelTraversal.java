package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：二叉树的锯齿形层次遍历
 * 描述：给出一棵二叉树，返回其节点值的锯齿形层次遍历（先从左往右，下一层再从右往左，层与层之间交替进行） 
 * 
 * 样例：
 * 给出一棵二叉树 {3,9,20,#,#,15,7},
 *          3
 *       *      *  
 *     9          20
 *              *    *
 *             15      7
 *             
 * 返回值：
 * [
 *  [3],
 *  [20,9],
 *  [15,7]
 * ]
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月22日 下午2:18:10
 */
public class BinaryTreeZigzagLevelTraversal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node7 = new TreeNode(7);
    	TreeNode node9 = new TreeNode(9);
    	TreeNode node15 = new TreeNode(15);
    	TreeNode node20 = new TreeNode(20);
    	
    	node3.left = node9;
    	node3.right = node20;
    	node20.left = node15;
    	node20.right = node7;
    	
    	BinaryTreeZigzagLevelTraversal binaryTreeZigzagLevelTraversal = new BinaryTreeZigzagLevelTraversal();
    	List<List<Integer>> rests = binaryTreeZigzagLevelTraversal.zigzagLevelOrder(node3);
    	for (List<Integer> list : rests) {
			System.out.println(Arrays.toString(list.toArray()));
		}
    }
    
    /*
     * @param root: A Tree
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
    	if(null==root) {
    		return new ArrayList<>();
    	}
    	
    	List<List<Integer>> rets = new ArrayList<>();
    	
    	Stack<TreeNode> stackForward = new Stack<>(); //正向
    	Stack<TreeNode> stackBackward = new Stack<>(); //反向
    	stackBackward.push(root);
    	
    	boolean forward = true; //正向
    	while(0!=stackBackward.size() || 0!=stackForward.size()) {
    		if(forward) {
    			stackForward.clear();
    			List<Integer> ret = new ArrayList<>();
    			while(0!=stackBackward.size()) {
    				TreeNode node = stackBackward.pop();
    				ret.add(node.val);
    				TreeNode leftNode = node.left; //正向先左后右
    				if(null!=leftNode) {
    					stackForward.push(leftNode);
    				}
    				
    				TreeNode rightNode = node.right;
    				if(null!=rightNode) {
    					stackForward.push(rightNode);
    				}
    			}
    			rets.add(ret);
    		} else {
    			stackBackward.clear();
    			List<Integer> ret = new ArrayList<>();
    			while(0!=stackForward.size()) {
    				TreeNode node = stackForward.pop();
    				ret.add(node.val);
    				TreeNode rightNode = node.right; //反向先右后左
    				if(null!=rightNode) {
    					stackBackward.push(rightNode);
    				}
    				
    				TreeNode leftNode = node.left;
    				if(null!=leftNode) {
    					stackBackward.push(leftNode);
    				}
    			}
    			rets.add(ret);
    		}
    		
    		forward = !forward; //切换方向
    	}
    	
        return rets;
    }
}
