package com.hiworld.lintcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 层次遍历：按结点的层次遍历(逐层从左往右访问)
 * 样例：
 *          3
 *        *   *
 *      9      20
 *            *  *
 *           15   7
 *  输出：
 *  [
 *      [1],
 *      [9,20],
 *      [15,7]
 *  ]
 *       
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月6日 下午3:26:02
 */
public class BinaryTreeLevelTraversal {
    
    public static void main(String[] args) {
        
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        
        node3.left = node9;
        node3.right = node20;
        node20.right = node15;
        node20.left = node7;
        
        BinaryTreeLevelTraversal binaryTreeLevelTraversal = new BinaryTreeLevelTraversal();
        binaryTreeLevelTraversal.levelTraversalInOneQueue(node3);
    }
    
    /**
     * 
     * @param root
     * @return
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        return levelTraversalInTwoQueue(root);
    }
    
    /**
     * 用两个队列来实现
     * @param root
     * @return
     */
    public ArrayList<ArrayList<Integer>> levelTraversalInTwoQueue(TreeNode root) {
        
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        
        LinkedList<TreeNode> queue_1 = new LinkedList<>();
        LinkedList<TreeNode> queue_2 = new LinkedList<>();
        TreeNode node = root;
        if(null == node) {
            return ret;
        }
        
        queue_1.addLast(node);
        
        while(queue_1.size()!=0) {
            ArrayList<Integer> childList = new ArrayList<>();
            for (TreeNode treeNode : queue_1) {
                childList.add(treeNode.val);
                if(null!=treeNode.left) {
                    queue_2.addLast(treeNode.left);
                }
                
                if(null!=treeNode.right) {
                    queue_2.addLast(treeNode.right);
                }
            }
            
            queue_1.clear();
            queue_1.addAll(queue_2);
            queue_2.clear();
            ret.add(childList);
        }
        
        return ret;
    }
    
    /**
     * 通过单个队列来实现
     * 用一个NULL来分割
     * @param root
     * @return
     */
    public ArrayList<ArrayList<Integer>> levelTraversalInOneQueue(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode node = root;
        if(null == node) {
            return ret;
        }
        
        queue.addLast(node);
        queue.addLast(null); //用一个null进行分割
        
        ArrayList<Integer> list = new ArrayList<>();
        while(queue.size()!=0) {
            TreeNode tmpNode = queue.pollFirst();
            if(null == tmpNode) {
                if(queue.size() != 0) { //这边需要注意,如果pollFirst是一个null,并且队列中没有元素了,那么就不要在添加分隔符null,不然导致死循环
                    queue.addLast(null);
                }
                ret.add(list);
                list = new ArrayList<>();
            } else {
                list.add(tmpNode.val);
                if(null!=tmpNode.left) {
                    queue.addLast(tmpNode.left);
                }
                
                if(null!=tmpNode.right) {
                    queue.addLast(tmpNode.right);
                }
            }
        }
        
        return ret;
    }
    
    /**
     * 通过深度优先搜索(DFS - Depth First Search)
     * @param root
     * @return
     */
    public ArrayList<ArrayList<Integer>> levelTraversalByDFS(TreeNode root) {
        //TODO
        return null;
    }
}

