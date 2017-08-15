package com.hiworld.lintcode.easy;

/**
 * 二进制相加,返回二进制
 * 如:
 * 11 + 1 = 100
 * 3 + 1 = 4 
 * 0011 + 0001 = 0100
 * 
 * 思路：
 * 按照字符串处理,从后往前遍历两个字符串,注意进位
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月4日 下午4:27:02
 */
public class BinaryAdd {

    public static void main(String[] args) {
        BinaryAdd binaryAdd = new BinaryAdd();
        System.out.println(binaryAdd.addBinary("100", "110010"));
    }
    
    /**
     * 
     * @param a
     * @param b
     * @return
     */
    String addBinary(String a, String b) {
        
        int aLength = a.length();
        int bLength = b.length();
        
        int max = aLength >= bLength ? aLength : bLength;
        //新数组的大小,预留一个位置,用于进位
        int resultLength = max + 1;
        char[] result = new char[resultLength];
        
        //前面是否有进位
        boolean hasCarry = false;
        
        //从后往前遍历
        int aStartIndex = a.length() - 1;
        int bStartIndex = b.length() - 1;
        //遍历次数,用于定位result数组
        int count = 0;
        
        for (; aStartIndex>=0 && bStartIndex>=0; --aStartIndex,--bStartIndex,++count) {
            char aChar = a.charAt(aStartIndex);
            char bChar = b.charAt(bStartIndex);
            
            if(aChar == bChar) { //如果相同'1'=='1' 或者 '0'=='0'
                if(hasCarry) {
                    result[resultLength - 1 - count] = '1';
                } else {
                    result[resultLength - 1 - count] = '0';
                }
                
                if(aChar == '1') {
                    hasCarry = true;
                } else {
                    hasCarry = false;
                }
            } else { //不相同
                if(hasCarry) {
                    result[resultLength - 1 - count] = '0';
                    hasCarry = true;
                } else {
                    result[resultLength - 1 - count] = '1';
                    hasCarry = false;
                }
            }
        }
        
        if(aStartIndex >= 0) { //B字符串遍历完毕,在遍历A数组
            for(int j=aStartIndex; j>=0; --j, ++count) {
                char aChar = a.charAt(j);
                if(aChar == '1') {
                    if(hasCarry) {
                        result[resultLength - 1 - count] = '0';
                        hasCarry = true;
                    } else {
                        result[resultLength - 1 - count] = '1';
                        hasCarry = false;
                    }
                } else {
                    if(hasCarry) {
                        result[resultLength - 1 - count] = '1';
                        hasCarry = false;
                    } else {
                        result[resultLength - 1 - count] = '0';
                        hasCarry = true;
                    }
                }
            }
        } 
        
        if(bStartIndex >= 0) { //A字符串遍历完毕,在遍历B数组
            for(int j=bStartIndex; j>=0; --j, ++count) {
                char bChar = b.charAt(j);
                if(bChar == '1') {
                    if(hasCarry) {
                        result[resultLength - 1 - count] = '0';
                        hasCarry = true;
                    } else {
                        result[resultLength - 1 - count] = '1';
                        hasCarry = false;
                    }
                } else {
                    if(hasCarry) {
                        result[resultLength - 1 - count] = '1';
                    } else {
                        result[resultLength - 1 - count] = '0';
                    }
                    hasCarry = false;
                }
            }
        }
        
        if(hasCarry) {
            result[0] = '1';
            return new String(result, 0, result.length);
        } else {
            result[0] = '0';
            return new String(result, 1, result.length-1);
        }
    }
}
