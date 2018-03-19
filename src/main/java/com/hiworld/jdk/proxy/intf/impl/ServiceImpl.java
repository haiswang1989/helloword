package com.hiworld.jdk.proxy.intf.impl;

import com.hiworld.jdk.proxy.intf.IService;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 上午10:26:07
 */
public class ServiceImpl implements IService {

    @Override
    public void method() {
        System.out.println("Method body.");
    }
}
