package com.chinamobile.cmss.bcse.config.bean;

import javax.ws.rs.PathParam;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 分词接口请求参数
 * @ClassName: AnalysisReq 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:47:31
 */
public class AnalysisReq {
	@PathParam("appId")
	@NotBlank(message ="appId不能为空;") 
	private String appId;
	
	@PathParam("userId")
	@NotBlank(message ="userId不能为空;") 
	private String userId;
	
	@PathParam("fieldSearch")
	@NotBlank(message ="fieldSearch不能为空;") 
	private String fieldSearch;
	
	@PathParam("searchQuery")
	@NotBlank(message ="searchQuery不能为空;") 
	private String searchQuery;

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

	public String getFieldSearch() {
		return fieldSearch;
	}

	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}

	//自动截取前100个字符
	public String getSearchQuery() {
		if(searchQuery.length()>100){
			return searchQuery.substring(0, 100);
		}
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	
	
	
	
}
