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
        return byLikeFindListCycle(nums);
    }
    
    /**
     * http://blog.csdn.net/liyuefeilong/article/details/50719741
     * 
     * 通过类似于"带环链表 II"的解决思路
     * 使用快慢指针的方式实现该问题
     * 
     * 理论思想：
     * 如果一个数组没有重复,可以将数组的下标(index)和对于的元素值映射起来,如数组[4,1,3,2]
     * 则映射关系为
     * 0->4,1->1,2->3,3->2
     * 这个映射关系可以表示一个函数f(n),其中n为下标,f(n)是映射到的数,我们从下标为0出发
     * 根据函数f(n)计算出来一个值,以这个值为新的下表,再利用f(n)计算,以此类推,可以产生一个类似于链表的序列
     * 
     * 对于一个有重复元素的数组,如[2,1,3,1],其映射关系为0->2,{1,3}->1,2->3
     * 这个时候产生的序列出现了重复,为0->2->3->1->1->1->1
     * 
     * 那么这个题目就可以转换成"求环路的起点" 
     * 
     * @param nums
     * @return
     */
    public int byLikeFindListCycle(int[] nums) {
        int slowIndex = 0;
        int fastIndex = 0;
        
        while(true) {
            slowIndex = nums[slowIndex];
            fastIndex = nums[nums[fastIndex]];
            
            if(slowIndex == fastIndex) {
                break;
            }
        }
        
        int headIndex = 0;
        while(true) {
            headIndex = nums[headIndex];
            slowIndex = nums[slowIndex];
            if(headIndex == slowIndex) {
                break;
            }
        }
        
        return slowIndex;
    }
    
    /**
     * 二分法
     * 时间复杂度：O(nlogn)
     * 
     * 理论知识：
     * 抽屉原理：10个苹果放到9个抽屉里面,如果苹果全部放到抽屉,那么必定有一个抽屉有2个或以上的苹果
     * 
     * 对于N+1个数,每个数的取值范围为1-N,那么必定至少有一个重复数字
     * 这组数中的最小值low为1 ,最大值high为N
     * 取中间值(1+N) / 2,假设为m,统计数组中小于或等于m的数字的count
     * 如果
     * count <= m,那么重复数字肯定在(mid+1,N)中间
     * 如果
     * count > m,那么重复数字肯定在(1,mid)中间
     * 如此循环
     * 当出现low < high不成立的时候也就是相等时,那么就返回
     * 
     * @param nums
     * @return
     */
    public int binarySearch(int[] nums) {
        int length = nums.length;
        int low = 1;
        int high = length - 1;
        
        while(low < high) {
            int mid = (high + low) / 2;
            int lessMidCnt = 0;
            for(int i=0; i<length; ++i) {
                if(nums[i] <= mid) {
                    lessMidCnt++;
                }
            }
            
            if(lessMidCnt <= mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        return low; //return high;一个效果
    }
    
    /**
     * 这个做法不满足题目的条件,他的空间复杂度不是O(1),但是是能够被AC的
     * 
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
