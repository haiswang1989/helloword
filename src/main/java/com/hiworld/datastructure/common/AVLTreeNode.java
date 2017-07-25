package com.hiworld.datastructure.common;

/**
 * AVL树的结点
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月24日 下午3:49:31
 */
public class AVLTreeNode {
    
    private int value; //当前结点的值
    //平衡因子
    //作为一个平衡二叉树,该值|bf| <= 1
    private int bf; 
    private int height; //该树的高度
    
    private AVLTreeNode leftTree; //左子树
    private AVLTreeNode rightTree; //右子树
    private AVLTreeNode parent; //父亲结点
    
    public AVLTreeNode(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getBf() {
        return bf;
    }
    public void setBf(int bf) {
        this.bf = bf;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public AVLTreeNode getLeftTree() {
        return leftTree;
    }
    public void setLeftTree(AVLTreeNode leftTree) {
        this.leftTree = leftTree;
    }
    public AVLTreeNode getRightTree() {
        return rightTree;
    }
    public void setRightTree(AVLTreeNode rightTree) {
        this.rightTree = rightTree;
    }
    public AVLTreeNode getParent() {
        return parent;
    }
    public void setParent(AVLTreeNode parent) {
        this.parent = parent;
    }
    
    public boolean isLeaf() {
        return null==this.getLeftTree() && null==this.getRightTree();
    }
    
    @Override
    public String toString() {
        return value + "";
    }
    
    /**
     * 重置高度
     */
    public void resetHeight() {
        int leftHeight = 0;
        int rightHeight = 0;
        AVLTreeNode leftNode = getLeftTree();
        AVLTreeNode rightNode = getRightTree();
        
        leftHeight = leftNode == null ? -1 : leftNode.getHeight();
        rightHeight = rightNode == null ? -1 : rightNode.getHeight();
        
        setHeight(Math.max(leftHeight, rightHeight) + 1);
    }
    
    /**
     * 重置节点BF
     */
    public void resetBF() {
        int leftHeight = 0;
        int rightHeight = 0;
        AVLTreeNode leftNode = getLeftTree();
        AVLTreeNode rightNode = getRightTree();
        
        leftHeight = leftNode == null ? 0 : leftNode.getHeight() + 1;
        rightHeight = rightNode == null ? 0 : rightNode.getHeight() + 1;
        
        setBf(leftHeight - rightHeight);
    }
}
