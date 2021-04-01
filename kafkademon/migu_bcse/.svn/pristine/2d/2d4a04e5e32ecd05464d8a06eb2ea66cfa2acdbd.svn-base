package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * @ClassName: JobRun
 * @Description: Map Reduce主函数类
 * @author: yangjing
 * @date: 2016年2月1日 上午10:02:27
 */
public class JobRun {

	public static void Process(String[] args) throws Exception {
		if (null == args || args.length != 4) {
			System.out.println(
					"输入参数错误，运行hadoop任务请输入:sudo -uhdfs hadoop jar logAnalyse.jar hadoop {keywords|count|cost} 输入目录 输出目录 ");
			return;
		}
		cost(args[2], args[3]);
		count(args[2], args[3]);
		keyWord(args[2], args[3]);
		secondCount(args[2], args[3]);
		
		numFound(args[2], args[3]);

		// System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	/**
	 * 搜索耗时
	 * 
	 * @param path1
	 * @param path2
	 * @throws Exception
	 */
	private static void cost(String path1, String path2) throws Exception {
		// MapReduce通用配置
		Configuration conf = new Configuration();
		conf.set("mapreduce.job.jar", "bcselog.jar");
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(JobRun.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 设置reduce任务个数
		job.setNumReduceTasks(1);
		job.setJobName("searchCost");
		job.setMapperClass(costMapper.class);
		job.setReducerClass(costReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		// mapreduce输入数据所在目录或者文件
		FileInputFormat.addInputPath(job, new Path(path1));

		// mapreduce输出数据的目录或者文件
		FileOutputFormat.setOutputPath(job, new Path(path2 + "/cost"));

		job.waitForCompletion(true);
	}

	private static void secondCount(String path1, String path2) throws Exception {

		// MapReduce通用配置
		Configuration conf = new Configuration();
		conf.set("mapreduce.job.jar", "bcselog.jar");
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(JobRun.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 设置reduce任务个数
		job.setNumReduceTasks(1);
		job.setJobName("secondCount");
		job.setMapperClass(CountSecondMapper.class);
		job.setReducerClass(CountSecondReduce.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		// mapreduce输入数据所在目录或者文件
		FileInputFormat.addInputPath(job, new Path(path1));

		// mapreduce输出数据的目录或者文件
		FileOutputFormat.setOutputPath(job, new Path(path2 + "/secondCount"));

		job.waitForCompletion(true);

	}

	/**
	 * 搜索次数
	 * 
	 * @param path1
	 * @param path2
	 * @throws Exception
	 */
	private static void count(String path1, String path2) throws Exception {

		// MapReduce通用配置
		Configuration conf = new Configuration();
		conf.set("mapreduce.job.jar", "bcselog.jar");
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(JobRun.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 设置reduce任务个数
		job.setNumReduceTasks(1);

		job.setJobName("searchCount");
		job.setMapperClass(countMapper.class);
		job.setReducerClass(countReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		// mapreduce输入数据所在目录或者文件
		FileInputFormat.addInputPath(job, new Path(path1));

		// mapreduce输出数据的目录或者文件
		FileOutputFormat.setOutputPath(job, new Path(path2 + "/count"));

		job.waitForCompletion(true);

	}

	/**
	 * 当天热词
	 * 
	 * @param path1
	 * @param path2
	 * @throws Exception
	 */
	private static void keyWord(String path1, String path2) throws Exception {

		// MapReduce通用配置
		Configuration conf = new Configuration();
		conf.set("mapreduce.job.jar", "bcselog.jar");
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(JobRun.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 设置reduce任务个数
		job.setNumReduceTasks(1);

		job.setJobName("searchKeywords");
		job.setMapperClass(wordMapper.class);
		job.setReducerClass(wordReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		// mapreduce输入数据所在目录或者文件
		FileInputFormat.addInputPath(job, new Path(path1));
		// mapreduce输出数据的目录或者文件
		FileOutputFormat.setOutputPath(job, new Path(path2 + "/keywords"));
		job.waitForCompletion(true);
	}

	/**
	 * 前一天搜索结果统计
	 * 
	 * @param path1
	 * @param path2
	 * @throws Exception
	 */
	private static void numFound(String path1, String path2) throws Exception {
		// MapReduce通用配置
		Configuration conf = new Configuration();
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(JobRun.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 设置reduce任务个数
		job.setNumReduceTasks(1);
		job.setJobName("flow");
		job.setMapperClass(NumFoundMapper.class);
		job.setReducerClass(NumFoundReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		// mapreduce输入数据所在目录或者文件
		FileInputFormat.addInputPath(job, new Path(path1));
		// mapreduce输出数据的目录或者文件
		FileOutputFormat.setOutputPath(job, new Path(path2 + "/numFound"));
		job.waitForCompletion(true);
	}
}
