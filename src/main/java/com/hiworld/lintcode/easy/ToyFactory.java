package com.hiworld.lintcode.easy;

public class ToyFactory {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ToyFactory tf = new ToyFactory();
        tf.getToy("Dog").talk();
        tf.getToy("Cat").talk();
    }
    
    /**
     * @param type a string
     * @return Get object of the type
     */
    public Toy getToy(String type) {
        // Write your code here
        if("Dog".equals(type)) {
            return new Dog();
        } else if("Cat".equals(type)) {
            return new Cat();
        } else {
            return null;
        }
    }
}

interface Toy {
    void talk();
}

class Dog implements Toy {
    @Override
    public void talk() {
        // TODO Auto-generated method stub
        System.out.println("Wow");
    }
}

class Cat implements Toy {
    @Override
    public void talk() {
        // TODO Auto-generated method stub
        System.out.println("Meow");
    }
}