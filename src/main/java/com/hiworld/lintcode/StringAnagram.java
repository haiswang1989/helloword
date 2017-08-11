package com.hiworld.lintcode;

public class StringAnagram {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StringAnagram stringAnagram = new StringAnagram();
        boolean ret = stringAnagram.anagram("ab", "ac");
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        // write your code here
        if(null==s && null==t) {
            return true;
        } else if(null==s && null!=t) {
            return false;
        } else if(null!=s && null==t) {
            return false;
        }
        
        int[] statistics_1 = new int[128]; 
        for (char c : s.toCharArray()) {
            statistics_1[c] = ++statistics_1[c];
        }
        
        int[] statistics_2 = new int[128];
        for (char c : t.toCharArray()) {
            statistics_2[c] = ++statistics_2[c];
        }
        
        for(int i=0; i<128; i++) {
            if(statistics_1[i] != statistics_2[i]) {
                return false;
            }
        }
        
        return true;
    }

}
