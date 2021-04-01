package com.chinamobile.cmss.bcse.search.bean;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.ParamVerifyConf;
import com.chinamobile.cmss.bcse.validate.annotation.InInclude;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupB;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupC;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupD;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupE;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupF;

/**
 * @ClassName: SearchBean
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年7月4日 下午3:50:26
 */
public class SearchBean {
	@GroupSequence({GroupA.class,GroupB.class,GroupC.class,GroupD.class,GroupE.class,GroupF.class})
	public interface searchGroup{
		
	}
	
	@NotEmpty(message = "appId"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupA.class})
	private String appId;
	@NotEmpty(message = "searchQuery"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupC.class})
	private String searchQuery;
	@Range(min=0,message="pageIndex"+ParamVerifyConf.PARAMS_NOT_INT_SIZE,groups={GroupD.class}) 
	private int pageIndex = 0;
	@Range(min=1,max=100,message="pageNum"+ParamVerifyConf.PARAMS_NOT_INT_SIZE,groups={GroupE.class}) 
	private int pageNum = 10;
	@InInclude(rangeValue={"0","1","2"}, message="rankType"+ParamVerifyConf.PARAMS_NOT_IN_INCLUDE,groups={GroupF.class})
	private String rankType = "1";
	private String queryType = "";
	private String sortRule;
	private String facetRule;
	private String filterRules;
	private String highLightFields;
	private String numFilter;
	private String groupHide = "";
	private String clFilter;
	private String sourceId;
	@NotEmpty(message = "userId"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupB.class})
	private String userId;
	private String isSuggest = "0";
	private int enableHighLight;
	private String fieldSearch = Config.DEFAULT_HANDLER;
	private String isLoadDic = "1";
	private String isSpread = "1";
	private String isShield = "1";
	private String isAnalysis = "1";
	private String isShow = "1";
	private JSONObject filterJson=new JSONObject();
	
	
	

	

	public JSONObject getFilterJson() {
		return filterJson;
	}

	public void setFilterJson(JSONObject filterJson) {
		this.filterJson = filterJson;
	}

	public String getIsLoadDic() {
		return isLoadDic;
	}

	public void setIsLoadDic(String isLoadDic) {
		this.isLoadDic = isLoadDic;
	}

	public String getIsSpread() {
		return isSpread;
	}

	public void setIsSpread(String isSpread) {
		this.isSpread = isSpread;
	}

	public String getIsShield() {
		return isShield;
	}

	public void setIsShield(String isShield) {
		this.isShield = isShield;
	}

	public String getIsAnalysis() {
		return isAnalysis;
	}

	public void setIsAnalysis(String isAnalysis) {
		this.isAnalysis = isAnalysis;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNumFilter() {
		return numFilter;
	}

	public void setNumFilter(String numFilter) {
		this.numFilter = numFilter;
	}

	public String getGroupHide() {
		return groupHide;
	}

	public void setGroupHide(String groupHide) {
		this.groupHide = groupHide;
	}

	public String getClFilter() {
		return clFilter;
	}

	public void setClFilter(String clFilter) {
		this.clFilter = clFilter;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getIsSuggest() {
		return isSuggest;
	}

	public void setIsSuggest(String isSuggest) {
		this.isSuggest = isSuggest;
	}

	public int getEnableHighLight() {
		return enableHighLight;
	}

	public void setEnableHighLight(int enableHighLight) {
		this.enableHighLight = enableHighLight;
	}

	public String getFieldSearch() {
		return fieldSearch;
	}

	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}

	public String getHighLightFields() {
		return highLightFields;
	}

	public void setHighLightFields(String highLightFields) {
		this.highLightFields = highLightFields;
	}

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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = (queryType == null ? "select" : queryType);
	}

	public String getSortRule() {
		return sortRule;
	}

	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
	}

	public String getFacetRule() {
		return facetRule;
	}

	public void setFacetRule(String facetRule) {
		this.facetRule = facetRule;
	}

	public String getFilterRules() {
		return filterRules;
	}

	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}

}
