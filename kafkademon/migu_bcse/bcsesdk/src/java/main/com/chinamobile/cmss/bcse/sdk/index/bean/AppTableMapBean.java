package com.chinamobile.cmss.bcse.sdk.index.bean;

import java.util.ArrayList;

/**
 * 
 * @ClassName: AppTableMapBean
 * @Description: 应用和数据表的对应关系
 * @author: jinjing
 * @date: 2016年2月17日 上午9:14:17
 */
public class AppTableMapBean {
	private String tableId;

	private String appId;

	private String tableName;

	private String sourceDir;

	private String isMainTable;

	private ArrayList<AppFieldBean> fields;

	public ArrayList<AppFieldBean> getFields() {
		return fields;
	}

	public void setFields(ArrayList<AppFieldBean> fields) {
		this.fields = fields;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId == null ? null : tableId.trim();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName == null ? null : tableName.trim();
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir == null ? null : sourceDir.trim();
	}

	public String getIsMainTable() {
		return isMainTable;
	}

	public void setIsMainTable(String isMainTable) {
		this.isMainTable = isMainTable == null ? null : isMainTable.trim();
	}
}