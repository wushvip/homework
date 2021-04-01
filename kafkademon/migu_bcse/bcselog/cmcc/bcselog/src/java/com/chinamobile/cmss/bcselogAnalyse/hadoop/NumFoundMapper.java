package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.chinamobile.cmss.bcselogAnalyse.tools.Constants;
import com.chinamobile.cmss.bcselogAnalyse.tools.Tools;


public class NumFoundMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text key = new Text();
	private IntWritable value = new IntWritable();

	@Override
	public void map(LongWritable row, Text content, Context context) throws IOException, InterruptedException {
		String line = content.toString();
		if (line.contains(Constants.LOG_SIGN_STR)) {
			Map<String, String> params = Tools.getParams(line);
			if (Tools.isNotBlank(params.get(Constants.PARAM_USER_ID), params.get(Constants.PARAM_APP_ID), params.get(Constants.PARAM_OPER_DATE),
					params.get(Constants.PARAM_NUM_FOUND))
					&& params.get(Constants.PARAM_NUM_FOUND).matches(Constants.REGEX_NUM)) {
				StringBuffer keySB = new StringBuffer();
				keySB.append(params.get(Constants.PARAM_USER_ID) + Constants.WHITE_SPACE);
				keySB.append(params.get(Constants.PARAM_APP_ID) + Constants.WHITE_SPACE);
				keySB.append(params.get(Constants.PARAM_OPER_DATE));
				//keySB.append(params.get(Constants.PARAM_FLAG));
				key.set(keySB.toString());

				value.set(Integer.parseInt(params.get(Constants.PARAM_NUM_FOUND)));
				context.write(key, value);
			}
		}
	}

	public static void main(String[] args) {
		String regex = "^[0-9]+$";
		String a = "1a";
		System.out.println(a.matches(regex));
	}

}
