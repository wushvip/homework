package com.chinamobile.cmss.bcse.index.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.tool.ConvertToCSV;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: GetDataSql
 * @Description: 获取数据库或者视图内容
 * @author: jinjing
 * @date: 2016年2月16日 下午3:08:12
 */
public class GetDataSql {

	/**
	 * 
	 * @Title: process
	 * @Description: 从数据库中将视图内容导入到本地文件
	 * @param userId
	 * @param appId
	 * @param FilePath
	 * @param tableProperties
	 * @return
	 * @return: String
	 */
	public static String process(String userId, String appId, String FilePath,
			ArrayList<TableProperty> tableProperties) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet reSet = null;
		try {
			String sql = "";

			if (1 == tableProperties.size()) { // 单表应用
				sql = "select * from   `" + tableProperties.get(0).getTableId() + "`";
			} else {
				sql = "select * from   " + appId + Config.INDEX_SPLIT + "view";
			}

			connection = DBPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			reSet = preparedStatement.executeQuery();

			FilePath = FilePath + appId + Config.INDEX_SPLIT + "view.csv";
			// 写CSV文件
			ConvertToCSV.writeToCsv(reSet, FilePath);
			return FilePath;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(userId, appId, "", LogUtil.INDEX_LOG, e);
		} finally { // 关闭链接
			DBPool.close(connection, preparedStatement, reSet);
		}
		return "";
	}

}
