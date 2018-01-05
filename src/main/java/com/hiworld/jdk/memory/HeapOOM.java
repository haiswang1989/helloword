package com.hiworld.jdk.memory;

import java.util.LinkedList;
import java.util.List;

/**
 * -Xmx64m -Xms64m
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月28日 下午3:14:53
 */
public class HeapOOM {

    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        System.out.println("Get count : " + count);
        List<byte[]> memoryList = new LinkedList<>();
        for(int i=0; i<count; ++i) {
            System.out.println("allocate count : " + i);
            memoryList.add(allocteMemory5M());
        }
        
        System.out.println("over...");
    }
    
    /**
     * 一次申请5M
     * @return
     */
    private static byte[] allocteMemory5M() {
        return new byte[5 * 1024 * 1024];
    }
}
