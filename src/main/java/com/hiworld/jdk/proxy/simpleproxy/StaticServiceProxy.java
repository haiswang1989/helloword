package com.hiworld.jdk.proxy.simpleproxy;

import com.hiworld.jdk.proxy.intf.IService;
import com.hiworld.jdk.proxy.intf.impl.ServiceImpl;

/**
 * "静态代理"
 * 
 * 缺点：
 * 1：每个需要代理的类都需要一个"代理类"与之对应
 * 2：如果代理类的"before"处理以及"after处理"都是一致的,那么就会出现很多重复代码
 *
 * 采用组合的方式,在代理类中嵌入被代理类的对象来实现
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 上午10:57:17
 */
public class StaticServiceProxy implements IService {
    
    //组合的方式
    private ServiceImpl serviceImpl;
    
    public StaticServiceProxy(ServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }
    
    @Override
    public void method() {
        System.out.println("Before call serviceImpl method.");
        serviceImpl.method();
        System.out.println("After call serviceImpl method.");
    }
}
