package com.chinamobile.cmss.bcse.index.dao;

import java.util.ArrayList;

import com.chinamobile.cmss.bcse.app.bean.AppFieldBean;



/**
 * 
 * @ClassName: AppFieldBeanDao
 * @Description: 应用字段表dao层接口
 * @author: jinjing
 * @date: 2016年2月17日 上午10:49:03
 */
public interface IndexFieldBeanDao {


	/**
	 * 
	 * @Title: selectFields
	 * @Description: 获取schema的字段
	 * @param appId
	 * @return
	 * @return: ArrayList<AppFieldBean>
	 */
	ArrayList<AppFieldBean> selectFields(String appId);
	


	/** 
	 * @Title: selectByTableId 
	 * @Description: 根据表ID获取应用数据
	 * @param tableId
	 * @return
	 * @return: ArrayList<AppFieldBean>
	 */
	ArrayList<AppFieldBean> selectByTableId(String tableId);
}