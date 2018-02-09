package com.hiworld.mybatis.cache;

import java.util.LinkedHashMap;

/**
 * 
 * LRU 最近最少使用
 * 
 * Mybatis的LRU队列的实现
 * 
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月9日 上午9:39:32
 */
public class LRUCache {
    
    private LinkedHashMap<Integer, Integer> cache;
    private Object eldestKey;
    
    public void setSize(final int size) {
        cache = new LinkedHashMap<Integer, Integer>(size, 0.75F, true) {
            private static final long serialVersionUID = 1L;
            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
                boolean needRemove = size() > size;
                if(needRemove) {
                    eldestKey = eldest.getKey(); //记录当前最老的缓存数据的key值,
                }
                
                return needRemove;
            }
        };
    }
    
    
    
    
}
