package com.hiworld.datastructure.tree.avl;

public class AVLTreeMain {

    public static void main(String[] args) {
        
        AVLTree avlTreeLL = new AVLTree();
        
        //LL的测试
        avlTreeLL.insert(7);
        avlTreeLL.insert(6);
        avlTreeLL.insert(5);
        avlTreeLL.insert(4);
        avlTreeLL.insert(3);
        avlTreeLL.insert(2);
        avlTreeLL.insert(1);
        
        //RR的测试
        AVLTree avlTreeRR = new AVLTree();
        avlTreeRR.insert(1);
        avlTreeRR.insert(2);
        avlTreeRR.insert(3);
        avlTreeRR.insert(4);
        avlTreeRR.insert(5);
        avlTreeRR.insert(6);
        avlTreeRR.insert(7);
        
        //LR的测试
        AVLTree avlTreeLR = new AVLTree();
        avlTreeLR.insert(5);
        avlTreeLR.insert(3);
        avlTreeLR.insert(4);
        
        //RL测试
        AVLTree avlTreeRL = new AVLTree();
        avlTreeRL.insert(5);
        avlTreeRL.insert(7);
        avlTreeRL.insert(6);
        
        System.out.println("fuck = " + avlTreeLL);
    }
}
