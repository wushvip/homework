package com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.tools.Config;
import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;

/**
 * @ClassName: InsertToSearchCount
 * @Description: 把搜索次数写入mysql数据库
 * @author: yangjing
 * @date: 2016年2月1日 上午9:39:00
 */
public class InsertToSearchCount {
	private static Logger logger = Logger.getLogger(InsertToSearchCount.class);
	private String filePath;

	public InsertToSearchCount(String path) {
		this.filePath = path;
	}

	/**
	 * @Title: insertRecordToMysql
	 * @Description: 把搜索次数写入mysql数据库，使用PreparedStatement类，批量写
	 * @param searchcounts
	 * @throws SQLException
	 * @return: boolean
	 */
	public boolean insertRecordToMysql(List<SearchCountFormat> searchcounts) throws SQLException {
		if (searchcounts != null && !searchcounts.isEmpty()) {
			MysqlConn mysqlConn = new MysqlConn();
			Connection conn = mysqlConn.getConnection();
			// 屏蔽自动提交功能
			conn.setAutoCommit(false);

			String sql = "insert into SEARCH_COUNT_HOUR" + "(USER_ID,APP_ID,OPER_DATE,OPER_HOUR,SEARCH_COUNT,FLAG)"
					+ "values(?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			for (SearchCountFormat searchcount : searchcounts) {
				ps.setString(1, searchcount.getUSER_ID());
				ps.setString(2, searchcount.getAPP_ID());
				ps.setString(3, searchcount.getOPER_DATE());
				ps.setString(4, searchcount.getOPER_HOUR());
				ps.setString(5, searchcount.getSEARCH_COUNT());
				ps.setString(6, searchcount.getFLAG());
				if (searchcount.getOPER_HOUR().trim().length() > 0)
					ps.addBatch();
			}
			try {
				ps.executeBatch();
				conn.commit();
				logger.info("数据提交成功");
			} catch (SQLException e) {
				conn.rollback();
				logger.error("数据提交失败，执行回滚：" + e);
			} finally {
				// 恢复自动提交功能
				conn.setAutoCommit(true);
				// 释放资源
				conn.close();
				ps.close();
			}
			return true;
		}
		logger.info("前一天的搜索次数为空");
		return false;
	}

	/**
	 * @Title: process
	 * @Description: 读取搜索次数文件，写入数据库
	 * @throws IOException
	 * @return: void
	 */
	public void process() throws IOException {
		File file = new File(this.filePath);
		if (file.exists() && file.length() > 0) {
			BufferedReader reader = null;
			List<SearchCountFormat> searchcounts = new ArrayList<SearchCountFormat>();
			int dcount = 0;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), "UTF-8")); // 解决乱码问题
				// 往搜索次数小时表插入前一天的数据，插入之前先清空前一天的数据
				String delete = "delete from SEARCH_COUNT_HOUR where OPER_DATE=date_sub(curdate(),interval 1 day); ";
				MysqlConn mysqlConn = new MysqlConn();
				mysqlConn.deleteSQL(delete);
				mysqlConn.release();

				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					// System.out.println(tempString);
					String[] arr = tempString.split("\\s+"); // \\s表示空格、回车、换行等空白符,+号表示一个或多个的意思
					if (arr.length == 6) {
						dcount++;
						String OPER_DATE = arr[0].trim();
						String OPER_HOUR = arr[1].trim();
						String USER_ID = arr[2].trim();
						String APP_ID = arr[3].trim();
						String FLAG = arr[4].trim();
						String SEARCH_COUNT = arr[5].trim();
						SearchCountFormat scf = new SearchCountFormat(USER_ID, APP_ID, OPER_DATE, OPER_HOUR,
								SEARCH_COUNT, FLAG);
						searchcounts.add(scf);
					}

					if (dcount % Config.NUM == 0) {
						logger.info("把第" + (dcount + 1 - Config.NUM) + "至" + dcount + "条数据插入数据库");
						insertRecordToMysql(searchcounts);
						searchcounts.clear();
					}
				}
				logger.info("把第" + (dcount + 1 - dcount % Config.NUM) + "至" + dcount + "条数据插入数据库");
				insertRecordToMysql(searchcounts);
			} catch (Exception e) {
				logger.error(e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
						reader = null;
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
		} else {
			logger.info("搜索次数文件不存在或内容为空：" + this.filePath);
		}
	}

}
