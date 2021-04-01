package com.chinamobile.cmss.bcselogAnalyse.hadoop;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.chinamobile.cmss.bcselogAnalyse.bean.NumFoundDay;
import com.chinamobile.cmss.bcselogAnalyse.bean.Range;

public class NumFoundReducer extends Reducer<Text, IntWritable, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<IntWritable> valueIterable, Context context) throws IOException, InterruptedException {
		final Map<String, Integer> rangeCountMap = new LinkedHashMap<String, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				for (Range range : NumFoundDay.RANGE_LIST) {
					this.put(range.toString(), 0);
				}
			}
		};
		for (IntWritable v : valueIterable) {
			Range range = NumFoundDay.getRangeByNum(v.get());
			if (range != null && rangeCountMap.containsKey(range.toString())) {
				rangeCountMap.put(range.toString(), rangeCountMap.get(range.toString()) + 1);
			}
		}
		StringBuffer valueSB = new StringBuffer();
		for (Map.Entry<String, Integer> entry : rangeCountMap.entrySet()) {
			valueSB.append(entry.getKey() + "=" + entry.getValue() + " ");
		}
		context.write(key, new Text(valueSB.toString()));
	}

}
