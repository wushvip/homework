package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountSecondReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, Iterable<IntWritable> iterable, Context context)
			throws IOException, InterruptedException {

		int count = 0;
		for (IntWritable i : iterable) {
			count++;
		}
		context.write(key, new IntWritable(count));
	}

}
