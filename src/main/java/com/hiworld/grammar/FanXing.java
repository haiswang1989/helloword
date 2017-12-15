package com.hiworld.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型相关
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年12月14日 上午11:13:46
 */
public class FanXing {
    
    public static void main(String[] args) {
        
        //List<T> tList = new ArrayList<>(); //编译出错
        List<Object> objList = new ArrayList<>();
        List<? extends Object> list = new ArrayList<Object>();
        
        Integer age = 27;
        String name = "haiswang";
        
        objList.add(age);
        objList.add(name);
        
//        list.add(age);
//        list.add(name);
    }
    
}
