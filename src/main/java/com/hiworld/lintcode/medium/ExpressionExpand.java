package com.hiworld.lintcode.medium;

/**
 * 问题：Expression Expand 
 * 描述：
 * 
 * 样例：
 * s = abc3[a] return abcaaa
 * s = 3[abc] return abcabcabc
 * s = 4[ac]dy, return acacacacdy
 * s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月31日 下午5:18:57
 */
public class ExpressionExpand {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        String s = "3[abc]";
        ExpressionExpand expressionExpand = new ExpressionExpand();
        String ret = expressionExpand.expressionExpand(s);
        System.out.println("ret : " + ret);
    }
    
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        // Write your code here
        if(null==s || s.length()==0) {
            return s;
        }
        
        StringBuilder tmp = new StringBuilder();//n[**],存储n个** 
        StringBuilder sBuilder = new StringBuilder(); //存储解析了一个[]的结果集
        
        int lastBeginIndex = -1;
        while(-1!=(lastBeginIndex=s.lastIndexOf("["))) { //找到最后一个"["
            int endIndex = s.indexOf("]", lastBeginIndex); //找到与最后一个"["匹配的"]"
            
            //获取n[]前面的n,注意这个可能是多位数
            int timeEnd = lastBeginIndex-1;
            int timeBegin = timeEnd;
            while(timeBegin > 0) {
                char timeC = s.charAt(timeBegin-1);
                if(timeC >= '0' && timeC <= '9') {
                    timeBegin--;
                } else {
                    break;
                }
            }
            
            int time = 0;
            if(timeEnd == timeBegin) {
                time = s.charAt(timeBegin)-'0';
            } else {
                time = Integer.parseInt(s.substring(timeBegin, timeEnd+1));
            }
            
            //获取[*]中的字符串
            String tmpString = s.substring(timeEnd+2, endIndex);
            while(time-- > 0) { //生成多个[]中的字符串
                tmp.append(tmpString);
            }
            
            //生成解析一次的结果,然后进行循环
            sBuilder.append(s.substring(0, timeBegin)).append(tmp.toString()).append(s.substring(endIndex+1));
            s = sBuilder.toString();
            
            tmp.setLength(0);
            sBuilder.setLength(0);
        }
        
        return s;
    }
}
