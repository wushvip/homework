package com.chinamobile.cmss.bcse.index.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * 
 * @ClassName: ExcuteSQL
 * @Description: 执行sql的类
 * @author: jinjing
 * @date: 2016年2月16日 下午3:07:31
 */
public class ExcuteSQL {

	/**
	 * 
	 * @Title: processNoReturn
	 * @Description: 执行无返回sql
	 * @param sql
	 * @throws Exception
	 * @return: void
	 */
	public static void processNoReturn(String sql) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 最后关闭链接
			DBPool.close(connection, preparedStatement, null);
		}
	}

	/**
	 * 
	 * @Title: processNoReturn
	 * @Description: 重载方法，执行无返回sqls集合
	 * @param sqls
	 * @return: void
	 */
	public static void processNoReturn(ArrayList<String> sqls) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBPool.getConnection();
			if (sqls.size() <= 0) {
				return;
			}
			preparedStatement = connection.prepareStatement(sqls.get(0));
			for (int i = 1; i < sqls.size(); i++) {
				preparedStatement.addBatch(sqls.get(i));
			}
			preparedStatement.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 最后关闭链接
			DBPool.close(connection, preparedStatement, null);
		}
	}

}
