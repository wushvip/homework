package com.chinamobile.cmss.bcse.index.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.constant.FieldType;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: RmTableSql
 * @Description: 删除表和关联的视图
 * @author: jinjing
 * @date: 2016年2月16日 下午3:10:01
 */
public class RmTableSql {

	public static void main(String[] args) {

		// 第一张表
		TableProperty tableProperty1 = new TableProperty();
		tableProperty1.setIsMainTable("1");
		tableProperty1.setTableId("main");
		HashMap<String, TableField> fieldMap1 = new HashMap<String, TableField>();

		TableField tableField11 = new TableField();
		tableField11.setKey("1");
		tableField11.setType(FieldType.INT);
		fieldMap1.put("id", tableField11);

		TableField tableField12 = new TableField();
		tableField12.setKey("0");
		tableField12.setType(FieldType.INT);
		tableField12.setOutTable("fourth");
		fieldMap1.put("name", tableField12);

		TableField tableField13 = new TableField();
		tableField13.setKey("0");
		tableField13.setType(FieldType.INT);
		tableField13.setOutTable("second");
		fieldMap1.put("iname", tableField13);
		tableProperty1.setFieldMap(fieldMap1);

		// 第二张表

		/*
		 * TableProperty tableProperty2 = new TableProperty();
		 * tableProperty2.setIsMainTable("0");
		 * tableProperty2.setTableName("second"); HashMap<String, TableField>
		 * fieldMap2 = new HashMap<String, TableField>();
		 * 
		 * TableField tableField21 = new TableField(); tableField21.setKey("1");
		 * tableField21.setType(FieldType.INT); fieldMap2.put("inameid",
		 * tableField21);
		 * 
		 * TableField tableField22 = new TableField(); tableField22.setKey("0");
		 * tableField22.setType(FieldType.INT);
		 * tableField22.setOutTable("third"); fieldMap2.put("year",
		 * tableField22);
		 * 
		 * tableProperty2.setFieldMap(fieldMap2);
		 * 
		 * // 第三张表
		 * 
		 * TableProperty tableProperty3 = new TableProperty();
		 * tableProperty3.setIsMainTable("0");
		 * tableProperty3.setTableName("third"); HashMap<String, TableField>
		 * fieldMap3 = new HashMap<String, TableField>();
		 * 
		 * TableField tableField31 = new TableField(); tableField31.setKey("1");
		 * tableField31.setType(FieldType.INT); fieldMap3.put("yearid",
		 * tableField31);
		 * 
		 * TableField tableField32 = new TableField(); tableField32.setKey("0");
		 * tableField32.setType(FieldType.INT); fieldMap3.put("year",
		 * tableField32);
		 * 
		 * tableProperty3.setFieldMap(fieldMap3);
		 * 
		 * // 第四张表
		 * 
		 * TableProperty tableProperty4 = new TableProperty();
		 * tableProperty4.setIsMainTable("0");
		 * tableProperty4.setTableName("fourth"); HashMap<String, TableField>
		 * fieldMap4 = new HashMap<String, TableField>();
		 * 
		 * TableField tableField41 = new TableField(); tableField41.setKey("1");
		 * tableField41.setType(FieldType.INT); fieldMap4.put("nameid",
		 * tableField41);
		 * 
		 * TableField tableField42 = new TableField(); tableField42.setKey("0");
		 * tableField42.setType(FieldType.INT); fieldMap4.put("name",
		 * tableField42);
		 * 
		 * tableProperty4.setFieldMap(fieldMap4);
		 */
		ArrayList<TableProperty> tableProperties = new ArrayList<TableProperty>();

		tableProperties.add(tableProperty1);
		/*
		 * tableProperties.add(tableProperty2);
		 * tableProperties.add(tableProperty3);
		 * tableProperties.add(tableProperty4);
		 */

		// 删除已经存在的相同表名的表
		RmTableSql.process(tableProperties, "jinjing", "test");

	}

	/**
	 * 
	 * @Title: process
	 * @Description: 对外的处理接口
	 * @param tableProperties
	 * @param userId
	 * @param appId
	 * @return
	 * @return: boolean
	 */
	public static boolean process(ArrayList<TableProperty> tableProperties, String userId, String appId) {

		try {
			if (tableProperties.size() <= 0) {
				return false;
			}
			// 单表应用
			if (1 == tableProperties.size()) {
				// 删除数据表
				//deleteTable(tableProperties, appId);
			} else { // 多表应用
				// 删除视图
				deleteView(tableProperties, userId, appId);
				// 删除约束关系
				deleteKey(tableProperties, userId, appId);
				// 删除数据表
				deleteTable(tableProperties, appId);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @Title: deleteTable
	 * @Description: 删除数据表
	 * @param tableProperties
	 * @param appId
	 * @throws Exception
	 * @return: void
	 */
	private static void deleteTable(ArrayList<TableProperty> tableProperties, String appId) throws Exception {

		// 遍历每一张表
		for (TableProperty tableProperty : tableProperties) {
			String tableId = tableProperty.getTableId();
			String sql = "drop table `" + tableId + "`";
			ExcuteSQL.processNoReturn(sql);
		}
	}

	/**
	 * 
	 * @Title: deleteKey
	 * @Description: 删除主外键
	 * @param tableProperties
	 * @param userId
	 * @param appId
	 * @throws Exception
	 * @return: void
	 */
	private static void deleteKey(ArrayList<TableProperty> tableProperties, String userId, String appId)
			throws Exception {

		// 遍历每一张表
		for (TableProperty tableProperty : tableProperties) {

			String tableId = tableProperty.getTableId();
			String sql = "SELECT CONSTRAINT_NAME FROM information_schema.KEY_COLUMN_USAGE a WHERE a.TABLE_NAME='"
					+ tableId + "'";

			ArrayList<String> keyNames = new ArrayList<String>();

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			try {
				connection = DBPool.getConnection();
				preparedStatement = connection.prepareStatement(sql);
				rs = preparedStatement.executeQuery();

				// 得到当前表所有的约束键
				while (rs.next()) {
					String keyName = rs.getString(1);
					if (!keyName.equals("PRIMARY")) {
						keyNames.add(keyName);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {// 一定要关闭链接
				DBPool.close(connection, preparedStatement, rs);
			}

			// 删除约束
			if (0 != keyNames.size()) {
				for (String string : keyNames) {
					String deleteSql = "ALTER TABLE " + tableId + " DROP FOREIGN KEY " + string;
					ExcuteSQL.processNoReturn(deleteSql);

				}
			}
		}

	}

	/**
	 * 
	 * @Title: deleteView
	 * @Description: 删除视图
	 * @param tableProperties
	 * @param userName
	 * @param appName
	 * @throws Exception
	 * @return: void
	 */
	private static void deleteView(ArrayList<TableProperty> tableProperties, String userName, String appName)
			throws Exception {

		String tableName = userName + "_" + appName + "_view";
		String sql = "drop VIEW " + tableName;
		System.out.println(sql);
		ExcuteSQL.processNoReturn(sql);
	}
}
