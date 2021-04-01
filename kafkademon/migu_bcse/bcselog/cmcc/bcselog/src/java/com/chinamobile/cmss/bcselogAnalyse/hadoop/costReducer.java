package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @ClassName: costReducer
 * @Description: 对搜索耗时信息进行reduce计算
 * @author: yangjing
 * @date: 2016年2月1日 上午9:59:17
 */
public class costReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text key, Iterable<IntWritable> iterable, Context context)
			throws IOException, InterruptedException {

		BigDecimal sum = new BigDecimal(0);
		int times = 0;
		long max = 0;
		long min = Integer.MAX_VALUE;
		for (IntWritable i : iterable) {
			if (i.get() < min) {
				min = i.get();
			}

			if (i.get() > max) {
				max = i.get();
			}

			sum=sum.add(new BigDecimal(i.get()));
			times++;
		}
		
		
		int AVG_TIME = sum.divide(new BigDecimal(times),10,RoundingMode.HALF_UP).intValue();
		// 计算每个小时平均的搜索耗时
		key = new Text(key.toString() + " " + min + " " + max);
		System.out.println("sum is: "+sum+"  times is: "+times+" AVG_TIME is: "+AVG_TIME);
		context.write(key, new IntWritable(AVG_TIME));
	}
}
