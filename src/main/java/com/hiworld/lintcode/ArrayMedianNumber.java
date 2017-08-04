package com.hiworld.lintcode;

/**
 * 问题：中位数
 * 描述：给定一个未排序的整数数组，找到其中位数。中位数是排序后数组的中间值，如果数组的个数是偶数个，则返回排序后数组的第N/2个数。
 * 
 * 样例：
 * 给出数组[4, 5, 1, 2, 3]， 返回 3
 * 给出数组[7, 9, 4, 5]，返回 5
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月31日 上午10:31:08
 */
public class ArrayMedianNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int ary[] = {-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-4,5,-1000};
        ArrayMedianNumber arrayMedianNumber = new ArrayMedianNumber();
        int ret = arrayMedianNumber.median(ary);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        // write your code here
        int length = -1;
        if(null==nums || 0==(length=nums.length)) {
            return 0;
        }
        
        int medianIndex = (length + 1) / 2 - 1; //中位数在数组中的位置
        return medianWithRecusion(nums, 0, nums.length-1, medianIndex);
    }
    
    /**
     * 快速排序的思想
     * @param nums
     * @param startIndex
     * @param endIndex
     * @param medianIndex
     * @return
     */
    public int medianWithRecusion(int[] nums, int startIndex, int endIndex, int medianIndex) {
        int ret = 0;
        if(startIndex == endIndex) { //如果数组只有一个元素了,那么中位数就是这个唯一的元素
            ret = nums[startIndex];
        }
        
        if(startIndex < endIndex) {
            int i = startIndex;
            int j = endIndex;
            int standard = nums[i];
            int standardIndex = i;
            
            while(i < j) {
                while(j > i && nums[j] >= standard) {
                    j--;
                }
                
                if(j > i) {
                    nums[standardIndex] = nums[j];
                    standardIndex = j;
                }
                
                while(i < j && nums[i] < standard) {
                    i++;
                }
                
                if(i < j) {
                    nums[standardIndex] = nums[i];
                    standardIndex = i;
                }
            }
            
            nums[standardIndex] = standard;
            if(standardIndex == medianIndex) { //如果基数就在中位数的位置,那么直接返回基数
                ret = standard;
            } else if(standardIndex < medianIndex) { //在基数的左边
                ret = medianWithRecusion(nums, standardIndex+1, endIndex, medianIndex);
            } else { //在基数的右边
                ret = medianWithRecusion(nums, startIndex, standardIndex-1, medianIndex);
            }
        }
        
        return ret;
    }
}
