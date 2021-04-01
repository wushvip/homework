package com.chinamobile.cmss.bcse.index.entry;

import java.util.ArrayList;

import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.RmTableSql;
import com.chinamobile.cmss.bcse.index.indexManager.DeleteCollection;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: DeleteApp
 * @Description: 删除应用的接口
 * @author: jinjing
 * @date: 2016年2月16日 上午9:31:02
 */
public class DeleteApp {

	/**
	 * 
	 * @Title: process
	 * @Description: 入口
	 * @param userId
	 * @param appId
	 * @param tableProperties
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String userId, String appId, ArrayList<TableProperty> tableProperties) {

		try {

			String coreName = appId;
			// 删除数据库相关表的信息
			RmTableSql.process(tableProperties, userId, appId);
			// 删除索引相关信息
			DeleteCollection.process(coreName);

			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "DeleteApp END");

		} catch (Exception e) {
			// e.printStackTrace();
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexDeleteAppError, LogUtil.INDEX_LOG, e);
			return false;
		}

		return true;
	}
}
