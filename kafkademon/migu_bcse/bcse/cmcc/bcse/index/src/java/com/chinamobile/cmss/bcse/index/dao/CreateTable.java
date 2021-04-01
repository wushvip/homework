package com.chinamobile.cmss.bcse.index.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: CreateTable
 * @Description: 创建表和关联的视图
 * @author: jinjing
 * @date: 2016年2月16日 下午2:53:08
 */
public class CreateTable {

	/**
	 * 
	 * @Title: process
	 * @Description: 处理具体的建表过程
	 * @param tableProperties
	 * @param userId
	 * @param appId
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public static boolean process(ArrayList<TableProperty> tableProperties, String userId, String appId)
			throws Exception {

		if (1 == tableProperties.size()) { // 单表应用
			// 删除已经存在的相同表名的表
			RmTableSql.process(tableProperties, userId, appId);
			// 建立数据表
			createTable(tableProperties, userId, appId);
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "create table sucess");
		}
		if (tableProperties.size() > 1) { // 多表应用
			// 删除已经存在的相同表名的表
			RmTableSql.process(tableProperties, userId, appId);
			// 建立数据表
			createTable(tableProperties, userId, appId);
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "create table sucess");
			// 修改主外键关系
			alterKey(tableProperties, userId, appId);
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "alter table sucess");
			// 建立视图
			createView(tableProperties, userId, appId);
		}
		return true;
	}

	/**
	 * 
	 * @Title: createTable
	 * @Description: 建立数据库表
	 * @param tableProperties
	 * @param userName
	 * @param appName
	 * @throws Exception
	 * @return: void
	 */
	private static void createTable(ArrayList<TableProperty> tableProperties, String userName, String appName)
			throws Exception {

		// 遍历需要创建的数据库每张表
		for (TableProperty tableProperty : tableProperties) {

			HashMap<String, TableField> fieldMap = tableProperty.getFieldMap();
			Iterator<Entry<String, TableField>> it = fieldMap.entrySet().iterator();
			// 记录字段和字段的属性
			String temp = "";
			// 遍历每一张表的所有字段
			while (it.hasNext()) {
				Map.Entry<String, TableField> entry = (Map.Entry<String, TableField>) it.next();
				// 处理主键
				if (((TableField) entry.getValue()).getKey().equals("1")) {
					temp = temp + entry.getKey() + " " + ((TableField) entry.getValue()).getType() + " PRIMARY KEY,";
				} else { // 非主键
					temp = temp + entry.getKey() + " " + ((TableField) entry.getValue()).getType() + ",";
				}
			}

			temp = temp.substring(0, temp.lastIndexOf(","));
			System.out.println("temp:" + temp);

			// 表名加上用户和应用名
			String sql = "create table `" + tableProperty.getTableId() + "`(" + temp + ")";
			ExcuteSQL.processNoReturn(sql);
		}
	}

	/**
	 * 
	 * @Title: alterKey
	 * @Description: 修改主外键关系
	 * @param tableProperties
	 * @param userName
	 * @param appName
	 * @throws Exception
	 * @return: void
	 */
	private static void alterKey(ArrayList<TableProperty> tableProperties, String userName, String appName)
			throws Exception {
		// 遍历当前所有表
		for (TableProperty tableProperty : tableProperties) {

			HashMap<String, TableField> fieldMap = tableProperty.getFieldMap();
			Iterator<Entry<String, TableField>> it = fieldMap.entrySet().iterator();

			// 遍历当前表
			while (it.hasNext()) {
				Map.Entry<String, TableField> entry = (Map.Entry<String, TableField>) it.next();

				TableField tableField = (TableField) entry.getValue();
				String tableName = tableField.getOutTable();
				// 有外表，则修改外表的关联关系
				if ("" != tableName) {

					// 找到外表的主键名
					String keyField = "";
					for (TableProperty tempProperty : tableProperties) {

						if (tempProperty.getTableId().equals(tableName)) {

							Iterator<Entry<String, TableField>> tempIt = tempProperty.getFieldMap().entrySet()
									.iterator();
							while (tempIt.hasNext()) {
								Map.Entry<String, TableField> tempEntry = (Map.Entry<String, TableField>) tempIt.next();
								TableField tempField = (TableField) tempEntry.getValue();
								if (tempField.getKey().equals("1")) {
									keyField = (String) tempEntry.getKey();
									break;
								}
							}
							break;
						}
					}

					// 添加主外键关系
					String sql = "ALTER TABLE " + userName + Config.INDEX_SPLIT + appName + Config.INDEX_SPLIT
							+ tableProperty.getTableId() + " ADD FOREIGN KEY (" + entry.getKey() + ") REFERENCES "
							+ userName + Config.INDEX_SPLIT + appName + Config.INDEX_SPLIT + tableName + "(" + keyField
							+ ")";

					ExcuteSQL.processNoReturn(sql);
				}
			}
		}
	}

	/**
	 * 
	 * @Title: createView
	 * @Description: 建立视图大表
	 * @param tableProperties
	 * @param userName
	 * @param appName
	 * @throws Exception
	 * @return: void
	 */
	private static void createView(ArrayList<TableProperty> tableProperties, String userName, String appName)
			throws Exception {

		String tableField = "";
		String tableNames = "";
		// 遍历表
		for (TableProperty tableProperty : tableProperties) {

			tableNames += userName + "_" + appName + "_" + tableProperty.getTableId() + ",";

			HashMap<String, TableField> fieldMap = tableProperty.getFieldMap();
			Iterator<Entry<String, TableField>> it = fieldMap.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, TableField> entry = (Map.Entry<String, TableField>) it.next();
				// 处理非主表的主键，忽略
				if (((TableField) entry.getValue()).getKey().equals("1")
						&& (tableProperty.getIsMainTable().equals("0"))) {
					continue;
				} else {
					tableField += userName + Config.INDEX_SPLIT + appName + Config.INDEX_SPLIT
							+ tableProperty.getTableId() + "." + entry.getKey().toString() + " "
							+ tableProperty.getTableId() + Config.INDEX_SPLIT + entry.getKey().toString() + ",";

				}
			}
		}

		System.out.println("tableNames:" + tableNames);
		tableNames = tableNames.substring(0, tableNames.lastIndexOf(","));
		tableField = tableField.substring(0, tableField.lastIndexOf(","));

		String sql = "CREATE view " + userName + Config.INDEX_SPLIT + appName + Config.INDEX_SPLIT + "view"
				+ " as SELECT " + tableField + " from " + tableNames;

		System.out.println(sql);

		ExcuteSQL.processNoReturn(sql);
	}
}
