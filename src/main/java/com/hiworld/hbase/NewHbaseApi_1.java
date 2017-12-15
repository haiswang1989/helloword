package com.hiworld.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

/**
 * 新版API操作方式
 * @author haiswang
 *
 */
public class NewHbaseApi_1 {

	public static Configuration configuration = null;
	
	public static void init()
	{
		configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
		configuration.set("hbase.zookeeper.quorum", "10.249.73.142,10.249.73.143,10.249.73.144");
		
//		configuration.set("hbase.zookeeper.property.clientPort", "2181");
//		configuration.set("hbase.zookeeper.quorum", "hbase-qa-zk1.stubcorp.com");
//		configuration.set("zookeeper.znode.parent", "/hbase-unsecure");
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		NewHbaseApi_1.init();
		
		Connection conn = ConnectionFactory.createConnection(configuration);
		HBaseAdmin admin = (HBaseAdmin)conn.getAdmin();
		
		createTable("n_htable", admin);
		HTable table = (HTable) conn.getTable(TableName.valueOf("n_htable"));
		/*
		insertData(table);
		queryAll(table);
		*/
		
		insertDataAutoFlushTrue(table);
		
		//insertDataAutoFlushFalse(table);
	}
	
	/**
	 * 创建表
	 * @param tableName
	 * @param admin
	 * @throws IOException
	 */
	public static void createTable(String tableName, HBaseAdmin admin) throws IOException
	{
		
		if(admin.tableExists(tableName))
		{
			System.out.println("n_htable is exist...");
			return;
		}
		
		HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));
		HColumnDescriptor hcd_info = new HColumnDescriptor("info");
		HColumnDescriptor hcd_data = new HColumnDescriptor("data");
		htd.addFamily(hcd_info);
		htd.addFamily(hcd_data);
		admin.createTable(htd);
	}
	
	/**
	 * 这种方式的速度比较慢
	 * @param table
	 * @throws IOException
	 */
	public static void insertDataAutoFlushTrue(HTable table) throws IOException
	{
		long t1 = System.currentTimeMillis();
		for(int i=0; i<5000; i++)
		{
			Put put = new Put((i+"").getBytes());
			put.addColumn("info".getBytes(), "age".getBytes(), String.valueOf(i).getBytes());
			put.addColumn("info".getBytes(), "sex".getBytes(), "male".getBytes());
			put.addColumn("info".getBytes(), "readme".getBytes(), "just read me".getBytes());
			System.out.println(put);
			//每次都需要RPC调用,将数据传送到服务端
			table.put(put);
		}
		long t2 = System.currentTimeMillis();
		
		System.out.println("insertDataAutoFlushTrue ,use : " + (t2-t1));
	}
	
	/**
	 * 这种方式的速度比上面要快很多
	 * @param table
	 * @throws IOException
	 */
	public static void insertDataAutoFlushFalse(HTable table) throws IOException
	{
		long t1 = System.currentTimeMillis();
		//开启客户端缓冲区
		table.setAutoFlush(false);
		for(int i=1000; i<2000; i++)
		{
			Put put = new Put("wanghaisheng".getBytes());
			put.addColumn("info".getBytes(), "age".getBytes(), String.valueOf(i).getBytes());
			put.addColumn("info".getBytes(), "sex".getBytes(), "male".getBytes());
			put.addColumn("data".getBytes(), "readme".getBytes(), "just read me".getBytes());
			//数据缓存在客户端,最后提交到服务端
			table.put(put);
		}
		//强制将数据写到服务端,一般情况下不需要客户端强制刷新缓冲区,客户端自己会监控缓冲器的大小,当
		table.flushCommits();
		long t2 = System.currentTimeMillis();
		table.setAutoFlush(true);
		System.out.println("insertDataAutoFlushFalse ,use : " + (t2-t1));
	}
	
	/**
	 * 插入数据
	 * @param table
	 * @throws IOException
	 */
	public static void insertData(HTable table) throws IOException
	{
		Put put = new Put("wanghaisheng".getBytes());
		put.addColumn("info".getBytes(), "age".getBytes(), "28".getBytes());
		put.addColumn("info".getBytes(), "sex".getBytes(), "male".getBytes());
		put.addColumn("data".getBytes(), "readme".getBytes(), "just read me".getBytes());
		table.put(put);
	}
	
	/**
	 * 查询所有的数据
	 * @param table
	 * @throws IOException
	 */
	public static void queryAll(HTable table) throws IOException
	{
		ResultScanner scanner = table.getScanner(new Scan());
		
		for (Result result : scanner) {
			for (Cell cell : result.rawCells()) {
				System.out.println("列族:" + new String(CellUtil.cloneFamily(cell)) + " ,列:" + new String(CellUtil.cloneQualifier(cell)) + ",值:" + new String(CellUtil.cloneValue(cell)));
			}
		}
	}

}
