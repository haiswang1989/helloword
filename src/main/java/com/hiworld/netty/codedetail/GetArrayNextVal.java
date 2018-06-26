package com.hiworld.netty.codedetail;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 关于取余操作,如果除数是2的整数次幂,可以将取余转换成位的操作,这样提高性能
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月10日 下午6:17:16
 */
public class GetArrayNextVal {
    
    private static int[] ary = new int[]{1,2,3,4,5,6,7,8};
    private static AtomicInteger indexForMod = new AtomicInteger();
    private static AtomicInteger indexForAnd = new AtomicInteger();
    
    public static void main(String[] args) {
        
        //除数是2的整数次幂
        int chushu = 8;
        for(int i=0; i<100; i++) {
            int byAnd = i & (chushu-1); //转换成位运算
            int byMod = i % chushu; //取余数
            System.out.println(byAnd + ":" + byMod);
        }
        
        int cnt = 1000000000;
        long use = getByModUse(cnt);
        System.out.println(use);
        
        use = getByAndUse(cnt);
        System.out.println(use);
    }
    
    /**
     * 
     * @param cnt
     * @return
     */
    private static long getByModUse(int cnt) {
        long t1 = System.currentTimeMillis();
        int ret = 0;
        while(cnt-- != 0) {
            ret = ary[indexForMod.getAndIncrement() & ary.length - 1];
        }
        return System.currentTimeMillis() - t1;
    }
    
    private static long getByAndUse(int cnt) {
        long t1 = System.currentTimeMillis();
        int ret = 0;
        while(cnt-- != 0) {
            ret = ary[indexForAnd.getAndIncrement() % ary.length];
        }
        return System.currentTimeMillis() - t1;
    }

}
