package com.hiworld.lintcode.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 问题：两个数组的交
 * 
 * 样例：
 * nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2].
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月21日 下午12:18:18
 */
public class ArrayIntersection {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2, 2};
        
        ArrayIntersection arrayIntersection = new ArrayIntersection();
        int[] ret = arrayIntersection.intersection_1(nums1, nums2);
        System.out.println(Arrays.toString(ret));
    }
    
    /**
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if((null==nums1 || 0==nums1.length) || (null==nums2 || 0==nums2.length)) {
            return new int[0];
        }
        
        return intersection_1(nums1, nums2);
    }
    
    /**
     * 方法一
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection_1(int[] nums1, int[] nums2) {
        
        HashSet<Integer> elements = new HashSet<>();
        for (Integer ele : nums1) {
            elements.add(ele);
        }
        
        HashSet<Integer> retSet = new HashSet<>();
        for (Integer ele : nums2) {
            if(elements.contains(ele)) {
                retSet.add(ele);
            }
        }
        
        int[] retArray = new int[retSet.size()];
        
        int index = 0;
        for (Integer i : retSet) {
            retArray[index++] = i;
        }
        
        return retArray;
    }
    
    public int[] intersection_2() {
        //TODO
       //其他解决方案
        return null;
    }
    
    public int[] intersection_3() {
        //TODO
        //其他解决方案
        return null;
    }
}
