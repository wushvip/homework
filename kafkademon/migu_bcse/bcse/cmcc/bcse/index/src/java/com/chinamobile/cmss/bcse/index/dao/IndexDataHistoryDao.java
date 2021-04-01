package com.chinamobile.cmss.bcse.index.dao;

import java.util.ArrayList;

import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.index.bean.AppDataHistoryBean;



/**
 * 
 * @ClassName: AppDataHistoryDao
 * @Description: 应用操作历史记录
 * @author: jinjing
 * @date: 2016年2月16日 上午9:28:48
 */
public interface IndexDataHistoryDao {

	/**
	 * 
	 * @Title: saveOperaHistroy
	 * @Description: 保存操作记录
	 * @param appReqBean
	 * @return
	 * @return: boolean
	 */
	boolean saveOperaHistroy(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: getOperaHistroy
	 * @Description: 获取操作的历史记录
	 * @param appReqBean
	 * @return
	 * @return: ArrayList<AppDataHistoryBean>
	 */
	ArrayList<AppDataHistoryBean> getOperaHistroy(AppReqBean appReqBean);
	
	int getTotalItemsHistory(AppReqBean appReqBean);

}