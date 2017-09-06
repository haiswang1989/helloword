package com.hiworld.lintcode.medium;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 问题：寻找丢失的数 II
 * 描述：给一个由 1 - n 的整数随机组成的一个字符串序列，其中丢失了一个整数，请找到它。
 * 注意事项：n <= 30
 * 
 * 样例：
 * 给出 n = 20, str = 19201234567891011121314151618
 * 丢失的数是 17 ，返回这个数。
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月6日 下午4:33:08
 */
public class ArrayFindMissingII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayFindMissingII arrayFindMissingII = new ArrayFindMissingII();
        int ret = arrayFindMissingII.findMissing2(20, "19201234567891011121314151618");
        System.out.println("ret : " + ret);
    }
    
    /*
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    public int findMissing2(int n, String str) {
        // write your code here
        return byMapSolution(n, str);
    }
    
    /**
     * 
     * @param n
     * @param str
     * @return
     */
    public int byMapSolution(int n, String str) {
        Map<Character, Integer> charsCnt = new HashMap<Character, Integer>();
        StringBuilder strBuilder = new StringBuilder();
        for(int i=1; i<=n; i++) {
            strBuilder.append(i+"");
        }
        
        for (Character c : strBuilder.toString().toCharArray()) {
            if(charsCnt.containsKey(c)) {
                charsCnt.put(c, charsCnt.get(c) + 1);
            } else {
                charsCnt.put(c, 1);
            }
        }
        
        for (Character c : str.toCharArray()) {
            int count = charsCnt.get(c);
            if(count == 1) {
                charsCnt.remove(c);
            } else {
                charsCnt.put(c, count-1);
            }
        }
        
        Set<Character> remainKeys = charsCnt.keySet();
        Iterator<Character> iter = remainKeys.iterator();
        if(remainKeys.size() == 1) {
        } else { 
            //remainKeys.size() == 2
        } 
        
        return 0;
    }
}
