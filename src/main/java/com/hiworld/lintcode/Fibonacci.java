package com.hiworld.lintcode;

/**
 * 斐波纳契数列
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月3日 下午6:17:35
 */
public class Fibonacci {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        System.out.println(fibonacci_1(47));
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println(fibonacci_2(47));
        long t3 = System.currentTimeMillis();
        System.out.println(t3-t2);
    }
    
    /**
     * 递归方式
     * @param n
     * @return
     */
    public static int fibonacci_1(int n) {
        if(n == 1) {
            return 0;
        } else if(n == 2) {
            return 1;
        } else {
            return fibonacci_1(n-2) + fibonacci_1(n-1);
        }
    }
    
    /**
     * 非递归方式实现,速度飞快
     * @param n
     * @return
     */
    public static int fibonacci_2(int n) {
        if(n == 1) {
            return 0;
        } else if(n == 2) {
            return 1;
        }
        
        int count = 3;
        int beforeOne = 0;
        int beforeTwo = 1;
        int temp = 0;
        while(count++ <= n) { //注意这边的临界情况
            temp = beforeOne + beforeTwo;
            beforeOne = beforeTwo;
            beforeTwo = temp;
        }
        
        return beforeTwo;
    }
}
