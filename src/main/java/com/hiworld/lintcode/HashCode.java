package com.hiworld.lintcode;

import java.util.HashMap;

/**
 * 问题：
 * hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 
 *   = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE
 *   = 3595978 % HASH_SIZE
 * 
 * 样例：
 * 对于key="abcd" 并且 size=100， 返回 78
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月14日 下午6:13:56
 */
public class HashCode {

    public static void main(String[] args) {
        HashCode hashCode = new HashCode();
        System.out.println(hashCode.hashCode("Wrong answer or accepted?".toCharArray(), 1000000007));
        System.out.println(hashCode.hashCode_not_mine("Wrong answer or accepted?".toCharArray(), 1000000007));
    }
    
    /**************************网上比较方便的写法*************************/
    
    /**
     * 该方式的理论基础
     * 1：(x * y) % z = [(x % z) * (y % z)] % z
     * 2：x * y(n) % z = x * 
     * 
     * @param key
     * @param HASH_SIZE
     * @return
     */
    public int hashCode_not_mine(char[] key,int HASH_SIZE) {
        if(null==key || 0==key.length) {
            return 0;
        }
        
        int length = key.length;
        
        long sum = key[0]; 
        
        for(int i=1; i<length; i++) { 
            sum = sum * 33 % HASH_SIZE + key[i];
        }
        
        return (int)(sum % HASH_SIZE);
    }
    
    
    /****************************自己的写法***************************/
    /**
     * 
     * @param key
     * @param HASH_SIZE
     * @return
     */
    public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
        if(null==key || 0==key.length) {
            return 0;
        }
        
        int length = key.length;
        long sum = 0l;
        
        HashMap<Integer, Integer> indexMultiplierMap = multiplier(length, HASH_SIZE);
        for(int index=0; index<length; ++index) {
            Integer multiplier = indexMultiplierMap.get(index);
            long temp = 1l * key[index] * multiplier;
            sum += doMod(temp, HASH_SIZE);
            sum = doMod(sum, HASH_SIZE);
        }
        
        return (int)(sum % HASH_SIZE);
    }
    
    /**
     * 获取乘数
     * 
     * index = 1, return 1
     * index = 2, renturn 33
     * index = 3, return 33 * 33
     * 
     * @param index 
     * @return
     */
    public HashMap<Integer, Integer> multiplier(int length, int HASH_SIZE) {
        HashMap<Integer, Integer> indexMultiplierMap = new HashMap<>();
        
        int endIndex = length - 1;
        indexMultiplierMap.put(endIndex, 1);
        
        long multiplier = 1l;
        while(--endIndex >= 0) {
            multiplier *= 33l;
            multiplier = doMod(multiplier, HASH_SIZE);
            indexMultiplierMap.put(endIndex, (int)multiplier);
        }
        
        return indexMultiplierMap;
    }
    
    /**
     * 取余
     * @param input
     * @param divisor
     * @return
     */
    public long doMod(long input, long divisor) {
        if(input >= divisor) {
            return input % divisor;
        }
        
        return input;
    }
}
