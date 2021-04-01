package com.chinamobile.cmss.bcselogAnalyse.statistic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.bean.Key;
import com.chinamobile.cmss.bcselogAnalyse.bean.Pair;
import com.chinamobile.cmss.bcselogAnalyse.bean.TopNPriorityQueue;
import com.chinamobile.cmss.bcselogAnalyse.redis.RedisOp;
import com.chinamobile.cmss.bcselogAnalyse.tools.Config;
import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;

/**
 * @ClassName: WordStatistic
 * @Description: 搜索热词统计
 * @author: yangjing
 * @date: 2016年6月13日 上午11:09:57
 */
public class WordStatistic {

	private static Logger logger = Logger.getLogger(WordStatistic.class);

	public static void main(String[] args) {
		HashMap<Key, TopNPriorityQueue> hashMap = new HashMap<Key, TopNPriorityQueue>();
		// 更新周数据
		ArrayList<String> weekDays = getWeek();
		getTopN(weekDays, hashMap);

		Set<Entry<Key, TopNPriorityQueue>> sets = hashMap.entrySet();
		for (Entry<Key, TopNPriorityQueue> entry : sets) {
			System.out.println(entry.getKey());
			TopNPriorityQueue queue = entry.getValue();
			List list = queue.sortedList(true);
			System.out.println(list.toString());
		}

		
	}

	// 遍历本地已分析结果，找到周和月的topN数据插入数据库
	public static void process() {
		HashMap<Key, TopNPriorityQueue> hashMap = new HashMap<Key, TopNPriorityQueue>();
		// 更新周数据
		ArrayList<String> weekDays = getWeek();
		hashMap = getTopN(weekDays, hashMap);
		// 更新周表数据库
		InsertIntoWeek(hashMap);
		// 更新redis
		try {
			RedisOp.insertHotkeyWeek(hashMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 更新月数据
		ArrayList<String> monthDays = getMonth();
		monthDays.removeAll(weekDays);
		hashMap = getTopN(monthDays, hashMap);
		// 更新月表数据库
		InsertIntoMonth(hashMap);
		// 更新月redis
		try {
			RedisOp.insertHotkeyMonth(hashMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 插入周表
	 * 
	 * @param hashMap
	 */
	public static void InsertIntoWeek(HashMap<Key, TopNPriorityQueue> hashMap) {
		MysqlConn mysqlConn = new MysqlConn();
		// 往热词周表插入前一天的数据，插入之前先清空前一天的数据
		String delete = "delete from HOTWORDS_WEEK where OPER_TIME=date_sub(curdate(),interval 1 day); ";
		mysqlConn.deleteSQL(delete);
		mysqlConn.release();
		String insert = "insert into HOTWORDS_WEEK(USER_ID,APP_ID,OPER_TIME,KEYWORDS,SEARCH_COUNT,FLAG) values(?,?,?,?,?,?)";
		excuteSql(hashMap, insert);
	}

	/**
	 * 插入月表
	 * 
	 * @param hashMap
	 */
	public static void InsertIntoMonth(HashMap<Key, TopNPriorityQueue> hashMap) {
		MysqlConn mysqlConn = new MysqlConn();
		// 往热词月表插入前一天的数据，插入之前先清空前一天的数据
		String delete = "delete from HOTWORDS_MONTH where OPER_TIME=date_sub(curdate(),interval 1 day); ";
		mysqlConn.deleteSQL(delete);
		mysqlConn.release();
		String insert = "insert into HOTWORDS_MONTH(USER_ID,APP_ID,OPER_TIME,KEYWORDS,SEARCH_COUNT,FLAG) values(?,?,?,?,?,?)";
		excuteSql(hashMap, insert);
	}

	/**
	 * 执行sql
	 */
	public static void excuteSql(HashMap<Key, TopNPriorityQueue> hashMap, String insert) {
		MysqlConn mysqlConn = new MysqlConn();
		Connection conn = mysqlConn.getConnection();
		// 屏蔽自动提交功能
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			logger.info(e1);
			e1.printStackTrace();
		}
		Set<Key> sets = hashMap.keySet();
		for (Key key : sets) {
			TopNPriorityQueue topNPriorityQueue = hashMap.get(key);
			List<Pair> values = topNPriorityQueue.sortedList(true);

			for (Pair keyword : values) {
				try {
					if (!key.isEmpty()) {
						PreparedStatement ps = conn.prepareStatement(insert);

						ps.setString(1, key.getUser_id());
						ps.setString(2, key.getApp_id());
						ps.setString(3, getPastDate(0));
						ps.setCharacterStream(4,
								new InputStreamReader(new ByteArrayInputStream(keyword.getWord().getBytes())));
						ps.setString(5, keyword.getCount() + "");
						ps.setString(6, key.getFlag());
						if (keyword.getWord().trim().length() > 0) {
							ps.addBatch();
							try {
								ps.executeBatch();
								conn.commit();
							} catch (Exception e) {
								System.out.println("keyword is :" + ps.toString());
								e.printStackTrace();
								logger.error("数据提交失败" + e);
							} finally {
								ps.close();
							}
						}
					}
				} catch (Exception e) {
					logger.info(e);
				}
			}
		}
		logger.info("数据提交成功");
		mysqlConn.release();
	}

	/**
	 * 遍历文件，获取topN
	 * 
	 * @param days
	 * @param hashMap
	 * @return
	 */
	public static HashMap<Key, TopNPriorityQueue> getTopN(ArrayList<String> days,
			HashMap<Key, TopNPriorityQueue> hashMap) {

		for (String day : days) {
			String path = Config.properties.getProperty("logoutput_path").trim() + day + File.separatorChar
					+ "keywords";
			try {
				readFile(path, hashMap);
			} catch (Exception e) {
				logger.info("file not found:" + e.getMessage());
				//e.printStackTrace();
			}
		}

		return hashMap;
	}

	public static void readFile(String filePath, HashMap<Key, TopNPriorityQueue> map) throws Exception {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8")); // 解决乱码问题
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

		reader.close();
	}

	/**
	 * 获取近一周的数据
	 * 
	 * @return
	 */
	static public ArrayList<String> getWeek() {
		ArrayList<String> arrayList = new ArrayList<String>(7);
		for (int i = 0; i < 7; i++) {
			arrayList.add(getPastDate(i));
		}
		System.out.println(arrayList.toString());
		return arrayList;
	}

	/**
	 * 获取近一个月的数据
	 * 
	 * @return
	 */
	static public ArrayList<String> getMonth() {
		ArrayList<String> arrayList = new ArrayList<String>(7);
		for (int i = 0; i < 30; i++) {
			arrayList.add(getPastDate(i));
		}
		System.out.println(arrayList.toString());
		return arrayList;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past - 1);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		// System.out.println(result);
		return result;
	}

}
