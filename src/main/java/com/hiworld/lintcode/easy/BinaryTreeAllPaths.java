package com.hiworld.lintcode.easy;

import java.util.ArrayList;
import java.util.List;

import com.hiworld.lintcode.common.TreeNode;

/**
 * 给一棵二叉树，找出从根节点到叶子节点的所有路径
 *           1
 *       *      *
 *     2           4
 *   *   * 
 * 2       3
 * 
 * 
 * 返回
 * [
 *  [1,2,2],
 *  [1,2,3]
 *  [1,4]
 * ]  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月7日 上午11:33:50
 */
public class BinaryTreeAllPaths {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node2_1 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        
        node1.left = node2;
        node1.right = node4;
        node2.left = node2_1;
        node2.right = node3;
        
        BinaryTreeAllPaths binaryTreeAllPaths = new BinaryTreeAllPaths();
        List<String> result = binaryTreeAllPaths.binaryTreePaths(node1);
        for (String path : result) {
            System.out.println("path : " + path);
        }
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        // Write your code here
        if(null == root) {
            return new ArrayList<>();
        }
        
        List<String> ret = new ArrayList<>();
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> levelNode = new ArrayList<>();
        allPaths(root, levelNode, allPaths);
        
        for (List<Integer> allPath : allPaths) {
            ret.add(constructOutput(allPath));
        }
        
        return ret;
    }
    
    /**
     * 获取所有路径(这部分逻辑和BinaryTreePathSum.java中一样)
     * @param node
     * @param levelNode
     * @param allPaths
     */
    void allPaths(TreeNode node, List<Integer> levelNode, List<List<Integer>> allPaths) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        
        levelNode.add(node.val);
        
        if(null==leftNode && null==rightNode) {
            allPaths.add(levelNode);
        } else {
            if(null!=leftNode && null==rightNode) {
                allPaths(leftNode, levelNode, allPaths);
            } else if(null==leftNode && null!=rightNode) {
                allPaths(rightNode, levelNode, allPaths);
            } else {
                List<Integer> copyList = new ArrayList<>(levelNode);
                allPaths(leftNode, levelNode, allPaths);
                allPaths(rightNode, copyList, allPaths);
            }
        }
    }
    
    /**
     * 构造标准的路径输出
     * @param allPath
     * @return
     */
    public String constructOutput(List<Integer> allPath) {
        StringBuilder sb = new StringBuilder();
        for (Integer nodeValue : allPath) {
            sb.append(nodeValue).append("->");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
