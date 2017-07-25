package com.hiworld.datastructure.tree.avl;

import com.hiworld.datastructure.common.AVLTreeNode;
import com.hiworld.datastructure.common.ChildType;

/**
 * 样例：
 * 简单：
 * (50)                                   (50)                                                 (55)
 *    *        以60位轴顺时针旋转左孩子              *              以50位轴逆时针旋转右孩子                       *      *
 *     (60)   ----------------------->       (55)          ----------------------->        (50)      (60)
 *    *                                        *
 * (55)                                         (60)
 * 
 * 复杂：
 *              (50)                                           (50)                                                     55
 *            *      *                                      *        *                                                *      *      
 *         (40)      (60)          60为轴顺时针旋转55     (40)        (55)              50为轴逆时针旋转55           50          60
 *                  *    *         ------------------>              *     *                   ----------------->  *  *            *
 *               (55)    (70)                                    (53)     (60)                                   40   53           70
 *               *                                                           *
 *            (53)                                                            (70)
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月25日 下午1:33:12
 */
public class RLAdjust extends AbstractAdjust {

    @Override
    public void doAdjust(AVLTree tree, AVLTreeNode imbalanceNode) {
        
        /*******************视图1转换到视图2*******************/
        AVLTreeNode imbalanceNodeParent = imbalanceNode.getParent();
        AVLTreeNode imbalanceNodeRightChild = imbalanceNode.getRightTree(); //60
        AVLTreeNode imbalanceNodeRightChildLeftChild = imbalanceNodeRightChild.getLeftTree(); //55
        setChild(imbalanceNode, imbalanceNodeRightChildLeftChild, ChildType.RIGHT, false);
        imbalanceNodeRightChild.setLeftTree(null);
        setChild(imbalanceNodeRightChildLeftChild, imbalanceNodeRightChild, ChildType.RIGHT, true);
        
        /*******************视图2转换到视图3*******************/
        AVLTreeNode imbalanceNodeRightChildNew = imbalanceNode.getRightTree(); //55
        AVLTreeNode imbalanceNodeRightChildLeftChildNew = imbalanceNodeRightChildNew.getLeftTree(); //53
        imbalanceNode.setRightTree(null);
        setChild(imbalanceNodeRightChildNew, imbalanceNode, ChildType.LEFT, false);
        setChild(imbalanceNode, imbalanceNodeRightChildLeftChildNew, ChildType.RIGHT, false);
        if(null!=imbalanceNodeParent) {
            ChildType childType = getChildType(imbalanceNodeParent, imbalanceNode);
            setChild(imbalanceNodeParent, imbalanceNodeRightChildNew, childType, false);
        } else {
            tree.setRoot(imbalanceNodeRightChildNew);
        }
    }
}
