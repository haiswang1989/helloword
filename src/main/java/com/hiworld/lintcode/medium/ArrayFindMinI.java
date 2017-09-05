package com.hiworld.lintcode.medium;

/**
 * 问题：寻找旋转排序数组中的最小值
 * 描述：假设一个旋转排序的数组其起始位置是未知的（比如0 1 2 4 5 6 7 可能变成是4 5 6 7 0 1 2）。你需要找到其中最小的元素。
 * 注意：你可以假设数组中不存在重复的元素。
 * 
 * 样例：给出[4,5,6,7,0,1,2]  返回 0
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月5日 上午9:50:38
 */
public class ArrayFindMinI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] nums = {4,5,6,7,0,1,2};
        
        ArrayFindMinI arrayFindMinI = new ArrayFindMinI();
        int ret = arrayFindMinI.findMin(nums);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if(nums.length == 1) {
            return nums[0];
        }
        
//        return byLoop(nums);
        return binarySearch(nums, 0, nums.length-1);
    }
    
    /**
     * 二分查找
     * 对于这样的 完全排序的选择后的数组 也可以使用二分查找来 获取最小值
     * 
     * @param nums
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public int binarySearch(int[] nums, int beginIndex, int endIndex) {
        if(beginIndex + 1 != endIndex) {
            int middleIndex = (beginIndex + endIndex) / 2;
            
            int beginVal = nums[beginIndex];
            int endVal = nums[endIndex];
            int middleVal = nums[middleIndex];
            
            if(middleVal > beginVal &&  middleVal > endVal) { //4,5,6,7,0,1,2  对于middle为7时
                if(beginVal < endVal) { //谁小,那么最小值就在middle和它的之间
                    endIndex = middleIndex;
                } else {
                    beginIndex = middleIndex;
                }
            } else if(middleVal < beginVal && middleVal < endVal) { //7,0,1,2 对于middle为0时
                if(beginVal < endVal) { //谁大,那么最小值就在他和middle的之间
                    beginIndex = middleIndex;
                } else {
                    endIndex = middleIndex;
                }
            } else { //剩下的就是严格按照 begin < middle < end 或者 begin > middle > end的情况
                if(beginVal < endVal) {
                    endIndex = middleIndex;
                } else {
                    beginIndex = middleIndex;
                }
            }
            
            return binarySearch(nums, beginIndex, endIndex);
        } else {
            return nums[beginIndex] < nums[endIndex] ? nums[beginIndex] : nums[endIndex];
        }
    }
    
    /**
     * 循环一次,时间复杂度O(n)
     * @param nums
     * @return
     */
    public int byLoop(int[] nums) {
        
        int first = nums[0]; //第一个元素
        int end = nums[nums.length-1]; //最后一个元素
        
        int before = nums[0]; //前面一个元素
        int curr = nums[1]; //当前元素
        
        int length = nums.length;
        boolean isLarge = true; //前面一个元素比当前元素 true：大  false：小
        if(before < curr) {
            isLarge = true;
        } else {
            isLarge = false;
        }
        
        
        for(int i=2; i<length; ++i) {
            before = curr;
            curr = nums[i];
            if((before < curr) && isLarge) { 
                //DO NOTHING
            } else { //出现 A > B < C 或者 A< B > C的情况,表示就已经找到最小值了
                return isLarge ? curr : before; 
            }
        }
        
        return first < end ? first : end; //如果一直都是A < B < C 或者  A > B > C,那么就找第一个或者最后一个元素
    }
}
