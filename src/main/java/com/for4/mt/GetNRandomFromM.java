package com.for4.mt;

import java.util.Arrays;
import java.util.Random;

/**
 * 从M个数中随机取N个数,确保N个数不重复
 * 
 * N <= M
 * 将设M为10,N为5
 * 
 * 第一步：选10以内的随机数假设是R,将Ary[R] 和 Ary[10]进行交换,Ary[10]就是第一个随机数
 * 第二步: 选9以内的随机数,假设是r,将Ary[r] 和 Ary[9]进行交换,Ary[9]就是第二个随机数
 * 以此类推,直到选到全部需要的随机数
 * 
 * 将选出的随机数放到后面,然后随机数的最大值逐步减小,确保不会生成已经选出的随机数所存放的位置
 * 然后随机数所在的索引位置,肯定都是没有被选到的数据
 * 
 * 
 * https://blog.csdn.net/shi0090/article/details/41672785
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午10:27:53
 */
public class GetNRandomFromM {

    public static void main(String[] args) {
        
        int[] arr = {0,11,2,33,14,56,26,97,81,90};
        int m = arr.length;
        int n= 10;
        GetNFromM getNFromM = new GetNFromM(arr, m, n);
        int[] retArr = getNFromM.get();
        System.out.println(Arrays.toString(retArr));
    }
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月11日 上午10:31:03
 */
class GetNFromM {
    
    private Random random = new Random();
    
    private int[] arr;
    private int m;
    private int n;
    
    public GetNFromM(int[] arrArgs, int mArgs, int nArgs) {
        this.arr = arrArgs;
        this.m = mArgs;
        this.n = nArgs;
    }
    
    public int[] get() {
        int[] ret = new int[n];
        for(int i=0; i<n; i++) {
            //依次生成m,m-1,m-2,m-3...m-n以内的随机数
            int randomVal = random.nextInt(m-i);
            //将第m,m-1,m-2,m-3...m-n个元素与随机数对应的位置进行交互
            //将选中的数放到最后
            swap(randomVal, m-i-1);
            //被选中的数据就用于构造返回值
            ret[i] = arr[m-i-1];
        }
        
        return ret;
    }
    
    public void swap(int swapIndex1, int swapIndex2) {
        int tmp = arr[swapIndex1];
        arr[swapIndex1] = arr[swapIndex2];
        arr[swapIndex2] = tmp;
    }
}


