package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hiworld.lintcode.common.ListNode;
import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：排序列表转换为二分查找树
 * 描述：给出一个所有元素以升序排序的单链表，将它转换成一棵高度平衡的二分查找树
 * 
 * 样例：
 * 1->2->3
 * 
 * 输出：
 * 		2
 *    *   *
 * 	1		3
 * 
 * 
 * @author Administrator
 *
 */
public class ListSortedListToBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ListNode node1 = new ListNode(-1);
		ListNode node2 = new ListNode(0);
		ListNode node3 = new ListNode(1);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
//		ListNode node8 = new ListNode(8);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
//		node7.next = node8;
		
		ListSortedListToBST listSortedListToBST = new ListSortedListToBST();
		TreeNode head = listSortedListToBST.sortedListToBST(node1);
		System.out.println(head);
	}
	
	/**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {  
        // write your code here
    	if(null==head) {
    		return null;
    	}
    	
    	//将所有的结点放入一个ArrayList中便于获取
    	ListNode node = head;
    	ArrayList<ListNode> nodes = new ArrayList<>();
    	nodes.add(node);
    	while(null!=(node=node.next)) {
    		nodes.add(node);
    	}
    	
    	int count = nodes.size(); //节点个数
    	
    	int treeHeight = 0; //树的高度
    	Map<Integer, Integer> height2NodeCount = new HashMap<>(); //树的高度和元素个数(满查找树)的映射
    	boolean isFull = false; //是否是满查找树
    	int temp = 0; 
    	while((temp=nodeCount(++treeHeight)) <= count) {
    		if(temp == count) {
    			isFull = true;
    		}
    		
    		height2NodeCount.put(treeHeight, temp);
    	}
    	
    	if(!isFull) { //非满查找树
    		height2NodeCount.put(treeHeight, temp);
    	} else {//如果是一颗满查找树
    	    --treeHeight;
    	}
    	
    	return withRecusion(nodes, 0, nodes.size()-1, height2NodeCount, treeHeight);
    }
    
    /**
     * 每个高度元素的个数(满查找树)
     * 等比数列的求和公式
     * (a1 - an*q) / 1 - q  (q!=1)
     * @param treeHeight
     * @return
     */
    public int nodeCount(int treeHeight) {
    	return (1 - twoPower(treeHeight-1) * 2) / (1 - 2);
    }
    
    /**
     * 这边是求2的N次方,这边可以缓存提高速度
     * @param power
     * @return
     */
    public int twoPower(int power) {
    	if(power == 0) {
    		return 1;
    	}
    	int result = 1;
    	while(power > 0) {
    		result *= 2;
    		power--;
    	}
    	
    	return result;
    }
    
    /**
     * 递归构造
     * @param nodes
     * @param fromIndex
     * @param endIndex
     * @param height2NodeCount
     * @param height
     * @return
     */
    public TreeNode withRecusion(ArrayList<ListNode> nodes, int fromIndex, int endIndex, Map<Integer, Integer> height2NodeCount, int height) {
    	int nodeCount = endIndex - fromIndex + 1;
    	if(nodeCount == 1) {
    		return new TreeNode(nodes.get(fromIndex).val);
    	} else if (nodeCount == 2) {
    		TreeNode head = new TreeNode(nodes.get(endIndex).val);
    		TreeNode leftNode = new TreeNode(nodes.get(fromIndex).val);
    		head.left = leftNode;
    		return head;
    	} else if(nodeCount == 3) {
    		TreeNode rightNode = new TreeNode(nodes.get(endIndex).val);
    		TreeNode head = new TreeNode(nodes.get(endIndex-1).val);
    		TreeNode leftNode = new TreeNode(nodes.get(fromIndex).val);
    		head.left = leftNode;
    		head.right = rightNode;
    		return head;
    	} 
    	
    	//右子树最少元素个数
    	int minRightNodeCount = height2NodeCount.get(height - 2);
    	//左边子树最大元素个数
    	int maxLeftNodeCount = height2NodeCount.get(height-1); 
    	        
    	//如何分左右子树的元素的个数
    	//注意点：
    	//1：确保右边的元素的个数不少于height2NodeCount.get(height - 2);
    	//2：确保左边的元素的个数不大于height2NodeCount.get(height-1);
    	int headNodeIndex = 0; 
    	if(minRightNodeCount + maxLeftNodeCount + 1 < nodeCount) {
    	    headNodeIndex = fromIndex + maxLeftNodeCount;
    	} else {
    	    headNodeIndex = endIndex - minRightNodeCount;
    	}
    	
    	//分配好了左右元素的个数,那么就直接递归好了
    	TreeNode leftTreeNode = withRecusion(nodes, fromIndex, headNodeIndex-1, height2NodeCount, height-1);
    	TreeNode rightTreeNode = withRecusion(nodes, headNodeIndex+1, endIndex, height2NodeCount, height-1);
    	
    	TreeNode childHead = new TreeNode(nodes.get(headNodeIndex).val);
    	childHead.left = leftTreeNode;
    	childHead.right = rightTreeNode;
    	
    	return childHead;
    }
}
