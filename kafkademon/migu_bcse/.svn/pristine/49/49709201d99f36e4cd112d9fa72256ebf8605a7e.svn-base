package com.chinamobile.cmss.bcse.index.entry;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.chinamobile.cmss.bcse.index.bean.SchemaProperty;
import com.chinamobile.cmss.bcse.index.bean.SolrConfProperty;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.ClearTable;
import com.chinamobile.cmss.bcse.index.dao.CreateTable;
import com.chinamobile.cmss.bcse.index.dao.InsertData;
import com.chinamobile.cmss.bcse.index.indexManager.CreateCollection;
import com.chinamobile.cmss.bcse.index.indexManager.CreateConfig;
import com.chinamobile.cmss.bcse.index.tool.DeleteFiles;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: CreateApp
 * @Description: 开放给控制层创建应用和修改应用的时候调用
 * @author: jinjing
 * @date: 2016年2月16日 上午9:29:44
 */
public class CreateApp {

	/**
	 * 
	 * @Title: process
	 * @Description: 入口
	 * @param userId
	 * @param appId
	 * @param tableProperties
	 * @param schemaProperty
	 * @param solrConfPropertys
	 * @param tableList
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String userId, String appId, ArrayList<TableProperty> tableProperties,
			SchemaProperty schemaProperty, ArrayList<SolrConfProperty> solrConfPropertys,
			LinkedHashMap<String, String> tableList) {

		try {

			String coreName = appId;

			if (tableList.size() == 1) { // 单表应用
				LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "create new");
				// 创建数据库表信息
				// CreateTable.process(tableProperties, userId, appId);
				// 创建配置文件

				String url1 = CreateApp.class.getResource("/TemplateConf").getPath() + File.separator;
				url1 = url1.replaceAll("%20", " ");
				System.out.println(url1);
				CreateConfig.process(schemaProperty, solrConfPropertys, Config.CORE_CONFIG_DIR + appId + File.separator,
						url1, coreName);
				// 创建核
				CreateCollection.process(coreName, Config.SHARD_NUM, Config.REPLICATE_NUM, Config.MAX_PER_NODE_NUM);

				/*
				 * String filePath = ""; for (Entry<String, String> entry :
				 * tableList.entrySet()) { // 获取表名 filePath = entry.getValue();
				 * }
				 */
				// 文件更新索引
				// UpdateIndex.singleProcess(userId, appId, tableProperties,
				// filePath);

			} else { // 多表应用

				LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "create new");
				// 创建数据库表信息
				CreateTable.process(tableProperties, userId, appId);
				// 创建配置文件
				String url1 = CreateApp.class.getResource("/TemplateConf").getPath() + File.separator;
				System.out.println(url1);
				CreateConfig.process(schemaProperty, solrConfPropertys, Config.CORE_CONFIG_DIR + appId + File.separator,
						url1, coreName);
				// 创建核
				CreateCollection.process(coreName, Config.SHARD_NUM, Config.REPLICATE_NUM, Config.MAX_PER_NODE_NUM);

				// 上传文件入数据库
				InsertData.process(tableList, userId, appId);
				// 删除数据文件
				DeleteFiles.process(tableList);
				// 文件更新索引
				// UpdateIndex.process(userId, appId, tableProperties);

				// 清空数据库
				ClearTable.process(tableList, userId, appId);
			}
			new DataApi().setNumToDataBase(userId, appId);
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "CreateApp END");

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexCreateAppError, LogUtil.INDEX_LOG, e);
			return false;
		}
		return true;
	}
}
