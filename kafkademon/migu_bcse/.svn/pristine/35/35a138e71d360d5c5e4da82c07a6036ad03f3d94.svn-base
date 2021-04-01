package com.chinamobile.cmss.bcse.index.dao;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: ClearTable
 * @Description: 清空数据库表
 * @author: jinjing
 * @date: 2016年2月16日 下午2:52:04
 */
public class ClearTable {

	/**
	 * 
	 * @Title: process
	 * @Description: 入口
	 * @param tableList
	 * @param userId
	 * @param appId
	 * @return
	 * @return: boolean
	 */
	public static boolean process(LinkedHashMap<String, String> tableList, String userId, String appId) {

		if (0 == tableList.size())
			return false;

		String tableId = "";
		try {
			// 遍历每一张表，并且更新数据
			for (Entry<String, String> entry : tableList.entrySet()) {
				// 获取表名
				tableId = entry.getKey();
				// 删除每一张表
				clearTable(tableId, appId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: clearTable
	 * @Description: 清空一张数据库表
	 * @param tableId
	 * @param appId
	 * @throws Exception
	 * @return: void
	 */
	private static void clearTable(String tableId, String appId) throws Exception {

		String sql = "Truncate Table `" + tableId + "`";
		ExcuteSQL.processNoReturn(sql);
	}
}
