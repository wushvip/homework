package com.chinamobile.cmss.bcse.index.entry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.ClearTable;
import com.chinamobile.cmss.bcse.index.dao.InsertData;
import com.chinamobile.cmss.bcse.index.indexManager.UpdateIndex;
import com.chinamobile.cmss.bcse.index.tool.DeleteFiles;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: UpdateData
 * @Description: 上传数据
 * @author: jinjing
 * @date: 2016年2月16日 下午2:19:04
 */
public class UpdateData {

	public static void main(String[] args) throws Exception {
		LinkedHashMap<String, String> tableList = new LinkedHashMap<>();
		// LinkedHashMap<String, String> temp = new LinkedHashMap<>();

		tableList.put("main", "game.csv");

		ArrayList<TableProperty> tableProperties = new ArrayList<TableProperty>();
		// IndexOperaParamUtil.getDataTableParam("chenmin@cmss.chinamobile.com",
		// "games", tableProperties, temp);
		System.out.println(process("chenmin@cmss.chinamobile.com", "games", tableList, tableProperties, "1"));
	}

	/**
	 * 
	 * @Title: process
	 * @Description: 入口
	 * @param userId
	 * @param appId
	 * @param tableList
	 * @param tableProperties
	 * @param type
	 *            更新的类型 1整条，2部分
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String userId, String appId, LinkedHashMap<String, String> tableList,
			ArrayList<TableProperty> tableProperties, String type) {
		boolean flag = true;
		try {

			if (tableList.size() == 1) {// 判断是不是单表应用，如果是单表应用则不经过数据库

				String filePath = "";
				for (Entry<String, String> entry : tableList.entrySet()) {
					// 获取文件名
					filePath = entry.getValue();
				}
				if (!UpdateIndex.singleProcess(userId, appId, tableProperties, filePath, type)) {
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
					if (!UpdateIndex.process(userId, appId, tableProperties)) {
						flag = false;
					}
				}
				// 删除数据文件
				DeleteFiles.process(tableList);
				// 清空数据库
				ClearTable.process(tableList, userId, appId);
			}
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "UPDATE DATA END");

			new DataApi().setNumToDataBase(userId, appId);

		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexUpdateDataError, LogUtil.INDEX_LOG, e);
			flag = false;
		}

		return flag;
	}
}
