package com.hiworld.lintcode.medium;

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
    
    boolean quit = false;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayFindMissingII arrayFindMissingII = new ArrayFindMissingII();
        int ret = arrayFindMissingII.findMissing2(12, "1234152678911");
        System.out.println("ret : " + ret);
    }
    
    /*
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    public int findMissing2(int n, String str) {
        // write your code here
        return solution(n, str);
    }
    
    /**************************网上的解决方案,深度优先搜索***************************/
    public int solution(int n, String str) {
        //数组的索引作为每个元素的值,存在则为true,不存在则为false
        //忽略index = 0的索引
        boolean[] numExist = new boolean[n + 1];
        dfs(0, n, str, numExist);
        int ret = Integer.MIN_VALUE;
        for(int i=1; i<numExist.length; ++i) {
            if(!numExist[i]) {
                ret = i;
                break;
            }
        }
        
        return ret;
    }
    
    /**
     * 深度优先搜索
     * @param fromIndex
     * @param n
     * @param str
     * @param numExist
     */
    public void dfs(int fromIndex, int n, String str, boolean[] numExist) {
        //索引位置越界
        if(fromIndex >= str.length()) {
            //到了这边可以直接退出了
            quit = true;
            return;
        }
        
        int currInt = str.charAt(fromIndex) - '0';
        if(currInt == 0) {
            return;
        }
        
        while(currInt <= n && !quit) {
            if(!numExist[currInt]) {
                numExist[currInt] = true;
                dfs(fromIndex + 1, n, str, numExist);
                //递归pop出来继续执行的时候,如果是停止指令,那么就直接返回了,不要再递归了
                if(quit) { 
                    return;
                } 
                //如果是搜索过程中的正常pop,那么需要回滚状态
                numExist[currInt] = false;
            }
            
            fromIndex++;
            if(fromIndex >= str.length()) {
                break;
            }
            
            //新值
            currInt = currInt * 10 + str.charAt(fromIndex) - '0';
        }
    }
}
