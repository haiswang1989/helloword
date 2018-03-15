package com.hiworld.jdk.memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class CurrHeapState {

    public static void main(String[] args) {
        
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        long usedMemory = memoryUsage.getUsed();
        System.out.println("已用的内存：" + usedMemory);
    }

}
