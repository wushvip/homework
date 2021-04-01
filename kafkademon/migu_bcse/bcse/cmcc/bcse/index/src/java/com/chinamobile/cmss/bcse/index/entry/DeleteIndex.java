package com.chinamobile.cmss.bcse.index.entry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.ClearTable;
import com.chinamobile.cmss.bcse.index.dao.InsertData;
import com.chinamobile.cmss.bcse.index.indexManager.DeleteIndexFromFile;
import com.chinamobile.cmss.bcse.index.tool.DeleteFiles;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 文件删除索引的入口
 * 
 * @author Administrator
 *
 */
public class DeleteIndex {

	public static boolean process(String userId, String appId, LinkedHashMap<String, String> tableList,
			ArrayList<TableProperty> tableProperties, String MutivalueSplit, String fileSplit) {
		boolean flag = true;
		try {

			if (tableList.size() == 1) {// 判断是不是单表应用，如果是单表应用则不经过数据库
				String filePath = "";
				for (Entry<String, String> entry : tableList.entrySet()) {
					// 获取文件名
					filePath = entry.getValue();
				}
				if (!DeleteIndexFromFile.singleProcess(userId, appId, tableProperties, filePath, MutivalueSplit,
						fileSplit)) {
					flag = false;
				}
				// 删除数据文件
				DeleteFiles.process(tableList);

			} else { // 多表应用

				// 清空数据库
				ClearTable.process(tableList, userId, appId);
				// 上传文件入数据库
				if (!InsertData.process(tableList, userId, appId)) {
					flag = false;
				} else {
					// 文件更新索引
					if (!DeleteIndexFromFile.process(userId, appId, tableProperties)) {
						flag = false;
					}
				}
				// 删除数据文件
				DeleteFiles.process(tableList);
				// 清空数据库
				ClearTable.process(tableList, userId, appId);
			}
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "UPDATE DATA END");

		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexUpdateDataError, LogUtil.INDEX_LOG, e);
			flag = false;
		}

		return flag;
	}
}
