package com.hiworld.jdk.collection;

import java.util.ArrayList;
import java.util.List;

public class ListTravelAndDel {

    public static void main(String[] args) {
        
        ArrayList<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        
        for (Integer integer : l) {
            System.out.println(integer);
            l.remove(integer);
        }
    }

}
