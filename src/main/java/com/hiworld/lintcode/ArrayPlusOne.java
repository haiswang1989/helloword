package com.hiworld.lintcode;

import java.util.Arrays;

/**
 * 问题：加一
 * 描述：给定一个非负数，表示一个数字数组，在该数的基础上+1，返回一个新的数组。该数字按照大小进行排列，最大的数在列表的最前面。
 * 样例：
 * 给定 [1,2,3] 表示 123, 返回 [1,2,4].
 * 给定 [9,9,9] 表示 999, 返回 [1,0,0,0].
 * @author Administrator
 *
 */
public class ArrayPlusOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = {1,2,3};
		ArrayPlusOne arrayPlusOne = new ArrayPlusOne();
		int[] ret = arrayPlusOne.plusOne(input);
		System.out.println(Arrays.toString(ret));
	}
	
	
	/**
	 * 注意上面问题的一个描述："该数字按照大小进行排列，最大的数在列表的最前面"
	 * 
	 * 也就是说除了全是9,否则就不会多处一位,也就是从没有进位开始,就可以直接数组copy
	 * 
     * @param digits a number represented as an array of digits
     * @return the result
     */
    public int[] plusOne(int[] digits) {
        // Write your code here
    	int length = -1;
    	if(null==digits || 0==(length=digits.length)) {
    		return digits;
    	}
    	
    	int[] ret = new int[length+1];
    	boolean hasCarry = true;
    	
    	int copyCount = 0;
    	for(int i=length-1; i>=0; --i) {
    		int currIndexVal = digits[i];
    		if(hasCarry) { //有进位就需要加1
    			++currIndexVal;
    			if(currIndexVal >= 10) { //大于10就需要取余,然后进行进位
    				hasCarry = true;
        			currIndexVal %= 10;
        		} else {
        			hasCarry = false;
        		}
        		
        		ret[i+1] = currIndexVal;
        		copyCount++;
    		} else { //只要没有出现进位,那么前面的元素就直接复制好了,这边做个简单的优化
    			int[] newRet = new int[length];
    			System.arraycopy(ret, length+1-copyCount, newRet, length-copyCount, copyCount);
    			System.arraycopy(digits, 0, newRet, 0, length-copyCount);
    			return newRet;
    		}
    	}
    	
    	/********这下面的逻辑只有全部是9这样的数字才会走到********/
    	if(hasCarry) { //如果最后跳出来有进位,那么表示多出了一位
    		ret[0] = 1;
    		return ret;
    	} else { //如果没有进位,那么表示没有多出一位,那么需要重新new一个数组,然后进行copy
    		int[] newRet = new int[length];
    		System.arraycopy(ret, 1, newRet, 0, length);
    		return newRet;
    	}
    }
    
    /**
     * 这个方式比较简单,但是相对比较慢
     * @param digits
     * @return
     */
    public int[] simpleButSlow(int[] digits) {
        // Write your code here
    	int length = -1;
    	if(null==digits || 0==(length=digits.length)) {
    		return digits;
    	}
    	
    	int[] ret = new int[length+1];
    	
    	boolean hasCarray = true; //默认一开始有个进位,也就是第一个数需要进行加1
    	for(int i=length-1; i>=0; --i) {
    		int currIndexVal = digits[i];
    		if(hasCarray) { //有进位就需要加1
    			++currIndexVal;
    		}
    		
    		if(currIndexVal >= 10) { //大于10就需要取余,然后进行进位
    			hasCarray = true;
    			currIndexVal %= 10;
    		} else {
    			hasCarray = false;
    		}
    		
    		ret[i+1] = currIndexVal;
    	}
    	
    	if(hasCarray) { //如果最后跳出来有进位,那么表示多出了一位
    		ret[0] = 1;
    		return ret;
    	} else { //如果没有进位,那么表示没有多出一位,那么需要重新new一个数组,然后进行copy
    		int[] newRet = new int[length];
    		System.arraycopy(ret, 1, newRet, 0, length);
    		return newRet;
    	}
    }
}
