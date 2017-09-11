package com.hiworld.lintcode.medium;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.hiworld.lintcode.common.NestedInteger;

public class NestedIteratorMain {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

class NestedIterator implements Iterator<Integer> {
    
    Stack<Iterator<NestedInteger>> stack = new Stack<>();
    
    Iterator<Integer> currInter = null;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        // Initialize your data structure here.
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        // Write your code here
        return 1;
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        // Write your code here
        return false;
    }

    @Override
    public void remove() {}
}


