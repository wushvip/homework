package com.chinamobile.cmss.bcse.index.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.app.bean.AppFieldBean;
import com.chinamobile.cmss.bcse.app.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.constant.FieldType;
import com.chinamobile.cmss.bcse.index.dao.IndexFieldBeanDao;
import com.chinamobile.cmss.bcse.index.dao.IndexTableMapBeanDao;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;


@Service("indexOperaParamUtil2")
public class IndexOperaParamUtil2 {

	@Resource
	private IndexTableMapBeanDao indexTableMapBeanDao;
	@Resource
	private IndexFieldBeanDao indexFieldBeanDao;

	/**
	 * 获取处理索引时需要的数据表的数据
	 * 
	 * @param userId
	 * @param appId
	 * @param tablePropertiesList
	 * @param sourceFileMap
	 * @throws Exception
	 */
	public void getDataTableParam(String userId, String appId, ArrayList<TableProperty> tablePropertiesList,
			LinkedHashMap<String, String> sourceFileMap) throws Exception {

		// 表名与源文件对应关系
		ArrayList<AppTableMapBean> appTableMapBeans = getSourceFileMap(appId, sourceFileMap);

		for (AppTableMapBean appTableMapBean : appTableMapBeans) {

			TableProperty tableProperty = new TableProperty();
			HashMap<String, TableField> fieldMap = new HashMap<String, TableField>();

			tableProperty.setIsMainTable(appTableMapBean.getIsMainTable());
			tableProperty.setTableId(appTableMapBean.getTableId());

			/**
			 * 获取每个表的字段
			 */
			ArrayList<AppFieldBean> appFieldBeans = indexFieldBeanDao.selectByTableId(appTableMapBean.getTableId());
			for (AppFieldBean appFieldBean : appFieldBeans) {
				TableField tableField = new TableField();
				tableField.setKey(appFieldBean.getIsPk());
				tableField.setType(appFieldBean.getFieldType());
				tableField.setIsMutivalue(appFieldBean.getIsMutivalue());
				tableField.setSrcField(appFieldBean.getSrcField());
				switch (appFieldBean.getFieldType()) {
				case "ANSJ":
					tableField.setType(FieldType.ANSJ);
					break;
				case "DATE":
					tableField.setType(FieldType.DATE);
					break;
				case "DOUBLE":
					tableField.setType(FieldType.DOUBLE);
					break;
				case "FLOAT":
					tableField.setType(FieldType.FLOAT);
					break;
				case "INT":
					tableField.setType(FieldType.INT);
					break;
				case "JP_NoSplit":
					tableField.setType(FieldType.JP_NoSplit);
					break;
				case "QP_NoSplit":
					tableField.setType(FieldType.QP_NoSplit);
					break;
				case "STRING":
					tableField.setType(FieldType.STRING);
					break;
				case "TEXT":
					tableField.setType(FieldType.TEXT);
					break;
				}

				// 多表待优化，优化时请和金晶讨论
				tableField.setOutTable(appFieldBean.getForeignKey());
				fieldMap.put(appFieldBean.getFieldName(), tableField);
			}

			tableProperty.setFieldMap(fieldMap);
			tablePropertiesList.add(tableProperty);
		}

	}

	/**
	 * 
	 * @Title: getSourceFileMap
	 * @Description: 获取当前应用下的数据表与其源文件的对应关系
	 * @param appId
	 * @param tableHashMap
	 * @return
	 * @return: ArrayList<AppTableMapBean>
	 */
	public ArrayList<AppTableMapBean> getSourceFileMap(String appId, LinkedHashMap<String, String> tableHashMap) {
		ArrayList<AppTableMapBean> appTableMapBeans = new ArrayList<>();
		try {
			appTableMapBeans = indexTableMapBeanDao.selectByAppId(appId);
			for (AppTableMapBean appTableMapBean : appTableMapBeans) {
				if ("".equals(appTableMapBean.getSourceDir()) || appTableMapBean.getSourceDir() == null) {
					tableHashMap.put(appTableMapBean.getTableId(), "");
				} else {
					tableHashMap.put(appTableMapBean.getTableId(), appTableMapBean.getSourceDir());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance("", appId, "0", LogUtil.WEB_LOG, e);
		}
		return appTableMapBeans;
	}

}
