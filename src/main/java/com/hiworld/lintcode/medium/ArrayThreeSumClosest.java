package com.hiworld.lintcode.medium;

/**
 * 问题：最接近的三数之和
 * 描述：给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。
 * 
 * 注意：
 * 只需要返回三元组之和，无需返回三元组本身
 * 
 * 样例：
 * 例如 S = [-1, 2, 1, -4] and target = 1. 和最接近 1 的三元组是 -1 + 2 + 1 = 2.
 * 
 * 挑战：
 * O(n^2) 时间, O(1) 额外空间。
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月15日 下午4:27:56
 */
public class ArrayThreeSumClosest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] numbers = {2,7,11,15};
        int target = 3;
        
        ArrayThreeSumClosest arrayThreeSumClosest = new ArrayThreeSumClosest();
        int closest = arrayThreeSumClosest.threeSumClosest(numbers, target);
        System.out.println("closest : " + closest);
    }
    
    /**
     * 
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        int length = numbers.length;
        //先进行排序
        sort(numbers, 0, length-1);
        
        int diff = Integer.MAX_VALUE; //相差最小的情况
        int retSum = Integer.MAX_VALUE; //相差最小时,sum的大小
        
        for(int i=0; i<length; i++) {
            int currNumber = numbers[i];
            int beginIndex = i+1;
            int endIndex = length-1;
            
            while(beginIndex < endIndex) {
                int beginVal = numbers[beginIndex];
                int endVal = numbers[endIndex];
                int sum = beginVal + endVal + currNumber;
                if(Math.abs(target - sum) < diff) { //最小的diff的时候,更新返回值
                    diff = Math.abs(target - sum);
                    retSum = sum;
                }
                
                if(sum == target) { //相等是最接近的
                    return sum;
                } else if(sum > target) {
                    endIndex--;
                } else {
                    beginIndex++;
                }
            }
        }
        
        return retSum;
    }
    
    /**
     * 先进行快排
     * @param numbers
     */
    public void sort(int[] numbers, int fromIndex, int endIndex) {
        if(fromIndex < endIndex) {
            int i = fromIndex;
            int j = endIndex;
            int base = numbers[i]; //基准数
            int fillIndex = i; //需要填充的index
            while(i < j) {
                while(j > i && numbers[j] >= base) { //先从后往前,注意这边或者下面总归要有一个地方处理等于的情况,不然会出现死循环
                    j--;
                }
                
                if(j > i) {
                    numbers[fillIndex] = numbers[j];
                    fillIndex = j;
                }
                
                while(i < j && numbers[i] < base) { //再从前往后
                    i++;
                }
                
                if(i < j) {
                    numbers[fillIndex] = numbers[i];
                    fillIndex = i;
                }
            }
            
            numbers[fillIndex] = base;
            sort(numbers, fromIndex, fillIndex-1);
            sort(numbers, fillIndex+1, endIndex);
        }
    }

}
