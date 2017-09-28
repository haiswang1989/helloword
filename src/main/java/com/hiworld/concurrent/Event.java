package com.hiworld.concurrent;

import lombok.Getter;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月28日 下午2:36:03
 */
public class Event {
    //产品Id
    @Getter
    private int productId;
    
    public Event(int productId) {
        this.productId = productId;
    }
    
    @Override
    public int hashCode() {
        return this.productId;
    }
}
