package com.hiworld.jdk.exception;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年9月26日 下午5:33:18
 */
public class RTException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public RTException(String msg) {
        super(msg);
    }
}
