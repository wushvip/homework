package com.chinamobile.cmss.bcse.app.bean;

/**
 * 
 * @ClassName: AppFieldBean
 * @Description: 应用的数据字段信息
 * @author: jinjing
 * @date: 2016年2月17日 上午9:12:40
 */
public class AppFieldBean {
	private String id;

	private String tableId;

	private String createDate;

	private String fieldName; // 字段名

	private String fieldType; // 字段类型

	private String isSearch; // 是否可搜索

	private String isFilter;

	private String isShow; // 是否可展示

	private String isPk; // 是否主键

	private String foreignKey=""; // 是否外键

	private String isMutivalue = "0"; // 是否多值

	private String srcField=""; // 源字段

	private String dynamicField = "0"; // 动态字段

	public String getIsMutivalue() {
		return isMutivalue;
	}

	public void setIsMutivalue(String isMutivalue) {
		this.isMutivalue = isMutivalue;
	}

	public String getSrcField() {
		return srcField;
	}

	public void setSrcField(String srcField) {
		this.srcField = srcField;
	}

	public String getDynamicField() {
		return dynamicField;
	}

	public void setDynamicField(String dynamicField) {
		this.dynamicField = dynamicField;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId == null ? null : tableId.trim();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? null : fieldName.trim();
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType == null ? null : fieldType.trim();
	}

	public String getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch == null ? null : isSearch.trim();
	}

	public String getIsFilter() {
		return isFilter;
	}

	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter == null ? null : isFilter.trim();
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow == null ? null : isShow.trim();
	}

	public String getIsPk() {
		return isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk == null ? null : isPk.trim();
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey == null ? null : foreignKey.trim();
	}
}