package com.hiworld.memory;

/**
 * java.lang.StackOverflowError
 * -Xss128 968次
 * -Xss256 2462次
 * -Xss512 5447次
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月28日 下午4:15:39
 */
public class StackOverFlow {
    
    public static void main(String[] args) {
        add(0);
    }
    
    public static void add(int count) {
        System.out.println("count : " + count);
        if(count == 12000) {
            try {
                Thread.sleep(100000l);
            } catch (InterruptedException e) {
            }
        } else {
            add(count+1);
        }
    }
}
