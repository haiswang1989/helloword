package com.hiworld.algorithm.sort;

import java.util.Arrays;

public class FastSort20180207 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int ary[] = {-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-1000};
        
        FastSort20180207 fastSort20180207 = new FastSort20180207();
        fastSort20180207.quickSort(ary, 0, ary.length-1);
        System.out.println(Arrays.toString(ary));
        
    }
    
    public void quickSort(int ary[], int fromIndex, int endIndex) {
        
        if(fromIndex < endIndex) {
            
            int i = fromIndex;
            int j = endIndex;
            
            int baseIndex = i;
            int base = ary[baseIndex];
            
            while(i < j) {
                while(i < j && ary[j] >= base) {
                    j--;
                }
                
                if(i < j) {
                    ary[baseIndex] = ary[j];
                    baseIndex = j;
                }
                
                while(i < j && ary[i] < base) {
                    i++;
                }
                
                if(i < j) {
                    ary[baseIndex] = ary[i];
                    baseIndex = i;
                }
            }
            
            ary[baseIndex] = base;
            quickSort(ary, fromIndex, baseIndex-1);
            quickSort(ary, baseIndex+1, endIndex);
        }
    }
}
