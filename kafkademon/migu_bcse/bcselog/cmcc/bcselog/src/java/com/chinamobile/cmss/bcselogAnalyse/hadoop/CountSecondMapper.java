package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.main.LogAnalyse;

/**
 * 计算每秒请求峰值
 * 
 * @author jinjing
 *
 */
public class CountSecondMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private static Logger logger = Logger.getLogger(LogAnalyse.class);

	/*public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "log_type=searchlog&oper_time=2017-03-24+19%3A29%3A11&keywords=*&time_cost=0ms&user_id=78443beb3836482ca342362ba37cdb3c&app_id=864a8159dc544ab59251c7474c828116&flag=1";
		String[] arr = str.split("&");
		Map<String, String> fields = new HashMap<String, String>();
		for (int j = 0; j < arr.length; j++) {

			String[] field = java.net.URLDecoder.decode(arr[j].trim(), "UTF-8").split("=");
			if (field.length == 2) {
				fields.put(field[0], field[1]);
				System.out.println("time is :" + field[1]);
			}
		}

		if (fields.containsKey("keywords")) {
			String sk = fields.get("oper_time") + " " + fields.get("user_id") + " " + fields.get("app_id") + " "
					+ fields.get("flag");
			System.out.println(sk);
		}
	}*/

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();

		String str = "GET /jf.gif?log_type=searchlog";
		if (line.contains(str)) {
			String[] arr = line.split("&");
			Map<String, String> fields = new HashMap<String, String>();
			for (int j = 0; j < arr.length; j++) {
				String[] field = null;
				try {
					field = java.net.URLDecoder.decode(arr[j].trim(), "UTF-8").split("=");
					if (field.length == 2) {
						fields.put(field[0], field[1]);
						logger.info("time is:" + field[0] + " " + field[1]);
						// System.out.println("time is :" + field[1]);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (fields.containsKey("keywords")) {
				String sk = fields.get("oper_time") + " " + fields.get("user_id") + " " + fields.get("app_id") + " "
						+ fields.get("flag");
				word.set(sk);
				context.write(word, one);
			}

		}

	}

}
