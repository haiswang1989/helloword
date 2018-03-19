package com.hiworld.jdk.proxy.intf.impl;

import com.hiworld.jdk.proxy.intf.IAnotherService;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月19日 上午11:05:57
 */
public class AnotherServiceImpl implements IAnotherService {

    @Override
    public void func() {
        System.out.println("Func body.");
    }
}
