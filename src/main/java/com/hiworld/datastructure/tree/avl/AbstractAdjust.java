package com.hiworld.datastructure.tree.avl;

import com.hiworld.datastructure.common.AVLTreeNode;
import com.hiworld.datastructure.common.ChildType;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月25日 上午11:02:10
 */
public abstract class AbstractAdjust implements IAdjust {
    
    public ChildType getChildType(AVLTreeNode parentNode, AVLTreeNode childNode) {
        
        AVLTreeNode leftChild = parentNode.getLeftTree();
        if(null!=leftChild) {
            return childNode == leftChild ? ChildType.LEFT:ChildType.RIGHT;
        } 
        
        return ChildType.RIGHT;
    }
    
    public void setChild(AVLTreeNode parent, AVLTreeNode child, ChildType childType, boolean needDepthReset) {
        if(childType == ChildType.LEFT) {
            parent.setLeftTree(child);
        } else {
            parent.setRightTree(child);
        }
        
        if(null!=child) {
            child.setParent(parent);
        }
        
        
        //设置完成以后重置
        resetHeight(child, needDepthReset);
        resetBf(child, needDepthReset);
        resetHeight(parent, needDepthReset);
        resetBf(parent, needDepthReset);
    }
    
    /**
     * 重置高度
     * @param node
     * @param needDepthReset 是否需要递归重置,就是不停的往上遍历,重新计算父亲结点的bf,当在下面添加了结点以后,就需要递归重置
     */
    public void resetHeight(AVLTreeNode node, boolean needDepthReset) {
        if(null == node) {
            return;
        }
        
        if(needDepthReset) {
            while(null!=node) {
                node.resetHeight();
                node = node.getParent();
            }
        } else {
            node.resetHeight();
        }
    }
    
    /**
     * 重置bf
     * @param node
     * @param needDepthReset 是否需要递归重置,就是不停的往上遍历,重新计算父亲结点的bf,当在下面添加了结点以后,就需要递归重置
     */
    public void resetBf(AVLTreeNode node, boolean needDepthReset) {
        if(null == node) {
            return;
        }
        
        if(needDepthReset) {
            while(null!=node) {
                node.resetBF();
                node = node.getParent();
            }
        } else {
            node.resetBF();
        }
    }
}
