package com.chinamobile.cmss.bcse.index.constant;

/**
 * 
 * @ClassName: SchemaFieldType
 * @Description: 前端和solr的对应关系
 * @author: jinjing
 * @date: 2016年2月16日 下午2:51:47
 */
public class SchemaFieldType {
	public static final String STRING = "STRING";
	public static final String INT = "INT";
	public static final String FLOAT = "FLOAT";
	public static final String LONG = "LONG";
	public static final String DOUBLE = "DOUBLE";
	public static final String DATE = "DATE"; // 日期类型
	public static final String ANSJ = "ANSJ";
	public static final String TEXT = "TEXT";
	public static final String QP_NoSplit = "QP_NoSplit"; // 全拼不分词
	public static final String JP_NoSplit = "JP_NoSplit"; // 简拼不分词
	public static final String ANSJ_JP = "ANSJ_JP"; // ANSJ简拼
	public static final String ANSJ_QP = "ANSJ_QP"; // ANSJ全拼
	public static final String ANSJ_PY = "ANSJ_PY"; // ANSJ拼音
	public static final String QP_Suggest = "QP_Suggest";
	public static final String JP_Suggest = "JP_Suggest";
}
