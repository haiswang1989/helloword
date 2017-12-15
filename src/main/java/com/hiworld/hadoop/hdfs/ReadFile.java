package com.hiworld.hadoop.hdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 读取文件
 * @author haiswang
 *
 */
public class ReadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Configuration config = new Configuration();
		
		FileSystem fileSystem = null;
		FSDataInputStream dis = null;
		BufferedReader br = null;
		try {
			fileSystem = FileSystem.get(config);
			
			Path filePath = new Path("hdfs://10.249.73.142:9000/data/bigdata/test-001.nb");
			dis = fileSystem.open(filePath);
			br = new BufferedReader(new InputStreamReader(dis));
			
			String tmpLine = null;
			while(null!=(tmpLine=br.readLine()))
			{
				System.out.println(tmpLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null!=br)
			{
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fileSystem!=null)
			{
				try {
					fileSystem.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("over...");
	}
}
