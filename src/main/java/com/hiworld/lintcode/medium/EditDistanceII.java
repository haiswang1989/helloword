package com.hiworld.lintcode.medium;

/**
 * 问题：编辑距离
 * 描述：给出两个字符串分别为S和T,判断是否可以通过一次编辑距离是1
 * 
 * 样例：
 * Given s = "aDb", t = "adb"
 * return true
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月1日 上午10:56:23
 */
public class EditDistanceII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        EditDistanceII editDistanceII = new EditDistanceII();
        boolean ret = editDistanceII.isOneEditDistance("a", "ab");
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param s a string
     * @param t a string
     * @return true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // Write your code here
        if(null==s && null==t) { //全为null,无需进行变化
            return false;
        } else if(null==s || null==t) { //有一个为null
            return false;
        }
        
        //都不为null
        int sLength = s.length();
        int tLength = t.length();
        
        if(sLength == tLength) { //长度相等,字符串不相等,那么就是要进行换操作
            if(s.equals(t)) { //完全一样的字符串,不需要更换就相等
                return false; 
            } else {
                return sameLengthOneEdit(s, t);
            }
        } else if(Math.abs(sLength-tLength) == 1) {
            if(sLength < tLength) {
                return unSameLengthOneEdit(t, s);
            } else {
                return unSameLengthOneEdit(s, t);
            }
        } else { //如果连个字符串的长度相差大于1,那么不可能经过一次变换获得
            return false;
        }
    }
    
    /**
     * 长度一样是否可以一次变换获得
     * @param s1
     * @param s2
     * @return
     */
    public boolean sameLengthOneEdit(String s1, String s2) {
        int notEqualsCnt = 0;
        for(int i=0; i<s1.length(); ++i) {
            if(s1.charAt(i) != s2.charAt(i)) {
                notEqualsCnt++;
                if(notEqualsCnt > 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * 长度相差一,是否一个一次变换获得
     * @param longerString
     * @param string
     * @return
     */
    public boolean unSameLengthOneEdit(String longerString, String string) {
        StringBuilder sb = new StringBuilder();
        int i=0;
        for(; i<string.length(); ++i) {
            char target = string.charAt(i);
            if(target != longerString.charAt(i)) {
                sb.append(longerString.charAt(i));
                sb.append(string.substring(i));
                break;
            } else {
                sb.append(target);
            }
        }
        
        if(i == string.length()) {
            sb.append(longerString.substring(i));
        }
        
        return sb.toString().equals(longerString);
    }
}
