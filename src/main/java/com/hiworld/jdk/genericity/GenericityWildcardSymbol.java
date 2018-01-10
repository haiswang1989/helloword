package com.hiworld.jdk.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * "PECS"原则
 * Producer(get) extents Consumer(add) super 原则
 * 
 * 把自己当做Producer,只能进行Get操作(别人)
 * 把自己当做Consumer,只能进行Add操作(别人)
 * 
 * 泛型"通配符"
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午3:48:07
 */
public class GenericityWildcardSymbol {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Box<Fruit> fruitBox = new Box<>(new Fruit("fruit"));
        Fruit fruit = fruitBox.getEle();
        System.out.println(fruit);
        
        Box<Apple> appleBox = new Box<>(new Apple("apple"));
        Apple apple = appleBox.getEle();
        System.out.println(apple);
        
        Box<Orange> orangeBox = new Box<>(new Orange("orange"));
        Orange orange = orangeBox.getEle();
        System.out.println(orange);
        
        
//        Box<Fruit> fruitAppleBox = new Box<>(new Apple()); //编译失败
//        Box<Fruit> fruitOrangeBox = new Box<>(new Orange()); //编译失败
        
        List<? super Fruit> canPutCannotGet = new ArrayList<>();
        canPutCannotGet.add(new Apple("apple"));
        canPutCannotGet.add(new Orange("orange"));
        
//        Fruit fruit1 = canPutCannotGet.get(0); //get不行
        
        List<? extends Fruit> canGetCannotPut = new ArrayList<>();
//        canGetCannotPut.add(new Apple("apple")); //add不行
        Fruit fruit2  = canGetCannotPut.get(0);
    }

}

/**
 * 水果
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午3:42:14
 */
class Fruit {
    
    public String desc;
    
    public Fruit(String desc) {
        this.desc = desc;
    }
    
    @Override
    public String toString() {
        return "i'am " + desc;
    }
}

/**
 * 苹果
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午3:42:22
 */
class Apple extends Fruit {
    
    public Apple(String desc) {
        super(desc);
    }
    
    @Override
    public String toString() {
        return "i'am " + desc;
    }
}

/**
 * 橘子
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午3:42:31
 */
class Orange extends Fruit {
    
    public Orange(String desc) {
        super(desc);
    }
    
    @Override
    public String toString() {
        return "i'am " + desc;
    }
}