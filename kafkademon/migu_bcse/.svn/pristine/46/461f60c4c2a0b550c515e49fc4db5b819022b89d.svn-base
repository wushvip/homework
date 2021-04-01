package com.chinamobile.cmss.bcse.app.dao;

import java.util.ArrayList;

import com.chinamobile.cmss.bcse.app.bean.AppTableMapBean;




/**
 * 
 * @ClassName: AppTableMapBeanDao
 * @Description: 应用数据表的dao接口
 * @author: jinjing
 * @date: 2016年2月16日 下午2:46:49
 */
public interface AppTableMapBeanDao {

	int deleteByPrimaryKey(String tableId);

	/**
	 * 
	 * @Title: insert
	 * @Description: 增加一个应用的一张表信息
	 * @param record
	 * @return
	 * @return: int
	 */
	int insert(AppTableMapBean record);

	/**
	 * 
	 * @Title: selectByAppId
	 * @Description: 根据appId查找
	 * @param appId
	 * @return
	 * @return: ArrayList<AppTableMapBean>
	 */
	ArrayList<AppTableMapBean> selectByAppId(String appId);

	/**
	 * 
	 * @Title: deleteByAppId
	 * @Description: 根据appId删除
	 * @param appId
	 * @return
	 * @return: int
	 */
	int deleteByAppId(String appId);

	/**
	 * 
	 * @Title: updateFile
	 * @Description: 上传文件时更新数据库关系
	 * @param tableId
	 * @param sourceDir
	 * @return
	 * @return: boolean
	 */
	boolean updateFile(String tableId, String sourceDir);

}