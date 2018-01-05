package com.hiworld.jdk.classloader;

public class ClassFornameCompareClassLoaderLoadClass {
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        initWithForname();
        
//        initWithClassLoaderLoadClass();
    }
    
    /**
     * 使用Class.forname来load class,除了把.class加载到JVM以外,还会对类进行解析,执行类的static块
     * 非static块不会进行初始化
     * @throws ClassNotFoundException
     */
    public static void initWithForname() throws ClassNotFoundException {
        Class.forName("com.hiworld.classloader.BeLoadClass");
    }
    
    /**
     * 使用ClassLoader.loadClass只会把.class加载到JVM,不会做任何的初始化
     * @throws ClassNotFoundException
     */
    public static void initWithClassLoaderLoadClass() throws ClassNotFoundException {
        ClassFornameCompareClassLoaderLoadClass.class.getClassLoader().loadClass("com.hiworld.classloader.BeLoadClass");
    }
}
