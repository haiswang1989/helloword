package com.hiworld.jdk.collection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 上午10:37:55
 */
public class CopyOnWriteArrayListSource {

    public static void main(String[] args) {
        
        final CopyOnWriteArrayList<Integer> cowArrayList = new CopyOnWriteArrayList<>();
        cowArrayList.add(1);
        cowArrayList.add(2);
        cowArrayList.add(3);
        cowArrayList.add(4);
        
//        cowArrayList.remove(1);
//        int value = cowArrayList.get(0);
//        System.out.println(value);
//        cowArrayList.clear();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Modify.");
                cowArrayList.set(2, 100);
            }
        }).start();
        
        Iterator<Integer> iter = cowArrayList.iterator();
        while(iter.hasNext()) {
            int value = iter.next();
            System.out.println(value);
        }
        
    }
}
