package com.chinamobile.cmss.bcse.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.app.bean.AppFieldBean;
import com.chinamobile.cmss.bcse.app.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.app.dao.AppFieldBeanDao;
import com.chinamobile.cmss.bcse.app.dao.AppInfoBeanDao;
import com.chinamobile.cmss.bcse.app.dao.AppTableMapBeanDao;
import com.chinamobile.cmss.bcse.index.bean.FieldProperty;
import com.chinamobile.cmss.bcse.index.constant.FieldType;
import com.chinamobile.cmss.bcse.index.bean.SchemaProperty;
import com.chinamobile.cmss.bcse.index.bean.SolrConfProperty;
import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.constant.SchemaFieldType;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

@Service("appStructureUtil")
public class AppStructureUtil {

	@Resource(name = "appInfoBeanDao")
	private AppInfoBeanDao appInfoBeanDao;
	@Resource
	private AppTableMapBeanDao appTableMapBeanDao;
	@Resource
	private AppFieldBeanDao appFieldBeanDao;

	/**
	 * 
	 * @Title: getIndexTableParam
	 * @Description: 创建应用后调用金晶的接口
	 * @param userId
	 * @param appId
	 * @param solrConfPropertiesList
	 * @return: void
	 */
	public void getIndexTableParam(String userId, String appId, ArrayList<SolrConfProperty> solrConfPropertiesList) {/*
		ShowParamBean showParamBean = new ShowParamBean();
		// 查询索引需要的参数
		showParamBean.setRuleType("4");
		showParamBean.setAppId(appId);
		List<RuleBeanWithBLOBs> ruleList = ruleBeanDao.selectRulesByAppId(showParamBean);

		*//**
		 * 索引表数据
		 *//*

		for (RuleBeanWithBLOBs indexBean : ruleList) {
			HashMap<String, Integer> includeFieldMap = new HashMap<String, Integer>();
			SolrConfProperty solrConfProperty = new SolrConfProperty();
			// 索引字段(域名)
			solrConfProperty.setRequestHeaderName(indexBean.getRuleName());
			String includeFieldStr = indexBean.getIncludeFields();
			if (!"".equals(includeFieldStr) && includeFieldStr != null) {
				String[] fieldArray = includeFieldStr.split(";");
				for (String field : fieldArray) {
					includeFieldMap.put(field, 1);

				}
			}
			solrConfProperty.setRequestField(includeFieldMap);
			solrConfPropertiesList.add(solrConfProperty);
		}

	*/}

	/**
	 * 
	 * @Title: getFieldTableParam
	 * @Description: 获取字段表数据并组装
	 * @param userId
	 * @param appId
	 * @param schemaProperty
	 * @return: void
	 */
	public void getFieldTableParam(String userId, String appId, SchemaProperty schemaProperty) {

		// 后台需要的字段表JavaBean为FieldProperty
		HashMap<String, FieldProperty> fieldHashMap = new HashMap<String, FieldProperty>();

		ArrayList<AppFieldBean> appFieldBeans = appFieldBeanDao.selectFields(appId);
		/**
		 * 字段表数据
		 */

		for (AppFieldBean appFieldBean : appFieldBeans) {
			FieldProperty fieldProperty = new FieldProperty();

			switch (appFieldBean.getFieldType()) {
			case "ANSJ":
				fieldProperty.setFieldType(SchemaFieldType.ANSJ);
				break;
			case "DATE":
				fieldProperty.setFieldType(SchemaFieldType.DATE);
				break;
			case "DOUBLE":
				fieldProperty.setFieldType(SchemaFieldType.DOUBLE);
				break;
			case "FLOAT":
				fieldProperty.setFieldType(SchemaFieldType.FLOAT);
				break;
			case "INT":
				fieldProperty.setFieldType(SchemaFieldType.INT);
				break;
			case "JP_NoSplit":
				fieldProperty.setFieldType(SchemaFieldType.JP_NoSplit);
				break;
			case "QP_NoSplit":
				fieldProperty.setFieldType(SchemaFieldType.QP_NoSplit);
				break;
			case "STRING":
				fieldProperty.setFieldType(SchemaFieldType.STRING);
				break;
			case "TEXT":
				fieldProperty.setFieldType(SchemaFieldType.TEXT);
				break;
			case "LONG":
				fieldProperty.setFieldType(SchemaFieldType.LONG);
				break;
			case "ANSJ_JP":
				fieldProperty.setFieldType(SchemaFieldType.ANSJ_JP);
				break;
			case "ANSJ_QP":
				fieldProperty.setFieldType(SchemaFieldType.ANSJ_QP);
				break;
			case "ANSJ_PY":
				fieldProperty.setFieldType(SchemaFieldType.ANSJ_PY);
				break;
			case "QP_Suggest":
				fieldProperty.setFieldType(SchemaFieldType.QP_Suggest);
				break;
			case "JP_Suggest":
				fieldProperty.setFieldType(SchemaFieldType.JP_Suggest);
				break;

			}

			// fieldProperty.setFieldType(fieldBean.getFieldType());
			// 1-可搜索(可索引)
			boolean isIndex = "1".equals(appFieldBean.getIsSearch()) ? true : false;
			fieldProperty.setIsIndex(isIndex);
			// 1-展示(可存储)
			boolean isStore = "1".equals(appFieldBean.getIsShow()) ? true : false;
			fieldProperty.setIsStored(isStore);
			// 1-为主键
			boolean isPk = "1".equals(appFieldBean.getIsPk()) ? true : false;
			fieldProperty.setIsPk(isPk);

			if (appFieldBean.getIsMutivalue().equals("1"))
				fieldProperty.setIS_MUTIVALUE(true);

			if (appFieldBean.getDynamicField().equals("1"))
				fieldProperty.setDYNAMIC_FIELD(true);

			if (appFieldBean.getSrcField() != null)
				fieldProperty.setSRC_FIELD(appFieldBean.getSrcField());

			fieldHashMap.put(appFieldBean.getFieldName(), fieldProperty);

		}

		schemaProperty.setFieldMap(fieldHashMap);
	}

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
			ArrayList<AppFieldBean> appFieldBeans = appFieldBeanDao.selectByTableId(appTableMapBean.getTableId());
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
				case "QP_Suggest":
					tableField.setType(FieldType.QP_Suggest);
					break;
				case "JP_Suggest":
					tableField.setType(FieldType.JP_Suggest);
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
			appTableMapBeans = appTableMapBeanDao.selectByAppId(appId);
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
			throw e;
		}
		return appTableMapBeans;
	}

}
