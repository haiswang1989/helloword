package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 问题：二叉树的层次遍历 II 
 * 描述：给出一棵二叉树，返回其节点值从底向上的层次序遍历（按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历）
 * 样例：
 *          3
 *        *   *
 *      9      20
 *            *  *
 *           15   7
 *  输出：
 *  [
 *      [15,7]
 *      [9,20],
 *      [3],
 *  ]
 *       
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月6日 下午3:26:02
 */
public class BinaryTreeLevelTraversalII {

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
        
        BinaryTreeLevelTraversalII binaryTreeLevelTraversalII = new BinaryTreeLevelTraversalII();
        ArrayList<ArrayList<Integer>> rets = binaryTreeLevelTraversalII.levelOrderBottom(node3);
        for (ArrayList<Integer> arrayList : rets) {
            System.out.println(Arrays.toString(arrayList.toArray()));
        }
    }
    
    /**
     * @param root: The root of binary tree.
     * @return: buttom-up level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        if(null == root) {
            return null;
        }
        
        TreeNode loopNode = root;
        
        LinkedList<TreeNode> loopNodes = new LinkedList<>();
        loopNodes.offer(loopNode);
        loopNodes.offer(null); //以一个null为信号,表示换行(level)了
        
        Stack<ArrayList<Integer>> stack = new Stack<>(); //用一个栈存储正向结果,方便构造反向的结果
        
        ArrayList<Integer> values = new ArrayList<>(); //每一层的结果 
        boolean hasNextLevel = false; //是否有下一层,用于控制是否需要插入null(换行)信息,避免死循环
        while(0!=loopNodes.size()) {
            TreeNode treeNode = loopNodes.poll();
            if(null!=treeNode) {
                values.add(treeNode.val);
                TreeNode leftNode = treeNode.left;
                if(null!=leftNode) {
                    loopNodes.offer(leftNode);
                    hasNextLevel = true;
                }
                
                TreeNode rightNode = treeNode.right;
                if(null!=rightNode) {
                    loopNodes.offer(rightNode);
                    hasNextLevel = true;
                }
            } else {
                if(hasNextLevel) {
                    loopNodes.offer(null);
                    hasNextLevel = false;
                }
                
                stack.push(values);
                values = new ArrayList<>();
            }
        }
        
        ArrayList<ArrayList<Integer>> rets = new ArrayList<>(); //构造返回值
        while(stack.size()!=0) { 
            rets.add(stack.pop());
        }
        
        return rets;
    }

}
