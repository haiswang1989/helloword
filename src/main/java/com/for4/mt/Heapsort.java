package com.for4.mt;

import java.util.Arrays;

/**
 * 堆排序
 * 
 * 大顶堆:
 * 每个结点的值都大于或等于其左右孩子结点的值
 * arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * 
 * 
 * 小顶堆:
 * 每个结点的值都小于或等于其做鱼孩子结点的值
 * arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2] 
 * 
 * 堆排序的基本思想：
 * 将待排序的序列构造成"大顶堆",此时整个堆的最大值就是堆顶的根结点
 * 将其与末尾元素进行交换,此时的最大值就在末尾,然后将剩下的n-1个
 * 元素重新构造成一个堆,这样就会得到剩下的n-1一个元素中的最大值,如此
 * 反复执行便能得到一个有序序列了
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月8日 上午10:26:36
 */
public class Heapsort {

    public static void main(String[] args) {
        int[] arr = {11,7,18,3,5,4,10,9};
        
        Heapsort heapsort = new Heapsort();
        heapsort.heapsort(arr);
        
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * 堆排序
     * @param arr
     */
    public void heapsort(int[] arr) {
        
        //获取最后一个需要调整的非叶子节点
        int adjustIndex = arr.length / 2 - 1;
        
        //从最后一个非叶子节点,从下往上,从右往左进行调整
        for(int i=adjustIndex; i>=0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        
        //将堆顶的元素与最后一个元素进行交换,然后继续调整
        for(int j=arr.length-1; j>0; j--) {
            swap(arr, 0, j);
            adjustHeap(arr, 0, j);
        }
    }
    
    /**
     * 
     * @param arr
     * @param adjustIndex
     * @param length
     */
    public void adjustHeap(int[] arr, int adjustIndex, int length) {
        
        //调整的非叶子节点
        int tmp = arr[adjustIndex];
        
        //调整结点的左孩子
        int leftChildIndex = adjustIndex * 2 + 1;
        
        //
        for(int k=leftChildIndex; k<length; k=k*2+1) {
            
            //调整结点的右孩子结点
            int rightChildIndex = leftChildIndex + 1;
            
            //右孩子结点存在且左结点小于又结点那么直接K指向右结点
            if(rightChildIndex < length && arr[leftChildIndex] < arr[rightChildIndex]) {
                k = rightChildIndex;
            }
            
            //如果子结点大于父节点,那么将子结点赋值给父结点(无需进行交换)
            if(arr[k] > tmp) {
                arr[adjustIndex] = arr[k];
                adjustIndex = k;
            } else {
                break;
            }
            
            leftChildIndex = adjustIndex * 2 + 1;
        }
        
        arr[adjustIndex] = tmp;
    }
    
    /**
     * 
     * @param arr
     * @param index1
     * @param index2
     */
    public void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
