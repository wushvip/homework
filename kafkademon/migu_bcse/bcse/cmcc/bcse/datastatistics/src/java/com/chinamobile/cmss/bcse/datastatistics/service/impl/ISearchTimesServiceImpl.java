package com.chinamobile.cmss.bcse.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogMaxSearchTimesBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogSearchTimesBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.UserAppBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.SearchTimesDao;
import com.chinamobile.cmss.bcse.datastatistics.service.ISearchTimesService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;



/**
 * @ClassName: ISearchTimesServiceImpl
 * @Description: 搜索次数
 * @author: lijingjing
 * @date: 2016年2月18日 上午10:26:15
 */
@Service("searchTimesService")
public class ISearchTimesServiceImpl implements ISearchTimesService {

	public static final int YESTERDAY = -1;

	@Resource
	private SearchTimesDao searchTimesDao;

	/**
	 * 
	 * @Title: showSearchTimesByDay
	 * @Description: 获取应用以天为单位的搜索次数数据量
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2016年5月26日
	 */
	public void showSearchTimesByDay(LogReqBean logReqBean,ResultBean resultBean) {
		
		ArrayList<UserAppBean> userList = logReqBean.getUserList();
		JSONArray jsonArray = new JSONArray();
		if (userList!=null && !userList.isEmpty()) {
			for (UserAppBean userAppBean : userList) {
				JSONObject dataJson = new JSONObject();
				logReqBean.setAppId(userAppBean.getAppId());
				logReqBean.setUserId(userAppBean.getUserId());
				ArrayList<LogSearchTimesBean> logSearchTimesPerDayList = null;
				HashMap<String, String> infoParam = new HashMap<String, String>();
				
				if(logReqBean.getCt()!=null || logReqBean.getBookType()!=null){
					logReqBean.setUserId(logReqBean.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
				}
				
				infoParam.put("USER_ID", logReqBean.getUserId());
				infoParam.put("APP_ID", logReqBean.getAppId());
				infoParam.put("OPER_DATE_START", Tool.getDayTime(logReqBean.getStartDate()));
				infoParam.put("OPER_DATE_END", Tool.getDayTime(logReqBean.getEndDate() + 1));
				try {
					logSearchTimesPerDayList = searchTimesDao.selectSearchTimesAsDay(infoParam);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}
				// 日期映射
				LinkedHashMap<String, String> perDaySearchTimes = new LinkedHashMap<String, String>();
				if (logSearchTimesPerDayList != null && logSearchTimesPerDayList.size() > 0) {
					for (LogSearchTimesBean logSearchTimesPerDayBean : logSearchTimesPerDayList)
						if (logSearchTimesPerDayBean != null && logSearchTimesPerDayBean.getSearch_count() > 0)
							perDaySearchTimes.put(logSearchTimesPerDayBean.getOper_date(),
									String.valueOf(logSearchTimesPerDayBean.getSearch_count()));
				}
				try {
					// 填充空缺的日期
					perDaySearchTimes = Tool.getWholeDateData(perDaySearchTimes, logReqBean.getStartDate(),
							logReqBean.getEndDate());
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
					
				}
				
				dataJson.put("resultMap", perDaySearchTimes);
				dataJson.put("userName", userAppBean.getUserName());
				dataJson.put("appName", userAppBean.getAppName());
				
				jsonArray.add(dataJson);
			}
		}else{
			Tool.serviceException(resultBean, MsgConfig.USERLIST_NOTEMPTY);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("data", jsonArray);
		resultBean.setResult(resultJson);

	}

	/**
	 * 
	 * @Title: showSearchTimesByHour
	 * @Description: 获取应用以小时为单位的搜索次数数据量
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2016年5月26日
	 */
	public void showSearchTimesByHour(LogReqBean logReqBean,ResultBean resultBean) {
		
		ArrayList<UserAppBean> userList = logReqBean.getUserList();
		JSONArray jsonArray = new JSONArray();
		if (userList!=null && !userList.isEmpty()) {
			for (UserAppBean userAppBean : userList) {
				JSONObject dataJson = new JSONObject();
				logReqBean.setAppId(userAppBean.getAppId());
				logReqBean.setUserId(userAppBean.getUserId());
				ArrayList<LogMaxSearchTimesBean> logMaxSearchTimesBeanList = null;

				HashMap<String, String> infoParam = new HashMap<String, String>();
				
				if(logReqBean.getCt()!=null || logReqBean.getBookType()!=null){
					logReqBean.setUserId(logReqBean.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
				}
				
				infoParam.put("USER_ID", logReqBean.getUserId());
				infoParam.put("APP_ID", logReqBean.getAppId());
				infoParam.put("OPER_DATE", Tool.getDayTime(YESTERDAY));
				try {
					logMaxSearchTimesBeanList = searchTimesDao.selectSearchTimesPerHour(infoParam);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}
				LinkedHashMap<String, String> perHourSearchTimes = new LinkedHashMap<String, String>();
				if (logMaxSearchTimesBeanList != null && logMaxSearchTimesBeanList.size() > 0) {
					for (LogMaxSearchTimesBean logMaxSearchTimesBean : logMaxSearchTimesBeanList)
						if (logMaxSearchTimesBean.getSearch_count() > 0 && logMaxSearchTimesBean.getOper_hour() >= 0) {
							System.out.println(logMaxSearchTimesBean.getOper_hour());
							perHourSearchTimes.put(String.valueOf(logMaxSearchTimesBean.getOper_hour()),
									String.valueOf(logMaxSearchTimesBean.getSearch_count()));
						}
				}
				try {
					// 填充空缺的时间
					perHourSearchTimes = Tool.getWholeHourData(perHourSearchTimes);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);	
				}
				dataJson.put("resultMap", perHourSearchTimes);
				dataJson.put("userName", userAppBean.getUserName());
				dataJson.put("appName", userAppBean.getAppName());
				jsonArray.add(dataJson);
			}
		}else{
			Tool.serviceException(resultBean, MsgConfig.USERLIST_NOTEMPTY);
		}
		
		JSONObject resultJson = new JSONObject();
		resultJson.put("data", jsonArray);
		resultBean.setResult(resultJson);


	}


	/**
	 * 
	 * @Title: yesterdaySearchTimes
	 * @Description: 获取昨日搜索次数数据
	 * @param userId
	 * @param appId
	 * @return 
	 * @Date:2016年5月30日
	 */
	public int yesterdaySearchTimes(String userId, String appId) {
		int yesterdaySearchTimes = 0;
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", userId);
		infoParam.put("APP_ID", appId);
		infoParam.put("OPER_DATE", Tool.getDayTime(YESTERDAY));
		

		LogSearchTimesBean logSearchTimesBean = null;
		try {
			logSearchTimesBean = searchTimesDao.selectSearchTimes(infoParam);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		if (logSearchTimesBean != null){
			yesterdaySearchTimes = logSearchTimesBean.getSearch_count();
		}
			return yesterdaySearchTimes;
	}

	/**
	 * 
	 * @Title: maxSearchTimes
	 * @Description: 获取最大搜索次数
	 * @param userId
	 * @param appId
	 * @return
	 * @throws Exception 
	 * @Date:2016年5月30日
	 */
	public int maxSearchTimes(String userId, String appId) {
		
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", userId);
		infoParam.put("APP_ID", appId);
		infoParam.put("OPER_DATE_START", Tool.getDayTime(-1) + " 00:00:00");
		infoParam.put("OPER_DATE_END", Tool.getDayTime(0) + " 00:00:00");
		LogMaxSearchTimesBean logMaxSearchTimesBean =null;
		try {
			logMaxSearchTimesBean = searchTimesDao.selectMaxSearchTimes(infoParam);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		int maxSearchTimes = 0;
		if (logMaxSearchTimesBean != null){
			maxSearchTimes = logMaxSearchTimesBean.getSearch_count();
		}
		
		return maxSearchTimes;
	}
}
