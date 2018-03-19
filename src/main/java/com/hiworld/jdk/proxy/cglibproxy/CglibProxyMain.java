package com.hiworld.jdk.proxy.cglibproxy;


/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 下午3:47:58
 */
public class CglibProxyMain {

    public static void main(String[] args) {
        
        NeedProxyService proxyService = new NeedProxyService();
        CglibProxy proxy = new CglibProxy();
        NeedProxyService needProxyServiceProxy = (NeedProxyService)proxy.getInstance(proxyService);
        needProxyServiceProxy.service();
    }

}
