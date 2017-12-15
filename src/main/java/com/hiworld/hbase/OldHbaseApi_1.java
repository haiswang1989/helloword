//package com.whs.base.hbase;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.KeyValue;
//import org.apache.hadoop.hbase.MasterNotRunningException;
//import org.apache.hadoop.hbase.ZooKeeperConnectionException;
//import org.apache.hadoop.hbase.client.Delete;
//import org.apache.hadoop.hbase.client.Get;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.client.ResultScanner;
//import org.apache.hadoop.hbase.client.Scan;
//import org.apache.hadoop.hbase.filter.Filter;
//import org.apache.hadoop.hbase.filter.FilterList;
//import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
//import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
//
///**
// * 基本API使用
// * @author haiswang
// *
// */
//public class OldHbaseApi_1 {
//
//	/**
//	 * @param args
//	 * @throws IOException 
//	 * @throws ZooKeeperConnectionException 
//	 * @throws MasterNotRunningException 
//	 */
//	public static void main(String[] args) throws Exception {
//		
//		String tableName = "n_user";
//		String rowKey = "wanghaisheng";
//		
//		Configuration conf = HBaseConfiguration.create();
//		
//		conf.set("hbase.zookeeper.property.clientPort", "2181");
//		conf.set("hbase.zookeeper.quorum", "10.249.73.143,10.249.73.144,10.249.73.145");
//		conf.set("hbase.master", "10.249.73.145:60000");
//		/*
//		createTable(conf, tableName);
//		insertData(conf, tableName);
//		getRow(conf, tableName, rowKey);
//		deleteRow(conf, tableName, "wanghaisheng");
//		getRow(conf, tableName, rowKey);
//		*/
//		
//		//queryAll(conf, tableName);
//		//queryByRowkey(conf, tableName, rowKey);
//		//queryByNormalQualifier(conf, tableName, "age", "3");
//		queryByQualifierList(conf, tableName, "age", "28", "name", "wanghaisheng1");
//	}
//	
//	/**
//	 * 多个qualifier条件联合查询
//	 * @param conf
//	 * @param tableName
//	 * @param qualifier1
//	 * @param qualifierVal1
//	 * @param qualifier2
//	 * @param qualifierVal2
//	 * @throws Exception
//	 */
//	public static void queryByQualifierList(Configuration conf ,String tableName ,String qualifier1 ,String qualifierVal1 ,String qualifier2 ,String qualifierVal2) throws Exception
//	{
//		HTable hTable = new HTable(conf, tableName);
//		
//		List<Filter> filters = new ArrayList<Filter>();
//		Filter filter1 = new SingleColumnValueFilter("info".getBytes(), qualifier1.getBytes(), CompareOp.EQUAL, qualifierVal1.getBytes());
//		Filter filter2 = new SingleColumnValueFilter("info".getBytes(), qualifier2.getBytes(), CompareOp.EQUAL, qualifierVal2.getBytes());
//		filters.add(filter1);
//		filters.add(filter2);
//		
//		FilterList filterList = new FilterList(filters);
//		
//		Scan scan = new Scan();
//		scan.setFilter(filterList);
//		
//		ResultScanner scanner = hTable.getScanner(scan);
//		for (Result result : scanner) {
//			for (KeyValue kv : result.raw()) {
//				System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//			}
//		}
//	}
//	
//	/**
//	 * 通过普通的qualifier和qualifier的值作为条件过滤查询
//	 * @param conf
//	 * @param tableName
//	 * @param qualifier
//	 * @param qualifierVal
//	 * @throws Exception 
//	 */
//	public static void queryByNormalQualifier(Configuration conf ,String tableName ,String qualifier ,String qualifierVal) throws Exception
//	{
//		HTable hTable = new HTable(conf, tableName);
//		
//		//这边的比较全是按照字典的顺序进行比较的
//		Filter queryFilter = new SingleColumnValueFilter("info".getBytes(), qualifier.getBytes(), CompareOp.EQUAL, qualifierVal.getBytes());
//		//Filter queryFilter = new SingleColumnValueFilter("info".getBytes(), qualifier.getBytes(), CompareOp.LESS, qualifierVal.getBytes());
//		//Filter queryFilter = new SingleColumnValueFilter("info".getBytes(), qualifier.getBytes(), CompareOp.GREATER, qualifierVal.getBytes());
//		Scan scan = new Scan();
//		scan.setFilter(queryFilter);
//		
//		ResultScanner scanner = hTable.getScanner(scan);
//		for (Result result : scanner) {
//			for (KeyValue kv : result.raw()) {
//				System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//			}
//		}
//		
//		hTable.close();
//	}
//	
//	/**
//	 * 通过rowkey作为条件查询
//	 * @param conf
//	 * @param tableName
//	 * @param rowKey
//	 * @throws Exception
//	 */
//	public static void queryByRowkey(Configuration conf ,String tableName ,String rowKey) throws Exception
//	{
//		HTable hTable = new HTable(conf, tableName);
//		Get scan = new Get(rowKey.getBytes());
//		Result result = hTable.get(scan);
//		
//		for (KeyValue kv : result.raw()) {
//			System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//		}
//		hTable.close();
//	}
//	
//	/**
//	 * 查询所有的数据
//	 * @param conf
//	 * @param tableName
//	 * @throws Exception
//	 */
//	public static void queryAll(Configuration conf ,String tableName) throws Exception
//	{
//		HTable hTable = new HTable(conf, tableName);
//		
//		ResultScanner scanner = hTable.getScanner(new Scan());
//		
//		for (Result result : scanner) {
//			for (KeyValue kv : result.raw()) {
//				System.out.println("列族:" + new String(kv.getFamily()) + " ,列:" + new String(kv.getQualifier())  +  " ,值:" + new String(kv.getValue()));
//			}
//		}
//		
//		hTable.close();
//	}
//	
//	/**
//	 * 根据非rowkey删除
//	 * @param tablename
//	 * @param nonRowkey
//	 * @param nonRowkeyVal
//	 */
//	public static void deleteByCondition(String tablename ,String nonRowkey ,String nonRowkeyVal)
//	{
//		//占时没有发现使用非rowkey作为条件删除了记录的API
//	}
//	
//	/**
//	 * 删除一条记录,以rowkey为参数
//	 * @param conf
//	 * @param tableName
//	 * @param rowKey
//	 * @throws Exception
//	 */
//	public static void deleteRow(Configuration conf, String tableName, String rowKey) throws Exception
//	{
//		HTable hTable = new HTable(conf, tableName);
//		List<Delete> deleteList = new ArrayList<Delete>();
//		
//		Delete dl = new Delete(rowKey.getBytes());
//		deleteList.add(dl);
//		hTable.delete(deleteList);
//		hTable.close();
//	}
//	
//	/**
//	 * 删除表
//	 * @param conf
//	 * @param tableName
//	 * @throws Exception
//	 */
//	public static void dropTable(Configuration conf ,String tableName) throws Exception
//	{
//		HBaseAdmin admin = new HBaseAdmin(conf);
//		admin.disableTable(tableName);
//		admin.deleteTable(tableName);
//		admin.close();
//	}
//	
//	/**
//	 * 插入数据
//	 * @param conf
//	 * @throws Exception 
//	 */
//	public static void insertData(Configuration conf ,String tableName) throws Exception
//	{
//		HTable hTable = new HTable(conf, tableName);
//		
//		Put put = new Put("wanghaisheng".getBytes());
//		put.add("info".getBytes(), "age".getBytes(), "28".getBytes());
//		put.add("info".getBytes(), "name".getBytes(), "wanghaisheng".getBytes());
//		put.add("info".getBytes(), "sex".getBytes(), "male".getBytes());
//		
//		hTable.put(put);
//		hTable.close();
//	}
//	
//	/**
//	 * 列的信息
//	 * @param conf
//	 * @throws Exception
//	 */
//	public static void getRow(Configuration conf ,String tableName ,String rowKey) throws Exception
//	{
//		
//		HTable table = new HTable(conf, tableName);
//		Get query = new Get(rowKey.getBytes());
//		Result result = table.get(query);
//		for (KeyValue kv : result.raw()) {
//			//列族-列名-列值
//			System.out.format("COLUMN\t %S:%s\t%s\n",new String(kv.getFamily()),new String(kv.getQualifier()),new String(kv.getValue()));
//		}
//		table.close();
//	}
//	
//	/**
//	 * 创建表
//	 * @param conf
//	 * @throws Exception
//	 */
//	public static void createTable(Configuration conf ,String tableName) throws Exception
//	{
//		HBaseAdmin admin = new HBaseAdmin(conf);
//		if(admin.tableExists(tableName))
//		{
//			System.out.println("table " + tableName + " is exists ,delete it.");
//			dropTable(conf, tableName);
//		}
//		
//		HTableDescriptor htd = new HTableDescriptor(tableName);
//		HColumnDescriptor hcd_info = new HColumnDescriptor("info");
//		HColumnDescriptor hcd_data = new HColumnDescriptor("data");
//		htd.addFamily(hcd_info);
//		htd.addFamily(hcd_data);
//		admin.createTable(htd);
//		admin.close();
//	}
//}
