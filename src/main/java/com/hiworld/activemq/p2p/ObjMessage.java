package com.hiworld.activemq.p2p;

import java.io.Serializable;

/**
 * Message对象
 * @author haiswang
 *
 */
public class ObjMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public ObjMessage(String message)
	{
		this.message = message;
	}
}