package com.chinamobile.cmss.bcse.app.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.util.AppStructureUtil;
import com.chinamobile.cmss.bcse.index.bean.SchemaProperty;
import com.chinamobile.cmss.bcse.index.bean.SolrConfProperty;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.entry.CreateApp;
import com.chinamobile.cmss.bcse.index.entry.DeleteApp;
import com.chinamobile.cmss.bcse.index.entry.DeleteIndex;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

@Service("indexEntry")
public class IndexEntry {
	@Resource
	private AppStructureUtil appStructureUtil;
	
	/**
	 * 
	 * @Title: createIndex 
	 * @Description: 调用创建索引入口
	 * @return
	 * @return: boolean
	 */
	public boolean createIndex(AppReqBean appReqBean){


		String appId = appReqBean.getAppId();
		String userId = appReqBean.getUserId();

		// 获取应用结构信息包括：数据信息、字段表信息、索引表信息
		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		// 字段表数据
		SchemaProperty schemaProperty = new SchemaProperty();
		// 索引表数据
		ArrayList<SolrConfProperty> solrConfPropertiesList = new ArrayList<SolrConfProperty>();
		// 上传数据源时，上传文件名与对应数据表名的映射关系
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
		// 数据表
		boolean createResult = false;
		try {
			appStructureUtil.getDataTableParam(userId, appId, tablePropertiesList, sourceFileMap);
			appStructureUtil.getFieldTableParam(userId, appId, schemaProperty);
			// 索引表
//			appStructureUtil.getIndexTableParam(userId, appId, solrConfPropertiesList);
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "数据表名map:" + sourceFileMap);
			createResult= CreateApp.process(userId, appId, tablePropertiesList, schemaProperty,
					solrConfPropertiesList, sourceFileMap);
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.APP_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		return createResult;
		
	}
	
	
	/**
	 * 
	 * @Title: deleteIndex 
	 * @Description: 调用删除索引
	 * @param appReqBean
	 * @return
	 * @return: boolean
	 */
	public boolean deleteIndex(AppReqBean appReqBean,ResultBean resultBean){
		String appId = appReqBean.getAppId();
		String userId = appReqBean.getUserId();
		
		// 获取应用结构信息包括：数据信息、字段表信息、索引表信息
		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		
		// 上传数据源时，上传文件名与对应数据表名的映射关系
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
		boolean delResult = false;
		try {
			
			appStructureUtil.getDataTableParam(userId, appId, tablePropertiesList, sourceFileMap);
			delResult = DeleteApp.process(userId, appId, tablePropertiesList);
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.APP_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		
		return delResult;
	}

}
