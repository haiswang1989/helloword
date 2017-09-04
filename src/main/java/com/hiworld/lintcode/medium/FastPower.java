package com.hiworld.lintcode.medium;

/**
 * 问题:快速幂
 * 描述：计算an % b，其中a，b和n都是32位的整数。
 * 
 * 样例：
 * 例如 2^31 % 3 = 2
 * 例如 100^1000 % 1000 = 0
 * 
 * 挑战：O(logn) --- 二分查找的时间复杂度(一半一半的递归)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月4日 下午4:34:04
 */
public class FastPower {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        FastPower fastPower = new FastPower();
        int ret = fastPower.fastPower(100, 1000, 1000);
        System.out.println("ret : " + ret);
    }
    
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if(n == 0) {
            return 1 % b; 
        } 
        
        Long result = withRecusion(a, b, n);
        return result.intValue();
    }
    
    /**
     * 
     * (A * B) % P = (A % P * B % P) % P
     * 
     * (A ^ N) % B
     * 如果N为奇数
     * (A^(N/2) * A^(N/2) * A) % B = (A^(N/2) % B * A^(N/2) % B * A % B) % B
     * 
     * 如果N为偶数
     * (A^(N/2) * A^(N/2)) % B = (A^(N/2) % B * A^(N/2) % B) % B
     * 
     * A^1 = A
     * A^0 = 1
     * @param a
     * @param b
     * @param n
     * @return
     */
    public long withRecusion(int a, int b, int n) {
        if(n == 1) {
            return a % b;
        }
        
        long value = withRecusion(a, b, n/2);
        if((n & 1) == 1) { //奇数
            //注意这边的溢出,如下写法可解决
            //return (value * value * (a % b)) % b;
            return ((value * value) % b * (a % b)) % b; 
        } else { //偶数
            return (value * value) % b;
        }
    }
}
