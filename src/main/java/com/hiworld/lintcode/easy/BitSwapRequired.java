package com.hiworld.lintcode.easy;

/**
 * 问题：如果要将整数A转换为B，需要改变多少个bit位？
 * 
 * 样例
 * 如把31转换为14，需要改变2个bit位。
 * (31)10=(11111)2
 * (14)10=(01110)2
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月14日 下午12:49:30
 */
public class BitSwapRequired {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int a = 31;
        int b = 14;
        
        BitSwapRequired bitSwapRequired = new BitSwapRequired();
        int count = bitSwapRequired.bitSwapRequired(a, b);
        System.out.println("need change : " + count);
    }
    
    /**
     * 核心主要的是计算一个数中1的个数
     * @param a
     * @param b
     * @return
     */
    public int bitSwapRequired(int a, int b) {
        //取异或,位置为1的就表示不相同的部分,需要进行变换的部分
        //需要注意的就是负数的问题
        int xor = a ^ b;
        return countBitOne(xor);
    }
    
    /**
     * 计算一个数的二进制中1的个数
     * @param xor
     * @return
     */
    public int countBitOne(int xor) {
        boolean isNegative = false;
        
        if(xor < 0) { //如果是负数,需要先吧前面的符号位忽略(转换成正数计算),计算结束后再加1
            isNegative = true;
            xor &= Integer.MAX_VALUE;
        }
        
        int oneCount = 0;
        while(xor != 0) {
            int temp = xor >> 1;
            if(temp<<1 != xor) {
                ++oneCount;
            }
            
            xor = temp;
        }
        
        if(isNegative) {
            ++oneCount;
        }
        
        return oneCount;
    }
}
