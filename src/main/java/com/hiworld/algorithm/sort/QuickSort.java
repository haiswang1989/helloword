package com.hiworld.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 核心思想：
 * 1：先从数列中取出一个数作为基数
 * 2：将比这个数大的全部放到他的右边，小于或等于它的数全部放到左边
 * 3：再对左右区间重复第二步，直到各个区间只有一个数
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月3日 上午11:46:54
 */
public class QuickSort {

    public static void main(String[] args) {
        int ary[] = {-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-1000};
        
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(ary, 0, ary.length-1);
        System.out.println(Arrays.toString(ary));
    }
    
    /**
     * 
     * @param ary
     * @param beginIndex
     * @param endIndex
     */
    public void quickSort(int ary[], int beginIndex, int endIndex) {
        
        int i = beginIndex; //开始位置
        int j = endIndex; //结束位置
        
        if(i < j) { //如果i == j,那么就退出递归了
            
            int standardIndex = beginIndex;
            int standard = ary[beginIndex]; //区间的第一个数作为基数
            
            while(i < j) {
                while(j > i && ary[j] >= standard) { //注意这边等于情况
                    j--;
                }
                
                if(j > i) {
                    ary[standardIndex] = ary[j];
                    standardIndex = j;
                }
                
                
                while(i < j && ary[i] < standard) {
                    i++;
                }
                
                if(i < j) {
                    ary[standardIndex] = ary[i];
                    standardIndex = i;
                }
            }
            
            ary[standardIndex] = standard;
            quickSort(ary, beginIndex, standardIndex-1); //递归调用
            quickSort(ary, standardIndex+1, endIndex);
        }
    }
    
    /**
     * 
     * @param ary
     * @param index1
     * @param index2
     */
    public void swap(int ary[], int index1, int index2) {
        int temp = ary[index1];
        ary[index1] = ary[index2];
        ary[index2] = temp;
    }
}
