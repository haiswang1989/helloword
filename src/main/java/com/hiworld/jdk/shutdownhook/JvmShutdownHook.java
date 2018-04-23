package com.hiworld.jdk.shutdownhook;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月19日 下午3:15:27
 */
public class JvmShutdownHook {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("优雅关闭.");
            }
        }));
        
        System.out.println("start.");
        /*
        while(true) {
            
        }
        */
        
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
        }
        
        /*
        //正常退出
        System.exit(0);
        
        //异常退出
        System.exit(-1);
        
        List<byte[]> memorys = new ArrayList<>();
        while(true) {
            byte[] oneM = new byte[1024 * 1024];
            memorys.add(oneM);
        }
        */
    }
}
