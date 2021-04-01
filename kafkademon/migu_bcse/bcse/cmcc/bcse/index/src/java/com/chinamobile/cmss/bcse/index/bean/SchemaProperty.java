package com.chinamobile.cmss.bcse.index.bean;

import java.util.HashMap;

/**
 * 
 * @ClassName: SchemaProperty
 * @Description: schema文件的相关属性
 * @author: jinjing
 * @date: 2016年2月16日 下午2:49:49
 */
public class SchemaProperty {

	/**
	 * 表中字段名字和对应的字段属性
	 */
	private HashMap<String, FieldProperty> fieldMap = new HashMap<String, FieldProperty>();

	final public HashMap<String, FieldProperty> getFieldMap() {
		return fieldMap;
	}

	final public void setFieldMap(HashMap<String, FieldProperty> fieldMap) {
		this.fieldMap = fieldMap;
	}

}
