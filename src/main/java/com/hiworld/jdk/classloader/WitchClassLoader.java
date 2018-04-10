package com.hiworld.jdk.classloader;

public class WitchClassLoader {

    public static void main(String[] args) {
        
        //app classloader
        ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(appClassLoader);
        
        //线程上下文classloader
        ClassLoader threadContextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(threadContextClassLoader);
        
        //NULL ,因为bootstrap是C写的,所以是null
        //jre/lib/rt.jar
        ClassLoader bootstrapClassLoader = String.class.getClassLoader();
        System.out.println(bootstrapClassLoader);
        
    }

}
