package com.hiworld.classloader;

public class WitchClassLoader {

    public static void main(String[] args) {
        
        //app classloader
        ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(appClassLoader);
        
        //app classloader
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        
        //NULL ,因为bootstrap是C写的,所以是null
        ClassLoader bootstrapClassLoader = String.class.getClassLoader();
        System.out.println(bootstrapClassLoader);
        
    }

}
