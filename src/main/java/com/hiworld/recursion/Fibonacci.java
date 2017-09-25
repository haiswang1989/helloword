package com.hiworld.recursion;

public class Fibonacci {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Fibonacci fibonacci = new Fibonacci();
        long result = fibonacci.tailRecusion(1l, 1l, 1000);
        System.out.println("result : " + result);
    }
    
    /**
     * 斐波那契(递归实现)
     * @param n
     * @return
     */
    public long recursion(int n) {
        if(n == 1) {
            return 1;
        } else if(n == 2) {
            return 1;
        }
        return recursion(n-1) + recursion(n-2);
    }
    
    /**
     * 尾递归
     * 定义：函数中递归调用都是在函数的末尾,这样的递归调用称为"尾递归"
     * 注意：只有当递归调用是整个函数体重最后执行的语句且它的返回值不属于表达式的一部分时
     * 这样的递归调用才是伪递归,如return recursion(n-1) + recursion(n-2);就不属于尾递归
     * 
     * @param a
     * @param b
     * @param n
     * @return
     */
    public long tailRecusion(long a, long b, int n) {
        if(n == 1) {
            return a;
        } else if(n == 2) {
            return b;
        }
        return tailRecusion(b, a+b, n-1);
    }
}
