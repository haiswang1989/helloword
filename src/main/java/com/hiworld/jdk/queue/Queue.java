package com.hiworld.jdk.queue;

import java.io.IOException;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月28日 下午4:51:10
 * @param <E>
 */
public interface Queue<E> {
    
    public void offer(E e) throws IOException, InterruptedException;
    
    public E peek() throws IOException, InterruptedException;
    
    public E poll() throws IOException, InterruptedException;
}
