package com.hiworld.jdk.object;

/**
 * Object equals()方法
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月24日 下午2:02:15
 */
public class ObjectEquals {

    public static void main(String[] args) {
        
        String[] strs = new String[3];
        System.out.println(strs.hashCode());
        
        strs[0] = "1";
        System.out.println(strs.hashCode());
        
        strs[0] = "2";
        System.out.println(strs.hashCode());
        
        String str1 = "123";
        String str2 = "123";
        System.out.println(str1 == str2);
    }

}
