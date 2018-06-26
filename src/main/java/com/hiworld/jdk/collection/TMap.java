package com.hiworld.jdk.collection;

import java.util.TreeMap;

/**
 * TreeMap是一个基于"红黑树"的非线程安全的Map
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年5月16日 上午11:07:34
 */
public class TMap {

    public static void main(String[] args) {
        
        TreeMap<String, String> treeMap = new TreeMap<>();
        
        treeMap.put("name", "haiswang");
        treeMap.put("age", "30");
        
        System.out.println("name : " + treeMap.get("name"));
        System.out.println("age : " + treeMap.get("age"));
    }

}
