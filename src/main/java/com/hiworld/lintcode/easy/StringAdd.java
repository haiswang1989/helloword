package com.hiworld.lintcode.easy;

/**
 * 两个String相加
 * 样例：
 * num1="123" num2="45" return "168"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月6日 上午10:01:26
 */
public class StringAdd {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String num1 = "123";
        String num2 = "45";    
        
        StringAdd stringAdd = new StringAdd();
        
        System.out.println(stringAdd.addStrings(num1, num2));
    }
    
    public String addStrings(String num1, String num2) {
        // Write your code here
        //其中任意一个String为空,则返回另一个
        if(isEmpty(num1)) { 
            return num2;
        }
        
        if(isEmpty(num2)) {
            return num1;
        }
        
        int num1Length = num1.length();
        int num2Length = num2.length();
        
        //new新的string的char的时候,比最长的string大1,用于进位
        int newLength = (num1Length >= num2Length ? num1Length : num2Length) + 1;
        char[] newStringChar = new char[newLength];
        
        int newStringHandleIndex = newLength - 1;
        int num1HandleIndex = num1Length - 1;
        int num2HandleIndex = num2Length - 1;
        
        boolean hasCarry = false;
        char num1_c = '0';
        char num2_c = '0';
        int sum = 0;
        int realV = 0;
        
        //遍历string,从后往前遍历
        for(;num1HandleIndex>=0 && num2HandleIndex>=0; --num1HandleIndex,--num2HandleIndex,--newStringHandleIndex) {
            num1_c = num1.charAt(num1HandleIndex);
            num2_c = num2.charAt(num2HandleIndex);
            
            sum = (num1_c - '0') + (num2_c - '0');
            if(hasCarry) {
                sum = sum + 1;
            }
            
            if(sum / 10 == 1) {
                hasCarry = true;
                realV = sum % 10;
            } else {
                hasCarry = false;
                realV = sum;
            }
            
            newStringChar[newStringHandleIndex] = (char)(realV + '0');
        }
        
        //累加结束后,再注意一下可能有一个字符串还没有累加结束
        if(num1HandleIndex < 0) {
            hasCarry = appendAdd(newStringChar, newStringHandleIndex, num2, num2HandleIndex, hasCarry);
        }
        
        if(num2HandleIndex < 0) {
            hasCarry = appendAdd(newStringChar, newStringHandleIndex, num1, num1HandleIndex, hasCarry);
        }
        
        if(hasCarry) {
            newStringChar[0] = '1';
            return new String(newStringChar);
        } else {
            return new String(newStringChar, 1, newStringChar.length-1);
        }
    }
    
   /**
    * 
    * @param newStringChar
    * @param newStringHandleIndex
    * @param num
    * @param numHandleIndex
    * @param hasCarry
    * @return
    */
    public boolean appendAdd(char[] newStringChar, int newStringHandleIndex, String num, int numHandleIndex, boolean hasCarry) {
        char num_c = '0';
        int sum = 0;
        int realV = 0;
        for(int i=numHandleIndex; i>=0; --i,--newStringHandleIndex) {
            num_c = num.charAt(i);
            sum = num_c - '0';
            if(hasCarry) {
                sum = sum + 1;
            }
            
            if(sum / 10 == 1) {
                hasCarry = true;
                realV = 0;
            } else {
                hasCarry = false;
                realV = sum;
            }
            
            newStringChar[newStringHandleIndex] = (char)(realV + '0');
        }
        
        return hasCarry;
    }
    
    public boolean isEmpty(String input) {
        return null==input || "".equals(input);
    }
    
}
