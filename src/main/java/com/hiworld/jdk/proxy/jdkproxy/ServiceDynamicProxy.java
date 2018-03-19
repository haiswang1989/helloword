package com.hiworld.jdk.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务的动态代理
 * 
 * 对于"同类型"的代理("before"操作和"after"操作一致的代理)可以抽象为一个"InvocationHandler"
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 上午10:33:06
 */
public class ServiceDynamicProxy implements InvocationHandler {
    
    private Object subject;
    
    public ServiceDynamicProxy(Object subject) {
        this.subject = subject;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Method.");
        Object retObj = method.invoke(subject, args);
        System.out.println("After Method.");
        return retObj;
    }
}
