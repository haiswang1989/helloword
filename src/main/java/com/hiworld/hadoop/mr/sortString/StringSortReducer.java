package com.hiworld.hadoop.mr.sortString;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class StringSortReducer extends MapReduceBase implements Reducer<Text, Text, Text, IntWritable> {
	
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
		
		while(values.hasNext())
		{
			values.next();
			outputCollector.collect(key, new IntWritable(1));
		}
	}
}
