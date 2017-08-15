package com.hiworld.lintcode.easy;

import java.util.ArrayList;

/**
 * 问题：主元素 
 * 描述：给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的二分之一。
 * 
 * 样例：
 * 给出数组[1,1,1,1,2,2,2]，返回 1
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月27日 上午10:21:15
 */
public class ArrayMajorityNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(1);
//        nums.add(1);
//        nums.add(1);
        nums.add(2);
        nums.add(2);
        nums.add(2);
        
        ArrayMajorityNumber arrayMajorityNumber = new ArrayMajorityNumber();
        System.out.println(" : " + arrayMajorityNumber.majorityNumber(nums));
    }
    
    /**
     * 理论基础：删除两个不一样的元素，那么剩下来的List的主元素和整个List的主元素是一样的(https://www.douban.com/note/505717075/)
     * 
     * 该种解法是有个前提的，就是主元素肯定存在，如果主元素不一定存在，需要在该方法的返回结果，再次遍历判断是不是主元素
     * 
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int size = nums.size();
        
        if(size == 1) {
            return nums.get(0);
        }
        
        int majorNumber = nums.get(0); //假设主元素就是第一个元素
        int count = 1; //默认的count值为1
        int countBegin = 0; //计算的开始位置
        for(int i=1; i<size; ++i) {
            int compareNumber = nums.get(i);
            if(compareNumber == majorNumber) {
                ++count; //如果相连两个元素相等,则count+1
            } else { 
                --count; //不相等则-1
            }
            
            if(-1 == count) { //当count值为-1时,那么从当前的位置的前一个元素开始,后面的主元素,和整个LIst的主元素相等
                majorNumber = compareNumber; //重置主元素
                count = 1; //重置count
                countBegin = i; //重置重新计算的元素开始位置
            }
            
            if(count > ((size-countBegin) / 2)) { //如果count值,大于元素的一半,那么主元素已经找到了()
                break;
            }
        }
        
        return majorNumber;
    }
}
