package com.chinamobile.cmss.bcse.index.dao;

import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @ClassName: DBPool
 * @Description: C3P0连接池
 * @author: jinjing
 * @date: 2016年2月16日 下午2:55:27
 */
public class DBPool {

	private static ComboPooledDataSource dataSource;

	static {
		try {
			dataSource = new ComboPooledDataSource();
			dataSource.setUser("root");
			dataSource.setPassword("bc-se");
			dataSource.setJdbcUrl("jdbc:mysql://10.133.5.140:3306/bcse_data?autoReconnect=true");
			dataSource.setDriverClass("com.mysql.jdbc.Driver");

			// 设置初始连接池的大小！
			dataSource.setInitialPoolSize(3);
			// 设置连接池的最小值！
			dataSource.setMinPoolSize(1);
			// 设置连接池的最大值！
			dataSource.setMaxPoolSize(10);
			// 设置连接池中的最大Statements数量！
			dataSource.setMaxStatements(20);
			// 设置连接池的最大空闲时间！
			dataSource.setMaxIdleTime(600);
			dataSource.setCheckoutTimeout(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		ResultSet resultSet = DBPool.getConnection().createStatement().executeQuery("select * from USER_INFO");
		while(resultSet.next()){
			System.out.println(resultSet.getString("USER_ID"));
		}
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("无法从数据源获取连接 ", e);
		}
	}

	public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}