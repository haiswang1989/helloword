package com.hiworld.lintcode.medium;

/**
 * 问题：寻找峰值
 * 描述：
 * 你给出一个整数数组(size为n)，其具有以下特点：
 * 1：相邻位置的数字是不同的
 * 2：A[0] < A[1] 并且 A[n - 2] > A[n - 1]
 * 
 * 假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
 * 
 * 注意：数组可能包含多个峰值，只需找到其中的任何一个即可
 * 
 * 样例：
 * 给出数组[1, 2, 1, 3, 4, 5, 7, 6]返回1, 即数值 2 所在位置, 或者6, 即数值 7 所在位置.
 * 
 * 挑战：
 * 时间复杂度 O(logN)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月5日 下午2:52:26
 */
public class ArrayFindPeak {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A = {1, 2, 1, 3, 4, 5, 7, 6};
        ArrayFindPeak arrayFindPeak = new ArrayFindPeak();
        int ret = arrayFindPeak.findPeak(A);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        if(null==A || A.length < 3) {
            return 0;
        }
        
//        return withLoop(A);
        
        return binarySearch(A);
    }
    
    /**
     * 二分查找
     * 获取mid结点
     * 如果 A[mid-1] > A[mid],那么A[start~mid]之间肯定存在峰值
     *  因为题目中已经表明A[0] < A[1] 再加上 A[mid-1] > A[mid]条件就可以判定A[start~mid]间存在峰值
     * 如果A[mid] < A[mid+1],那么A[mid~end]之间肯定存在峰值
     *  因为题目中已经表明A[n-2] > A[n-1],再加上A[mid] < A[mid+1]条件就可以判定A[mid~end]之间存在峰值
     * else A[mid] > A[mid-1] && A[mid] > A[mid+1]
     *  这个情况表示,峰值已经找到,就是mid,直接返回就可以了
     * 
     * @param A
     * @return
     */
    public int binarySearch(int[] A) {
        int startIndex = 1;
        int endIndex = A.length - 2;
        
        while(startIndex + 1 != endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            int middleVal = A[middleIndex];
            
            if(A[middleIndex-1] > middleVal) {
                endIndex = middleIndex - 1; 
            } else if(A[middleIndex+1] > middleVal) {
                startIndex = middleIndex + 1;
            } else {
                return middleIndex;
            }
        }
        
        if(A[startIndex] < A[endIndex]) { //右边出现峰值
            return endIndex;
        } else { //左边出现峰值
            return startIndex;
        }
    }
    
    /**
     * 思路：遍历整个数组
     * 取窗口长度为3, 窗口一个一个往下移动
     * 如果出现中间的元素大于两边的元素,那么就找到了峰顶了
     * 
     * 时间复杂度：O(n)
     * @param A
     */
    public int withLoop(int[] A) {
        int firstIndex = 0;
        int secondIndex = 1;
        int thirdIndex = 2;
        int length = A.length;
        while(thirdIndex < length) {
            if(A[firstIndex] < A[secondIndex]  && A[secondIndex] > A[thirdIndex]) {
                return secondIndex;
            } else {
                firstIndex = secondIndex;
                secondIndex = thirdIndex;
                thirdIndex++;
            }
        }
        
        return 0;
    }
}
