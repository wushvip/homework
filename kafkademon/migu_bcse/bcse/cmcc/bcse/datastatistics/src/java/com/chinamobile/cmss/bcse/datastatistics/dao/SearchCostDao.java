package com.chinamobile.cmss.bcse.datastatistics.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.chinamobile.cmss.bcse.datastatistics.bean.LogSearchCostTimeBean;

/** 
 * @ClassName: SearchCostDao 
 * @Description: TODO 搜索耗时操作数据库接口
 * @author: chenmin
 * @date: 2016年1月29日 下午6:01:51  
 */
public interface SearchCostDao {
	
	/** 
	 * @Title: selectSearchCostPerDay 
	 * @Description: TODO 按天查询搜索耗时
	 * @param infoParam
	 * @return
	 * @return: ArrayList<LogSearchCostTimeBean>
	 */
	public ArrayList<LogSearchCostTimeBean>  selectSearchCostPerDay(HashMap<String, String> infoParam);
	
	/** 
	 * @Title: selectSearchCostPerHour 
	 * @Description: TODO 按小时查询搜索耗时
	 * @param infoParam
	 * @return
	 * @return: ArrayList<LogSearchCostTimeBean>
	 */
	public ArrayList<LogSearchCostTimeBean>  selectSearchCostPerHour(HashMap<String, String> infoParam);

}
