package com.hiworld.hadoop.RPC;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

/**
 * RPC服务的实现类
 * @author haiswang
 *
 */
public class RPCServiceImpl implements IRPCService {

	public String rpcService(String name) {
		return "hello ," + name + " ~";
	}

	public ProtocolSignature getProtocolSignature(String protocol,
			long clientVersion, int clientMethodsHash) throws IOException {
		return new ProtocolSignature();
	}

	public long getProtocolVersion(String protocol, long clientVersion)
			throws IOException {
		return IRPCService.versionID;
	}

}
