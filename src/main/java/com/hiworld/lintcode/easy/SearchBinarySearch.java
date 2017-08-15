package com.hiworld.lintcode.easy;

/**
 * 问题：给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1。
 * 
 * 样例：在数组 [1, 2, 3, 3, 4, 5, 10] 中二分查找3，返回2
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月13日 下午2:09:09
 */
public class SearchBinarySearch {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] nums = {4,5,9,9,12,13,14,15,15,18};
        SearchBinarySearch searchBinarySearch = new SearchBinarySearch();
        int index = searchBinarySearch.binarySearch(nums, 10);
        System.out.println("index : " + index);
    }
    
    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int index = doSearch(nums, target);
        if(-1!=index) { //这边seach到以后,还需要继续找第一个,判断是不是出现连续
            while(--index >= 0 && nums[index] == target) { //注意index必须大于0,不然会出现数组越界
            }
            
            return ++index;
        }
        
        return index;
    }
    
    /**
     * 查找target的index,不管是否是第一个
     * @param nums
     * @param target
     * @return
     */
    public int doSearch(int[] nums, int target) {
        //先检查一下数组
        if(null==nums || 0==nums.length) {
            return -1;
        }
        
        int startIndex = 0;
        int endIndex = nums.length - 1;
        
        //先检查一下是不是在范围以内
        if(target < nums[startIndex] || target > nums[endIndex] ) {
            return -1;
        }
        
        while(startIndex < endIndex && 1 != (endIndex-startIndex)) {
            int start = nums[startIndex];
            int end = nums[endIndex];
            if(start == target) {
                return startIndex;
            } else if(end == target) {
                return endIndex;
            } 
            
            int middleIndex = (startIndex + endIndex) / 2;
            if(target == nums[middleIndex]) {
                return middleIndex;
            } else if(target < nums[middleIndex]) {
                startIndex++;
                endIndex = middleIndex - 1;
            } else { //target > nums[middleIndex]
                endIndex--;
                startIndex = middleIndex + 1; 
            }
        }
        
        //加上这两个判断是为了while循环中的 1 != (endIndex-startIndex) 条件进行弥补
        //避免传入的数组就是长度为2,那么直接只使用如下的while循环,会出现漏判断
        //注意这边只能放到while下面,如果放到while上面,在while跳出来的时候,还要进行一次判断
        if(target == nums[startIndex]) { //开始结点是不是target
            return startIndex;
        }
        
        if(target == nums[endIndex]) { //结束结点是不是target
            return endIndex;
        }
        
        return -1;
    }
}
