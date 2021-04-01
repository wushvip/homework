package com.chinamobile.cmss.bcse.app.dao;

import java.util.ArrayList;
import java.util.List;

import com.chinamobile.cmss.bcse.app.bean.AppFieldBean;
import com.chinamobile.cmss.bcse.app.bean.FieldInfoBean;


/**
 * 
 * @ClassName: AppFieldBeanDao
 * @Description: 应用字段表dao层接口
 * @author: jinjing
 * @date: 2016年2月17日 上午10:49:03
 */
public interface AppFieldBeanDao {

	/** 
	 * @Title: updateVersion 
	 * @Description: 更新记录版本号
	 * @return: int
	 */
	int updateVersion();
	/**
	 * 
	 * @Title: insert
	 * @Description: 插入一个字段信息
	 * @param record
	 * @return
	 * @return: int
	 */
	int insert(AppFieldBean record);

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
	 * 
	 * @Title: selectStringFields
	 * @Description: 获取指定应用下所有非数值及日期字段
	 * @param appId
	 * @return
	 * @return: ArrayList<AppFieldBean>
	 */
	ArrayList<FieldInfoBean> selectStringFields(String appId);

	
	/** 
	 * @Title: selectIndexedFields 
	 * @Description: 获取索引字段
	 * @param appId
	 * @return
	 * @return: ArrayList<FieldInfoBean>
	 */
	ArrayList<FieldInfoBean> selectIndexedFields(String appId);

	/** 
	 * @Title: selectNumericalFields 
	 * @Description: 类型为数值或日期类型的字段(搜索测试)
	 * @param appId
	 * @return
	 * @return: ArrayList<FieldInfoBean>
	 */
	ArrayList<FieldInfoBean> selectNumericalFields(String appId);

	/** 
	 * @Title: selectSortedFields 
	 * @Description: 获取排序字段
	 * @param appId
	 * @return
	 * @return: ArrayList<FieldInfoBean>
	 */
	ArrayList<FieldInfoBean> selectSortedFields(String appId);

	/** 
	 * @Title: selectGroupStatisticsFields 
	 * @Description: 获取分组统计字段
	 * @param appId
	 * @return
	 * @return: ArrayList<FieldInfoBean>
	 */
	ArrayList<FieldInfoBean> selectGroupStatisticsFields(String appId);

	/** 
	 * @Title: selectTokenizedFields 
	 * @Description: 获取建立索引且未分词的字段，分组统计时使用
	 * @param appId
	 * @return
	 * @return: ArrayList<FieldInfoBean>
	 */
	ArrayList<FieldInfoBean> selectTokenizedFields(String appId);

	/** 
	 * @Title: selectShownableFields 
	 * @Description: 获取可展示字段
	 * @param appId
	 * @return
	 * @return: ArrayList<FieldInfoBean>
	 */
	ArrayList<FieldInfoBean> selectShownableFields(String appId);

	/** 
	 * @Title: selectApplicationPK 
	 * @Description: 获取应用主键信息
	 * @param appId
	 * @return
	 * @return: AppFieldBean
	 */
	AppFieldBean selectApplicationPK(String appId);

	/** 
	 * @Title: selectByTableId 
	 * @Description: 根据表ID获取应用数据
	 * @param tableId
	 * @return
	 * @return: ArrayList<AppFieldBean>
	 */
	ArrayList<AppFieldBean> selectByTableId(String tableId);
}