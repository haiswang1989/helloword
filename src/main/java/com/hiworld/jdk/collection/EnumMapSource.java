package com.hiworld.jdk.collection;

import java.util.EnumMap;

/**
 * 以枚举值为一个入参，构造与枚举值等大的数组
 * 以Enum.ordinal()下标来访问数组
 * 
 * 内部实际以一个数组来存在元素(索引速度快)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年2月6日 上午9:49:34
 */
public class EnumMapSource {

    public static void main(String[] args) {
        
        EnumMap<Color, String> enumMap = new EnumMap<>(Color.class);
        
        //对于Key的值,每次都需要进行Check,是否是enum.class
        //或者是该类的子类
        enumMap.put(Color.RED, "红色");
        enumMap.put(Color.BLACK, "黑色");
        enumMap.put(Color.WHITE, "白色");
        
        System.out.println("size : " + enumMap.size());
        
        String desc = enumMap.get(Color.RED);
        System.out.println(desc);
    }
}

enum Color {
    RED,BLACK,WHITE;
}
