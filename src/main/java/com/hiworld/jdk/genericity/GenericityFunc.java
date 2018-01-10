package com.hiworld.jdk.genericity;

/**
 * 泛型 方法
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午2:31:09
 */
public class GenericityFunc {
    
    public static void main(String[] args) {
        StaticClass.printKV("key-1", "String-value-1");
        StaticClass.printKV("key-2", 1000);
        StaticClass.<String,String>printKV("xxx", "xxx");
        
        System.out.println(StaticClass.compare(1, 2));
        System.out.println(StaticClass.compare(1.1, 2.2));
    }
}

/**
 * 返回值前面是"泛型"方法的参数类型
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午3:04:34
 */
class StaticClass {
    
    /**
     * 泛型方法
     * @param key
     * @param value
     */
    public static <K,V> void printKV(K key, V value) {
        System.out.println("key : " + key + " ,value : " + value);
    }
    
    /**
     * 
     * @param input1
     * @param input2
     * @return
     */
    public static <T extends Comparable<T>> T compare(T input1, T input2) {
        return input1.compareTo(input2) >= 0 ? input1 : input2;
    }
}
