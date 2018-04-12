package com.hiworld.jdk.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHMap {

    public static void main(String[] args) {
        
        ConcurrentHashMap<String, String> cHashMap = new ConcurrentHashMap<>();
        
        cHashMap.put("name", "wanghaisheng");
        
        cHashMap.get("name");
        
        cHashMap.get("age");
    }

}
