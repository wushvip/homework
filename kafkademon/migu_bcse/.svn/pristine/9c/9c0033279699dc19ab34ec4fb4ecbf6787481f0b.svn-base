package com.chinamobile.cmss.bcse.datastatistics.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.chinamobile.cmss.bcse.datastatistics.bean.LogMaxSearchTimesBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogSearchTimesBean;

/** 
 * @ClassName: SearchTimesDao 
 * @Description: TODO 搜索次数操作数据库接口
 * @author: chenmin
 * @date: 2016年1月29日 下午6:02:59  
 */
public interface SearchTimesDao {
	
	/** 
	 * @Title: selectSearchTimesAsDay 
	 * @Description: TODO 按天查询搜索次数接口
	 * @param infoParam
	 * @return
	 * @return: ArrayList<LogSearchTimesBean>
	 */
	public ArrayList<LogSearchTimesBean> selectSearchTimesAsDay(HashMap<String, String> infoParam);
	
	/** 
	 * @Title: selectSearchTimesPerHour 
	 * @Description: TODO 按小时查询搜索次数接口
	 * @param infoParam
	 * @return
	 * @return: ArrayList<LogMaxSearchTimesBean>
	 */
	public ArrayList<LogMaxSearchTimesBean> selectSearchTimesPerHour(HashMap<String, String> infoParam);
	
	/** 
	 * @Title: selectSearchTimes 
	 * @Description: TODO 昨日搜索次数
	 * @param infoParam
	 * @return
	 * @return: LogSearchTimesBean
	 */
	public LogSearchTimesBean selectSearchTimes(HashMap<String, String> infoParam);
	
	/** 
	 * @Title: selectMaxSearchTimes 
	 * @Description: TODO 搜索次数峰值
	 * @param infoParam
	 * @return
	 * @return: LogMaxSearchTimesBean
	 */
	public LogMaxSearchTimesBean selectMaxSearchTimes(HashMap<String, String> infoParam);
	
}
