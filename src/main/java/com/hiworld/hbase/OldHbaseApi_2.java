//package com.whs.base.hbase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.KeyValue;
//import org.apache.hadoop.hbase.client.Get;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.client.ResultScanner;
//import org.apache.hadoop.hbase.client.Scan;
//import org.apache.hadoop.hbase.filter.ByteArrayComparable;
//import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
//import org.apache.hadoop.hbase.filter.FilterList;
//import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
//
///**
// * 数据获取示例
// * @author haiswang
// *
// */
//public class OldHbaseApi_2 {
//	
//	public static Configuration configuration = null;
//	
//	/**
//	 * 初始化Configuration
//	 */
//	public static void init()
//	{
//		configuration = HBaseConfiguration.create();
//		configuration.set("hbase.zookeeper.property.clientPort", "2181");
//		configuration.set("hbase.zookeeper.quorum", "10.249.73.143,10.249.73.144,10.249.73.145");
//		configuration.set("hbase.master", "10.249.73.145:60000");
//	}
//	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) throws Exception {
//		OldHbaseApi_2.init();
//		System.out.println("-------------->selectRowKey");
//		selectRowKey("htable", "wanghaisheng");
//		System.out.println("-------------->selectRowKeyFamily");
//		selectRowKeyFamily("htable", "wanghaisheng", "info");
//		System.out.println("-------------->selectRowkeyFamilyColumn");
//		selectRowkeyFamilyColumn("htable", "wanghaisheng", "info", "sex");
//		System.out.println("-------------->selectFilter");
//		List<String> conditions = new ArrayList<String>();
//		conditions.add("info,age,28");
//		conditions.add("info,sex,male");
//		selectFilter("htable", conditions);
//		
//	}
//	
//	/**
//	 * 查询指定rowKey的所有列族的数据
//	 * @param tableName
//	 * @param rowKey
//	 * @throws Exception
//	 */
//	public static void selectRowKey(String tableName ,String rowKey) throws Exception
//	{
//		HTable table = new HTable(configuration, tableName);
//		Get get = new Get(rowKey.getBytes());
//		Result result = table.get(get);
//		
//		for (KeyValue kv : result.raw()) {
//			System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//		}
//	}
//	
//	/**
//	 * 获取指定rowkey的指定列族的数据
//	 * @param tableName
//	 * @param rowKey
//	 * @param family
//	 * @throws Exception
//	 */
//	public static void selectRowKeyFamily(String tableName, String rowKey, String family) throws Exception
//	{
//		HTable table = new HTable(configuration, tableName);
//		Get get = new Get(rowKey.getBytes());
//		get.addFamily(family.getBytes());
//		
//		Result result = table.get(get);
//		for (KeyValue kv : result.raw()) {
//			System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//		}
//	}
//	
//	/**
//	 * 获取指定rowkey的指定列族的指定列的数据
//	 * @param tableName
//	 * @param rowKey
//	 * @param family
//	 * @param column
//	 * @throws Exception
//	 */
//	public static void selectRowkeyFamilyColumn(String tableName, String rowKey, String family, String column) throws Exception
//	{
//		HTable table = new HTable(configuration, tableName);
//		Get get = new Get(rowKey.getBytes());
//		get.addColumn(family.getBytes() ,column.getBytes());
//		
//		Result result = table.get(get);
//		for (KeyValue kv : result.raw()) {
//			System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//		}
//	}
//	
//	/**
//	 * 根据Filter来查询结果
//	 * 默认进行字典比较
//	 * @param tableName
//	 * @param conditions
//	 * @throws Exception
//	 */
//	public static void selectFilter(String tableName, List<String> conditions) throws Exception
//	{
//		HTable table = new HTable(configuration, tableName);
//		Scan scan = new Scan();
//		FilterList filters = new FilterList();
//		for (String condition : conditions) {
//			String[] where = condition.split(",");
//			filters.addFilter(new SingleColumnValueFilter(where[0].getBytes(), where[1].getBytes(), CompareOp.EQUAL, where[2].getBytes()));
//		}
//		
//		scan.setFilter(filters);
//		ResultScanner scanner = table.getScanner(scan);
//		for (Result result : scanner) {
//			for (KeyValue kv : result.raw()) {
//				System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//			}
//		}
//	}
//	
//	/*
//	public static void selectAgeGranterThan(String tableName, int age)
//	{
//		HTable table = new HTable(configuration, tableName);
//		Scan scan = new Scan();
//		new SingleColumnValueExcludeFilter
//		//Filter filter = new SingleColumnValueExcludeFilter("info".getBytes(), "age".getBytes(), CompareOp.EQUAL);
//	}
//	*/
//}
//
//class Comparator extends ByteArrayComparable
//{
//	private byte[] value;
//	
//	public Comparator(byte[] value) {
//		super(value);
//		this.value = value;
//	}
//
//	@Override
//	public int compareTo(byte[] value, int offset, int length) {
//		return 0;
//	}
//
//	@Override
//	public byte[] toByteArray() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
