package com.hiworld.jdk.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.hiworld.jdk.proxy.intf.IAnotherService;
import com.hiworld.jdk.proxy.intf.IService;
import com.hiworld.jdk.proxy.intf.impl.AnotherServiceImpl;
import com.hiworld.jdk.proxy.intf.impl.ServiceImpl;

/**
 * JDK 动态代理
 * 
 * 1：JDK代理解决了普通代理需要为每一个类实现一个"代理类"的缺陷
 * 2：避免多个被代理类的"before"处理以及"after处理"逻辑一致的重复代码
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 上午10:52:26
 */
public class DynamicProxyMain {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        
        //对IService进行代理
        IService service = new ServiceImpl();
        InvocationHandler invocationHandler = new ServiceDynamicProxy(service);
        ClassLoader loader = service.getClass().getClassLoader();
        Class[] interfaces = service.getClass().getInterfaces();
        IService serviceProxy = (IService)Proxy.newProxyInstance(loader, interfaces, invocationHandler);
        serviceProxy.method();
        
        //对IAnotherService进行代理
        IAnotherService anotherServoce = new AnotherServiceImpl();
        invocationHandler = new ServiceDynamicProxy(anotherServoce);
        loader = anotherServoce.getClass().getClassLoader();
        interfaces = anotherServoce.getClass().getInterfaces();
        IAnotherService anotherServiceProxy = (IAnotherService)Proxy.newProxyInstance(loader, interfaces, invocationHandler);
        anotherServiceProxy.func();
    }

}
