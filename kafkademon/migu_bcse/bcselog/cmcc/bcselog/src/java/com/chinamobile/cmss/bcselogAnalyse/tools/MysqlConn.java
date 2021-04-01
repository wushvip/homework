package com.chinamobile.cmss.bcselogAnalyse.tools;

import java.sql.*;

import org.apache.log4j.Logger;

/**
 * @ClassName: MysqlConn
 * @Description:连接mysql数据库
 * @author: yangjing
 * @date: 2016年2月1日 上午9:46:50
 */
public class MysqlConn {

	private Logger logger = Logger.getLogger(MysqlConn.class);
	private Connection conn = null;
	private PreparedStatement statement = null;

	public MysqlConn() {
		// 加载驱动
		try {
			Class.forName(Config.DRIVER);
			conn = DriverManager.getConnection(Config.URL, Config.USER, Config.PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return conn;
	}

	/**
	 * @Title: selectSQL
	 * @Description: 查询
	 * @param sql
	 * @return: ResultSet
	 */
	public ResultSet selectSQL(String sql) {
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			logger.error(e);
		}
		return rs;
	}

	/**
	 * @Title: insertSQL
	 * @Description: 插入
	 * @param sql
	 * @return: boolean
	 */
	public boolean insertSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("插入数据库时出错：", e);
		} catch (Exception e) {
			logger.error("插入时出错：", e);
		}
		return false;
	}

	/**
	 * @Title: deleteSQL
	 * @Description: 删除
	 * @param sql
	 * @return: boolean
	 */
	public boolean deleteSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("插入数据库时出错：", e);
		} catch (Exception e) {
			logger.error("插入时出错：", e);
		}
		return false;
	}

	/**
	 * @Title: updateSQL
	 * @Description: 修改
	 * @param sql
	 * @return: boolean
	 */
	public boolean updateSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("更新数据库时出错：", e);
		} catch (Exception e) {
			logger.error("更新时出错：", e);
		}
		return false;
	}

	/**
	 * @Title: release
	 * @Description: 断开连接
	 * @return: void
	 */
	public void release() {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		statement = null;
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		conn = null;
	}

}