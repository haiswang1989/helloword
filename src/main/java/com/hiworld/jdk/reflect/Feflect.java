package com.hiworld.jdk.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Feflect {

    public static void main(String[] args) {
        
        Clazz clazz = new Clazz();
        
        Method method = null;
        try {
            method = Clazz.class.getDeclaredMethod("func");
            method.setAccessible(true);
            method.invoke(clazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
    }

}
