package com.hiworld.lintcode.easy;

/**
 * 问题：删除排序数组中的重复数字
 * 描述：给定一个排序数组，在原数组中删除重复出现的数字，使得每个元素只出现一次，并且返回新的数组的长度。
 * 
 * 挑战：不要使用额外的数组空间，必须在原地没有额外空间的条件下完成。
 * 
 * 样例：给出数组A =[1,1,2]，你的函数应该返回长度2，此时A=[1,2]。
 * 
 * 
 * 挑战：如果可以允许出现两次重复将如何处理？
 * 
 * @author Administrator
 *
 */
public class ArrayRemoveDuplicatesII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,1,2,2,2,3,3};
		
		ArrayRemoveDuplicatesII arrayRemoveDuplicates = new ArrayRemoveDuplicatesII();
		int length = arrayRemoveDuplicates.removeDuplicates(nums);
		System.out.println("length : " + length);
	}
	
	/**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
    	int length = -1;
    	if(null==nums || 0==(length=nums.length)) {
    		return 0;
    	}
    	
    	int beforeNum = nums[0];
    	
    	int sameIndexFrom = 0; //重复元素索引的开始
    	int sameIndexEnd = 0; //重复元素索引的结束
    	
    	int delNumCnt = 0; //删除的元素的个数
    	
    	for(int i=1; i<(length - delNumCnt); ++i) {
    		int currNum = nums[i];
    		if(beforeNum == currNum) {
    			sameIndexEnd = i;
    		} else {
				if((sameIndexEnd-sameIndexFrom) >= 2) {
					delNumCnt += copyArray(nums, sameIndexFrom, sameIndexEnd);
    				//这边重新设置遍历的开始位置位重复元素的开始位置,经过++i也就从下一个位置开始执行
    				//beforeNum还是之前重复的元素的值,所以下一次遍历肯定不一样,这样使用正常遍历的方式,来复位sameIndexFrom和beforeNum
    				i = sameIndexFrom + 1; //多保留了一个重复元素,那么就以重复元素的结尾来开始遍历
    			} else {
    				//如果是连续不一样,这边需要重新设置beforeNum和sameIndexFrom
    				sameIndexFrom = i;
    				beforeNum = currNum;
    			}
    			
    			sameIndexEnd = 0; //这边需要把重复元素索引的结束位置复位
    		}
    	}
    	
    	if((sameIndexEnd-sameIndexFrom) >= 2) { //处理末尾连续相等
    		delNumCnt += copyArray(nums, sameIndexFrom, sameIndexEnd);
    	}
    	
    	return nums.length - delNumCnt;
    }
    
    /**
     * 覆盖重复元素
     * @param nums
     * @param fromIndex
     * @param endIndex
     * @return 删除元素的个数
     */
    public int copyArray(int[] nums, int fromIndex, int endIndex) {
    	System.arraycopy(nums, endIndex, nums, fromIndex + 1, nums.length - endIndex); //这边在copy的时候要保留一个重复
    	return endIndex - fromIndex - 1; //同理覆盖元素就会少一个
    }

}
