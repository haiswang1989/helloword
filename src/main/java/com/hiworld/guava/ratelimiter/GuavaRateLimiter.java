package com.hiworld.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Guava基于令牌桶算法的实现类
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月18日 上午10:04:35
 */
public class GuavaRateLimiter {

    public static void main(String[] args) {
        
        //限流,每秒1000个令牌
        RateLimiter rateLimiter = RateLimiter.create(1000.0); 
        while(true) {
            //判断是否拿到令牌
            boolean allow = rateLimiter.tryAcquire();
        }
    }
}
