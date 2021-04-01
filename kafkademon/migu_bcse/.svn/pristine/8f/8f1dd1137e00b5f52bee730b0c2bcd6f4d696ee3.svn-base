package com.chinamobile.cmss.bcse.search.bean;


import java.util.HashMap;

import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

/** 
 * @ClassName: BCSEQuery 
 * @Description: TODO 封装搜索参数
 * @author: chenmin
 * @date: 2017年3月4日 下午2:38:47  
 */
public class BCSEQuery extends SolrQuery{
	
	/**
	 * @fieldName: solrParams
	 * @fieldType: JSONObject
	 * @Description: TODO  高级语法
	 */
	private  JSONObject advancedParams=new JSONObject();
	
	private String sortConfig;
	
	/**
	 * @fieldName: searchQuery
	 * @fieldType: String 
	 * @Description: TODO 用户检索词
	 */
	private  String searchQuery;
	
	/**
	 * @fieldName: sortRule
	 * @fieldType: String
	 * @Description: TODO 排序配置规则
	 */
	private  String sortRule;
	/**
	 * @fieldName: FactRule
	 * @fieldType: String
	 * @Description: TODO 分组统计规则
	 */
	private  String FacetRule;
	/**
	 * @fieldName: correctWord
	 * @fieldType: String
	 * @Description: TODO 纠错结果
	 */
	private  String correctWord;
	
	/**
	 * @fieldName: analysisWords
	 * @fieldType: String[]
	 * @Description: TODO 分词结果
	 */
	private  String[] analysisWords;
	
	
	/**
	 * @fieldName: searchResult
	 * @fieldType: SearchResultBean
	 * @Description: TODO 检索结果封装
	 */
	private SearchResultBean searchResult=new SearchResultBean();
	
	
    /**
     * @fieldName: resultBean
     * @fieldType: ResultBean
     * @Description: TODO
     */
    private ResultBean resultBean=new ResultBean();
    
	
    private String appId;
    
    private String userId;
    
    private long costTime;
    
    private int pageIndex=1;
    
    private int pageNum=10;
    
    private JSONObject filterJson;
    
    private String highLightFields;    
    
    //paramMap
    private HashMap<String,Object> paramMap;
    private HashMap<String,Object> logMap;
    
    
    
    
	public HashMap<String, Object> getLogMap() {
		return logMap;
	}


	public void setLogMap(HashMap<String, Object> logMap) {
		this.logMap = logMap;
	}


	public HashMap<String, Object> getParamMap() {
		return paramMap;
	}


	public void setParamMap(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}


	public String getSortConfig() {
		return sortConfig;
	}


	public void setSortConfig(String sortConfig) {
		this.sortConfig = sortConfig;
	}


	public JSONObject getFilterJson() {
		return filterJson;
	}


	public void setFilterJson(JSONObject filterJson) {
		this.filterJson = filterJson;
	}


	public String getHighLightFields() {
		return highLightFields;
	}


	public void setHighLightFields(String highLightFields) {
		this.highLightFields = highLightFields;
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


	public ResultBean getResultBean() {
		return resultBean;
	}


	public void setResultBean(ResultBean resultBean) {
		this.resultBean = resultBean;
	}


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


	public long getCostTime() {
		return costTime;
	}


	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}


	public JSONObject getAdvancedParams() {
		return advancedParams;
	}


	public void setAdvancedParams(JSONObject advancedParams) {
		this.advancedParams = advancedParams;
	}




	public String getSearchQuery() {
		return searchQuery;
	}


	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}



	public String getSortRule() {
		return sortRule;
	}


	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
	}


	


	public String getFacetRule() {
		return FacetRule;
	}


	public void setFacetRule(String facetRule) {
		FacetRule = facetRule;
	}


	public String getCorrectWord() {
		return correctWord;
	}


	public void setCorrectWord(String correctWord) {
		this.correctWord = correctWord;
	}


	public String[] getAnalysisWords() {
		return analysisWords;
	}


	public void setAnalysisWords(String[] analysisWords) {
		this.analysisWords = analysisWords;
	}


	public SearchResultBean getSearchResult() {
		return searchResult;
	}


	public void setSearchResult(SearchResultBean searchResult) {
		this.searchResult = searchResult;
	}


	
	

}
