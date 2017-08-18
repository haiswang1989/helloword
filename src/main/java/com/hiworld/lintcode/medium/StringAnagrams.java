package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 问题：乱序字符串
 * 描述：给出一个字符串数组S，找到其中所有的乱序字符串(Anagram)。如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序不同的字符串也在S中。
 * 
 * 注意：所有的字符串都只包含小写字母
 * 
 * 样例：
 * 对于字符串数组 ["lint","intl","inlt","code"]
 * 返回 ["lint","inlt","intl"]

 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月16日 下午4:30:49
 */
public class StringAnagrams {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StringAnagrams stringAnagrams = new StringAnagrams();
        String[] strs = {"lint","intl","inlt","code"};
        List<String> ret = stringAnagrams.anagrams(strs);
        System.out.println(Arrays.toString(ret.toArray()));
    }
    
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        // write your code here
        HashMap<HashMap<Character,Integer>, List<String>> statics = new HashMap<>();
        
        for (String str : strs) {
            HashMap<Character, Integer> key = toHashMap(str);
            if(statics.containsKey(key)) {
                statics.get(key).add(str);
            } else {
                List<String> values = new ArrayList<>();
                values.add(str);
                statics.put(key, values);
            }
        }
        
        List<String> retValues = new ArrayList<>();
        for (List<String> value : statics.values()) {
            if(value.size() > 1) {
                retValues.addAll(value);
            }
        }
        
        return retValues;
    }
    
    public HashMap<Character, Integer> toHashMap(String str) {
        // TODO Auto-generated method stub
        HashMap<Character, Integer> ret = new HashMap<>();
        for (Character c : str.toCharArray()) {
            if(ret.containsKey(c)) {
                ret.put(c, ret.get(c)+1);
            } else {
                ret.put(c, 1);
            }
        }
        
        return ret;
    }

}
