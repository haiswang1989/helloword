package com.hiworld.netty.codedetail;

import java.util.concurrent.atomic.AtomicInteger;

/**
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
