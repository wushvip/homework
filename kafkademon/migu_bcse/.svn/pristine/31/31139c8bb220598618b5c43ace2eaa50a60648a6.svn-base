package com.chinamobile.cmss.bcselogAnalyse.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql.HdfsToMysql;

/** 
 * @ClassName: Tools 
 * @Description: 工具类
 * @author: yangjing
 * @date: 2016年5月30日 下午5:08:18  
 */
public class Tools {
	
	private static Logger logger = Logger.getLogger(HdfsToMysql.class);

	/** 
	 * @Title: getYesterday 
	 * @Description: 获得昨天的日期
	 * @return
	 * @return: String
	 */
	public static String getYesterday() {
		Date now = new Date(); 
		Date yesterday = new Date();
		Calendar cal = Calendar.getInstance(); // 得到日历
		cal.setTime(now);// 把当前时间赋给日历
		cal.add(Calendar.DAY_OF_MONTH, -1); 
		yesterday = cal.getTime(); // 得到前一天的时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String date = dateFormat.format(yesterday);
		return date;
	}
	
	
	public static String getTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");
		String jobRunTime = dateFormat.format(Calendar.getInstance().getTime());
		return jobRunTime;
	}
	
	
	
	/** 
	 * @Title: arrayToString 
	 * @Description: 数组转成字符串
	 * @param array
	 * @return
	 * @return: String
	 */
	public static String arrayToString(String[] array){
		String str = "";
		for(String s:array){
			str = str + s + " ";		
		}
		str = str.substring(0, str.length()-1);
		return str;
	}

	/**
	 * 获取一行日志信息的参数
	 * 
	 * @param logLine
	 * @return
	 */
	public static Map<String, String> getParams(String line) {
		line = line.substring(line.indexOf(Constants.LOG_SIGN_STR) + Constants.LOG_SIGN_STR.length());
		Map<String, String> params = new HashMap<String, String>();
		String[] arr = line.split("&");
		for (int j = 0; j < arr.length; j++) {
			String[] field = null;
			try {
				field = java.net.URLDecoder.decode(arr[j].trim(), "UTF-8").split("=");
				if (field.length == 2) {
					params.put(field[0], field[1]);
				}
			} catch (Exception e) {
				logger.error("异常日志数据:" + line + "/n[" + arr[j].trim() + "] urldecode error!");
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		if (params.containsKey(Constants.PARAM_OPER_TIME)) {
			String value = params.get(Constants.PARAM_OPER_TIME);
			params.put(Constants.PARAM_OPER_DATE, value.substring(0, value.indexOf(" ")));
			params.put(Constants.PARAM_OPER_HOUR, value.substring(value.indexOf(" ") + 1, value.indexOf(":")));
		}
		if (!params.containsKey(Constants.PARAM_FLAG) || !StringUtils.equals("0", params.get(Constants.PARAM_FLAG))) {
			params.put(Constants.PARAM_FLAG, "1");
		}
		return params;
	}

	public static boolean isNotBlank(String... params) {
		boolean result = true;
		for (String param : params) {
			if (StringUtils.isBlank(param)) {
				result = false;
				break;
			}
		}
		return result;
	}

}
