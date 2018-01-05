package com.hiworld.jdk.queue;

import java.io.IOException;

public abstract class AbstractQueue<E> implements Queue<E> {

    @Override
    public abstract void offer(E e) throws IOException;

    @Override
    public abstract E peek() throws IOException;

    @Override
    public abstract E poll() throws IOException;
}
