package com.chinamobile.cmss.bcse.config.bean;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 纠错接口请求实体
 * @ClassName: RecoveryReq 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:50:00
 */
public class RecoveryReq {
	@PathParam("appId")
	@NotBlank(message ="appId不能为空;") 
	private String appId;
	
	@PathParam("userId")
	@NotBlank(message ="userId不能为空;") 
	private String userId;
	
	@PathParam("searchQuery")
	@NotBlank(message ="searchQuery不能为空;") 
	@NotNull(message ="searchQuery不能为空;") 
	private String searchQuery;
	
	@PathParam("isLoadDic")
	private String isLoadDic;
	
	
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

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String getIsLoadDic() {
		return isLoadDic;
	}

	public void setIsLoadDic(String isLoadDic) {
		this.isLoadDic = isLoadDic;
	}

	
	
	
	
	
	
}
