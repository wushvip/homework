package com.chinamobile.cmss.bcse.sdk.index.bean;

import java.util.HashMap;

/**
 * 
 * @ClassName: TableProperty
 * @Description: 数据表的属�??
 * @author: jinjing
 * @date: 2016�?2�?16�? 下午2:50:27
 */
public class TableProperty {

	private String tableId; // 表名�?
	private String isMainTable; // 是否是主�? 0:�? 1：是
	private HashMap<String, TableField> fieldMap; // 表中字段名字和对应的字段类型

	
	final public String getIsMainTable() {
		return isMainTable;
	}

	final public void setIsMainTable(String isMainTable) {
		this.isMainTable = isMainTable;
	}

	final public HashMap<String, TableField> getFieldMap() {
		return fieldMap;
	}

	final public void setFieldMap(HashMap<String, TableField> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

}
