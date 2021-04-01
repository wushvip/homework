package com.chinamobile.cmss.bcse.config.bean;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 分组接口请求实体
 * @ClassName: FacetRuleReq 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:49:06
 */
public class FacetRuleReq {
	
	@NotBlank(message ="应用id不能为空;") 
	private String appId;
	
	@NotBlank(message ="用户id不能为空;") 
	private String userId;	
	
	@NotBlank(message ="规则名称不能为空;") 
	@Length(max=30,message="规则名称长度最大为30;")
	private String ruleName;
	
	@NotEmpty(message="includeFields不能为空;")
	private List<String> includeFields;
	
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
	
	
	public List<String> getIncludeFields() {
		return includeFields;
	}
	public void setIncludeFields(List<String> includeFields) {
		this.includeFields = includeFields;
	}
	
	
	
}
