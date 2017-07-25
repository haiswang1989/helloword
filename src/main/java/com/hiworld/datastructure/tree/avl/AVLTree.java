package com.hiworld.datastructure.tree.avl;

import java.util.HashMap;
import java.util.Map;

import com.hiworld.datastructure.common.AVLTreeNode;
import com.hiworld.datastructure.common.ImbalanceType;

/**
 * AVL树
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月24日 下午4:14:56
 */
public class AVLTree {
    
    private AVLTreeNode root;
    
    private Map<ImbalanceType, IAdjust> adjusts = new HashMap<>();
    {
        adjusts.put(ImbalanceType.LL, new LLAdjust());
        adjusts.put(ImbalanceType.RR, new RRAdjust());
        adjusts.put(ImbalanceType.LR, new LRAdjust());
        adjusts.put(ImbalanceType.RL, new RLAdjust());
    }
    
    public AVLTree() {}
    
    public void insert(int value) {
        if(null==root) {
            root = new AVLTreeNode(value);
            root.setHeight(0);
            return;
        }
        
        AVLTreeNode node = root;
        boolean inserted = false;
        while(!inserted) {
            if(value == node.getValue()) {
                inserted = true;
            } else if(value < node.getValue()) {
                AVLTreeNode leftNode = node.getLeftTree();
                if(null == leftNode) {
                    leftNode = new AVLTreeNode(value);
                    modifyTree(node, leftNode);
                    inserted = true;
                }
                node = leftNode;
            } else {
                AVLTreeNode rightNode = node.getRightTree();
                if(null == rightNode) {
                    rightNode = new AVLTreeNode(value);
                    modifyTree(node, rightNode);
                    inserted = true;
                }
                node = rightNode;
            }
        }
    }
    
    /**
     * 
     * @param parent
     * @param node
     */
    private void modifyTree(AVLTreeNode parent, AVLTreeNode node) {
        insertNode(parent, node);
        updateHeight(node);
        AVLTreeNode imbalanceNode = updateBalanceFactor(node);
        if(null!=imbalanceNode) {
            ImbalanceType type = imbalanceType(imbalanceNode);
            doAdjust(imbalanceNode, type);
        }
    }
    
    /**
     * 插入结点
     * @param parent
     * @param node
     */
    private void insertNode(AVLTreeNode parent, AVLTreeNode node) {
        node.setHeight(0);
        node.setParent(parent);
        if(parent.getValue() < node.getValue()) {
            parent.setRightTree(node);
        } else {
            parent.setLeftTree(node);
        }
    }
    
    /**
     * 更新线路上父结点的高度
     * @param node
     */
    private void updateHeight(AVLTreeNode node) {
        AVLTreeNode parentNode = node.getParent();
        int currentNodeHeight = node.getHeight();
        while(null!=parentNode) {
            int parentNodeHeight = parentNode.getHeight();
            if(parentNodeHeight > currentNodeHeight) {
                break;
            }
            
            parentNode.setHeight(currentNodeHeight + 1);
            parentNode = parentNode.getParent();
            currentNodeHeight++;
        }
    }
    
    /**
     * 
     * @param node
     * @return
     */
    private AVLTreeNode updateBalanceFactor(AVLTreeNode node) {
        AVLTreeNode imbalanceNode = null; // 不平衡的根节点
        while(null!=node) {
            if(node.isLeaf()) {
                node.setBf(0);
                node = node.getParent();
                continue;
            }
            
            int leftTreeHeight = 0;
            int rightTreeHeight = 0;
            
            AVLTreeNode leftNode = node.getLeftTree();
            AVLTreeNode rightNode = node.getRightTree();
            
            leftTreeHeight = null==leftNode?0:leftNode.getHeight()+1;
            rightTreeHeight = null==rightNode?0:rightNode.getHeight()+1;
            
            node.setBf(leftTreeHeight-rightTreeHeight);
            
            if(-1<=node.getBf() && node.getBf()<=1) {
                node = node.getParent();
            } else {
                imbalanceNode = node;
                break;
            }
        }
        
        return imbalanceNode;
    }
    
    /**
     * 平衡AVL树
     * @param imbalanceNode
     * @param type
     */
    public void doAdjust(AVLTreeNode imbalanceNode, ImbalanceType type) {
        IAdjust adjust = adjusts.get(type);
        adjust.doAdjust(this, imbalanceNode);
    }
    
    /**
     * 
     * 获取不平衡状态类型
     * @param imbalanceNode 不平衡的结点
     * @return
     */
    public ImbalanceType imbalanceType(AVLTreeNode imbalanceNode) {
        //TODO BUG
        int bf = imbalanceNode.getBf();
        if(bf > 0) { //LL OR LR
            AVLTreeNode leftTree = imbalanceNode.getLeftTree();
            
            AVLTreeNode leftTreeLeftChild = leftTree.getLeftTree();
            AVLTreeNode leftTreeRightChild = leftTree.getRightTree();
            
            int leftTreeLeftChildHeight = null==leftTreeLeftChild ? 0 : leftTreeLeftChild.getHeight() + 1;
            int leftTreeRightChildHeight = null==leftTreeRightChild ? 0 : leftTreeRightChild.getHeight() + 1;
            
            if(leftTreeLeftChildHeight > leftTreeRightChildHeight) {
                return ImbalanceType.LL;
            } else { 
                return ImbalanceType.LR;
            }
        } else { //RR OR RL
            AVLTreeNode rightTree = imbalanceNode.getRightTree();
            
            AVLTreeNode rightTreeLeftChild = rightTree.getLeftTree();
            AVLTreeNode rightTreeRightChild = rightTree.getRightTree();
            
            int rightTreeLeftChildHeight = null==rightTreeLeftChild ? 0 : rightTreeLeftChild.getHeight() + 1;
            int rightTreeRightChildHeight = null==rightTreeRightChild ? 0 : rightTreeRightChild.getHeight() + 1;
            
            if(rightTreeLeftChildHeight > rightTreeRightChildHeight) {
                return ImbalanceType.RL;
            } else {
                return ImbalanceType.RR;
            }
        }
    }
    
    public void setRoot(AVLTreeNode root) {
        root.setParent(null);
        this.root = root;
    }
}
