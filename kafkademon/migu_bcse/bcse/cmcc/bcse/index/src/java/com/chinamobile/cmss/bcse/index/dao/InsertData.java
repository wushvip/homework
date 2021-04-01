package com.chinamobile.cmss.bcse.index.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.index.tool.ReadFromCsv;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: InsertData
 * @Description: 向数据库表中插入数据记录
 * @author: jinjing
 * @date: 2016年2月16日 下午3:09:20
 */
public class InsertData {

	public static void main(String[] args) throws SQLException {
		/*
		 * String sql =
		 * "insert into neuDqbingATqqDcom_test_test(id,name,sex,address,company) values(\"3\",\"qb2\",\"男\",\"辽宁省沈阳市和平区文化路\",\"东北大学\")"
		 * ; PreparedStatement pStatement = ConnectMysql.GetMysqlInstance()
		 * .prepareStatement(sql); pStatement.execute();
		 */
	}

	/**
	 * 
	 * @Title: process
	 * @Description: 入口
	 * @param tableList
	 * @param userId
	 * @param appId
	 * @return
	 * @return: boolean
	 */
	public static boolean process(LinkedHashMap<String, String> tableList, String userId, String appId) {

		if (0 == tableList.size())
			return false;

		String tableId = "";
		try {
			// 遍历每一张表，并且更新数据
			for (Entry<String, String> entry : tableList.entrySet()) {

				tableId = entry.getKey();
				String filePath = entry.getValue();
				// 更新每一张表
				/*
				 * if (!insertTable(ReadFromCsv.read(filePath, ','), tableId,
				 * userId, appId)) return false;
				 */
			}
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: insertTable
	 * @Description: 将对应的文件插入对应的数据库表
	 * @param lineList
	 * @param tableId
	 * @param userId
	 * @param appId
	 * @return
	 * @return: boolean
	 */
	private static boolean insertTable(List<String[]> lineList, String tableId, String userId, String appId) {

		try {
			System.out.println("start");
			StringBuffer sb = new StringBuffer();

			// 获取文件第一行的列名
			String[] tableTitle = lineList.get(0);
			String tempString = "";
			for (String string : tableTitle) {
				tempString += (string + ",");
			}
			tempString = tempString.substring(0, tempString.lastIndexOf(","));

			// 获取数据库表的列名
			HashSet<String> hSet = GetTableHeader.process(tableId, userId, appId);

			/**
			 * 验证文件和数据库字段是否一致
			 */
			if (hSet.size() != tableTitle.length) {
				System.out.println("db table size:" + hSet.size());
				System.out.println("file title size:" + tableTitle.length);
				System.out.println("coloum error1");
				Exception e = new Exception("数据列数不正确");
				LogUtil.loggerEntrance(userId, appId, ExceptionConstants.UpdateIndexError, LogUtil.INDEX_LOG, e);
				return false;
			} else {
				for (String string : tableTitle) {

					if (hSet.contains(string)) {
						hSet.remove(string);
						System.out.println(hSet.size());
					}
				}
				if (0 != hSet.size()) {
					Exception e = new Exception("数据格式不正确");
					LogUtil.loggerEntrance(userId, appId, ExceptionConstants.UpdateIndexError, LogUtil.INDEX_LOG, e);
					System.out.println("coloum error2");
					return false;
				}
				System.out.println("coloums ok");
			}

			System.out.println("line count:" + lineList.size());

			/**
			 * 开始上传数据，数据量1000000万条以上用批处理方式
			 */
			if (lineList.size() > 100000) {
				ArrayList<String> sqls = new ArrayList<String>();
				// 遍历每一行
				for (int j = 1; j < lineList.size(); j++) {
					String[] line = lineList.get(j);
					sb.setLength(0);
					// 此处利用stringBuffer有助于提升效率
					sb.append("insert into `" + tableId + "`(" + tempString + ")" + " values(");
					String temp = "\"" + line[0] + "\"";
					for (int i = 1; i < line.length; i++) {
						temp = temp + "," + "\"" + line[i] + "\"";
					}
					sb.append(temp);
					sb.append(")");
					sqls.add(sb.toString());
				}
				ExcuteSQL.processNoReturn(sqls);

			} else { // 小于100万条，每一条插入
				/*
				 * Connection connection = DBPool.getConnection();
				 * PreparedStatement preparedStatement = null;
				 */
				// 遍历每一行
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				for (int j = 1; j < lineList.size(); j++) {
					try {
						String[] line = lineList.get(j);
						sb.setLength(0);
						sb.append("insert into `" + tableId + "`(" + tempString + ")" + " values(");
						String temp = "\"" + line[0] + "\"";
						for (int i = 1; i < line.length; i++) {
							temp = temp + "," + "\"" + line[i] + "\"";
						}
						sb.append(temp);
						sb.append(")");
						String sql = sb.toString();
						System.out.println("sql:" + sql);
						connection = DBPool.getConnection();
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.execute();
					} catch (Exception e) {
						// 可能会打印很多异常
						e.printStackTrace();
					} finally {
						// 无论如何要释放链接
						DBPool.close(connection, preparedStatement, null);
					}
				}
				/* DBPool.close(connection, preparedStatement, null); */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
