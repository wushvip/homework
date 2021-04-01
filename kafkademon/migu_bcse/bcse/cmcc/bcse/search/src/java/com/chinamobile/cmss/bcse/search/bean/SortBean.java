package com.chinamobile.cmss.bcse.search.bean;
/**
 *
 * @author chenmin
 * @date   2015�?1�?9�?
 *
 * TODO  高级配置，排序规则的父类bean
 *
 */
public class SortBean {
	
	public String name;
	
	//匹配的字�?
	private String matchField;
	//表达�?
	private String operator;
	//筛�?范围1,2,3,4
	private int standard;
	//排序组合,publicationDate:des元素
	private String[] sortArray;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMatchField() {
		return matchField;
	}
	public void setMatchField(String matchField) {
		this.matchField = matchField;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public String[] getSortArray() {
		return sortArray;
	}
	public void setSortArray(String[] sortArray) {
		this.sortArray = sortArray;
	}

	
}
