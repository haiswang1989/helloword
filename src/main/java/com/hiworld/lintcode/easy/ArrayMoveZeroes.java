package com.hiworld.lintcode.easy;

import java.util.Arrays;

/**
 * 问题：移动零
 * 描述：给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序
 * 注意：1.必须在原数组上操作 2.最小化操作数
 * 样例：给出 nums = [0, 1, 0, 3, 12], 调用函数之后, nums = [1, 3, 12, 0, 0].
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月2日 下午3:10:45
 */
public class ArrayMoveZeroes {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int nums[] = {0, 0, 0, 3, 12};
        
        ArrayMoveZeroes arrayMoveZeroes = new ArrayMoveZeroes();
        arrayMoveZeroes.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        // Write your code here
        int length = 0;
        if(null==nums || 0== (length=nums.length)) {
            return;
        }
        
        withSwapElement(nums, length);
    }
    
    /**
     * 通过数组的copy实现
     * @param nums
     * @param length
     */
    public void withArrayCopy(int[] nums, int length) {
        int moveCount = 0; //记录移动次数
        for(int i=0; i<(length-moveCount);) { //这边要时刻调整处理的最后一个元素,因为后面再不停的被填充0
            if(0==nums[i] && i!=length-1) { 
                System.arraycopy(nums, i+1, nums, i, length-i-1); //遇到0,则从这边进行copy,把0之后的所有元素,copy到当前位置
                nums[length-1] = 0; //最后一个元素补0
                ++moveCount;  //移动次数+1
            } else {
                ++i; //注意,如果发生数值移动,这个i是不能进行++,避免出现连续两个0无法移动的情况
            }
        }
    }
    
    /**
     * 通过交换元素实现
     * @param nums
     * @param length
     */
    public void withSwapElement(int[] nums, int length) {
        boolean findNotZero = false; //是不是找非0元素,如果已经找到了0,那么就需要找非0元素进行交换
        int zeroIndex = -1; //0元素的索引位置
        for(int i=0; i<length; ++i) {
            if(nums[i] == 0) { 
                if(!findNotZero) {
                    zeroIndex = i;
                    findNotZero = true;
                }
            } else {
                if(findNotZero) {
                    swapEle(nums, zeroIndex, i); //交换元素
                    i = zeroIndex; //这边重新设置数值遍历的开始位置
                    findNotZero = false; //注意这边状态为复位
                } 
            }
        }
    }
    
    /**
     * 交换两个元素
     * @param nums
     * @param index1
     * @param index2
     */
    public void swapEle(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
