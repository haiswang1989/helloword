package com.hiworld.guava.stopwatch;


import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

/**
 * 计时,获取时间流逝的方式
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月26日 上午10:19:49
 */
public class TimeElapse {

    public static void main(String[] args) throws InterruptedException {
        
        Stopwatch stopwatch = Stopwatch.createStarted();
        
        long elapseTime = 0l;
        try {
            TimeUnit.SECONDS.sleep(1);
            elapseTime = stopwatch.elapsed(TimeUnit.SECONDS);
        } finally {
            if(null!=stopwatch && stopwatch.isRunning()) {
                elapseTime = stopwatch.elapsed(TimeUnit.SECONDS);
            }
        }
        
        System.out.println("elapse time : " + elapseTime);
    }

}
