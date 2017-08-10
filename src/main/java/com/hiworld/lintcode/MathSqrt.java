package com.hiworld.lintcode;

/**
 * 问题：x的平方根
 * 描述：实现 int sqrt(int x) 函数，计算并返回 x 的平方根。
 * 
 * 样例：
 * sqrt(3) = 1
 * sqrt(4) = 2
 * sqrt(5) = 2
 * sqrt(10) = 3
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月10日 下午3:14:38
 */
public class MathSqrt {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MathSqrt mathSqrt = new MathSqrt();
        int ret = mathSqrt.sqrt(2147483647);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 主要注意两个点：
     * 1：平均数的溢出
     * 2：平方的溢出
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        int begin = 1;
        int end = x;
        long result = 1l * x; //
        while(true) {
            int middle = average(begin, end); //使用自己实现的求平均数的方法,避免出现Integer溢出
            long square = 1l * middle * middle; //square定义为long,避免两个Integer相乘出现溢出
            if(square == result) {
                return middle;
            } else if(square < result) {
                int middlePlus1 = middle + 1;
                square = 1l * middlePlus1 * middlePlus1;
                if(square == result) {
                    return middlePlus1;
                } else if(square > result) {
                    return middle;
                } else {
                    begin = middle;
                }
            } else { //square > result
                int middleMinus1 = middle - 1;
                square = 1l * middleMinus1 * middleMinus1;
                if(square == result || square < result) {
                    return middleMinus1;
                } else {
                    end = middle;
                }
            }
        }
    }
    
    /**
     * 求平均数,避免溢出的情况
     * @param i1
     * @param i2
     * @return
     */
    public int average(int i1, int i2) {
        int remainder = 0;
        int ret = 0;
        ret += i1 / 2;
        if(i1 % 2 != 0) {
            remainder += 1;
        }
        
        ret += i2 / 2;
        if(i2 % 2 != 0) {
            remainder += 1;
        }
        
        return ret + remainder/2;
    }
}
