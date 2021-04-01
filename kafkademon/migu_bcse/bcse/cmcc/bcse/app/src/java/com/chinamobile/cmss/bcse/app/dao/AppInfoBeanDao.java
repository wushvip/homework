package com.chinamobile.cmss.bcse.app.dao;

import java.util.ArrayList;

import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;




/**
 * 
 * @ClassName: AppInfoBeanDao
 * @Description: 应用基本信息的dao接口
 * @author: jinjing
 * @date: 2016年2月16日 下午2:44:13
 */
public interface AppInfoBeanDao {
	
	
	/** 
	 * @Title: updateVersion 
	 * @Description: 更新记录版本号
	 * @return
	 * @return: int
	 */
	int updateVersion();
	
	/** 
	 * @Title: getAppInfoByAppId 
	 * @Description: 获取指定id的应用信息
	 * @param appId
	 * @return: AppInfoBean
	 */
	AppInfoBean getAppInfoByAppId(String appId);

	/**
	 * 
	 * @Title: updateAppDetail
	 * @Description: 更新应用信息
	 * @param appReqBean
	 * @return
	 * @return: int
	 */
	int updateAppDetail(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: getAppList
	 * @Description: 获取应用列表
	 * @param appReqBean
	 * @return
	 * @return: ArrayList<AppInfoBean>
	 */
	ArrayList<AppInfoBean> getAppList(AppReqBean appReqBean);
	int getTotalItemsNum(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: getAllAppList
	 * @Description: 获取所有应用
	 * @param appReqBean
	 * @return
	 * @return: ArrayList<AppInfoBean>
	 */
	ArrayList<AppInfoBean> getAllAppList(AppReqBean appReqBean);
	
	/**
	 * 
	 * @Title: getAllAppInfos
	 * @Description: 获取所有应用
	 * @param appReqBean
	 * @return: ArrayList<AppInfoBean>
	 */
	ArrayList<AppInfoBean> getAllAppInfos();

	/**
	 * 
	 * @Title: getAppListByStatus
	 * @Description: 根据状态，获取相应的应用列表
	 * @param appReqBean
	 * @return
	 * @return: AppInfoBean
	 */
	ArrayList<AppInfoBean> getAppListByStatus(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: DeleteAppDetail
	 * @Description: 删除应用的信息接口
	 * @param appReqBean
	 * @return
	 * @return: int
	 */
	int DeleteAppDetail(AppReqBean appReqBean);
	int DeleteCreating(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: isExistApp
	 * @Description: 判断应用是否存在
	 * @param appReqBean
	 * @return
	 * @return: ArrayList<AppInfoBean>
	 */
	ArrayList<AppInfoBean> isExistApp(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: addAppInfoDetail
	 * @Description: 增加一个应用的信息
	 * @param appReqBean
	 * @return
	 * @return: int
	 */
	int addAppInfoDetail(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: showSignAppDetail
	 * @Description: 获取应用的基本信息
	 * @param appReqBean
	 * @return
	 * @return: AppInfoBean
	 */
	AppInfoBean showSignAppDetail(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: modifyAppStatus
	 * @Description: 修改应用状态
	 * @param appReqBean
	 * @return
	 * @return: boolean
	 */
	int modifyAppStatus(AppReqBean appReqBean);

	/**
	 * 
	 * @Title: modifyAppStatus
	 * @Description: 启动或者停用用户的应用
	 * @param appReqBean
	 * @return
	 * @return: boolean
	 */
	int StopOrOpenAppStatus(AppReqBean appReqBean);

}