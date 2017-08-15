package com.hiworld.lintcode.easy;

import java.util.ArrayList;
import java.util.List;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 二叉树路径之和
 * 
 * 问题：给定一个二叉树，找出所有路径中各节点相加总和等于给定"目标值"的路径
 * 一个有效路径,指的是从根结点到叶子结点的路径
 *          1
 *       *      *
 *     2           4
 *   *   * 
 * 2       3
 * 
 * 目标值 = 5
 * 
 * 返回
 * [
 *  [1,2,2],
 *  [1,4]
 * ]           
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月6日 下午5:35:51
 */
public class BinaryTreePathSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node2_1 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        
        node1.left = node2;
        node1.right = node4;
        node2.left = node2_1;
        node2.right = node3;
        
        BinaryTreePathSum binaryTreePathSum = new BinaryTreePathSum();
        List<List<Integer>> result = binaryTreePathSum.binaryTreePathSum(node1, 5);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
    
    /**
     * 
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        if(null == root) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        //获取所有路径
        allPath(root, list, allPaths);
        
        //存储不满足条件的路径
        List<List<Integer>> needRemovePaths = new ArrayList<>();
        for (List<Integer> path : allPaths) {
            //计算路径的和
            int sum = sum(path);
            if(sum != target) {
                needRemovePaths.add(path);
            }
        }
        
        //删除不满足条件的路径
        for (List<Integer> needRemovePath : needRemovePaths) {
            allPaths.remove(needRemovePath);
        }
        
        return allPaths;
    }
    
    /**
     * 获得所有的路径
     * 采用递归的方式,一层一层的往下遍历
     * 遍历下一层的时候,将上一层的路径List传播到下一层,注意:如果左右结点都不为null的时候,需要copy List传递下去
     * 当左右子树都是null的时候,递归停止,这个路径就已经完毕
     * @param node
     * @param list
     * @param allPaths
     */
    public void allPath(TreeNode node, List<Integer> list, List<List<Integer>> allPaths) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        list.add(node.val);
        
        if(null==leftNode && null==rightNode) {
            allPaths.add(list);
        } else {
            if(null != leftNode && null == rightNode) { //如果左右子树,只有一个不为null,那么直接使用上层传递过来的list,不需要再创建
                allPath(leftNode, list, allPaths);
            } else if(null != rightNode && null == leftNode) {
                allPath(rightNode, list, allPaths);
            } else { //如果两个子树都不为空,一个使用上层传递过来的,一个使用copy的list
                List<Integer> copyList = new ArrayList<>(list);
                allPath(leftNode, list, allPaths);
                allPath(rightNode, copyList, allPaths);
            }
        }
    }
    
    /**
     * 计算路径上的结点的和
     * @param path
     * @return
     */
    public int sum(List<Integer> path) {
        int sum = 0;
        for (Integer nodeValue : path) {
            sum += nodeValue;
        }
        
        return sum;
    }
}
