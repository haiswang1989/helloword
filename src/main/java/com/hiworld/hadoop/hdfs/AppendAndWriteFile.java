package com.hiworld.hadoop.hdfs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 追加写入一个文件
 * @author haiswang
 *
 */
public class AppendAndWriteFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Configuration config = new Configuration();
		
		Path filePath = new Path("hdfs://10.249.73.145:9000/data/bigdata/test-001.nb");
		
		FileSystem fileSystem = null;
		FSDataOutputStream dos = null;
		BufferedWriter br = null;
		
		try {
			fileSystem = FileSystem.get(config);
			//append方法是打开文件的输入输出流,追加着写
			//如果文件以及存在,那么追加写
			//如果文件不存在,抛出FileNotFoundException
			dos = fileSystem.append(filePath);
			br = new BufferedWriter(new OutputStreamWriter(dos));
			br.write("追加者	男	50\n");
			br.flush();
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
			
			if(null!=fileSystem)
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
