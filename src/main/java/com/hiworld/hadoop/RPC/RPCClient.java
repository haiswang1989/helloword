package com.hiworld.hadoop.RPC;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Configuration config = new Configuration();
		IRPCService rpcService = (IRPCService)RPC.getProxy(IRPCService.class, 12306, new InetSocketAddress("10.249.73.140", 6080), config);
		String retVal = rpcService.rpcService("wanghaisheng");
		System.out.println(retVal);
		RPC.stopProxy(rpcService);
	}

}
