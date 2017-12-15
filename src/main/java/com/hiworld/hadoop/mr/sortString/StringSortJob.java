package com.hiworld.hadoop.mr.sortString;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class StringSortJob {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
		JobConf conf = new JobConf();
		
		conf.setJobName("StringSort");
		
		/**
		 * 这两个参数必须设置,不然Job会运行失败
		 */
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		conf.setMapperClass(StringSortMapper.class);
		/**
		 * Combiner类其实就是在Map的输出进入Reducer之前,先针对每个Map的输出进行一次Reducer
		 */
		conf.setCombinerClass(StringSortReducer.class);
		conf.setReducerClass(StringSortReducer.class);
		
		FileInputFormat.setInputPaths(conf, new Path("hdfs://10.249.73.145:9000/data/bigdata/sortinput"));
		/**
		 * 这边设置的输出目录不能已经存在?不然会报错,FileAlreadyExistException?
		 * MR的输出默认不能够被覆盖,所以这边的输出目录必须不存在
		 */
		FileOutputFormat.setOutputPath(conf, new Path("hdfs://10.249.73.145:9000/data/bigdata/sortoutput"));
		
		JobClient.runJob(conf);
		
	}

}
