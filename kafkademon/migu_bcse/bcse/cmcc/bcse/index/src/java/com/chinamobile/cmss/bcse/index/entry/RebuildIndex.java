package com.chinamobile.cmss.bcse.index.entry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.ClearTable;
import com.chinamobile.cmss.bcse.index.dao.InsertData;
import com.chinamobile.cmss.bcse.index.indexManager.ClearIndex;
import com.chinamobile.cmss.bcse.index.indexManager.UpdateIndex;
import com.chinamobile.cmss.bcse.index.tool.DeleteFiles;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: RebuildIndex
 * @Description: 重建索引的接口
 * @author: jinjing
 * @date: 2016年2月16日 下午2:18:40
 */
public class RebuildIndex {

	/**
	 * 
	 * @Title: process
	 * @Description: 入口
	 * @param userId
	 * @param appId
	 * @param tableList
	 * @param tableProperties
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String userId, String appId, LinkedHashMap<String, String> tableList,
			ArrayList<TableProperty> tableProperties) {
		boolean flag = true;
		try {
			String coreName = appId;

			if (tableList.size() == 1) { // 单表应用
				// 清空索引
				ClearIndex.process(coreName);
/*
				String filePath = "";
				for (Entry<String, String> entry : tableList.entrySet()) {
					// 获取表名
					filePath = entry.getValue();
				}
				// 文件更新索引
				if (!UpdateIndex.singleProcess(userId, appId, tableProperties, filePath, "1")) {
					flag = false;
				}*/

			} else { // 多表应用
				// 清空索引
				ClearIndex.process(coreName);

				// 清空数据库
				ClearTable.process(tableList, userId, appId);

				// 上传文件入数据库
				if (!InsertData.process(tableList, userId, appId)) {
				} else {
					// 文件更新索引
					if (!UpdateIndex.process(userId, appId, tableProperties)) {
						flag = false;
					}
				}

				// 删除数据文件
				DeleteFiles.process(tableList);
				// 清空数据库
				ClearTable.process(tableList, userId, appId);

			}

			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "clear index END");

		} catch (Exception e) {
			e.printStackTrace();

			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexRebuildIndexError, LogUtil.INDEX_LOG, e);
			return false;
		}
		return flag;
	}

}
