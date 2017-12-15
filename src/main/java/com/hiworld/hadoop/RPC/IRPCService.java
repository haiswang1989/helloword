package com.hiworld.hadoop.RPC;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * RPC服务接口
 * 在客户端和服务端都会存储一份
 * 服务接口需要继承VersionedProtocol,必须提供一个PROTOCOL_VERSION
 * @author haiswang
 *
 */
public interface IRPCService extends VersionedProtocol {
	
	long versionID = 12306L;
	
	public String rpcService(String name);
	
}
