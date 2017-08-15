package com.hiworld.lintcode.easy;

/**
 * 问题：丑数
 * 描述：写一个程序来检测一个整数是不是丑数
 * 定义：丑数的定义是，只包含质因子 2, 3, 5 的正整数。比如 6, 8 就是丑数，但是 14 不是丑数以为他包含了质因子 7。
 * 
 * 样例：
 * 给出 num = 8，返回 true。
 * 给出 num = 14，返回 false。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月14日 下午3:50:46
 */
public class IntegerIsUgly {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        IntegerIsUgly integerIsUgly = new IntegerIsUgly();
        
        boolean ret = integerIsUgly.isUgly(1500);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param num an integer
     * @return true if num is an ugly number or false
     */
    public boolean isUgly(int num) {
        // Write your code here
        while(num > 1) {
            if((num / 5) * 5 == num) {
                num /= 5;
            } else if((num / 3) * 3 == num) {
                num /= 3;
            } else if((num / 2) * 2 == num) {
                num /= 2;
            }else {
                break;
            }
        }
        
        return num == 1;
    }

}
