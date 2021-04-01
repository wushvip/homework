package com.chinamobile.cmss.bcse.app.service;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.bean.AppRspBean;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

public interface ApplicationManagerService {

	/**
	 * @Title: getDataOperaHistory
	 * @Description: 获取数据处理历史记录
	 * @param appReqBean
	 * @return
	 * @throws ParseException
	 * @return: AppRspBean
	 */
	void showIndexOper(AppReqBean appReqBean,ResultBean resultBean);
	/**
	 * 
	 * @Title: getAppList
	 * @Description: 获取应用列表
	 * @param appReqBean
	 * @return
	 * @throws Exception
	 * @return: AppRspBean
	 */
//	public AppRspBean getAppList(AppReqBean appReqBean);
	void showAppList(AppReqBean appReqBean,ResultBean resultBean);

	/**
	 * 
	 * @Title: deleApp
	 * @Description: 删除应用
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	void delete(AppReqBean appReqBean,ResultBean resultBean);

	/**
	 * 
	 * @Title: modifyAppStatus
	 * @Description: 修改应用状态
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	void update(AppReqBean appReqBean,ResultBean resultBean);

	/**
	 * 
	 * @Title: showAppStructure
	 * @Description: 获取单个应用的应用结构
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	void showStructure(AppReqBean appReqBean,ResultBean resultBean);



	void showBasicInfo(AppReqBean appReqBean,ResultBean resultBean);



	/**
	 * 
	 * @Title: getAllAppList
	 * @Description: 启动或者停用用户的应用
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	void stopOrOpenApp(AppReqBean appReqBean);


	void updateAppStructure(AppReqBean appReqBean, ResultBean resultBean);

	void saveAppStructure(AppReqBean appReqBean, ResultBean resultBean);

	void createApp(AppReqBean appReqBean, ResultBean resultBean);

	void isExistCreatingApp(AppReqBean appReqBean, ResultBean resultBean);
	void updateAppInfoDetail(AppReqBean appReqBean, ResultBean resultBean);
	void addAppInfoDetail(AppReqBean appReqBean, ResultBean resultBean);

	void deleteAppStructure(AppReqBean appReqBean, ResultBean resultBean);

}
