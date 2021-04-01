package com.chinamobile.cmss.bcse.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.chinamobile.cmss.bcse.datastatistics.bean.DataInfoBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.UserAppBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.DataInfoDao;
import com.chinamobile.cmss.bcse.datastatistics.service.IDataInfoService;
import com.chinamobile.cmss.bcse.datastatistics.tool.DataInfoTool;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;



@Service("dataInfoService")
public class IDataInfoServiceImpl implements IDataInfoService {

	public static final int YESTERDAY = -1;
	@Resource
	private DataInfoDao dataInfoDao;

	/**
	 * 
	 * @Title: showDataInfo
	 * @Description: 获取数据量
	 * @param logReqBean
	 * @param resultBean
	 * @Date:2016年5月26日
	 */
	public void showDataInfo(LogReqBean logReqBean, ResultBean resultBean) {
		
		JSONArray jsonArray = new JSONArray();
		ArrayList<UserAppBean> userList = logReqBean.getUserList();
		// 根据用户获取数据量
		if (userList!=null && !userList.isEmpty()) {
			for (UserAppBean userAppBean : userList) {
				JSONObject dataJson = new JSONObject();
				logReqBean.setAppId(userAppBean.getAppId());
				logReqBean.setUserId(userAppBean.getUserId());
				ArrayList<DataInfoBean> dataInfoList = null;
				ArrayList<DataInfoBean> dataInfoStartList = null;
				HashMap<String, String> infoParam = new HashMap<String, String>();
				infoParam.put("USER_ID", logReqBean.getUserId());
				infoParam.put("APP_ID", logReqBean.getAppId());
				infoParam.put("OPER_DATE_START", Tool.getDayTime(logReqBean.getStartDate()));
				infoParam.put("OPER_DATE_END", Tool.getDayTime(logReqBean.getEndDate() + 1));
				try {
					dataInfoStartList = dataInfoDao.selectdataNumberByDay(infoParam);
					dataInfoList = dataInfoDao.selectdataNumberRecently(infoParam);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}
				LinkedHashMap<String, String> dataLinkMap = new LinkedHashMap<String, String>();

				try {
					Integer size = 0;
					if(dataInfoStartList==null || dataInfoStartList.isEmpty()){
						dataInfoStartList = dataInfoDao.selectdataNumberBefore(infoParam);
						if(dataInfoStartList!=null && !dataInfoStartList.isEmpty()){
							size = dataInfoStartList.get(0).getSize();
						}
					}
					// 日期映射
					for (DataInfoBean dataInfoBean : dataInfoList) {
						String date = Tool.formatDateString(dataInfoBean.getDateTime());
						dataLinkMap.put(date, String.valueOf(dataInfoBean.getSize()));
					}
					// 填充空缺的日期
					/*dataLinkMap = Tool.getWholeDateData(dataLinkMap, logReqBean.getStartDate(),
							logReqBean.getEndDate());*/
					
					dataLinkMap = DataInfoTool.getWholeDateDataForDataInfo(dataLinkMap, logReqBean.getStartDate(),
							logReqBean.getEndDate(), size);
					
					
					

				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
					throw new SystemException(MsgConfig.SYS_EXCP);
				}

				dataJson.put("resultMap", dataLinkMap);
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

	/*
	 * (non Javadoc)
	 * 
	 * @Title: getDataNumsNew
	 * 
	 * @Description: 获取最新数据量
	 * 
	 * @param logReqBean
	 * 
	 * @return
	 * 
	 * @see
	 * com.bcse.web.service.log.IDataInfoService#getDataNumsNew(com.bcse.web.
	 * bean.log.LogReqBean)
	 */
/*	public DataInfoBean getDataNumsNew(LogReqBean logReqBean) {
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", logReqBean.getUserId());
		infoParam.put("APP_ID", logReqBean.getAppId());
		infoParam.put("OPER_DATE", Tool.getDayTime(YESTERDAY));
		return this.dataInfoDao.selectdataNumberNew(infoParam);
	}*/

	/*
	 * (non Javadoc)
	 * 
	 * @Title: dataNumberNew
	 * 
	 * @Description: 获取昨天数据量
	 * 
	 * @param userId
	 * 
	 * @param appId
	 * 
	 * @return
	 * 
	 * @see
	 * com.bcse.web.service.log.IDataInfoService#dataNumberNew(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public DataInfoBean dataNumberNew(String userId, String appId) {
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", userId);
		infoParam.put("APP_ID", appId);
		infoParam.put("OPER_DATE", Tool.getDayTime(YESTERDAY));
		return this.dataInfoDao.selectdataNumberNew(infoParam);
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: insertDataLatest
	 * 
	 * @Description: 更新索引后插入数据量
	 * 
	 * @param dataInfoBean
	 * 
	 * @return
	 * 
	 * @see
	 * com.bcse.web.service.log.IDataInfoService#insertDataLatest(com.bcse.web.
	 * bean.log.DataInfoBean)
	 */
	@Override
	public int insertDataLatest(DataInfoBean dataInfoBean) {
		int flag = 0;
		flag = this.dataInfoDao.insertdataNumberLatest(dataInfoBean);
		return flag;
	}

}
