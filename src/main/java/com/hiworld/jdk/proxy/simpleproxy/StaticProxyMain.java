package com.hiworld.jdk.proxy.simpleproxy;

import com.hiworld.jdk.proxy.intf.impl.ServiceImpl;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 上午10:59:05
 */
public class StaticProxyMain {

    public static void main(String[] args) {
        
        ServiceImpl serviceImpl = new ServiceImpl();
        
        StaticServiceProxy serviceProxy = new StaticServiceProxy(serviceImpl);
        serviceProxy.method();
    }
}
