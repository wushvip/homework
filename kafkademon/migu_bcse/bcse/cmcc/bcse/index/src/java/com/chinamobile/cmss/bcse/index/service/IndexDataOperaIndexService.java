package com.chinamobile.cmss.bcse.index.service;

import java.text.ParseException;

import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

public interface IndexDataOperaIndexService {

	/**
	 * 根据文件删除部分索引
	 * 
	 * @param appReqBean
	 * @return
	 */
	// AppRspBean deleteIndexFromFile(AppReqBean appReqBean);
	void deleteIndexFromFile(AppReqBean appReqBean, ResultBean resultBean);

	/**
	 * @Title: clearAppIndex
	 * @Description: 清空索引
	 * @param appReqBean
	 * @return
	 * @throws Exception
	 * @return: AppRspBean
	 */
	// AppRspBean clearAppIndex(AppReqBean appReqBean) throws Exception;
	void clearIndex(AppReqBean appReqBean, ResultBean resultBean);

	/**
	 * @Title: updateAppIndex
	 * @Description: 更新索引
	 * @param appReqBean
	 * @return
	 * @throws Exception
	 * @return: AppRspBean
	 */
	// AppRspBean updateAppIndex(AppReqBean appReqBean) throws Exception;
	void updateIndex(AppReqBean appReqBean, ResultBean resultBean, String type);

	/**
	 * 
	 * @param appReqBean
	 * @param resultBean
	 */
	void updateFormJson(AppReqBean appReqBean, ResultBean resultBean) throws Exception;

	/**
	 * 
	 * @param appReqBean
	 * @param resultBean
	 */
	void deleteFormJson(AppReqBean appReqBean, ResultBean resultBean);

	/**
	 * @Title: getDataOperaHistory
	 * @Description: 获取数据处理历史记录
	 * @param appReqBean
	 * @return
	 * @throws ParseException
	 * @return: AppRspBean
	 */
	void showIndexOper(AppReqBean appReqBean, ResultBean resultBean);
	// AppRspBean getDataOperaHistory(AppReqBean appReqBean);

	/**
	 * @Title: saveDataOperaHistory
	 * @Description: 插入数据处理记录
	 * @param appReqBean
	 * @return
	 * @return: boolean
	 */
	boolean saveDataOperaHistory(AppReqBean appReqBean);

	ResultBean getSampleData(AppReqBean appReqBean);

	ResultBean useSampleData(AppReqBean appReqBean);

}
