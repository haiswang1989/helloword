package com.hiworld.lintcode;

/**
 * 
 * 1 读作“1个1”或11.
 * 11 读作“2个1”或21.
 * 21 读作“1个2，1个1”或1211.
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月12日 下午2:18:36
 */
public class CountAndSay {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(6));
    }
    
    public String countAndSay(int n) {
        // Write your code here
        if(n == 1) {
            return "1";
        }
        
        char emptyChar = ' ';
        int count = 0;
        char what = emptyChar;
        
        String lastCountSay = "1";
        StringBuilder ret = new StringBuilder();
        
        while(--n >= 1) { 
            //依赖遍历的复位
            ret.setLength(0);
            what = emptyChar;
            count = 0;
            
            for (char c : lastCountSay.toCharArray()) {
                if(c == what) {
                    ++count; //如果当前字符和正在统计的字符一样,直接次数累加
                } else { //如果不一样
                    if(what != emptyChar) { //如果what是空字符,则表示刚开始遍历第一个字符
                        ret.append(count).append(what);
                    }
                    what = c; //设置what
                    count = 1; //设置次数
                }
            }
            
            ret.append(count).append(what);
            lastCountSay = ret.toString(); //这次的"输出"当做下次遍历的"输入"
        }
        
        return ret.toString();
    }
}
