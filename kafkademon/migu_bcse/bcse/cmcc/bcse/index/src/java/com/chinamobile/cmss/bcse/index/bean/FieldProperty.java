package com.chinamobile.cmss.bcse.index.bean;

/**
 * 
 * @ClassName: FieldProperty
 * @Description: 每个字段的信息
 * @author: jinjing
 * @date: 2016年2月16日 下午2:48:25
 */
public class FieldProperty {

	private String FieldType; // 字段的类型
	private boolean isIndex; // 是否索引(是否可搜素)
	private boolean isStored; // 是否存储(是否可展示)
	private boolean isPk; // 是否为主键

	private boolean IS_MUTIVALUE = false; // 是否多值
	private String SRC_FIELD; // 源字段
	private boolean DYNAMIC_FIELD = false; // 是否动态字段

	public Boolean getIsPk() {
		return isPk;
	}

	public void setIsPk(Boolean isPk) {
		this.isPk = isPk;
	}

	final public String getFieldType() {
		return FieldType;
	}

	final public void setFieldType(String fieldType) {
		FieldType = fieldType;
	}

	final public Boolean getIsIndex() {
		return isIndex;
	}

	final public void setIsIndex(Boolean isIndex) {
		this.isIndex = isIndex;
	}

	final public Boolean getIsStored() {
		return isStored;
	}

	final public void setIsStored(Boolean isStored) {
		this.isStored = isStored;
	}

	public boolean isDYNAMIC_FIELD() {
		return DYNAMIC_FIELD;
	}

	public void setDYNAMIC_FIELD(boolean dYNAMIC_FIELD) {
		DYNAMIC_FIELD = dYNAMIC_FIELD;
	}

	public boolean isIS_MUTIVALUE() {
		return IS_MUTIVALUE;
	}

	public void setIS_MUTIVALUE(boolean iS_MUTIVALUE) {
		IS_MUTIVALUE = iS_MUTIVALUE;
	}

	public String getSRC_FIELD() {
		return SRC_FIELD;
	}

	public void setSRC_FIELD(String sRC_FIELD) {
		SRC_FIELD = sRC_FIELD;
	}

}
