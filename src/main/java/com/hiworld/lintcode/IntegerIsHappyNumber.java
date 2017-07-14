package com.hiworld.lintcode;

/**
 * 问题：写一个算法来判断一个数是不是"快乐数"。
 * 一个数是不是快乐是这么定义的：对于一个正整数，每一次将该数替换为他每个位置上的数字的平方和，然后重复这个过程直到这个数变为1，
 * 或是无限循环但始终变不到1。如果可以变为1，那么这个数就是快乐数
 * 
 * 样例：
 * 19 就是一个快乐数
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月14日 下午2:58:10
 */
public class IntegerIsHappyNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        IntegerIsHappyNumber integerIsHappyNumber = new IntegerIsHappyNumber();
        System.out.println(integerIsHappyNumber.squareSum(145));
    }
    
    /**
     * 循环计算,计算过程中出现结果为1表示是快乐数
     * 如果出现结果为4表示非快乐数,因为4会造成死循环
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        // Write your code here
        while(true) {
            if(1==n) { //如果值为1那么就是快乐数
                return true;
            } else if(4 == n) { 
                ////如果计算过程中出现n为4那么就不是,因为4会造成一个循环
                //4 16 37 58 89 145 42 20 4
                return false;
            }
            n = squareSum(n);
        }
    }
    
    /**
     * 求一个数字的各位的平方和
     * @param n
     * @return
     */
    public int squareSum(int n) {
        int sum = 0;
        while(true) {
            int remainder = n % 10;
            sum += remainder * remainder;
            if(remainder == n) {
                break;
            } else {
                n = n / 10;
            }
        }
        return sum;
    }

}
