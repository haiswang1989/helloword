package com.hiworld.lintcode.medium;

/**
 * 问题：删除数字 
 * 描述：给出一个字符串 A, 表示一个 n 位正整数, 删除其中 k 位数字, 使得剩余的数字仍然按照原来的顺序排列产生一个新的正整数。找到删除 k 个数字之后的最小正整数
 * 
 * 样例：
 * 给出一个字符串代表的正整数 A 和一个整数 k, 其中 A = 178542, k = 4
 * 返回一个字符串 "12"
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月31日 下午4:36:46
 */
public class DeleteDigits {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DeleteDigits deleteDigits = new DeleteDigits();
        String ret = deleteDigits.deleteDigits("90249", 2);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 
     * @param A: A positive integer which has N digits, A is a string.
     * @param k: Remove k digits.
     * @return: A string
     */
    public String deleteDigits(String A, int k) {
        // write your code here
        int length = 0;
        if(null==A || (length=A.length()) < k) {
            return null;
        }
        
        if(length == k) {
            return "";
        }
        
        //注意如果返回值的前面是'0',那么就需要删除
        return trimLeftZero(greedy(A.toCharArray(), length, k));
    }
    
    /**
     * 删除左边的'0'
     * @param str
     * @return
     */
    public String trimLeftZero(String str) {
        boolean needRemove = true;
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if(!(c == '0' && needRemove)) {
                sb.append(c);
                needRemove = false;
            } 
        }
        
        return sb.toString();
    }
    
    /**
     * 贪心
     * 当前面一个字符比当前一个字符大的时候,就删除前面一个字符
     * 对于是一个完全地址的字符,那么删除最后一个
     * @param A
     * @param length
     * @param k
     * @return
     */
    public String greedy(char[] A, int length, int k) {
        for(int i=0; i<k; ++i) {
            char last = A[i];
            int removeIndex = i;
            for(int j=i+1; j<length; ++j) {
                char curr = A[j];
                if(last > curr) { //出现变小的情况,那么就需要删除last字符
                    break;
                } else {
                    last = curr;
                    removeIndex = j;
                }
            }
            
            System.arraycopy(A, i, A, i+1, removeIndex - i);
        }
        
        return new String(A, k, length-k);
    }
}
