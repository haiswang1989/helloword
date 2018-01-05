package com.hiworld.jdk.for20171226;

import java.util.Arrays;

public class FastSort {

    public static void main(String[] args) {
        int[] ary = {72,6,57,88,60,42,83,73,48,85};
        int i = 0;
        int j = ary.length - 1;
        fastSort(ary, i, j);
        System.out.println(Arrays.toString(ary));
    }
    
    public static void fastSort(int[] arrays, int fromIndex, int endIndex) {
        
        if(fromIndex < endIndex) {
            
            int i = fromIndex;
            int j = endIndex;
            int baseIndex = i;
            int base = arrays[baseIndex];
            
            
            while(i < j) {
                while(i<j && arrays[j]>=base) {
                    j--;
                }
                
                if(i<j) {
                    arrays[baseIndex] = arrays[j];
                    baseIndex = j;
                }
                
                while(i<j && arrays[i]<base) {
                    i++;
                }
                
                if(i < j) {
                    arrays[baseIndex] = arrays[i];
                    baseIndex = i;
                }
            }
            
            arrays[baseIndex] = base;
            fastSort(arrays, fromIndex, baseIndex-1);
            fastSort(arrays, baseIndex+1, endIndex);
        }
        
        
        
    }
}


