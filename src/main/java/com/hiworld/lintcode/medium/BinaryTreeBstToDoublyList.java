package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import com.hiworld.lintcode.common.DoublyListNode;
import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：将二叉查找树转换成双链表
 * 描述：将一个二叉查找树按照中序遍历转换成双向链表。
 * 
 * 样例：
 * 			4
 * 	      *   *
 * 		2		5		
 *     * *
 * 	  1   3
 * 
 * 	  1<->2<->3<->4<->5
 * 
 * @author Administrator
 *
 */
public class BinaryTreeBstToDoublyList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		
		node4.left = node2;
		node4.right = node5;
		node2.left = node1;
		node2.right = node3;
		
		BinaryTreeBstToDoublyList binaryTreeBstToDoublyList = new BinaryTreeBstToDoublyList();
		DoublyListNode node = binaryTreeBstToDoublyList.bstToDoublyList(node4);
		System.out.println("----");
	}
	
	/**
	 * 
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        // Write your code here
    	if(null==root) {
    		return null;
    	}
    	
//    	return withRecusion(root);
    	
    	return withStack(root);
    }
    
    /**
     * 使用栈的方式,直接构造返回的链表 
     * @param root
     * @return
     */
    public DoublyListNode withStack(TreeNode root) {
    	Stack<TreeNode> stack = new Stack<>(); 
    	HashSet<TreeNode> hasVisit = new HashSet<>(); //存储访问过的节点
    	TreeNode node = root;
    	
    	DoublyListNode before = null; //前一个节点
    	DoublyListNode head = null; //开始节点
    	
    	while(null!=node || 0!=stack.size()) {
    		if(null!=node) {
    			TreeNode leftNode = node.left;
    			if(null!=leftNode) {
    				stack.push(node);
    				node = leftNode;
    			} else { //如果没有左节点了,那么该节点就可以访问了
    				hasVisit.add(node); //记录该节点以及访问过
    				DoublyListNode curr = new DoublyListNode(node.val);
    				if(null==head) {
    					head = curr;
    					before = curr;
    				} else {
    					before.next = curr;
    					curr.prev = before;
    					before = curr;
    				}
    				
    				stack.push(node); //就算该节点已经访问过了,那么我们也需要push到stack中,为了在下一个循环中,在else中访问他的right节点
    				node = null;
    			}
    		} else {
    			node = stack.pop(); //pop出来节点
    			if(!hasVisit.contains(node)) { //如果该节点没有访问过,那么就可以直接访问了
    				DoublyListNode curr = new DoublyListNode(node.val);
    				if(null==head) {
    					head = curr;
    					before = curr;
    				} else {
    					before.next = curr;
    					curr.prev = before;
    					before = curr;
    				}
    				hasVisit.add(node);
    			}
				
				node = node.right; //直接设置遍历节点为这个节点的right节点,等待静茹下一次循环的if循环,访问right节点的left节点
    		} 
    	}
    	
    	return head;
    }
    
    /**
     * 
     * @param root
     * @return
     */
    public DoublyListNode withRecusion(TreeNode root) {
    	//使用递归的结果再构造双向链表
    	ArrayList<Integer> vals = new ArrayList<>();
    	traversalWithRecusion(root, vals);
    	
    	DoublyListNode head = null;
    	DoublyListNode before = null;
    	for (Integer val : vals) {
    		DoublyListNode curr = new DoublyListNode(val);
    		if(null==before) {
    			before = curr;
    			head = curr;
    		} else {
    			curr.prev = before;
    			before.next = curr;
    			before = curr;
    		}
		}
    	
    	return head;
    }
    
    /**
     * 递归遍历二叉树
     * @param node
     */
    public void traversalWithRecusion(TreeNode node, ArrayList<Integer> vals) {
    	TreeNode leftTree = node.left;
    	if(null!=leftTree) {
    		traversalWithRecusion(leftTree, vals);
    	}
    	
    	vals.add(node.val);
    	
    	TreeNode rightTree = node.right;
    	if(null!=rightTree) {
    		traversalWithRecusion(rightTree, vals);
    	}
    }

}
