package com.chinamobile.cmss.bcse.config.bean;


import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 屏蔽接口请求参数
 * @ClassName: ShieldRuleReq 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:51:11
 */
public class ShieldRuleReq {
	
	
	@NotBlank(message ="应用id不能为空;") 
	private String appId;
	
	@NotBlank(message ="用户id不能为空;") 
	private String userId;	
	
	@NotBlank(message ="规则名称不能为空;") 
	@Length(max=30,message="规则名称长度最大为30;")
	private String ruleName;
	
	@NotEmpty(message="屏蔽关键词不能为空;")
	@Size(min=1, max=30,message="屏蔽关键词最多为30个;")
	private List<String> includeKeywords;
	
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
	public List<String> getIncludeKeywords() {
		return includeKeywords;
	}
	public void setIncludeKeywords(List<String> includeKeywords) {
		this.includeKeywords = includeKeywords;
	}
	
	
	
	
	
}
