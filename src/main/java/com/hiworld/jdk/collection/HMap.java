package com.hiworld.jdk.collection;

import java.util.HashMap;

public class HMap {

    public static void main(String[] args) {
        
        HashMap<String, String> hMap = new HashMap<>();
        
        for(int i=0; i<20; i++) {
            hMap.put(i + "", i + "");
        }
        
        
        
    }
}
