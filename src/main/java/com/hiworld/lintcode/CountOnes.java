package com.hiworld.lintcode;

/**
 * 计算在一个 32 位的整数的二进制表示中有多少个 1
 * 
 * 样例：
 * 给定 32 (100000)，返回 1
 * 给定 5 (101)，返回 2
 * 给定 1023 (1111111111)，返回 10
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月12日 上午10:11:33
 */
public class CountOnes {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CountOnes countOnes = new CountOnes();
        int count = countOnes.countOnes(-1);
        System.out.println("one count : " + count);
    }
    
    /**
     * 计算一个数的二进制表示中1的个数
     * @param num
     * @return
     */
    public int countOnes(int num) {
        // write your code here
        //需要特别注意的是,如果传入的是负数,要做特殊处理
        //负数在计算机中使用的是补码表示
        //一个负数的补码就是这个负数的反码+1
        //一个负数的反码，就是这个负数的源码除了符号位，每一位取反
        //比如：-5的原码 10000101 -5的反码 11111010 -5的补码 11111011
        
        //这样这边计算负数的时候,先将负数的符号位变成0,转成正数来处理
        boolean isNegative = false;
        if(num < 0) {
            isNegative = true;
            //与最大的正整数取与,也就是符号位变成0,,其他位不变
            num = num & Integer.MAX_VALUE;
        }
        
        int oneCount = 0;
        while(num != 0) {
            //如果右移一位
            int temp = num >> 1;
            
            //如果右移一位的值和该值左移一位不相等,说明右边被移掉了一个一
            //如果相等,那么说明移掉了一个0
            if((temp << 1) != num) {
                oneCount++;
            }
            
            num = temp;
        }
        
        //如果是负数,由于符号位也是1,所以这边要+1
        if(isNegative) {
            ++oneCount;
        }
        
        return oneCount;
    }
}
