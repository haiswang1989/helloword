package com.hiworld.lintcode.easy;

public class IntegerCheckPowerOfTwo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        IntegerCheckPowerOfTwo integerCheckPowerOfTwo = new IntegerCheckPowerOfTwo();
        System.out.println("6 : " +  integerCheckPowerOfTwo.checkPowerOf2(6));
    }
    
    /*
     * @param n: An integer
     * @return: True or false
     */
    public boolean checkPowerOf2(int n) {
        // write your code here
        if(n <= 0) { //负数直接返回false
            return false;
        }
        
        int oneCount = 0;
        
        while(0 != n) {
            int newN = n>>1;
            if(newN * 2 != n) {
                oneCount++;
            }
            
            if(oneCount > 1) {
                return false;
            }
            
            n = newN;
        }
        
        return true;
    }

}
