package com.chinamobile.cmss.bcse.sdk.index.service;

import java.util.HashMap;

import com.chinamobile.cmss.bcse.sdk.constant.Constant;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.index.bean.WriteLogControllerBean;

/**
 * Log记录类
 * 
 * @author QuanBing
 * 
 */
public class LogApi {

	public static final String SEARCH_LOG = "searchLog";
	public static final String INDEX_LOG = "indexLog";
	public static final String WEB_LOG = "webLog";

	/**
	 * log记录的入口(运行关键信息)
	 * 
	 * @param message
	 */
	public static void loggerEntrance(String logType, String message,
			BCSEClient client) {

		try {

			HashMap<String, Object> opts = new HashMap<String, Object>();
			opts.put("logType", logType);
			opts.put("message", message);
			opts.put("opType", "RUN_LOG");

			client.call(Constant.LOG_POST_URL, opts, Constant.POST);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * log记录的入口（exception）
	 * 
	 * @param message
	 */
	public static void loggerEntrance(String errorCode, String logType,
			Exception e, BCSEClient client) {
		try {

			HashMap<String, Object> opts = new HashMap<String, Object>();
			opts.put("logType", logType);
			opts.put("errorCode", errorCode);
			opts.put("opType", "ERROR_LOG");
			opts.put("e", e);

			client.call(Constant.LOG_POST_URL, opts, Constant.POST);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
