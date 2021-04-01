package com.chinamobile.cmss.bcse.tool.tools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.datastatistics.bean.ErrorLogBean;
import com.chinamobile.cmss.bcse.datastatistics.service.IErrorLogService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;


/**
 * Log记录类
 * 
 * @author QuanBing
 * 
 */
public class LogUtil {

	private static Logger logger = Logger.getLogger(LogUtil.class);
	private IErrorLogService errorLogService=(IErrorLogService) Config.ac.getBean("errorLogService");

	//private IErrorLogService errorLogService = new IErrorLogServiceImpl();
/*	public LogUtil() {
		errorLogService = new IErrorLogServiceImpl();
	}*/

	// 项目保存的log目录
	// public static final String LOG_FOLDER_NAME = Config.LOG_FOLDER_NAME;

	public static final String SEARCH_LOG = "searchLog";
	public static final String INDEX_LOG = "indexLog";
	public static final String WEB_LOG = "webLog";
	public static final String APP_LOG = "appLog";
	public static final String EVALUATE_LOG = "evaluateLog";
	public static final String STATISTICS_LOG = "statisticsLog";
	public static final String CONFIG_LOG = "configLog";
	
	public static String LOG_TIME;

	public static void main(String[] args) {
//		loggerEntrance(INDEX_LOG, "index_log test");
//		loggerEntrance(WEB_LOG, "web log test");
//		loggerEntrance(SEARCH_LOG, "search log test");
		loggerEntrance("1", "101", "100", WEB_LOG, new Exception("ss"));
	}

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
	 * log记录的入口（exception）
	 * 
	 * @param message
	 */
	/** 
	 * @Title: loggerEntrance 
	 * @Description: TODO
	 * @param userId
	 * @param appId
	 * @param errorCode
	 * @param logType
	 * @param e
	 * @return: void
	 */
	public static void loggerEntrance(String userId, String appId, String errorCode, String logType, Exception e) {
		try {
			logger.error(getPrefixInfo(logType) + errorStack(e));
			new LogUtil().writeErrorLog(userId, appId, errorCode, logType, ExceptionConstants.map.get(errorCode));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	public static void loggerEntrance(String userId, String appId, String errorCode, String logType, Exception e,
			String message) {
		try {
			logger.error(getPrefixInfo(logType) + errorStack(e));
			new LogUtil().writeErrorLog(userId, appId, errorCode, logType, message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
		if (SEARCH_LOG.equals(logType))
			preFix = "search module log（大云检索模块日志）===";
		else if (INDEX_LOG.equals(logType))
			preFix = "index module log（大云索引模块日志）===";
		else if (WEB_LOG.equals(logType))
			preFix = "web module log（大云WEB平台日志）===";
		else if (EVALUATE_LOG.equals(logType))
			preFix = "evaluate module log（大云搜索评测模块日志）===";
		else if (STATISTICS_LOG.equals(logType))
			preFix = "evaluate module log（大云搜索数据分析模块日志）===";
		else if (APP_LOG.equals(logType))
			preFix = "app module log（大云搜索应用模块日志）===";
		else if (CONFIG_LOG.equals(logType))
			preFix = "config module log（大云高级配置模块日志）===";
		return preFix;
	}

	/**
	 * 将错误信息写入数据库
	 */
	public  void writeErrorLog(String userId, String appId, String errorCode, String module, String errorDetails)
			throws Exception {
		ErrorLogBean errorLogBean = new ErrorLogBean();
		errorLogBean.setUserId(userId);
		errorLogBean.setAppId(appId);
		errorLogBean.setCreateDate(getFullTime());
		errorLogBean.setModule(module);
		errorLogBean.setCode(Integer.parseInt(errorCode));
		errorLogBean.setDetail(errorDetails);
		errorLogBean.setFlag(0);
		//new IErrorLogServiceImpl().insertErrorLogs(errorLogBean);
		errorLogService.insertErrorLogs(errorLogBean);
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
