package com.chinamobile.cmss.bcse.index.bean;

import java.util.HashMap;

import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 * 
 * @ClassName: TableProperty
 * @Description: 数据表的属性
 * @author: jinjing
 * @date: 2016年2月16日 下午2:50:27
 */
public class TableProperty {

	private String tableId; // 表名称
	private String isMainTable; // 是否是主表 0:否 1：是
	private String fileSplit;
	private String mutiValueSplit=Config.MUTIVALUE_SPLIT;
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

	public String getFileSplit() {
		return fileSplit;
	}

	public void setFileSplit(String fileSplit) {
		this.fileSplit = fileSplit;
	}

	public String getMutiValueSplit() {
		return mutiValueSplit;
	}

	public void setMutiValueSplit(String mutiValueSplit) {
		this.mutiValueSplit = mutiValueSplit;
	}

}
