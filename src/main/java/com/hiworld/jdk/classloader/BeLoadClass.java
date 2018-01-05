package com.hiworld.jdk.classloader;


public class BeLoadClass {
    
    private static String STATIC_MEMBER = null;
    
    private String NON_STATIC_MEMBER = null;
    
    {
        System.out.println("init non static member...");
        NON_STATIC_MEMBER = "2";
    }
    
    static {
        System.out.println("init static member...");
        STATIC_MEMBER = "1";
    }
    
    public BeLoadClass() {
        System.out.println("Construct method...");
    }
    
    public static String getStaticMember() {
        System.out.println("Get static member...");
        return STATIC_MEMBER;
    }
    
    public String getNonStaticMember() {
        return NON_STATIC_MEMBER;
    }
}
