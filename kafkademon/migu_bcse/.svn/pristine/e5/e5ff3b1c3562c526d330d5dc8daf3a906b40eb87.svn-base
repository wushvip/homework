package com.chinamobile.cmss.bcse.datastatistics.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.chinamobile.cmss.bcse.datastatistics.bean.DataInfoBean;

/** 
 * @ClassName: DataInfoBeanDao 
 * @Description: TODO 数据量操作数据库接口
 * @author: chenmin 
 * 
 */
public interface DataInfoDao {

	/** 
	 * @Title: selectdataNumberNew 
	 * @Description: TODO 最新数据量
	 * @param infoParam
	 * @return
	 * @return: DataInfoBean
	 */
	public DataInfoBean selectdataNumberNew(HashMap<String, String> infoParam);

	/** 
	 * @Title: selectdataNumberRecently 
	 * @Description: TODO 最近数据量
	 * @param infoParam
	 * @return
	 * @return: ArrayList<DataInfoBean>
	 */
	public ArrayList<DataInfoBean> selectdataNumberRecently(HashMap<String, String> infoParam);
	
	/** 
	 * @Title: DeleteData 
	 * @Description: TODO 删除数据
	 * @param infoParam
	 * @return
	 * @return: int
	 */
	public int DeleteData(HashMap<String, String> infoParam);
	
	/** 
	 * @Title: insertdataNumberLatest 
	 * @Description: TODO 插入数据
	 * @param dataInfoBean
	 * @return
	 * @return: int
	 */
	public int insertdataNumberLatest(DataInfoBean dataInfoBean);
	
	/** 
	 * @Title: selectdataNumberRecently 
	 * @Description: TODO 某天之前数据量
	 * @param infoParam
	 * @return
	 * @return: ArrayList<DataInfoBean>
	 */
	public ArrayList<DataInfoBean> selectdataNumberBefore(HashMap<String, String> infoParam);
	
	/** 
	 * @Title: selectdataNumberRecently 
	 * @Description: TODO 某天的数据量
	 * @param infoParam
	 * @return
	 * @return: ArrayList<DataInfoBean>
	 */
	public ArrayList<DataInfoBean> selectdataNumberByDay(HashMap<String, String> infoParam);
}