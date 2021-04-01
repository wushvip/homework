package com.chinamobile.cmss.bcse.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.datastatistics.bean.ErrorLogBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.ErrorLogDao;
import com.chinamobile.cmss.bcse.datastatistics.service.IErrorLogService;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;



/**
 * @ClassName: IErrorLogServiceImpl
 * @Description: 错误日志
 * @author: lijingjing
 * @date: 2016年2月18日 上午10:25:31
 */
@Service("errorLogService")
public class IErrorLogServiceImpl implements IErrorLogService {

	@Resource
	private ErrorLogDao errorLogDao;

	/**
	 * 
	 * @Title: getErrorLogs
	 * @Description: 获取错误日志列表
	 * @param logReqBean
	 * @return 
	 * @Date:2016年5月26日
	 */
	public void showErrorLogs(LogReqBean logReqBean,ResultBean resultBean) {
		ArrayList<ErrorLogBean> errorList= null;
		logReqBean.setStartDateStr(Tool.getDayTime(logReqBean.getStartDate()));
		logReqBean.setEndDateStr(Tool.getDayTime(logReqBean.getEndDate() + 1));
		int totalItems = 0;
		logReqBean.setStartNum((logReqBean.getPageIndex() - 1) * logReqBean.getPageNum());
		try {
			errorList = errorLogDao.selectErrorLogAsDays(logReqBean);
			totalItems = errorLogDao.getTotalItemsErrorLog(logReqBean);
			LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "totalItems num is :" + totalItems);
			LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "errorList list is :" + errorList);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("errorLogList", errorList);
		resultJson.put("totalItems", totalItems);
		resultBean.setResult(resultJson);
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: insertErrorLogs
	 * 
	 * @Description: 插入错误日志
	 * 
	 * @param errorLogReqBean
	 * 
	 * @return
	 * 
	 * @see
	 * com.bcse.web.service.log.IErrorLogService#insertErrorLogs(com.bcse.web.
	 * bean.log.ErrorLogBean)
	 */
	@Override
	public int insertErrorLogs(ErrorLogBean errorLogReqBean) {
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", errorLogReqBean.getUserId());
		infoParam.put("APP_ID", errorLogReqBean.getAppId());
		infoParam.put("LOG_TIME", errorLogReqBean.getCreateDate().toString());
		infoParam.put("FLAG", errorLogReqBean.getFlag().toString());
		infoParam.put("MODULE", errorLogReqBean.getModule());
		infoParam.put("ERROR_CODE", errorLogReqBean.getCode().toString());
		infoParam.put("ERROR_DETAILS", errorLogReqBean.getDetail());
		return errorLogDao.insertErrorLog(infoParam);
	}
}
