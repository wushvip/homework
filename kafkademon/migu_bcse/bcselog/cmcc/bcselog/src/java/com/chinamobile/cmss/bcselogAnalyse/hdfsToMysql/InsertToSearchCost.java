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
 * @ClassName: InsertToSearchCost
 * @Description: 把搜索耗时写入mysql数据库
 * @author: yangjing
 * @date: 2016年2月1日 上午9:36:30
 */
public class InsertToSearchCost {
	private static Logger logger = Logger.getLogger(InsertToSearchCost.class);
	private String filePath;

	public InsertToSearchCost(String path) {
		this.filePath = path;
	}

	/**
	 * @Title: insertRecordToMysql
	 * @Description: 把搜索耗时写入mysql数据库，使用PreparedStatement类，批量写
	 * @param costs
	 * @throws SQLException
	 * @return: boolean
	 */
	public boolean insertRecordToMysql(List<SearchCostFormat> costs) throws SQLException {
		if (costs != null && !costs.isEmpty()) {
			MysqlConn mysqlConn = new MysqlConn();
			Connection conn = mysqlConn.getConnection();
			// 屏蔽自动提交功能
			conn.setAutoCommit(false);

			String sql = "insert into SEARCH_COST_HOUR"
					+ "(USER_ID,APP_ID,OPER_DATE,OPER_HOUR,MAX_COST,MIN_COST,AVG_COST,FLAG)"
					+ "values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			for (SearchCostFormat cost : costs) {
				ps.setString(1, cost.getUSER_ID());
				ps.setString(2, cost.getAPP_ID());
				ps.setString(3, cost.getOPER_DATE());
				ps.setString(4, cost.getOPER_HOUR());
				ps.setString(5, cost.getMAX_COST());
				ps.setString(6, cost.getMIN_COST());
				ps.setString(7, cost.getAVG_COST());
				ps.setString(8, cost.getFLAG());
				if (cost.getOPER_HOUR().trim().length() > 0)
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
		logger.info("前一天的搜索耗时为空");
		return false;
	}

	/**
	 * @Title: process
	 * @Description: 读取搜索耗时文件，写入数据库
	 * @throws IOException
	 * @return: void
	 */
	public void process() throws IOException {
		File file = new File(this.filePath);
		if (file.exists() && file.length() > 0) {
			BufferedReader reader = null;
			List<SearchCostFormat> costs = new ArrayList<SearchCostFormat>();
			int dcount = 0;
			try {
				MysqlConn mysqlConn = new MysqlConn();
				// 往搜索次数小时表插入前一天的数据，插入之前先清空前一天的数据
				String delete = "delete from SEARCH_COST_HOUR where OPER_DATE=date_sub(curdate(),interval 1 day); ";
				mysqlConn.deleteSQL(delete);
				mysqlConn.release();

				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); // 解决乱码问题
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					String[] arr = tempString.split("\\s+"); // \\s表示空格、回车、换行等空白符,+号表示一个或多个的意思
					if (arr.length == 8) {
						dcount++;
						String OPER_DATE = arr[0].trim();
						String OPER_HOUR = arr[1].trim();
						String USER_ID = arr[2].trim();
						String APP_ID = arr[3].trim();
						String FLAG = arr[4].trim();
						String MIN_COST = arr[5].trim();
						String MAX_COST = arr[6].trim();
						String AVG_COST = arr[7].trim();

						SearchCostFormat scf = new SearchCostFormat(USER_ID, APP_ID, OPER_DATE, OPER_HOUR, AVG_COST,
								MIN_COST, MAX_COST, FLAG);
						costs.add(scf);
					}
					if (dcount % Config.NUM == 0) {
						logger.info("把第" + (dcount + 1 - Config.NUM) + "至" + dcount + "条数据插入数据库");
						insertRecordToMysql(costs);
						costs.clear();
					}
				}
				logger.info("把第" + (dcount + 1 - dcount % Config.NUM) + "至" + dcount + "条数据插入数据库");
				insertRecordToMysql(costs);

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
			logger.info("搜索耗时文件不存在或内容为空：" + this.filePath);
		}
	}

}
