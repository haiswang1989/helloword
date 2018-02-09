package com.hiworld.jdk.object;

import java.util.Arrays;

public class SystemSource {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] srcArray = new int[]{1,2,3,4,5};
        int[] dstArray = new int[5];
        System.arraycopy(srcArray, 0, dstArray, 0, 5);
        System.out.println(Arrays.toString(dstArray));
    }

}
