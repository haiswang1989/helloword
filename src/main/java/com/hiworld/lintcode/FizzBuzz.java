package com.hiworld.lintcode;

import java.util.ArrayList;

/**
 * 问题：
 * 给你一个整数n. 从 1 到 n 按照下面的规则打印每个数：
 * 如果这个数被3整除，打印fizz.
 * 如果这个数被5整除，打印buzz.
 * 如果这个数能同时被3和5整除，打印fizz buzz.
 * 
 * 比如 n = 15, 返回一个字符串数组：
 * [
 * "1", "2", "fizz",
 * "4", "buzz", "fizz",
 * "7", "8", "fizz",
 * "buzz", "11", "fizz",
 * "13", "14", "fizz buzz"
 * ]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月13日 下午3:15:09
 */
public class FizzBuzz {

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        fizzBuzz.fizzBuzz(15);
    }
    
    /**
     * 
     * @param n
     * @return
     */
    public ArrayList<String> fizzBuzz(int n) {
        ArrayList<String> results = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                results.add("fizz buzz");
            } else if (i % 5 == 0) {
                results.add("buzz");
            } else if (i % 3 == 0) {
                results.add("fizz");
            } else {
                results.add(String.valueOf(i));
            }
        }
        return results;
    }
}   
