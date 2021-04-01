package com.chinamobile.cmss.bcse.datastatistics.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.chinamobile.cmss.bcse.datastatistics.bean.ErrorLogBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;

/** 
 * @ClassName: ErrorLogBeanDao 
 * @Description: TODO 错误日志操作数据库接口
 * @author: chenmin
 * @date: 2016年1月29日 下午6:00:15  
 */
public interface ErrorLogDao {
    
	/** 
	 * @Title: selectErrorLogAsDays 
	 * @Description: TODO 按天查询错误日志
	 * @param infoParam
	 * @return
	 * @return: ArrayList<ErrorLogBean>
	 */
	public ArrayList<ErrorLogBean> selectErrorLogAsDays(LogReqBean logReqBean);
	int getTotalItemsErrorLog(LogReqBean logReqBean);
	
	/** 
	 * @Title: insertErrorLog 
	 * @Description: TODO 插入错误日志
	 * @param infoParam
	 * @return
	 * @return: int
	 */
	public int insertErrorLog(HashMap<String, String> infoParam);
	
}