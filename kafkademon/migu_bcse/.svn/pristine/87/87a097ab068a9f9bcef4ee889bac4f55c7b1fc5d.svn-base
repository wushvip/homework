package com.chinamobile.cmss.bcse.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogSearchCostTimeBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.UserAppBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.SearchCostDao;
import com.chinamobile.cmss.bcse.datastatistics.service.ISearchCostService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;



/**
 * @ClassName: ISearchCostServiceImpl
 * @Description: 搜索耗时
 * @author: lijingjing
 * @date: 2016年2月18日 上午10:25:54
 */
@Service("searchCostService")
public class ISearchCostServiceImpl implements ISearchCostService {

	public static final int YESTERDAY = -1;

	@Resource
	private SearchCostDao searchCostDao;

	/**
	 * 
	 * @Title: showSearchCostByDay
	 * @Description: 获取应用以天为单位的搜索耗时数据量
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2016年5月26日
	 */
	public void showSearchCostByDay(LogReqBean logReqBean, ResultBean resultBean) {
		
		ArrayList<UserAppBean> userList = logReqBean.getUserList();
		JSONArray jsonArray = new JSONArray();
		if (userList!=null && !userList.isEmpty()) {
			for (UserAppBean userAppBean : userList) {
				JSONObject dataJson = new JSONObject();
				logReqBean.setAppId(userAppBean.getAppId());
				logReqBean.setUserId(userAppBean.getUserId());
				ArrayList<LogSearchCostTimeBean> logSearchCostPerDayList = null;
				HashMap<String, String> infoParam = new HashMap<String, String>();
				if(logReqBean.getCt()!=null || logReqBean.getBookType()!=null){
					logReqBean.setUserId(logReqBean.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
				}
				infoParam.put("USER_ID", logReqBean.getUserId());
				infoParam.put("APP_ID", logReqBean.getAppId());
				infoParam.put("OPER_DATE_START", Tool.getDayTime(logReqBean.getStartDate()));
				infoParam.put("OPER_DATE_END", Tool.getDayTime(logReqBean.getEndDate() + 1));
				try {
					logSearchCostPerDayList = searchCostDao.selectSearchCostPerDay(infoParam);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}
				// 日期映射
				LinkedHashMap<String, String> perDaySearchCostHash = new LinkedHashMap<String, String>();
				if (logSearchCostPerDayList != null && logSearchCostPerDayList.size() > 0) {
					for (LogSearchCostTimeBean logMaxSearchCostBean : logSearchCostPerDayList)
						if (logMaxSearchCostBean != null && logMaxSearchCostBean.getOper_date() != null) {
							perDaySearchCostHash.put(logMaxSearchCostBean.getOper_date(),
									String.valueOf(logMaxSearchCostBean.getMax_cost()) + ","
											+ String.valueOf(logMaxSearchCostBean.getMin_cost()) + ","
											+ String.valueOf(logMaxSearchCostBean.getAvg_cost()));
						}
				}
				// 填充空缺的日期
				try {
					perDaySearchCostHash = Tool.getWholeDateData(perDaySearchCostHash, logReqBean.getStartDate(),
							logReqBean.getEndDate());
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}

				dataJson.put("resultMap", perDaySearchCostHash);
				dataJson.put("userName", userAppBean.getUserName());
				dataJson.put("appName", userAppBean.getAppName());

				jsonArray.add(dataJson);
			}
		} else {
			Tool.serviceException(resultBean, MsgConfig.USERLIST_NOTEMPTY);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("data", jsonArray);
		resultBean.setResult(resultJson);

	}

	/**
	 * 
	 * @Title: showSearchCostByHour
	 * @Description: 获取应用以小时为单位的搜索耗时数据量
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2016年5月26日
	 */
	public void showSearchCostByHour(LogReqBean logReqBean, ResultBean resultBean) {
		
		ArrayList<UserAppBean> userList = logReqBean.getUserList();
		JSONArray jsonArray = new JSONArray();
		if (userList!=null && !userList.isEmpty()) {
			for (UserAppBean userAppBean : userList) {
				JSONObject dataJson = new JSONObject();
				logReqBean.setAppId(userAppBean.getAppId());
				logReqBean.setUserId(userAppBean.getUserId());
				ArrayList<LogSearchCostTimeBean> logMaxSearchCostBeanList = null;
				HashMap<String, String> infoParam = new HashMap<String, String>();
				if(logReqBean.getCt()!=null || logReqBean.getBookType()!=null){
					logReqBean.setUserId(logReqBean.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
				}
				infoParam.put("USER_ID", logReqBean.getUserId());
				infoParam.put("APP_ID", logReqBean.getAppId());
				infoParam.put("OPER_DATE", Tool.getDayTime(YESTERDAY));
				try {
					logMaxSearchCostBeanList = searchCostDao.selectSearchCostPerHour(infoParam);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}

				// 日期映射
				LinkedHashMap<String, String> perHourSearchCostHash = new LinkedHashMap<String, String>();
				if (logMaxSearchCostBeanList != null && logMaxSearchCostBeanList.size() > 0) {
					for (LogSearchCostTimeBean logMaxSearchCostBean : logMaxSearchCostBeanList)
						if (logMaxSearchCostBean != null && logMaxSearchCostBean.getOper_hour() >= 0) {
							perHourSearchCostHash.put(String.valueOf(logMaxSearchCostBean.getOper_hour()),
									String.valueOf(logMaxSearchCostBean.getMax_cost()) + ","
											+ String.valueOf(logMaxSearchCostBean.getMin_cost()) + ","
											+ String.valueOf(logMaxSearchCostBean.getAvg_cost()));
						}
				}
				// 填充空缺的日期
				try {
					perHourSearchCostHash = Tool.getWholeHourData(perHourSearchCostHash);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}

				dataJson.put("resultMap", perHourSearchCostHash);
				dataJson.put("userName", userAppBean.getUserName());
				dataJson.put("appName", userAppBean.getAppName());

				jsonArray.add(dataJson);
			}
		} else {
			Tool.serviceException(resultBean, MsgConfig.USERLIST_NOTEMPTY);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("data", jsonArray);
		resultBean.setResult(resultJson);

	}

}
