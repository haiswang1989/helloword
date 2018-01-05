package com.hiworld.jdk.queue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GetTClass extends ArrayList<Integer> {

    public static void main(String[] args) {
        
        ParameterizedType type = (ParameterizedType)GetTClass.class.getGenericSuperclass();
        Type t = type.getActualTypeArguments()[0];
        System.out.println((Class)t);
    }

}
