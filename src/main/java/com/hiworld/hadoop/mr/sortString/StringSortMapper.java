package com.hiworld.hadoop.mr.sortString;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class StringSortMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> outputCollector, Reporter arg3) throws IOException {
		outputCollector.collect(value, value);
	}
}
