package com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.statistic.WordStatistic;
import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;

public class InsertSecondCount {

	private static Logger logger = Logger.getLogger(InsertToSearchCount.class);
	private String filePath;

	public InsertSecondCount(String path) {
		this.filePath = path;
	}

	public boolean insertRecordToMysql(HashMap<SecondCountkey, Integer> hashMap) throws SQLException {
		if (hashMap != null && !hashMap.isEmpty()) {
			MysqlConn mysqlConn = new MysqlConn();
			Connection conn = mysqlConn.getConnection();
			// 屏蔽自动提交功能
			conn.setAutoCommit(false);

			String sql = "insert into SEARCH_COUNT_SECOND" + "(USER_ID,APP_ID,OPER_TIME,SEARCH_COUNT,FLAG)"
					+ "values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			Set<SecondCountkey> keys = hashMap.keySet();
			System.out.println(keys.toArray().toString());
			for (SecondCountkey secondCountkey : keys) {
				ps.setString(1, secondCountkey.getUSER_ID());
				ps.setString(2, secondCountkey.getAPP_ID());
				ps.setString(3, WordStatistic.getPastDate(0));
				ps.setString(4, hashMap.get(secondCountkey) + "");
				ps.setString(5, secondCountkey.getFLAG());
				System.out.println("SEARCH_COUNT_SECOND" + ps.toString());
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

	public void process() {
		File file = new File(this.filePath);
		if (file.exists() && file.length() > 0) {
			BufferedReader reader = null;

			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), "UTF-8")); // 解决乱码问题
				// 往搜索次数小时表插入前一天的数据，插入之前先清空前一天的数据
				String delete = "delete from SEARCH_COUNT_SECOND where OPER_TIME=date_sub(curdate(),interval 1 day); ";
				MysqlConn mysqlConn = new MysqlConn();
				mysqlConn.deleteSQL(delete);
				mysqlConn.release();

				// 存储最大值
				HashMap<SecondCountkey, Integer> hashMap = new HashMap<SecondCountkey, Integer>();
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					String[] arr = tempString.split("\\s+"); // \\s表示空格、回车、换行等空白符,+号表示一个或多个的意思

					String OPER_TIME = arr[0].trim();
					String USER_ID = arr[2].trim();
					String APP_ID = arr[3].trim();
					String FLAG = arr[4].trim();
					String SEARCH_COUNT = arr[5].trim();

					SecondCountkey secondCountkey = new SecondCountkey(OPER_TIME, USER_ID, APP_ID, FLAG);
					if (hashMap.containsKey(secondCountkey)) {
						if (Integer.parseInt(SEARCH_COUNT) > hashMap.get(secondCountkey)) {
							hashMap.remove(secondCountkey);
							hashMap.put(secondCountkey, Integer.parseInt(SEARCH_COUNT));
						}
					} else {
						hashMap.put(secondCountkey, Integer.parseInt(SEARCH_COUNT));
					}

				}
				insertRecordToMysql(hashMap);
				logger.info("将最大的次数插入数据库数据插入数据库：" + hashMap.size());

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

	public static void main(String[] args) {
		SecondCountkey secondCountkey = new SecondCountkey("1", "2", "3", "4");
		SecondCountkey secondCountkey2 = new SecondCountkey("1", "2", "3", "4");
		HashMap<SecondCountkey, Integer> map = new HashMap<SecondCountkey, Integer>();
		map.put(secondCountkey, 1);
		System.out.println(map.containsKey(secondCountkey));
	}

	static class SecondCountkey {

		private String OPER_TIME;
		private String USER_ID;
		private String APP_ID;
		private String FLAG;

		public SecondCountkey(String OPER_TIME, String USER_ID, String APP_ID, String FLAG) {
			this.APP_ID = APP_ID;
			this.FLAG = FLAG;
			this.OPER_TIME = OPER_TIME;

			this.USER_ID = USER_ID;
		}

		@Override
		public int hashCode() {

			return (OPER_TIME + USER_ID + APP_ID + FLAG).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;
			if (((SecondCountkey) obj).APP_ID.equals(this.APP_ID) && ((SecondCountkey) obj).FLAG.equals(this.FLAG)
					&& ((SecondCountkey) obj).OPER_TIME.equals(this.OPER_TIME)
					&& ((SecondCountkey) obj).USER_ID.equals(USER_ID)) {
				return true;
			}
			return false;

		}

		public String getOPER_TIME() {
			return OPER_TIME;
		}

		public void setOPER_TIME(String oPER_TIME) {
			OPER_TIME = oPER_TIME;
		}

		public String getUSER_ID() {
			return USER_ID;
		}

		public void setUSER_ID(String uSER_ID) {
			USER_ID = uSER_ID;
		}

		public String getAPP_ID() {
			return APP_ID;
		}

		public void setAPP_ID(String aPP_ID) {
			APP_ID = aPP_ID;
		}

		public String getFLAG() {
			return FLAG;
		}

		public void setFLAG(String fLAG) {
			FLAG = fLAG;
		}

	}

}
