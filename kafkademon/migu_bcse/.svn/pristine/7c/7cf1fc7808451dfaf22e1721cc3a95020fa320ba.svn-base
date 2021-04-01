package com.chinamobile.cmss.bcse.datastatistics.tool;

import java.text.ParseException;
import java.util.LinkedHashMap;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

public class DataInfoTool {
	/**
	 * 在返回的数据中，可能存在部分因没有数据而缺失的日期(数据量专用)
	 * 
	 * @return LinkedHashMap<String,String>
	 * @date:2017-3-10下午4:53:29
	 * @author:zhuxiang
	 * @throws ParseException
	 */
	public static LinkedHashMap<String, String> getWholeDateDataForDataInfo(
			LinkedHashMap<String, String> resultMap, int start, int end ,Integer first)
			throws ParseException {
		
		LinkedHashMap<String, String> sortMap = new LinkedHashMap<String, String>();
		String initDate = Tool.getDayTime(start);
		String initSize = first.toString();
		for (int i = 0; i < end - start + 1; i++) {

			if (resultMap.containsKey(initDate)) {

				sortMap.put(initDate, resultMap.get(initDate));
				initSize = resultMap.get(initDate);

			} else {
				sortMap.put(initDate, initSize);
			}
			initDate = Tool.getDateTime(initDate, 1);
		}
		return sortMap;
	}

}
