package com.chinamobile.cmss.bcse.config.bean;


import org.hibernate.validator.constraints.NotBlank;
/**
 * 删除接口请求实体
 * @ClassName: DeleteRuleReq 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:48:54
 */
public class DeleteRuleReq {

	@NotBlank(message ="应用id不能为空;") 
	private String appId;
	
	@NotBlank(message ="用户id不能为空;") 
	private String userId;	
	
	
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
	
	
	
	
}
