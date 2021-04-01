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

import com.chinamobile.cmss.bcselogAnalyse.bean.NumFoundDay;
import com.chinamobile.cmss.bcselogAnalyse.bean.Range;
import com.chinamobile.cmss.bcselogAnalyse.tools.Constants;
import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;

public class InsertToNumFound {

	private static Logger logger = Logger.getLogger(InsertToNumFound.class);

	/**
	 * 写入数据库表NUM_FOUND_DAY
	 * 
	 * @throws IOException
	 */
	public static void process() throws IOException {
		File file = new File(Constants.LOG_OUTPUT_PATH_NUM_FOUND);
		// File file = new File("part-r-00000");
		if (file.exists() && file.length() > 0) {
			BufferedReader reader = null;
			List<NumFoundDay> list = new ArrayList<>();
			int count = 0;
			try {
				deleteRecord();
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); // 解决乱码问题
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					String[] arr = tempString.split("\\s+"); // \\s表示空格、回车、换行等空白符,+号表示一个或多个的意思
					if (arr.length > 3) {
						String userId = arr[0];
						String appId = arr[1];
						String date = arr[2];
						for (int i = 3; i < arr.length; i++) {
							if (arr[i].split("=").length == 2) {
								String rangeToString = arr[i].split("=")[0];
								Integer searchCount = Integer.parseInt(arr[i].split("=")[1]);
								Range range = Range.getRange(rangeToString);
								if (range != null) {
									list.add(new NumFoundDay(userId, appId, date, range, searchCount));
									if (list.size() >= Constants.BATCH_INSERT_COUNT) {
										logger.info("把第" + (count + 1) + "至" + (count + list.size()) + "条数据插入数据库");
										insertRecord(list);
										count += list.size();
										list.clear();
									}
								}
							}
						}
					}
				}
				logger.info("把第" + (count + 1) + "至" + (count + list.size()) + "条数据插入数据库");
				insertRecord(list);
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
			logger.info("纠错统计文件文件不存在或内容为空：" + Constants.LOG_OUTPUT_PATH_NUM_FOUND);
		}
	}

	/**
	 * 插入之前先清空前一天记录
	 */
	public static void deleteRecord() {
		MysqlConn mysqlConn = new MysqlConn();
		String delete = "delete from NUM_FOUND_DAY where OPER_TIME='" + Constants.YESTERDAY + "';";
		mysqlConn.deleteSQL(delete);
		mysqlConn.release();
	}

	/**
	 * 批量插入数据
	 * 
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public static boolean insertRecord(List<NumFoundDay> list) throws SQLException {
		if (null != list && !list.isEmpty()) {
			MysqlConn mysqlConn = new MysqlConn();
			Connection conn = mysqlConn.getConnection();
			// 屏蔽自动提交功能
			conn.setAutoCommit(false);
			String sql = "insert into NUM_FOUND_DAY(USER_ID, APP_ID, OPER_TIME, RANGE_MIN, RANGE_MAX, SEARCH_COUNT) " + "values(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			for (NumFoundDay bean : list) {
				ps.setString(1, bean.getUserId());
				ps.setString(2, bean.getAppId());
				ps.setString(3, bean.getDate());
				ps.setInt(4, bean.getRange().getMin());
				ps.setInt(5, bean.getRange().getMax());
				ps.setInt(6, bean.getSearchCount());
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
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		process();
	}

}
