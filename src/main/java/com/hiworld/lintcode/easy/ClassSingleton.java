package com.hiworld.lintcode.easy;

public class ClassSingleton {
	
	private volatile static ClassSingleton instance;
	
	private ClassSingleton() {
		
	}
	
	/**
     * @return: The same instance of this class every time
     */
    public static ClassSingleton getInstance() {
        // write your code here
    	/*
    	if(null==instance) {
    		synchronized (ClassSingleton.class) {
				if(null==instance) {
					instance = new ClassSingleton();
				}
			}
    	}
    	
    	return instance;
    	*/
    	
    	return INNERCLASS.instance;
    }
    
    //内部类的实现方式
    static class INNERCLASS {
		private static ClassSingleton instance = new ClassSingleton();
    }
	
}
