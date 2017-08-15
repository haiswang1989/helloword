package com.hiworld.lintcode.easy;

public class DigitsAdd {

    public static void main(String[] args) {
        DigitsAdd digitsAdd = new DigitsAdd();
        int num = 38;
        System.out.println(digitsAdd.addDigitsWithRecursion(num));
        System.out.println(digitsAdd.addDigitsWithOutRecursion(num));
    }
    
    /**
     * 递归的方式实现 
     * @param num
     * @return
     */
    int addDigitsWithRecursion(int num) {
        if(num < 10) {
            return num;
        }
        
        String numString = String.valueOf(num);
        int sum = 0;
        for (char c : numString.toCharArray()) {
            sum += (c - '0');
        }
        
        return addDigitsWithRecursion(sum);
    }
    
    /**
     * 非递归O(1)复杂度解决方式
     * @param num
     * @return
     */
    int addDigitsWithOutRecursion(int num) {
        if(num < 10) {
            return num;
        }
        
        String numString = String.valueOf(num);
        int sum = 0;
        for (char c : numString.toCharArray()) {
            sum += (c - '0');
        }
        
        int result = sum % 9;
        if(0 == result) {
            return 9;
        } else {
            return result;
        }
    }
}
