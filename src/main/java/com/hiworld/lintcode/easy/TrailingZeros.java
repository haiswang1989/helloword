package com.hiworld.lintcode.easy;

/**
 * 问题：尾部的零
 * 描述：设计一个算法，计算出n阶乘中尾部零的个数
 * 样例：
 * 11! = 39916800，因此应该返回 2
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月11日 下午3:59:01
 */
public class TrailingZeros {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TrailingZeros trailingZeros = new TrailingZeros();
        long count = trailingZeros.trailingZeros(10);
        System.out.println("count : " + count);
    }
    
    /*
     * 这个问题可以看出,寻找5的因子
     * 
     * 需要注意的:
     * 5
     * 10
     * 15
     * 20
     * 30 
     * 对于这样的数,需要进行count++ 
     * 
     * 25 = 5*5    是两个5的因子
     * 50 = 5*5*2  是两个5的因子
     * 75 = 5*5*3  是两个5的因子
     * 100 = 5*5*4 是两个5的因子
     * 对于这样的需要进行 count+2
     * 
     * 125 = 5*5*5 是三个5的因子
     * 对于这样的需要进行count+3
     * 
     * 除法的意义：
     * 
     * 1 2 3 4 (5) 6 7 8 9 (10) 11 12 13 14 (15) 16 17 18 19 (20) 21 22 23 24 (25) 26 //5,10,15,20,25 分别计算一次
     * 5 10 15 20 (25) //25再计算一次
     * 
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        long count = 0;
        while(n > 5) { //不大于5,那么就不会出现0
            count += (n/5);
            n /= 5;
        }
        
        return count;
    }
}
