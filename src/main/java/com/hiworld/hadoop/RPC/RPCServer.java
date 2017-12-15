package com.hiworld.hadoop.RPC;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration config = new Configuration();
		
		//Server rpcServer = new RPC.Server(new RPCServiceImpl(), config, "10.249.73.140", 6080);
		
		
		RPC.Builder builder = new RPC.Builder(config).setBindAddress("10.249.73.140").setPort(6080).setInstance(new RPCServiceImpl()).setProtocol(RPCServiceImpl.class);
		Server rpcServer = builder.build();
		rpcServer.start();
	}

}
