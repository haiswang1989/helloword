package com.hiworld.hadoop.hdfs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 创建一个文件并且写入数据
 * @author haiswang
 *
 */
public class CreateAndWriteNewFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Configuration config = new Configuration();
		
		FileSystem fileSystem = null;
		
		try {
			fileSystem = FileSystem.get(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Path createFile = new Path("hdfs://10.249.73.145:9000/data/bigdata/test-002.nb");
		FSDataOutputStream dos = null;
		BufferedWriter bw = null;
		try {
			//create方法是打开文件的输入输出流,从头开始写
			//如果文件以及存在,那么覆盖写
			//如果文件不存在,那么创建
			dos = fileSystem.create(createFile);
			bw = new BufferedWriter(new OutputStreamWriter(dos));
			bw.write("王海胜	男	28\n");
			bw.write("王海明	男	31\n");
			bw.write("王海飞	男	33\n");
			bw.write("王海达	男	36\n");
			bw.write("王海云	男	35\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null!=dos)
			{
				try {
					dos.close();
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
