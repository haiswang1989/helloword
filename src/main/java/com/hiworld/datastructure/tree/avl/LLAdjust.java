package com.hiworld.datastructure.tree.avl;

import com.hiworld.datastructure.common.AVLTreeNode;
import com.hiworld.datastructure.common.ChildType;

/**
 * 样例：
 * 最简单的样子：
 *              (5)                     (4)
 *             *                     *       *
 *          (4)        ----->    (3)           (5)   //以4位轴,顺时针旋转5
 *         *   
 *      (3)
 * 
 * 复杂：
 *               (50)                              (40)
 *              *    *                            *    *
 *           (40)    (60)                       (30)  (50)
 *            *                  ----->         *     *  *                //以40位轴,顺时针旋转50
 *         (30)(45)                           (20)  (45) (60)
 *          *   
 *       (20)
 * 
 * 
 * 
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月25日 上午10:34:44
 */
public class LLAdjust extends AbstractAdjust {

    @Override
    public void doAdjust(AVLTree tree, AVLTreeNode imbalanceNode) {
        AVLTreeNode imbalanceNodeParent = imbalanceNode.getParent(); //父亲结点
        AVLTreeNode imbalanceNodeLeftChild = imbalanceNode.getLeftTree(); //左孩子结点
        AVLTreeNode imbalanceNodeLeftChildRightChild = imbalanceNodeLeftChild.getRightTree(); //左孩子结点的右子树
        imbalanceNode.setLeftTree(null);
        setChild(imbalanceNodeLeftChild, imbalanceNode, ChildType.RIGHT, false);
        if(null!=imbalanceNodeLeftChildRightChild) {
            setChild(imbalanceNode, imbalanceNodeLeftChildRightChild, ChildType.LEFT, false);
        }
        
        if(null!=imbalanceNodeParent) {
            ChildType childType = getChildType(imbalanceNodeParent, imbalanceNode);
            setChild(imbalanceNodeParent, imbalanceNodeLeftChild, childType, false);
            
            
        } else {
            tree.setRoot(imbalanceNodeLeftChild);
        }
    }

}
