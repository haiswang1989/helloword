package com.hiworld.cache;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LRU:Least recently used
 * 最近最少使用淘汰策略
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月14日 下午4:50:18
 */
public class LRUCache<K,V> {
    
    //LRU的淘汰策略通过LinkedHashMap来实现
    private LinkedHashMap<K,Object> eliminateStrategy;
    
    //实际存储K-V的cache对象
    private ICache<K,V> cache;
    
    //cache的实际大小(size/loadFactor) + 1,避免出现扩容
    private int actualSize;
    
    //加载因子
    private float loadFactor;
    
    //cache的大小
    private int size;
    
    //用于填充LinkedHashMap的
    private static final Object OBJ = new Object();
    
    public LRUCache(final int size, float loadFactor) {
        this.size = size;
        this.loadFactor = loadFactor;
        this.actualSize = (int)(this.size / this.loadFactor) + 1;
        cache = new ConcurrentHashMapCache<>(this.actualSize, this.loadFactor);
        eliminateStrategy = new LinkedHashMap<K,Object>(this.actualSize, this.loadFactor, true) {
            //构造函数的第三个参数:排序模式
            //accessOrder : true 访问顺序
            //accessOrder : false 插入顺序
            private static final long serialVersionUID = 1L;
            
            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<K, Object> eldest) {
                //eldest为需要删除entry
                //如果accessOrder:true,这个entry为"最近最少被访问"元素,LRU
                //如果accessOrder:false,这个entry为"最早插入的"元素,FIFO
                boolean needRemove = size() > size;
                if(needRemove) {
                    K key = eldest.getKey();
                    //缓存淘汰
                    cache.remove(key);
                }
                
                return needRemove;
            }
        };
    }
    
    /**
     * 
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        eliminateStrategy.put(key, OBJ);
        return cache.put(key, value);
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public V get(Object key) {
        eliminateStrategy.get(key);
        return cache.get(key);
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public V remove(Object key) {
        eliminateStrategy.remove(key);
        return cache.remove(key);
    }
    
    /**
     * 
     * @return
     */
    public int size() {
        return cache.size();
    }
}

/**
 * 缓存API接口
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月15日 下午1:58:03
 * @param <K>
 * @param <V>
 */
interface ICache<K,V> {
    
    /**
     * 
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value);
    
    /**
     * 
     * @param key
     * @return
     */
    public V get(Object key);
    
    /**
     * 
     * @param key
     * @return
     */
    public V remove(Object key);
    
    /**
     * 
     * @return
     */
    public int size();
}

/**
 * 缓存的ConcurrentHashMap的实现
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年3月14日 下午5:47:00
 * @param <K>
 * @param <V>
 */
class ConcurrentHashMapCache<K,V> implements ICache<K,V> {
    
    private ConcurrentHashMap<K, V> cache;
    
    public ConcurrentHashMapCache(int size, float loadFactor) {
        cache = new ConcurrentHashMap<>(size, loadFactor);
    }
    
    @Override
    public V put(K key, V value) {
        return cache.put(key, value);
    }
    
    @Override
    public V get(Object key) {
        return cache.get(key);
    }
    
    @Override
    public V remove(Object key) {
        return cache.remove(key);
    }
    
    @Override
    public int size() {
        return cache.size();
    }
}