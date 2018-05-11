package com.hiworld.netty.codedetail;

/**
 * 
 * 判断一个数是不是2的N次方
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月10日 下午6:08:21
 */
public class IsPowerOfTwo {

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(0));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(2));
        System.out.println(isPowerOfTwo(3));
        System.out.println(isPowerOfTwo(4));
        System.out.println(isPowerOfTwo(5));
        System.out.println(isPowerOfTwo(6));
    }
    
    /**
     * Netty中的实现
     * @param val
     * @return
     */
    private static boolean isPowerOfTwo(int val) {
        //负数在计算机中是用与之对应的正数的补码表示的
        //8--->1000
        //8的反码0111 反码的补码为1000,所以-8在计算机中的二进制表示是1000 
        return (val & -val) == val;
    }
}
