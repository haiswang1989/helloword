package com.hiworld.lintcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 将二叉查找树，转换成另一颗数，这颗树的每个结点为该位置的结点的值+所有比该结点值大的结点的sum
 * 
 * 样例：
 * 
 *          5
 *        *   *
 *      2       13
 *      
 * 返回结果：
 *          18
 *        *    *
 *      20       13
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月11日 下午2:33:51
 */
public class BinarySearchTreeToGreaterTree {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node13 = new TreeNode(13);
        
        node5.left = node2;
        node5.right = node13;
        
        BinarySearchTreeToGreaterTree binarySearchTreeToGreaterTree = new BinarySearchTreeToGreaterTree();
        TreeNode newTree = binarySearchTreeToGreaterTree.convertBST(node5);
        System.out.println(newTree);
    }
    
    public TreeNode convertBST(TreeNode root) {
        // Write your code here
        if(null == root) {
            return null;
        }
        
        TreeNode node = root;
        convertWithOutRecursion(node);
        
//        root = convertWithInorderTraversalAndClone(root);
        return root;
    }
    
    /***************************直接(右 -> 中 -> 左)遍历实现**************************/
    public void convertWithOutRecursion(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        while(0!=stack.size() || null!=node) {
            if(null!=node) {
                stack.push(node);
                node = node.right;
            } else {
                node = stack.pop();
                sum += node.val;
                node.val = sum; 
                node = node.left;
            }
        }
    }
    
    
    
    
    /********************************二叉树clone + 中序遍历实现*******************************/
    
    /**
     * 二叉树clone + 中序遍历实现
     * @param root
     * @return
     */
    public TreeNode convertWithInorderTraversalAndClone(TreeNode root) {
        LinkedList<TreeNode> inorderTraversalResult = inorderTraversal(root);
        //新的结点的值
        HashMap<TreeNode, Integer> newNodeVals = convert(inorderTraversalResult);
        return cloneConstructNewTree(root, newNodeVals);
    }
    
    /**
     * 采用递归clone二叉树的方式来构造新的二叉树
     * @param node
     * @param nodeAddSum
     * @return
     */
    public TreeNode cloneConstructNewTree(TreeNode node, HashMap<TreeNode, Integer> nodeAddSum) {
        if(null==node) {
            return null;
        }
        
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        
        //构造新的结点
        TreeNode newNode = new TreeNode(nodeAddSum.get(node));
        
        //递归方式构造左右子树
        TreeNode leftTree = cloneConstructNewTree(leftNode, nodeAddSum);
        TreeNode rightTree = cloneConstructNewTree(rightNode, nodeAddSum);
        
        newNode.left = leftTree;
        newNode.right = rightTree;
        
        return newNode;
    }
    
    
    
    /**
     * 中序遍历,由于是搜索二叉树,所以中序遍历后的结果是从小到大排序好的
     * @param node
     * @return
     */
    public LinkedList<TreeNode> inorderTraversal(TreeNode node) {
        LinkedList<TreeNode> nodeList = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(stack.size()!=0 || null!=node) {
            if(null!=node) {
                stack.push(node);
                TreeNode leftNode = node.left;
                node = leftNode;
            } else {
                node = stack.pop();
                nodeList.offerLast(node);
                node = node.right;
            }
        }
        
        return nodeList;
    }
    
    /**
     * 构造每个结点新建后的值
     * 每个结点的新值,就是当前结点的值加上该结点的所有右边结点的值的总和
     * @param inorderTraversalResult
     * @return
     */
    public HashMap<TreeNode, Integer> convert(LinkedList<TreeNode> inorderTraversalResult) {
        HashMap<TreeNode, Integer> nodeAddSum = new HashMap<>();
        int length = inorderTraversalResult.size();
        int count = 0;
        int beforeValue = 0;
        while(count++ < length) {
            TreeNode node = inorderTraversalResult.pollLast();
            beforeValue += node.val;
            nodeAddSum.put(node, beforeValue);
        }
        
        return nodeAddSum;
    }
}
