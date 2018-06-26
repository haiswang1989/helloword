package com.hiworld.jdk.collection;

import java.util.Hashtable;

/**
 * 
 * HashTable
 * 
 * Hashtable是一个线程安全的类
 * 用于存储K-V对的和HashMap的功能一致
 * 
 * 内部是通过将方法使用"synchronized"来修饰实现线程安全的
 * 
 * Hashtable和HashMap的底层的存储结构基本是一致的,都是通过数组+链表的方式来进行存储的
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月16日 上午10:08:31
 */
public class HTable {

    public static void main(String[] args) {
        
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("name", "haiswang");
        hashtable.put("age", "30");
        
        System.out.println("name : " + hashtable.get("name"));
        System.out.println("age : " + hashtable.get("age"));
        
    }

}
