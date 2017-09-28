package com.hiworld.concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import com.google.common.base.Preconditions;

/**
 * 存储需要处理的产品的数据结构
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月28日 下午2:35:11
 */
public class Datastructure<T> {
    
    //圆环的最大大小
    private static final int MAX_RING_SIZE = 65535;
    
    //锁
    private ReentrantLock lock = new ReentrantLock();
    
    //产品最大数量
    private int maxProductCnt;
    
    //当前处理的的产品的index
    private int currentIndex;
    
    //内部用数组存储
    SlotElement<T>[] rings = null;
    
    /**
     * 内部存储Event的数据结构
     * <p>Description:</p>
     * @author hansen.wang
     * @date 2017年9月28日 上午10:55:09
     * @param <T>
     */
    static class SlotElement<T> extends ReentrantLock {
        
        private static final long serialVersionUID = 1L;
        
        private ArrayList<T> elements;
        
        public void put(T event) {
            lock();
            try {
                if(null==elements) {
                    elements = new ArrayList<>();
                }
                elements.add(event);
            } finally {
                unlock();
            }
        }
        
        private ArrayList<T> get() {
            lock();
            try {
                if(null == elements) {
                    return null;
                }
                
                ArrayList<T> ret = elements;
                elements = null;
                return ret;
            } finally {
                unlock();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public Datastructure(int maxProductCnt) {
        if(maxProductCnt <= 0) {
            throw new IllegalArgumentException("maxProductCnt must larger than zero.");
        }
        
        if(maxProductCnt > MAX_RING_SIZE) {
            maxProductCnt = MAX_RING_SIZE;
        }
        
        this.maxProductCnt = maxProductCnt;
        this.currentIndex = 0;
        rings = (SlotElement<T>[])new SlotElement[this.maxProductCnt];
        for(int i=0; i<this.maxProductCnt; i++) {
            rings[i] = new SlotElement<>();
        }
    }
    
    public Datastructure() {
        this(MAX_RING_SIZE);
    }
    
    /**
     * 
     * @param eleGetCnt
     * @return
     */
    public ArrayList<T> get() {
        lock.lock();
        try {
            if(currentIndex == maxProductCnt) {
                currentIndex = 0;
            }
            
            SlotElement<T> elements = rings[currentIndex++];
            return elements.get();
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 放入元素
     * @param event
     */
    public void put(T event) {
        Preconditions.checkNotNull(event, "event cannot be null.");
        int hashCode = event.hashCode();
        int index = hashCode % this.maxProductCnt;
        SlotElement<T> elements = rings[index];
        elements.put(event);
    }
}
