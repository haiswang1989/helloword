package com.hiworld.jdk.directmemory;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class TestByteBuf {

    public static void main(String[] args) {
        int oneG = 1024 * 1024 * 1024;
        ByteBuffer dirBuffer = ByteBuffer.allocateDirect(oneG);
        
        
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        dirBuffer.clear();
    }
}
