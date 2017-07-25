package com.hiworld.datastructure.tree.avl;

import com.hiworld.datastructure.common.AVLTreeNode;
import com.hiworld.datastructure.common.ChildType;


/**
 * 样例：
 * 简单的样子：
 * (50)                                       (60)
 *     *                                    *       *                      //以60为轴,逆时针旋转50
 *      (60)                     ---->   (50)       (70)
 *          *
 *           (70)
 *           
 * 复杂的样子：
 *           (50)                                              (60)          
 *         *       *                                         *       *  
 *      (40)       (60)            ----->               (50)          (70)                 //以60为轴逆时针旋转50
 *                *    *                              *     *              *
 *              (55)   (70)                        (40)    (55)             (80)
 *                        *           
 *                         (80)
 *      
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月25日 上午11:41:29
 */
public class RRAdjust extends AbstractAdjust {

    @Override
    public void doAdjust(AVLTree tree, AVLTreeNode imbalanceNode) {
        AVLTreeNode imbalanceNodeParent = imbalanceNode.getParent();
        AVLTreeNode imbalanceNodeRightChild = imbalanceNode.getRightTree();
        AVLTreeNode imbalanceNodeRightChildLeftChild = imbalanceNodeRightChild.getLeftTree();
        imbalanceNode.setRightTree(null);
        setChild(imbalanceNodeRightChild, imbalanceNode, ChildType.LEFT, false);
        if(null!=imbalanceNodeRightChildLeftChild) {
            setChild(imbalanceNode, imbalanceNodeRightChildLeftChild, ChildType.RIGHT, false);
        }
        
        if(null!=imbalanceNodeParent) {
            ChildType childType = getChildType(imbalanceNodeParent, imbalanceNode);
            setChild(imbalanceNodeParent, imbalanceNodeRightChild, childType, false);
        } else {
            tree.setRoot(imbalanceNodeRightChild);
        }
    }
}
