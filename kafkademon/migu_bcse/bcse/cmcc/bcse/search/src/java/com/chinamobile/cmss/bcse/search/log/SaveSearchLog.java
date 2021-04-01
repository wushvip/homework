package com.chinamobile.cmss.bcse.search.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;


/** 
 * @ClassName: SaveSearchLog 
 * @Description: TODO  写搜索日志的接口
 * @author: chenmin
 * @date: 2016年1月29日 下午5:39:11  
 */
public class SaveSearchLog {
	private static Logger logger = Logger.getLogger(SaveSearchLog.class);
	public static void main(String[] args) throws Exception {

		/*
		 * String string = "%E4%BF%9D%E5%8D%AB%E8%90%9D%E5%8D%9C"; String
		 * aString = (String) URLDecoder.decode(string, "utf-8");
		 * System.out.print(aString);
		 * 
		 */
		QueryBean queryBean = new QueryBean();
		SearchResultBean searchResultBean = new SearchResultBean();
		queryBean.setUserId("chenmin@china.cmss.cmoble");
		//queryBean.setAppName("testlog");
		queryBean.setSearchQuery("java");
		searchResultBean.setCostTime(123);
		new SaveSearchLog().saveLogInfo(searchResultBean, queryBean);
	}

	/**
	 * 日志保存
	 * 
	 * @param response
	 * @param searchResultBean
	 * @param queryBean
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * 
	 *             http://10.133.5.140:8000/jf.gif?log_type=searchlog&oper_time=
	 *             YY-mm-dd HH:mm：
	 *             ss&keywords=XXXXXXXX&time_cost=20ms&username=test
	 *             &appname=poi&
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void saveLogInfo(SearchResultBean searchResultBean,
			QueryBean queryBean) throws Exception {
		
		String timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		String keywords = queryBean.getSearchQuery();
		long time_cost = searchResultBean.getCostTime();
		int totals = searchResultBean.getTotalItems();
		StringBuffer logInfoBuffer = new StringBuffer();
		String userNameString = queryBean.getUserId();
		String appNameString = queryBean.getAppId();
		logInfoBuffer.append("log_type=" + URLEncoder.encode("searchlog"));
		logInfoBuffer.append("&oper_time=" + URLEncoder.encode(timeNow));
		logInfoBuffer.append("&keywords=" + URLEncoder.encode(keywords));
		logInfoBuffer.append("&totalItems=" + URLEncoder.encode(String.valueOf(totals)));
		logInfoBuffer.append("&time_cost="
				+ URLEncoder.encode(time_cost + "ms"));
		logInfoBuffer.append("&user_id="
				+ URLEncoder.encode(userNameString));
		logInfoBuffer.append("&app_id="
				+ URLEncoder.encode(appNameString));
		logInfoBuffer.append("&flag=" + URLEncoder.encode("1")+ "&");
		saveSearchLog(Config.LOG_ADDRESS, logInfoBuffer.toString());

		
	}
	
	/** 
	 * @Title: saveLogInfo 
	 * @Description: TODO
	 * @param bcseQuery
	 * @throws Exception
	 * @return: void
	 */
	public void saveLogInfo(BCSEQuery bcseQuery)  {
		
		String timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		String keywords = bcseQuery.getSearchQuery();
		long time_cost = bcseQuery.getSearchResult().getCostTime();
		int totals = bcseQuery.getSearchResult().getTotalItems();
		StringBuffer logInfoBuffer = new StringBuffer();
		String userNameString = bcseQuery.getUserId();
		String appNameString = bcseQuery.getAppId();
		logInfoBuffer.append("log_type=" + URLEncoder.encode("searchlog"));
		logInfoBuffer.append("&oper_time=" + URLEncoder.encode(timeNow));
		logInfoBuffer.append("&keywords=" + URLEncoder.encode(keywords));
		logInfoBuffer.append("&totalItems=" + URLEncoder.encode(String.valueOf(totals)));
		logInfoBuffer.append("&time_cost="
				+ URLEncoder.encode(time_cost + "ms"));
		logInfoBuffer.append("&user_id="
				+ URLEncoder.encode(userNameString));
		logInfoBuffer.append("&app_id="
				+ URLEncoder.encode(appNameString));
		
		//写入paramMap
		HashMap<String,Object> map = new HashMap<String,Object>();
		map = bcseQuery.getParamMap();
		if (map != null && map.size()>0) {
			if (map.keySet().contains("log_enable") && "false".equals(String.valueOf(map.get("log_enable"))) ) {
				logger.error("ParamMap contain key=log_enable,value=false,it's a flag that search log cannot be saved.");
				return;
			}
			for (String key : map.keySet()) {

				logInfoBuffer.append("&"+key+"=" + URLEncoder.encode(String.valueOf(map.get(key))));
			}
		}
		logInfoBuffer.append("&flag=" + URLEncoder.encode("1")+ "&");
		saveSearchLog(Config.LOG_ADDRESS, logInfoBuffer.toString());

	}
	
	
	public void saveSearchLog(BCSEQuery bcseQuery)  {
		try {
			StringBuffer logInfoBuffer = new StringBuffer();
			String timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());
			logInfoBuffer.append("log_type=" + URLEncoder.encode("searchlog"));
			logInfoBuffer.append("&oper_time=" + URLEncoder.encode(timeNow));
			//写入paramMap
			HashMap<String,Object> map = new HashMap<String,Object>();
			map = bcseQuery.getLogMap();
			if (map != null && map.size()>0) {
				
				for (String key : map.keySet()) {

					logInfoBuffer.append("&"+key+"=" + URLEncoder.encode(String.valueOf(map.get(key))));
				}
			}
			logInfoBuffer.append("&flag=" + URLEncoder.encode("1")+ "&");
			saveSearchLog(Config.MIGU_LOG_ADDRESS, logInfoBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}


	}

	/**
	 * 写日志
	 * 
	 * @param fileName
	 * @param content
	 * 
	 */
	public void saveSearchLog(final String url, final String param) {
		Runnable r = new Runnable() {
			public void run() {
				try {
					sendGet(url, param);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {

		String result = "";
		BufferedReader in = null;
		URLConnection connection = null;
		try {
			String urlNameString = url + "?" + param;
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, urlNameString);
//			System.out.println(urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,"发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
