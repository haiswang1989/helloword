package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 问题：逆波兰表达式求值
 * 描述：求逆波兰表达式的值。在逆波兰表达法中，其有效的运算符号包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰计数表达
 * 
 * 样例：
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月1日 下午1:48:54
 */
public class EvalRPN {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        String[] tokens = {"18"};
        
        EvalRPN evalRPN = new EvalRPN();
        int ret = evalRPN.evalRPN(tokens);
        System.out.println("ret : " + ret);
    }
    
    /*
     * @param tokens: The Reverse Polish Notation
     * @return: the value
     */
    public int evalRPN(String[] tokens) {
        // write your code here
        ArrayList<String> input = new ArrayList<>();
        for (String token : tokens) {
            input.add(token);
        }
        
        return solution(input);
    }
    
    /**
     * 
     * @param tokens
     * @return
     */
    public int solution(ArrayList<String> tokens) {
        HashSet<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        
        String first = null;
        String second = null;
        
        ArrayList<String> newTokens = new ArrayList<>();
        
        int length = tokens.size();
        
        for(int i=0; i<length; ++i) {
            String token = tokens.get(i);
            if(operators.contains(token)) { //遇到运算符了
                //计算
                int result = calculate(first, second, token);
                if(newTokens.size() == 0 && i == (length-1)) { //全部计算完成
                    return result;
                } else { //没有计算完成
                    if(newTokens.size() != 0) { //如果newTokens里面有数据,那么就拿出最后一个数作为first
                        first = newTokens.remove(newTokens.size()-1);
                    } else {
                        first = null; //如果newTokens中没有数据了,那么就赋值为null,等待轮训tokens
                    }
                    second = result + ""; //计算的结果作为second
                }
            } else { 
                String tmp = first; //轮训tokens
                first = second;
                second = token;
                if(null!=tmp) {
                    newTokens.add(tmp);
                }
            }
        }
        
        //对于tokens只有一个元素的情况的
        return Integer.parseInt(second);
    }
    
    /**
     * 计算
     * @param first
     * @param second
     * @param token
     * @return
     */
    public int calculate(String first, String second, String token) {
        int firstInt = Integer.parseInt(first);
        int secondInt = Integer.parseInt(second);
        int result = 0;
        switch (token) {
            case "+":
                result = firstInt + secondInt;
                break;
            case "-":
                result = firstInt - secondInt;
                break;
            case "*":
                result = firstInt * secondInt;
                break;
            case "/":
                result = firstInt / secondInt;
                break;
            default:
                break;
        }
        return result;
    }
}
