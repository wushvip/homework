package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @ClassName: wordMapper
 * @Description: 解析日志文件，提取关键词信息，进行mapper计算
 * @author: yangjing
 * @date: 2016年2月1日 上午10:05:17
 */
public class wordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
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
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
			if (fields.containsKey("keywords")) {
				// 把关键词中的空格和tab替换成“&”
				String kws = fields.get("keywords").replace(" ", "&").replace("	", "&");
				String sk = fields.get("oper_time").substring(0, 10) + " " + kws + " " + fields.get("user_id") + " "
						+ fields.get("app_id") + " " + fields.get("flag");
				word.set(sk);
				context.write(word, one);
			}

		}

	}

}
