package com.hiworld.lintcode.easy;

/**
 * 二分查找
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月4日 下午4:01:10
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 4, 5, 5};
        
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.findPosition(nums, 5));
        
    }
    
    public int findPosition(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        int targetIndex = -1;
        
        if(null==nums || 0==nums.length) { //对于空数组的特殊处理,如果是空数组直接返回-1
            return -1;
        }
        
        if(nums[startIndex] == target) { //测试第一个元素
            return startIndex;
        }
        
        if(nums[endIndex] == target) { //测试最后一个元素
            return endIndex;
        }
        
        int middleIndex = (startIndex + endIndex) / 2 ; //中间元素索引
        
        while(startIndex != middleIndex && endIndex != middleIndex) { //注意这边的跳出循环判断,如果计算出来的中间元素索引和开始索引,结束索引中的任意一个相等,那么久跳出循环
            if(nums[middleIndex] == target) {
                targetIndex = middleIndex;
                break;
            } else if(nums[middleIndex] < target) {
                startIndex = middleIndex;
            } else {
                endIndex = middleIndex;
            }
            
            middleIndex = (startIndex + endIndex) / 2 ;
        }
        
        return targetIndex;
    }

}
