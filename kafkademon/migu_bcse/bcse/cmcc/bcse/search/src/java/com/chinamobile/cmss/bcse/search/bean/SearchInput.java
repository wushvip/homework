package com.chinamobile.cmss.bcse.search.bean;


import java.util.HashMap;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.tool.config.ParamVerifyConf;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupB;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupC;

/** 
 * @ClassName:  
 * @Description: TODO 封装搜索参数
 * @author: chenmin
 * @date: 2017年3月4日 下午2:38:47  
 */
public class SearchInput{
	
	private String searchQuery;
	private int pageIndex = 1;
	private int pageNum=10;
	
	
	/**
	 * 
	 * @fieldName: solrParams
	 * @fieldType: JSONObject
	 * @Description: TODO  高级语法
	 * 
	 */
	private  JSONObject advancedParams=new JSONObject();
	
	
	private String sortConfig;
	
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
	private  String facetRule;
	/**
	 * @fieldName: correctWord
	 * @fieldType: String
	 * @Description: TODO 纠错结果
	 */
	private  String correctWord;
	
	private  String suggestRule;
	
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
	private SearchResultBean searchResult;
	
	
    /**
     * @fieldName: resultBean
     * @fieldType: ResultBean
     * @Description: TODO
     */
    private ResultBean resultBean;
    
	
    private String appId;
    
    private String userId;
    
    
    //数媒需求新增字段:门户
    private String ct;
    //渠道
    private String bookType;
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


	public String getCt() {
		return ct;
	}


	public void setCt(String ct) {
		this.ct = ct;
	}


	



	public String getBookType() {
		return bookType;
	}


	public void setBookType(String bookType) {
		this.bookType = bookType;
	}


	public String getSortConfig() {
		return sortConfig;
	}


	public void setSortConfig(String sortConfig) {
		this.sortConfig = sortConfig;
	}


	private JSONObject filterJson=new JSONObject();
    
    private String highLightFields;    
    
    
    
    
	public String getSuggestRule() {
		return suggestRule;
	}


	public void setSuggestRule(String suggestRule) {
		this.suggestRule = suggestRule;
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
		return facetRule;
	}


	public void setFacetRule(String facetRule) {
		this.facetRule = facetRule;
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
