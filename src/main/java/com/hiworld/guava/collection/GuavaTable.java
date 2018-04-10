package com.hiworld.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class GuavaTable {

    public static void main(String[] args) {
        
        Table<String, Integer, Integer> table = HashBasedTable.create();
        table.put("wanghaisheng", 1, 1);
        table.put("wanghaisheng", 2, 2);
        
        System.out.println(table);
    }

}
