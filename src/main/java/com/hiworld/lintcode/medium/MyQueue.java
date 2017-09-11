package com.hiworld.lintcode.medium;

import java.util.Stack;

/**
 * 问题：用栈实现队列
 * 描述：正如标题所述，你需要使用两个栈来实现队列的一些操作。
 * 队列应支持push(element)，pop() 和 top()，其中pop是弹出队列中的第一个(最前面的)元素。
 * pop和top方法都应该返回第一个元素的值。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月11日 下午2:03:22
 */
public class MyQueue {
    
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    private boolean lastOperIsPush = true;
    
    public MyQueue() {
        // do intialization if necessary
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        if(!lastOperIsPush) {
            while(!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            lastOperIsPush = true;
        }
        
        stack1.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if(lastOperIsPush) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            
            lastOperIsPush = false;
        }
        
        if(!stack2.isEmpty()) {
            return stack2.pop();
        }
        
        return 0;
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        if(lastOperIsPush) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            
            lastOperIsPush = false;
        }
        
        if(!stack2.isEmpty()) {
            return stack2.peek();
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        System.out.println(myQueue.pop());
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.top());
        System.out.println(myQueue.pop());
        
    }
}
