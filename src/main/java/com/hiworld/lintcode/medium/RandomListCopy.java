package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiworld.lintcode.common.RandomListNode;

/**
 * 问题：复制带随机指针的链表 
 * 描述：给出一个链表，每个节点包含一个额外增加的随机指针可以指向链表中的任何节点或空的节点。返回一个深拷贝的链表。
 * 
 * 挑战：可否使用O(1)的空间
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月30日 上午9:15:36
 */
public class RandomListCopy {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        
        node1.random = node3;
        node2.random = node4;
        
        RandomListCopy randomListCopy = new RandomListCopy();
        RandomListNode ret = randomListCopy.solution(node1);
        System.out.println("-----");
    }
    
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        if(null==head) {
            return null;
        }
        
        return mySolution(head);
    }
    
    /*****************************这个是网上比较精巧的解法,空间复杂度O(1)***************************/
    public RandomListNode solution(RandomListNode head) {
        RandomListNode node = head;
        //copy元素
        //OL node1->node2->node3->node4
        //NL node1->newnode1->node2->newnode2->node3->newnode3->node4->newnode4
        while(null!=node) {
            RandomListNode newNode = new RandomListNode(node.label);
            RandomListNode tmpNode = node;
            node = node.next;
            tmpNode.next = newNode;
            newNode.next = node;
        }
        
        ////设置random
        //假如node1的random 为 node3
        //那么就设置node1.next.random = node3.next;
        RandomListNode setRandomNode = head;
        while(null!=setRandomNode) {
            RandomListNode randomNode = setRandomNode.random;
            if(null!=randomNode) {
                setRandomNode.next.random = randomNode.next;
            }
            
            setRandomNode = setRandomNode.next.next;
        }
        
        //去除旧的结点
        RandomListNode removeNode = head;
        RandomListNode newHead = null;
        RandomListNode beforeNode = null;
        while(null!=removeNode) {
            RandomListNode tmp = removeNode.next;
            if(null==beforeNode) {
                newHead = tmp;
                beforeNode = tmp;
            } else {
                beforeNode.next = tmp;
                beforeNode = tmp;
            }
            
            removeNode = tmp.next;
        }
        return newHead;
    }
    
    
    /**************************这是我自己的实现方式,缺点空间复杂度不是O(1)***************************/
    
    /**
     * OL(老的链表)
     * NL(新的链表)
     * 
     * @param head
     */
    public RandomListNode mySolution(RandomListNode head) {
        RandomListNode beforeNode = null; //存储前一个结点
        RandomListNode newHead = null; //新的head结点
        RandomListNode node = head;
        
        Map<RandomListNode, RandomListNode> old2New = new HashMap<>(); //存储老结点和对应位置的新结点的映射
        
        // OL中
        // node1->node2->node3
        // node1 的random指向 node3
        // NL中
        // newnode1
        // 存储node3 和  newnode1的映射,以便后面newnode3创建的时候,来触发newnode1来修改random
        Map<RandomListNode, List<RandomListNode>> oldRandom2NewNode = new HashMap<>();
        
        while(null!=node) {
            RandomListNode newNode = new RandomListNode(node.label);
            if(null==beforeNode) {
                beforeNode = newNode;
                newHead = newNode;
            } else {
                beforeNode.next = newNode;
                beforeNode = newNode;
            }
            
            RandomListNode randomNode = node.random;
            if(null!=randomNode) { //如果random结点不为null
                RandomListNode newRandomNode = old2New.get(randomNode); 
                if(null!=newRandomNode) { //如果random结点对应的新结点已经创建,那么直接设置random
                    newNode.random = newRandomNode;
                } else { //如果没有创建,那么就放入map,等待下次结点创建出来的时候,进行触发修改random
                    List<RandomListNode> newNodes = oldRandom2NewNode.get(randomNode);
                    if(null!=newNodes) {
                        newNodes.add(newNode);
                    } else {
                        newNodes = new ArrayList<>();
                        newNodes.add(newNode);
                        oldRandom2NewNode.put(randomNode, newNodes);
                    }
                }
            }
            
            old2New.put(node, newNode);
            
            //查找哪个新node的原始node依赖该node的原始node
            //现在可以设置对应的新的node的随机node
            if(oldRandom2NewNode.containsKey(node)) {
                List<RandomListNode> needSetRandomNodes = oldRandom2NewNode.get(node);
                for (RandomListNode randomListNode : needSetRandomNodes) {
                    randomListNode.random = newNode;
                }
                oldRandom2NewNode.remove(node);
            }
            
            node = node.next;
        }
        
        return newHead;
    }
}

