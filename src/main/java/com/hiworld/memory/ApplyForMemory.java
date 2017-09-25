package com.hiworld.memory;

import java.util.ArrayList;
import java.util.List;

public class ApplyForMemory {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int count = Integer.parseInt(args[0]);
        List<OneM> list = new ArrayList<>();
        for(int i=0; i<count; i++) {
            list.add(new OneM());
        }
        
        while(true) {
        }
    }
}

class OneM {
    byte[] oneM = null;
    public OneM() {
        oneM = new byte[1024 * 1024];
    }
}
