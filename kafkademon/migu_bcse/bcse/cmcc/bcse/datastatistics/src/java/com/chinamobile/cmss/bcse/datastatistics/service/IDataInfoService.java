package com.chinamobile.cmss.bcse.datastatistics.service;


import com.chinamobile.cmss.bcse.datastatistics.bean.DataInfoBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;


/** 
 * @ClassName: IDataInfoService 
 * @Description: TODO 数据量获取服务
 * @author: chenmin
 * @date: 2016年1月29日 下午5:50:00  
 */
public interface IDataInfoService {
	
	
	/**
	 * @Title: getDataNums 获取数据量
	 * @Description: TODO
	 * @param logReqBean
	 * @return
	 * @return: ArrayList<DataInfoBean>
	 */
	public void showDataInfo(LogReqBean logReqBean,ResultBean resultBean);

	/**
	 * @Title: getDataNumsNew
	 * @Description: TODO 最新的数据量
	 * @param logReqBean
	 * @return
	 * @return: DataInfoBean
	 */
//	public DataInfoBean getDataNumsNew(LogReqBean logReqBean);

	/** 
	 * @Title: dataNumberNew 
	 * @Description: TODO 重载最新的数据量
	 * @param userId
	 * @param appId
	 * @return
	 * @return: DataInfoBean
	 */
	public DataInfoBean dataNumberNew(String userId, String appId);
	
	/** 
	 * @Title: insertDataLatest  
	 * @Description: TODO 更新索引后插入数据量
	 * @param dataInfoBean
	 * @return
	 * @return: int
	 */
	public int insertDataLatest(DataInfoBean dataInfoBean);

}
