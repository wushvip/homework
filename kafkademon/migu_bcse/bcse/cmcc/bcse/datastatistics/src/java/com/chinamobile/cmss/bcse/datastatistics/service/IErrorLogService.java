package com.chinamobile.cmss.bcse.datastatistics.service;

import com.chinamobile.cmss.bcse.datastatistics.bean.ErrorLogBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

/**
 * @ClassName: IErrorLogService
 * @Description: TODO 错误日志服务
 * @author: chenmin
 * @date: 2016年1月29日 下午5:51:27
 */
public interface IErrorLogService {
	
	/**
	 * @Title: getErrorLogs
	 * @Description: TODO 获取错误日志
	 * @param logReqBean
	 * @return
	 * @return: ArrayList<ErrorLogBean>
	 */
	public void showErrorLogs(LogReqBean logReqBean,ResultBean resultBean);

	/**
	 * @Title: insertErrorLogs
	 * @Description: TODO 插入错误日志
	 * @param errorLogReqBean
	 * @return
	 * @return: int
	 */
	public int insertErrorLogs(ErrorLogBean errorLogReqBean);

}
