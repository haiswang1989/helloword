package com.hiworld.lintcode;

/**
 * 问题：落单的数
 * 描述：给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
 * 样例：给出 [1,2,2,1,3,4,3]，返回 4
 * 
 * 挑战：一次遍历，常数级的额外空间复杂度
 * 
 * @author Administrator
 *
 */
public class ArraySingleNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] A = {1,2,2,1,3,4,3};
		ArraySingleNumber arraySingleNumber = new ArraySingleNumber();
		int num = arraySingleNumber.singleNumber(A);
		System.out.println("single num : " + num);
	}
	
	/**
     *@param A : an integer array
     *return : a integer 
     */
   public int singleNumber(int[] A) {
       // Write your code here
	   int xor = 0;
	   
	   for (int num : A) {
		   xor ^= num;
	   }
	   
	   return xor;
   }

}
