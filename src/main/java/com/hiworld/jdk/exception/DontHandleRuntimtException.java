package com.hiworld.jdk.exception;

public class DontHandleRuntimtException {

    public static void main(String[] args) {
        DontHandleRuntimtException dontHandleRuntimtException = new DontHandleRuntimtException();
        dontHandleRuntimtException.func_1();
        System.out.println("...");
    }
    
    public int func_1() {
        return func_2();
    }
    
    public int func_2() {
        return func_3();
    }

    public int func_3() {
        return 1/0;
    }
    
}


