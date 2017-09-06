package com.hiworld.lintcode.medium;

/**
 * 问题： 寻找缺失的数
 * 描述：给出一个包含 0 .. N 中 N 个数的序列，找出0 .. N 中没有出现在序列中的那个数。
 * 样例：
 * N = 4 且序列为 [0, 1, 3] 时，缺失的数为2。
 * 
 * 挑战：在数组上原地完成，使用O(1)的额外空间和O(N)的时间。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月6日 下午3:36:54
 */
public class ArrayFindMissingI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] nums = {0, 1, 3};
        ArrayFindMissingI arrayFindMissing = new ArrayFindMissingI();
        int ret = arrayFindMissing.findMissing(nums);
        System.out.println("ret : " + ret);
    }
    
    
    public int findMissing(int[] nums) {
        // write your code here
        int ret = bySum(nums);
        return ret == 0 ? nums.length : ret;
    }
    
    /**
     * 
     * 这个解法很巧妙,但是必须确保没有重复的值
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 
     * 思路：
     * 通过求和公式,先计算出如果不缺少数据的情况下,sum的值应该是什么
     * 然后再遍历nums,一个一个的减,最后的结果就是缺少的值
     * 
     * @param nums
     * @return
     */
    public int bySum(int[] nums) {
        int length = nums.length; //这个包含0
        int actualLength = length - 1; //去除0的实际长度
        
        int sum = (actualLength+1)*(actualLength+2) / 2;
        
        for (int num : nums) {
            sum -= num;
        }
        
        return sum;
    }
    
    /**  
     * 思路:
     * 将元素放到该放的位置上,如元素的值为0,那么讲元素放到index=0的地方
     * 设置完成以后,再次遍历一下数组,看index!=nums[index],那么index就是要找的值
     * 如果index都等于nums[index],那么返回nums.length
     *   
     * @param nums: an array of integers
     * @return: an integer
     */
    public int solution_1(int[] nums) {
        int length = nums.length;
        for(int i=0; i<length; ++i) {
            
            int currIndex = i;
            int value = nums[i];
            int toIndex = value;
            
            while(currIndex != toIndex && toIndex>=0 && toIndex<length) {
                value = set(nums, toIndex, value);
                currIndex = toIndex;
                toIndex = value;
            }
        }
        
        for(int i=0; i<length; ++i) {
            if(nums[i] != i) {
                return i;
            }
        }
        
        return length;
    }
    
    /**
     * 设置元素的值
     * @param nums
     * @param index
     * @param value
     * @return
     */
    public int set(int[] nums, int index, int value) {
        int tmp = nums[index];
        nums[index] = value;
        return tmp;
    }

}
