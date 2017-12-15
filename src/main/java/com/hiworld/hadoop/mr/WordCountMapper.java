package com.hiworld.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * 统计单词个数的Mapper
 * @author haiswang
 *
 */
public class WordCountMapper extends MapReduceBase implements org.apache.hadoop.mapred.Mapper<LongWritable, Text, Text, IntWritable>{

		public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter)
				throws IOException {
			
			for (String v : value.toString().split("\t")) {
				outputCollector.collect(new Text(v), new IntWritable(1));
			}
			
		}
}
