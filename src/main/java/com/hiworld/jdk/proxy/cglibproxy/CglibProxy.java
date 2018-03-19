package com.hiworld.jdk.proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGLIB代理是通过继承业务类,生成的动态代理类是业务类的子类,通过重写业务方法进行代理
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 下午3:36:04
 */
public class CglibProxy implements MethodInterceptor {
    
    private Object proxyTarget; //需要代理的类的对象,供代理类进行真正的业务调研
    
    public Object getInstance(Object target) {
        this.proxyTarget = target;
        
        //创建增强器
        Enhancer enhancer = new Enhancer();
        //为增强器指定需要代理的业务类
        enhancer.setSuperclass(proxyTarget.getClass());
        //设置回调:对于代理类上所有的方法的调用
        //都会调用callback,而callback则需要实现intercept()方法进行拦截
        enhancer.setCallback(this);
        //创建动态代理类对象并返回
        return enhancer.create();
    }
    
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before Method.");
        proxy.invokeSuper(obj, args);
        System.out.println("After Method.");
        return null;
    }
    
}
