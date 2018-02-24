package com.hiworld.shenjian58;

import java.util.HashMap;
import java.util.Map;

/**
 * 场景：业务有"定时任务"或者"定时超时"的场景
 * 
 * 假设场景：缓存的淘汰策略,最近10s内没有访问的key进行淘汰
 * 
 *  
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月23日 下午4:29:01
 */
public class DelayTrigger {
    
    public static void main(String[] args) {
        
    }
    
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月23日 下午4:48:49
 * @param <K>
 * @param <V>
 */
class Cache<K,V> {
    
    //实际存储K-V的结构
    private Map<K, CacheValue> cache;
    
    //最长的超时时间,这个值会用于构建,环形数组的的元素个数(单位s)
    private long maxTimeout;
    
    //定时器的执行间隔,单位毫秒
    private long timerPeriod;
    
    //环形数组的大小
    private long ringArraySize;
    
    /**
     * 
     * @param maxTimeout
     */
    public Cache(long maxTimeout) {
        this(maxTimeout, 1L);
    }
    
    /**
     * 
     * @param maxTimeout
     * @param timerPeriod
     */
    public Cache(long maxTimeout, long timerPeriod) {
        this.maxTimeout = maxTimeout;
        this.timerPeriod = timerPeriod;
        cache = new HashMap<>();
        
        ringArraySize = (maxTimeout * 1000L) / timerPeriod;
    }
    
    /**
     * 
     * @param key
     * @param value
     * @param timeout 该Key的超时时间
     */
    public void put(K key, V value, long timeout) {
        cache.put(key, new CacheValue(value, timeout));
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public V get(K key) {
        return null;
    }
    
    /**
     * 判断一个key是否超时
     * @param key
     * @param timeout
     * @return
     */
    private boolean isKeyTimeout(K key, long timeout) {
       return true; 
    }
    
    
    /**
     * 
     * <p>Description:</p>
     * @author hansen.wang
     * @date 2018年2月23日 下午4:55:19
     */
    class CacheValue {
        private V value;
        private long timeout;
        
        public CacheValue(V value, long timeout) {
            this.value = value;
            this.timeout = timeout;
        }
        
        public V getValue() {
            return value;
        }

        public long getTimeout() {
            return timeout;
        }
    }
}

