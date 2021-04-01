package com.chinamobile.cmss.bcse.index.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: GetTableHeader
 * @Description: 获取数据表表头
 * @author: jinjing
 * @date: 2016年2月16日 下午3:08:35
 */
public class GetTableHeader {

	/**
	 * 
	 * @Title: process
	 * @Description: 处理入口
	 * @param tableId
	 * @param userId
	 * @param appId
	 * @return
	 * @return: HashSet<String>
	 */
	public static HashSet<String> process(String tableId, String userId, String appId) {

		HashSet<String> headerSet = new HashSet<String>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rSet = null;
		try {
			String sql = "show columns from `" + tableId + "`";

			connection = DBPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			rSet = preparedStatement.executeQuery();

			while (rSet.next()) { // 加入每一个列名
				headerSet.add(rSet.getString("Field"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(userId, appId, "", LogUtil.INDEX_LOG, e);
		} finally {
			DBPool.close(connection, preparedStatement, rSet);
		}
		return headerSet;
	}
}
