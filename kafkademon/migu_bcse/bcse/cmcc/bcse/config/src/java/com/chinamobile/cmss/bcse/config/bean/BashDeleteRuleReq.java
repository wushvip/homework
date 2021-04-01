package com.chinamobile.cmss.bcse.config.bean;


import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 批量删除接口请求实体
 * @ClassName: BashDeleteRuleReq 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:48:24
 */
public class BashDeleteRuleReq {
	

	@NotBlank(message ="应用id不能为空;") 
	private String appId;
	
	@NotBlank(message ="用户id不能为空;") 
	private String userId;	
	
	@NotEmpty(message="ruleNames不能为空;")
	@Size(min=1, max=5,message="ruleNames最多为5个;")
	private List<String> ruleNames;
	
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
	public List<String> getRuleNames() {
		return ruleNames;
	}
	public void setRuleNames(List<String> ruleNames) {
		this.ruleNames = ruleNames;
	}
	
	
	
	
	
	
}
