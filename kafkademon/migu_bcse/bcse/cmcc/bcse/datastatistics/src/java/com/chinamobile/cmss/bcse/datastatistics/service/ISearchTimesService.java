package com.chinamobile.cmss.bcse.datastatistics.service;


import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

/** 
 * @ClassName: ISearchTimesService 
 * @Description: 
 * @author: chenmin
 * @date: 2016年1月29日 下午5:53:12  
 */
public interface ISearchTimesService {
	
	/** 
	 * @Title: getSearchTimesByDay 
	 * @Description:  按天获取搜索次数
	 * @param logReqBean 
	 * @return
	 * @return: ArrayList<LogSearchTimesBean>
	 */
	public void showSearchTimesByDay(LogReqBean logReqBean,ResultBean resultBean);
	
	/** 
	 * @Title: getSearchTimesByHour 
	 * @Description:  按小时获取搜索次数
	 * @param logReqBean
	 * @return
	 * @return: ArrayList<LogMaxSearchTimesBean>
	 */
	public void showSearchTimesByHour(LogReqBean logReqBean,ResultBean resultBean);
	
	/** 
	 * @Title: yesterdaySearchTimes 
	 * @Description: TODO查询昨日搜索次数
	 * @param userId
	 * @param appId
	 * @return
	 * @return: int
	 */
	public int yesterdaySearchTimes(String userId, String appId);
	/** 
	 * @Title: maxSearchTimes 
	 * @Description: TODO搜索峰值
	 * @param userId
	 * @param appId
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int maxSearchTimes(String userId, String appId);

}
