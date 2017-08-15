package com.hiworld.lintcode.easy;

/**
 * 实现加法运算,但是不能使用"+"运算符
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月4日 下午4:02:06
 */
public class APlusB {

    public static void main(String[] args) {
        APlusB aPlusB = new APlusB();
        System.out.println(aPlusB.aplusb(4, 5));
    }
    
    /**
     * 1 + 2 = 3;
     * 0001 + 0010 = 0011
     * 0001 | 0010 = 0011
     * 
     * 2 + 3 = 5;
     * 0010 + 0011 = 0101
     *
     * 0010 ^ 0011 = 0001
     * 0010 & 0011 = 0010
     * 0010 << 1 = 0100
     * 0100 | 0001 = 0101
     * 解析：
     * 1:相同位置同为1的要把1左移一位,然后把当前位置清"0",也就是&操作以后<<1
     * 2:相同位置一个为"1"另一个为"0"则把"1"留在当前的位置,也就是去异或(^)
     * 3:然后把第一和第二步的结果对应的位置或运算(|)
     * @param a
     * @param b
     * @return
     */
    int aplusb(int a, int b) {
        int temp1 = (a & b) << 1;
        int temp2 = a ^ b;
        return temp1 | temp2;
    }
}
