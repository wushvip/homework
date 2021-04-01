package com.chinamobile.cmss.bcse.sdk.tools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;


public class LogUtil {
	private static Logger logger = Logger.getLogger(LogUtil.class);

/*	public LogUtil() {
		errorLogService = new IErrorLogServiceImpl();
	}*/

	// 项目保存的log目录
	// public static final String LOG_FOLDER_NAME = Config.LOG_FOLDER_NAME;

	public static final String ANALY_LOG = "BCSEAnalysis";
	public static final String CLIENT_LOG = "BCSEClient";
	public static final String CORRECT_LOG = "BCSECorrect";
	public static final String HEATW_LOG = "BCSEHeatWord";
	public static final String INDEX_LOG = "BCSEIndex";
	public static final String SEARCH_LOG = "BCSESearch";
	public static final String SUGGEST_LOG = "BCSESuggest";
	public static final String CONFIG_LOG = "BCSEConfig";
	
	public static String LOG_TIME;


	/**
	 * log记录的入口(运行关键信息)
	 * 
	 * @param message
	 */
	public static void loggerEntrance(String logType, String message) {
		// getLogger().info(getPrefixInfo(logType) + message + "\n");
		logger.info(getPrefixInfo(logType) + message);
	}


	/**
	 * @param e
	 * 
	 *            打印异常的堆栈信息
	 */
	public static String errorStack(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		return sw.toString();
	}

	/**
	 * 获取log类型前缀信息
	 * 
	 * @param logType
	 * @return
	 */
	public static String getPrefixInfo(String logType) {
		String preFix = "";
		if (CLIENT_LOG.equals(logType))
			preFix = "BCSEClient module log（SDK客户端模块日志）===";
		else if (CORRECT_LOG.equals(logType))
			preFix = "BCSECorrect module log（SDK糾錯模块日志）===";
		else if (HEATW_LOG.equals(logType))
			preFix = "BCSEHeatWord module log（SDK热词模块日志）===";
		else if (INDEX_LOG.equals(logType))
			preFix = "BCSEIndex module log（SDK索引模块日志）===";
		else if (SEARCH_LOG.equals(logType))
			preFix = "BCSESearch module log（SDK搜索模块日志）===";
		else if (SUGGEST_LOG.equals(logType))
			preFix = "BCSESuggest module log（SDK智能联想模块日志）===";
		else if (ANALY_LOG.equals(logType))
			preFix = "BCSEAnalysis module log（SDK分词模块日志）===";
		return preFix;
	}

	

	/**
	 * 获取标准化时间(年月日 时分秒)
	 * 
	 * @return
	 */
	public static String getFullTime() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * 获取标准化时间(年月日 )
	 * 
	 * @return
	 */
	public static String getDateTime() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
