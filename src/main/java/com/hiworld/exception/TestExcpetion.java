package com.hiworld.exception;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月26日 下午5:36:36
 */
public class TestExcpetion {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TestExcpetion testExcpetion = new TestExcpetion();
        
        try {
            testExcpetion.func();
        } catch(RTException e) {
            e.printStackTrace();
        }
    }
    
    //对于RuntimeException,不需要throws定义
    public void func() /*throws RTException*/ {
        throw new RTException("Funk");
    }
}
