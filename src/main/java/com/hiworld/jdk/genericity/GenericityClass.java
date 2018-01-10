package com.hiworld.jdk.genericity;

/**
 * 泛型 类
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 上午11:27:13
 */
public class GenericityClass {
    
    public static void main(String[] args) {
        Box<String> nameBox = new Box<>("wanghaisheng");
        System.out.println("name box : " + nameBox.getEle());
        
        Box<Integer> ageBox = new Box<>(29);
        System.out.println("age box : " + ageBox.getEle());
    }
}

/**
 * 盒子,可以存储任何元素
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 上午11:26:16
 * @param <T>
 */
class Box<T> {
    
    private T ele;
    
    public Box(T e) {
        this.ele = e;
    }

    public T getEle() {
        return ele;
    }

    public void setEle(T ele) {
        this.ele = ele;
    }
}
