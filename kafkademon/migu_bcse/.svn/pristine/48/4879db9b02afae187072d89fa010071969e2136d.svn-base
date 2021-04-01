package com.chinamobile.cmss.bcselogAnalyse.hadoop;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


/** 
 * @ClassName: wordReducer 
 * @Description: 对关键词信息进行reduce计算
 * @author: yangjing
 * @date: 2016年2月1日 上午10:05:50  
 */
public class wordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	static final int N=10;
	
	public void reduce(Text key, Iterable<IntWritable> iterable,Context context) 
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable i : iterable) {
			sum += i.get();
		}
		context.write(key, new IntWritable(sum));
	}

}

