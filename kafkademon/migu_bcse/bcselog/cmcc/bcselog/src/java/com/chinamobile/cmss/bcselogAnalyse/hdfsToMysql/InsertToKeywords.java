package com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.bean.Key;
import com.chinamobile.cmss.bcselogAnalyse.bean.Pair;
import com.chinamobile.cmss.bcselogAnalyse.bean.TopNPriorityQueue;
import com.chinamobile.cmss.bcselogAnalyse.redis.RedisOp;
import com.chinamobile.cmss.bcselogAnalyse.statistic.WordStatistic;
import com.chinamobile.cmss.bcselogAnalyse.tools.Config;
import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;

/**
 * @ClassName: InsertToKeywords
 * @Description: 把搜索关键词写入mysql数据库
 * @author: yangjing
 * @date: 2016年2月1日 上午9:31:22
 */
public class InsertToKeywords {
	private static Logger logger = Logger.getLogger(InsertToKeywords.class);
	private String filePath;

	public InsertToKeywords(String path) {
		this.filePath = path;
	}

	/**
	 * @Title: insertRecordToMysql
	 * @Description: 把搜索关键词写入mysql数据库，使用PreparedStatement类，批量写
	 * @param keywords
	 * @throws SQLException
	 * @return: boolean
	 */
	public static boolean insertRecordToMysql(HashMap<Key, TopNPriorityQueue> map) throws SQLException {
		if (map != null && !map.isEmpty()) {
			MysqlConn mysqlConn = new MysqlConn();
			Connection conn = mysqlConn.getConnection();
			// 屏蔽自动提交功能
			conn.setAutoCommit(false);
			String sql = "insert into HOTWORDS_DAY" + "(user_id,app_id,oper_time,keywords,search_count,flag)"
					+ "values(?,?,?,?,?,?)";
			try {
				Set<Key> sets = map.keySet();
				for (Key key : sets) {

					TopNPriorityQueue topNPriorityQueue = map.get(key);
					List<Pair> values = topNPriorityQueue.sortedList(true);

					for (Pair keyword : values) {

						if (!key.isEmpty()) {
							PreparedStatement ps = conn.prepareStatement(sql);

							ps.setString(1, key.getUser_id());
							ps.setString(2, key.getApp_id());
							ps.setString(3, WordStatistic.getPastDate(0));
							ps.setCharacterStream(4,
									new InputStreamReader(new ByteArrayInputStream(keyword.getWord().getBytes())));
							ps.setString(5, keyword.getCount() + "");
							ps.setString(6, key.getFlag());
							if (keyword.getWord().trim().length() > 0)

							{
								ps.addBatch();
								try {
									ps.executeBatch();
									conn.commit();
								} catch (Exception e) {
									// conn.rollback();
									System.out.println("keyword is :" + ps.toString());
									e.printStackTrace();
									logger.error("数据提交失败，执行回滚" + e);
								} finally {
									ps.close();
								}
							}
						}
					}
				}
				logger.info("数据提交成功");
			} finally {

				try {
					RedisOp.insertHotkeyDay(map);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 释放资源
				conn.close();

			}
			return true;
		}
		logger.info("前一天的搜索关键词为空");
		return false;

	}

	/**
	 * @Title: process
	 * @Description: 读取关键词文件，写入数据库
	 * @throws IOException
	 * @return: void
	 */
	public void process() throws IOException {
		File file = new File(this.filePath);
		if (file.exists() && file.length() > 0) {
			BufferedReader reader = null;
			HashMap<Key, TopNPriorityQueue> map = new HashMap<Key, TopNPriorityQueue>();

			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); // 解决乱码问题
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					String[] arr = tempString.split("\\s+"); // \\s表示空格、回车、换行等空白符,+号表示一个或多个的意思
					if (arr.length == 6) {

						// String oper_time = arr[0].trim();
						String key_words = arr[1].trim().replace("&", " "); // 把关键词中的“&”替换回空格
						String user_id = arr[2].trim();
						String app_id = arr[3].trim();
						String flag = arr[4].trim();
						String search_count = arr[5].trim();

						Key key = new Key(user_id, app_id, flag);
						Pair pair = new Pair(key_words, Integer.parseInt(search_count));
						if (map.containsKey(key)) {
							map.get(key).add(pair);
						} else {
							TopNPriorityQueue topNPriorityQueue = new TopNPriorityQueue(
									Integer.parseInt(Config.properties.getProperty("TOP_N", "100").trim()));
							topNPriorityQueue.add(pair);
							map.put(key, topNPriorityQueue);
						}
					}

				}
				logger.info("把topN热词数据插入数据库");
				insertRecordToMysql(map);
			} catch (Exception e) {
				e.printStackTrace();
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
			logger.info("热词文件不存在或内容为空：" + this.filePath);
		}
	}

	public static void main(String[] args) {
		TopNPriorityQueue topNPriorityQueue = new TopNPriorityQueue(6);
		for (int i = 0; i < 10; i++) {
			Pair searchCountFormat = new Pair("0", i);

			topNPriorityQueue.add(searchCountFormat);
		}

		Key key = new Key("1", "1", "1");
		Key key2 = new Key("1", "1", "1");

		HashMap<Key, String> hashMap = new HashMap<Key, String>();
		hashMap.put(key, key.toString());
		if (hashMap.containsKey(key2))
			System.out.println("exist");

		Iterator<Pair> it = topNPriorityQueue.sortedList(true).iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

}
