package com.chinamobile.cmss.bcse.config.bean;


import java.io.Serializable;
import java.util.Date;
/**
 * 规则
 * @ClassName: Rule 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:50:48
 */
public class Rule implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -3344883715715930256L;
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	
	private String id;  
	private String appId; 
	private String userId;
	private String ruleName;
	private Date createDate;
	private String ruleType;
	private String includeFields;
	private String fieldWeights;
	private String includeKeywords;
	private String spreadIds;
	
	private String showField;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getIncludeFields() {
		return includeFields;
	}
	public void setIncludeFields(String includeFields) {
		this.includeFields = includeFields;
	}
	
	public String getIncludeKeywords() {
		return includeKeywords;
	}
	public void setIncludeKeywords(String includeKeywords) {
		this.includeKeywords = includeKeywords;
	}
	public String getSpreadIds() {
		return spreadIds;
	}
	public void setSpreadIds(String spreadIds) {
		this.spreadIds = spreadIds;
	}
	
	public String getShowField() {
		return showField;
	}
	public void setShowField(String showField) {
		this.showField = showField;
	}
		
	public String getFieldWeights() {
		return fieldWeights;
	}
	public void setFieldWeights(String fieldWeights) {
		this.fieldWeights = fieldWeights;
	}
	
	public Rule() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
