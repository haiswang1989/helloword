package com.hiworld.lintcode.medium;

/**
 * 问题：寻找旋转排序数组中的最小值 II 
 * 描述：假设一个旋转排序的数组其起始位置是未知的（比如0 1 2 4 5 6 7 可能变成是4 5 6 7 0 1 2）。你需要找到其中最小的元素。
 * 注意：数组中可能存在重复的元素
 * 
 * 样例：给出[4,4,5,6,7,0,1,2]  返回 0
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月5日 上午11:57:12
 */
public class ArrayFindMinII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] num = {1,1,-1,1};
        ArrayFindMinII arrayFindMinII = new ArrayFindMinII();
        int ret = arrayFindMinII.findMin(num);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 和ArrayFindMinI的区别就是元素可能存在重复
     * 
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if(num.length == 1) {
            return num[0];
        }
        
        return binarySearch(num, 0, num.length-1);
    }
    
    /**
     * 
     * 4,4,5,6,7,0,1,2
     * 当middle(7)大于最右边的值(2)时,表示最小值在middle~right之间
     * 
     * 7,0,1,2
     * 当middle(0)小于最左边的值(7)时,表示最小值在left~middle之间
     * 
     * 其他的情况就直接最endIndex--
     * @param nums
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public int binarySearch(int[] nums, int beginIndex, int endIndex) {
        while(beginIndex + 1 != endIndex) { //相对于beginIndex < endIndex,beginIndex + 1 != endIndex更不容易出现死循环
            int middleIndex = (beginIndex + endIndex) / 2;
            int beginVal = nums[beginIndex];
            int endVal = nums[endIndex];
            int middleVal = nums[middleIndex];
            
            if(middleVal > endVal) {
                //对于降序就是一个二分
                //这个情况包含降序
                beginIndex = middleIndex;
            } else if(middleVal < beginVal) {
                endIndex = middleIndex;
            } else { 
                //无法确定在哪个区间的时候就让endIndex--,因为endIndex是一定大于等于最小值
                //对于完全升序的数组,那么这边就是一个O(n)的遍历
                endIndex--;
            }
        }
        
        return nums[beginIndex] < nums[endIndex] ? nums[beginIndex] : nums[endIndex];
    }
}
