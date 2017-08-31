package com.hiworld.lintcode.medium;

/**
 * 问题：统计数字
 * 描述：计算数字k在0到n中的出现的次数，k可能是0~9的一个值
 * 
 * 样例：
 * 例如n=12，k=1，在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]，我们发现1出现了5次 (1, 10, 11, 12)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月31日 下午4:14:52
 */
public class DigitCounts {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DigitCounts digitCounts = new DigitCounts();
        int count = digitCounts.getCount(10, 0);
        System.out.println("count ：" + count);
    }
    
    /*
     * 暴力解法(AC)
     * param k : As description.
     * param n : As description.
     * return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        // write your code here
        if(n < k) {
            return 0;
        }
        
        int count = 0;
        for(int i=0; i<=n; i++) {
            count += getCount(i, k);
        }
        
        return count;
    }
    
    /**
     * 
     * @param num
     * @param k
     * @return
     */
    public int getCount(int num, int k) {
        if(k == 0 && num == 0) { //如果num和查找的K都是0,那么直接返回1
            return 1;
        }
        
        int count = 0;
        while(num != 0) {
            int mode = num % 10;
            if(mode == k) {
                count++;
            }
            
            num /= 10; 
        }
        return count;
    }
}
