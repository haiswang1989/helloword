package com.hiworld.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 问题：两个数组的交
 * 
 * 样例：
 * nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2].
 * 
 * 挑战：
 * 1：如果数组已经排序了，算法如何设计
 * 2：如果nums1的长度比nums2的长度短，那个算法比较优
 * 3：如果nums2已经排序好了,被序列化到在磁盘,并且无法将无法将数组直接一次性全部加载到内存,如何设计算法
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月21日 下午12:18:18
 */
public class ArrayIntersectionII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums1 = {4,7,9,7,6,7};
        int[] nums2 = {5,0,0,6,1,6,2,2,4};
        
        ArrayIntersectionII arrayIntersection = new ArrayIntersectionII();
        int[] ret = arrayIntersection.intersectionHash(nums1, nums2);
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
     * 使用了先排序,在查找的方式
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionSort(int[] nums1, int[] nums2) {
        if((null==nums1 || 0==nums1.length) || (null==nums2 || 0==nums2.length)) {
            return new int[0];
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        return intersection_1(nums1, nums2);
    }
    
    /**
     * Hash方式
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionHash(int[] nums1, int[] nums2) {
        //元素添加一个次数
        HashMap<Integer, Integer> elementCount = new HashMap<>();
        for (int ele1 : nums1) {
            if(elementCount.containsKey(ele1)) {
                elementCount.put(ele1, elementCount.get(ele1) + 1);
            } else {
                elementCount.put(ele1, 1);
            }
        }
        
        List<Integer> retList = new ArrayList<>();
        for (int ele2 : nums2) {
            if(elementCount.containsKey(ele2)) { //如果包含该元素
                retList.add(ele2);
                int count = elementCount.get(ele2);
                if(count > 1) { //如果count > 1
                    elementCount.put(ele2, --count); //那么次数更新成count-1
                } else { //count == 1
                    elementCount.remove(ele2); //remove该key
                }
            }
        }
        
        return convertArray(retList);
    }
    
    /**
     * num1和num2已经排序的情况下
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection_1(int[] nums1, int[] nums2) {
        
        int nums1Length = nums1.length;
        int nums1Index = 0;
        
        List<Integer> retList = new ArrayList<>();
        
        for (int ele2 : nums2) {
            for(; nums1Index < nums1Length; ++nums1Index) { //如果元素是已经排序好的,那么不需要再嵌套遍历中,每次都从0开,上次遍历到哪边,就从哪边开始
                int ele1 = nums1[nums1Index];
                if(ele1 == ele2) { //如果元素已经找到,那么直接break出去
                    retList.add(ele1);
                    nums1Index++;
                    break;
                } else if(ele1 > ele2) { //如果已经大于要找的值,说明没有找到,那么也break出去
                    break;
                } else {
                    //ele1 < ele2
                    //继续往后遍历
                }
            }
            
            if(nums1Index == nums1Length) { //如果到了nums1数组的结尾,那么nums2也不需要遍历
                break;
            }
        }
        
        return convertArray(retList);
    }
    
    /**
     * 如果nums1的长度比nums2的长度短,如何优化算法
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection_2(int[] nums1, int[] nums2) {
        //TODO
        return null;
    }
    
    /**
     * 如果nums2已经排序好了,被序列化到在磁盘,并且无法将无法将数组直接一次性全部加载到内存,如何设计算法
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection_3(int[] nums1, int[] nums2) {
        //TODO
        return null;
    }
    
    public int[] convertArray(List<Integer> list) {
        int[] retArray = new int[list.size()];
        int index = 0;
        for (Integer i : list) {
            retArray[index++] = i;
        }
        
        return retArray;
    }
}
