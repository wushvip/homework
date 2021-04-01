package com.chinamobile.cmss.bcse.search.bean;

import org.hibernate.validator.constraints.NotEmpty;

public class SuggestBean {
	
	@NotEmpty(message = "appId不能为空;")  
	private String appId;
	@NotEmpty(message = "searchQuery不能为空;")  
	private String searchQuery;
	private int pageIndex = 0;
	private int pageNum = 10;
	private String rankType="0";
	@NotEmpty(message = "suggestType不能为空;")  
	private String suggestType="";
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getRankType() {
		return rankType;
	}
	public void setRankType(String rankType) {
		this.rankType = rankType;
	}
	public String getSuggestType() {
		return suggestType;
	}
	public void setSuggestType(String suggestType) {
		this.suggestType = suggestType;
	}
	
	
	

}
