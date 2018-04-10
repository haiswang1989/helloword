package com.hiworld.jdk.collection;

import lombok.Data;

public class RevertList {
    
    
    public static void main(String[] args) {
        
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月29日 下午6:05:42
 * @param <E>
 */
class MyList<E> {
    
    Node<E> head;
    Node<E> end;
    
    public void print() {
        Node<E> loopNode = head;
        while(null!=loopNode) {
            System.out.print(loopNode.getValue() + ",");
            loopNode = loopNode.next;
        }
    }
}

@Data
class Node<E> {
    
    E value;
    
    Node<E> next;
}
