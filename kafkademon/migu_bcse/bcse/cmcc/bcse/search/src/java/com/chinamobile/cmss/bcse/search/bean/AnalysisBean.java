package com.chinamobile.cmss.bcse.search.bean;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.NotEmpty;

import com.chinamobile.cmss.bcse.tool.config.ParamVerifyConf;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupB;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupC;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupD;

public class AnalysisBean {
	
	@GroupSequence({GroupB.class,GroupA.class,GroupC.class,GroupD.class})
	public interface analysisGroup{
		
	}
	@NotEmpty(message="分词字段"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupA.class})
	private String fieldSearch;
	@NotEmpty(message="分词输入"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupB.class})
	private String searchQuery;
	@NotEmpty(message="userId"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupC.class})
	private String userId;
	@NotEmpty(message="appId"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupD.class})
	private String appId;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getFieldSearch() {
		return fieldSearch;
	}
	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}
	public String getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	
	

}
