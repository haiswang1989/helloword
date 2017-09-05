package com.hiworld.lintcode.medium;

/**
 * 问题：查找重复的数字
 * 描述：给定一个数组长度为N+1,当中每个元素的值在1-n之间,假设当中有且仅有一个重复元素,但是重复的次数不定
 * 
 * 注意事项：
 * 1：你不能够修改数组(假设数组只读)
 * 2：O(1)的空间复杂度
 * 3：时间复杂度小于O(n^2)
 * 4: 有且仅有一个重复元素,但是重复的次数不定
 * 
 * 样例：
 * Given nums = [5,5,4,3,2,1] return 5
 * Given nums = [5,4,4,3,2,1] return 4
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月5日 下午6:04:43
 */
public class ArrayFindDuplicate {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] nums = {5,4,4,3,2,1};
        
        ArrayFindDuplicate arrayFindDuplicate = new ArrayFindDuplicate();
        int ret = arrayFindDuplicate.findDuplicate(nums);
        System.out.println("ret : " + ret);
    }
    /**
     * @param nums an array containing n + 1 integers which is between 1 and n
     * @return the duplicate one
     */
    public int findDuplicate(int[] nums) {
        // Write your code here
        return byArray(nums);
    }
    
    /**
     * 通过一个Array,来记录1-n出现的次数
     * 变遍历一遍,填充array
     * 在遍历一遍,获取 count > 1的index
     * 返回值就是index + 1
     * @param nums
     * @return
     */
    public int byArray(int[] nums) {
        int length = nums.length;
        
        int[] count = new int[length];
        for (int i : nums) {
            count[i-1]++;
        }
        
        for(int i=0; i<count.length; i++) {
            if(count[i] > 1) {
                return i+1;
            }
        }
        
        return Integer.MAX_VALUE;
    }
}
