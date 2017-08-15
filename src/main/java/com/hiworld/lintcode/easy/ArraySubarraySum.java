package com.hiworld.lintcode.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * 问题：子数组之和
 * 描述：给定一个整数数组，找到和为零的子数组。你的代码应该返回满足要求的子数组的起始位置和结束位置
 * 样例：
 * 给出 [-3, 1, 2, -3, 4]，返回[0, 2] 或者 [1, 3].
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月10日 下午4:23:06
 */
public class ArraySubarraySum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        File file = new File("D:\\chrom_download\\16.in");
        
        int[] nums = new int[160005];
        
        String line = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int index = 0;
            while(null!=(line=br.readLine())) {
                nums[index++] = Integer.parseInt(line.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("read over...");
        
        //int[] nums = {-3, 1, 2, -3, 4};
        ArraySubarraySum arraySubarraySum = new ArraySubarraySum();
        ArrayList<Integer> ret = arraySubarraySum.subarraySum(nums);
        System.out.println(Arrays.toString(ret.toArray()));
    }
    
    /**
     * 
     * i < j;
     * sum[i] = a[0] + ...+ a[i]; 
     * sum[j] = a[0] + ...+ a[j];
     * 如果sum[i] == 0,则a[0]+a[1]+...+a[i] = 0;
     * 如果sum[i]和sum[j]相等，则a[i + 1] + ... + a[j] = 0;
     * 
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        int length = -1;
        if(null==nums || 0==(length=nums.length)) {
            return null;
        }
        
        ArrayList<Integer> ret = new ArrayList<>();
        
        HashMap<Integer, Integer> valIndex = new HashMap<>();
        int[] sum = new int[length];
        sum[0] = nums[0];
        valIndex.put(sum[0], 0); //注意这边的第一个元素也需要放入Map
        
        for(int i=1; i<length; ++i) {
            sum[i] = sum[i-1] + nums[i];
            if(sum[i] == 0) { //如果遇到sum为0的直接返回,表示已经找到了,不需要再找了
                ret.add(0);
                ret.add(i);
                return ret;
            } else {
                if(valIndex.containsKey(sum[i])) { //如果遇到的sum值在之前已经出现也就是sum[i] == sum[j] ,那么返回[i+1,j]
                    ret.add(valIndex.get(sum[i]) + 1);
                    ret.add(i);
                    return ret;
                } else { //否则把sum和index存储到map中
                    valIndex.put(sum[i], i);
                }
            }
        }
        
        if(sum[0] == 0) {
            ret.add(0);
            ret.add(0);
            return ret;
        }
        
        return null;
    }
}
