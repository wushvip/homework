package com.chinamobile.cmss.bcse.search.bean;

import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 * @ClassName: QueryBean
 * @Description: TODO 查询参数封装
 * @author: chenmin
 * @date: 2016年2月26日 下午3:08:10
 */

public class QueryBean extends SolrQuery{
	

	/**
	 * 
	 * @param userId
	 *            用户id
	 * @param timeStamp
	 *            时间
	 * @param appName
	 *            应用名称
	 * @param searchQuery
	 *            查询窜
	 * @param pageId
	 *            请求的页码，从第0页
	 * @param numPerPage
	 *            每页的数量
	 * @param queryEncode
	 *            请求的编码格式
	 * @param rankType
	 *            排序类型
	 * @param numFilter
	 *            数值过滤
	 * @param clFilter
	 *            分类过滤
	 * @param sourceId
	 *            搜索来源
	 * @param isSuggest
	 *            是否智能提示
	 * @param enableHightLight
	 *            是否高亮
	 * @param sortRule
	 *            指定字段排序
	 * @param facetRule
	 *            分组统计的字段
	 * @param fieldSearch
	 *            按域搜索字段
	 * @param secondSearch
	 *            二次搜索补全结果
	 * @param groupHide
	 *            分组折叠的字段
	 * 
	 * 
	 *            测试接口额外增加的字
	 * @param isSpread
	 *            推广设置
	 * @param isShield
	 *            屏蔽设置
	 * @param isAnalysis
	 *            查询分析
	 * @param isShow
	 *            结果展现
	 * 
	 */
	private boolean secondSearch;
	private String userId;
	private String timeStamp;
	private String appId;
	private String searchQuery;
	private String oldInput;
	private int pageIndex = 0;
	private int pageNum = 10;
	private int queryEncode;
	private String rankType="0";
	private String numFilter;
	private String groupHide = "";
	private String clFilter;
	private String sourceId;
	private String isSuggest = "0";
	private int enableHighLight;
/*	public AppInfoDataBean getAppInfoDataBean() {
		return appInfoDataBean;
	}

	public void setAppInfoDataBean(AppInfoDataBean appInfoDataBean) {
		this.appInfoDataBean = appInfoDataBean;
	}*/

	private String sortRule;
	private String facetRule;
	private String fieldSearch=Config.DEFAULT_HANDLER;
/*	public AppInfoDataBean appInfoDataBean;*/
	private String filterRules;
	private String highLightFields;
	private String queryType="select";
	private String suggestType="";
	private JSONObject filterJson=new JSONObject();
	
	private String isLoadDic="1";
	

	private String isSpread = "1";
	private String isShield = "1";
	private String isAnalysis = "1";
	private String isShow = "1";
	
	
	

	public JSONObject getFilterJson() {
		return filterJson;
	}

	public void setFilterJson(JSONObject filterJson) {
		this.filterJson = filterJson;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getSuggestType() {
		return suggestType;
	}

	public void setSuggestType(String suggestType) {
		this.suggestType = suggestType;
	}

	public String getHighLightFields() {
		return highLightFields;
	}

	public void setHighLightFields(String highLightFields) {
		this.highLightFields = highLightFields;
	}

	public String getOldInput() {
		return oldInput;
	}

	public void setOldInput(String oldInput) {
		this.oldInput = oldInput;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}



	public boolean isSecondSearch() {
		return secondSearch;
	}

	public void setSecondSearch(boolean secondSearch) {
		this.secondSearch = secondSearch;
	}

	public String getFieldSearch() {
		return fieldSearch;
	}

	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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

	public int getQueryEncode() {
		return queryEncode;
	}

	public void setQueryEncode(int queryEncode) {
		this.queryEncode = queryEncode;
	}

	
	public String getNumFilter() {
		return numFilter;
	}

	public void setNumFilter(String numFilter) {
		this.numFilter = numFilter;
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

	public int getEnableHighLight() {
		return enableHighLight;
	}

	public void setEnableHighLight(int enableHighLight) {
		this.enableHighLight = enableHighLight;
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

	public String getGroupHide() {
		return groupHide;
	}

	public void setGroupHide(String groupHide) {
		this.groupHide = groupHide;
	}

	/*public RedisDataBean getRedisDataBean() {
		return redisDataBean;
	}

	public void setRedisDataBean(RedisDataBean redisDataBean) {
		this.redisDataBean = redisDataBean;
	}*/

	public String getFilterRules() {
		return filterRules;
	}

	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}


	public String getIsLoadDic() {
		return isLoadDic;
	}

	public void setIsLoadDic(String isLoadDic) {
		this.isLoadDic = isLoadDic;
	}

	public String getRankType() {
		return rankType;
	}

	public void setRankType(String rankType) {
		this.rankType = rankType;
	}

	public String getIsSuggest() {
		return isSuggest;
	}

	public void setIsSuggest(String isSuggest) {
		this.isSuggest = isSuggest;
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

	

	
}
