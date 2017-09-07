package com.hiworld.lintcode.medium;

import com.hiworld.lintcode.common.SVNRepo;

/**
 * 问题：第一个错误的代码版本
 * 描述：代码库的版本号是从 1 到 n 的整数。某一天，有人提交了错误版本的代码，因此造成自身及之后版本的代码在单元测试中均出错。请找出第一个错误的版本号。
 * 你可以通过 isBadVersion 的接口来判断版本号 version 是否在单元测试中出错，具体接口详情和调用方法请见代码的注释部分
 * 
 * 样例：
 * 给出 n=5
 * 调用isBadVersion(3)，得到false
 * 调用isBadVersion(5)，得到true
 * 调用isBadVersion(4)，得到true
 * 此时我们可以断定4是第一个错误的版本号
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月7日 上午10:45:22
 */
public class FindFirstBadVersion {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FindFirstBadVersion findFirstBadVersion = new FindFirstBadVersion();
        int ret = findFirstBadVersion.findFirstBadVersion(10);
        System.out.println("ret : " + ret);
    }
    
    /**
     * 思路:使用二分查找
     * 注意:内存溢出的情况
     * 
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        Long fromVersion = 1l; //这边转换成long,避免出现溢出
        if(isBadVersion(fromVersion)) {
            return fromVersion.intValue();
        }
        
        Long endVersion = Long.parseLong(n+"");
        while((fromVersion+1) != endVersion) { //用这个方式来判定停止循环,百试不爽
            //这样计算middle的方式会偏大,但是不会出现溢出
            Long middleVersion = (fromVersion + endVersion) / 2;
            if(isBadVersion(middleVersion)) {
                endVersion = middleVersion;
            } else {
                fromVersion = middleVersion;
            }
        }
        
        return endVersion.intValue();
    }
    
    public boolean isBadVersion(Long version) {
        return SVNRepo.isBadVersion(version.intValue());
    }
}
