package com.hiworld.datastructure.tree.avl;

import com.hiworld.datastructure.common.AVLTreeNode;
import com.hiworld.datastructure.common.ChildType;

/**
 * 样例：
 * 简单：
 *          (50)                                                   (50)                             45
 *         *            非平衡结点左孩子(40)为轴逆时针旋转(45)       *             LL平衡                        *     *
 *       (40)            ------------------------------->       (45)           ---------->      40          50
 *         *                                                    *
 *          (45)                                             (40)
 * 
 * 
 * 复杂：
 *           (50)                                             (50)                                       (45)
 *          *    *                                           *     *                                    *     *
 *       (40)    (60)     以40位轴逆时针旋转(45)           (45)    (60)      以45为轴顺时针旋转(50)     (40)    (50)
 *       *  *             ----------------->             *     *             ------------------->     *      *   *
 *    (30)  (45)                                       (40)   (47)                                  (30)   (47)  (60)
 *            *                                        *
 *            (47)                                   (30)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月25日 下午1:00:01
 */

public class LRAdjust extends AbstractAdjust {

    @Override
    public void doAdjust(AVLTree tree, AVLTreeNode imbalanceNode) {
        /*******************视图1转换到视图2*******************/
        AVLTreeNode imbalanceNodeParent = imbalanceNode.getParent();
        AVLTreeNode imbalanceNodeLeftChild = imbalanceNode.getLeftTree(); //40
        AVLTreeNode imbalanceNodeLeftChildRightChild = imbalanceNodeLeftChild.getRightTree(); //45
        setChild(imbalanceNode, imbalanceNodeLeftChildRightChild, ChildType.LEFT, false);
        imbalanceNodeLeftChild.setRightTree(null);
        setChild(imbalanceNodeLeftChildRightChild, imbalanceNodeLeftChild, ChildType.LEFT, true);
        
        /********************视图2转换到视图3********************/
        AVLTreeNode imbalanceNodeLeftTreeChildNew = imbalanceNode.getLeftTree(); //45
        AVLTreeNode imbalanceNodeLeftTreeChildRightChildNew = imbalanceNodeLeftTreeChildNew.getRightTree(); //47
        imbalanceNode.setLeftTree(null);
        setChild(imbalanceNodeLeftTreeChildNew, imbalanceNode, ChildType.RIGHT, false);
        setChild(imbalanceNode, imbalanceNodeLeftTreeChildRightChildNew, ChildType.LEFT, false);
        if(null!=imbalanceNodeParent) {
            ChildType childType = getChildType(imbalanceNodeParent, imbalanceNode);
            setChild(imbalanceNodeParent, imbalanceNodeLeftTreeChildNew, childType, false);
        } else {
            tree.setRoot(imbalanceNodeLeftTreeChildNew);
        }
    }
}
