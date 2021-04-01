package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @ClassName: countReducer
 * @Description: 对搜索次数信息进行reduce计算
 * @author: yangjing
 * @date: 2016年2月1日 上午10:01:42
 */
public class countReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text key, Iterable<IntWritable> iterable, Context context)
			throws IOException, InterruptedException {

		int count = 0;
		for (IntWritable i : iterable) {
			count++;
		}
		context.write(key, new IntWritable(count));
	}

}
