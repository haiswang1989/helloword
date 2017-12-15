package com.hiworld.hadoop.mr;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * 统计单词个数的reducer
 * @author haiswang
 *
 */
public class WordCountReducer extends MapReduceBase implements org.apache.hadoop.mapred.Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text text, Iterator<IntWritable> iter,
			OutputCollector<Text, IntWritable> outputCollector, Reporter reporter)
			throws IOException {
		
		int sum = 0;
		
		while(iter.hasNext())
		{
			sum += iter.next().get();
		}
		
		outputCollector.collect(text, new IntWritable(sum));
	}
	
}
