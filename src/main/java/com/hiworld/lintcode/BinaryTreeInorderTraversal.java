package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 二叉树的"中序遍历"(不管是前序,中序,后序都是针对根结点的而言的)
 * 
 * 左子树->根节点->右子树
 * 
 * 样例：
 *      1
 *        *      
 *          2
 *        *
 *      3
 * 
 * 返回:[1,3,2]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月6日 上午11:09:38
 */
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        
//        node2.left = node3;
//        node1.right = node2;
//        
        
//        node1.left = node2;
//        node1.right = node3;
        
        node1.right = node2;
        node2.right = node3;
        
        BinaryTreeInorderTraversal binaryTreeInorderTraversal = new BinaryTreeInorderTraversal();
        
        ArrayList<Integer> result = binaryTreeInorderTraversal.inorderTraversal(node1);
        binaryTreeInorderTraversal.print(result);
    }
    
    /**
     * 
     * @param root
     * @return
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        //return inorderTraversalWithRecursion(root);
        return inorderTraversalWithOutRecursion(root);
    }
    
    /**
     * 采用递归的方式,进行"中序遍历"
     * @param root
     * @return
     */
    public ArrayList<Integer> inorderTraversalWithRecursion(TreeNode root) {
        if(null == root) {
            return null;
        }
        
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        if(null!=leftNode) {
            ArrayList<Integer> leftArrayList = inorderTraversalWithRecursion(leftNode);
            if(null != leftArrayList) {
                arrayList.addAll(0, leftArrayList); //左子树必须放在前面
            }
        }
        
        arrayList.add(root.val); //中间结点紧接着放到后面
        
        if(null!=rightNode) {
            ArrayList<Integer> rightArrayList = inorderTraversalWithRecursion(rightNode);
            if(null!=rightArrayList) {
                arrayList.addAll(rightArrayList); //右子树放到后面
            }
        }
        
        return arrayList;
    }
    
    /**
     * 采用非递归的方式,进行"中序遍历"
     * 想法：
     * 不停的遍历left结点，把根结点都压栈，直到left结点为null了,那么pop结点出来，把该结点进行打印
     * 然后再处理这个pop出来的结点的右结点
     * 
     * @param root
     * @return
     */
    public ArrayList<Integer> inorderTraversalWithOutRecursion(TreeNode root) {
        if(null==root) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        
        ArrayList<Integer> retList = new ArrayList<>();
        
        while(stack.size() != 0 || null!=node) {
            if(null == node) { //node为null是一个出栈的过程
                TreeNode popNode = stack.pop();
                retList.add(popNode.val); //先把pop出来的结点添加到返回列表中(中)
                node = popNode.right; //处理pop出来的结点的右结点
            } else { //node非null是一个压栈的过程
                stack.push(node); //首先将结点压栈
                node = node.left; //准备继续遍历左结点
            }
        }
        
        return retList;
    }
    
    public void print(Collection<?> collection) {
        for (Object obj : collection) {
            System.out.print(obj.toString() + "-");
        }
        System.out.println("");
    }
    
}
