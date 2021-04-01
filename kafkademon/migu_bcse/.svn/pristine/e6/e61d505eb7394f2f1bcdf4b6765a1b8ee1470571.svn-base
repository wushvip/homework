package com.chinamobile.cmss.bcse.search.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocumentList;

import com.alibaba.fastjson.JSONArray;



/** 
 * @ClassName: SearchResultBean 
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年5月30日 下午4:35:57  
 */
public class SearchResultBean {

	/**
	 * 
	 * @param code
	 *            请求成功与否的标志
	 * @param message
	 *            错误信息
	 * @param totalItems
	 *             结果总数
	 * @param costTime
	 *            搜索时间
	 * @param resultList
	 *            结果列表
	 * @param segList
	 *            分词列表
	 * @param recoveryResult
	 *            纠错结果
	 * @param facetFields
	 *            分组显示
	 * 
	 * 
	 */

	private int totalItems = 0;
	private long costTime = 0;
	private SolrDocumentList resultList;
	private ArrayList<String> segList;
	private String recoveryResult = "";
	private JSONArray facetList;
	private Map<String, Integer> facetQueries;
//	private List<GroupCommand> groupInfo;
	private Map<String, GroupInfoBean> groupInfo = new LinkedHashMap<String, GroupInfoBean>();
	private Map<String, Map<String, List<String>>> highLightResult;
	private SolrDocumentList suggestions;
	
	
	
	
	
	
	


	

	public Map<String, Integer> getFacetQueries() {
		return facetQueries;
	}

	public void setFacetQueries(Map<String, Integer> facetQueries) {
		this.facetQueries = facetQueries;
	}

	public SolrDocumentList getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(SolrDocumentList suggestions) {
		this.suggestions = suggestions;
	}

	public JSONArray getFacetList() {
		return facetList;
	}

	public void setFacetList(JSONArray facetList) {
		this.facetList = facetList;
	}

	public Map<String, Map<String, List<String>>> getHighLightResult() {
		return highLightResult;
	}

	public void setHighLightResult(Map<String, Map<String, List<String>>> highLightResult) {
		this.highLightResult = highLightResult;
	}

	

	

	
	

	public String getRecoveryResult() {
		return recoveryResult;
	}

	public void setRecoveryResult(String recoveryResult) {
		this.recoveryResult = recoveryResult;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public long getCostTime() {
		return costTime;
	}

	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}

	public SolrDocumentList getResultList() {
		return resultList;
	}

	public void setResultList(SolrDocumentList resultList) {
		this.resultList = resultList;
	}

	public ArrayList<String> getSegList() {
		return segList;
	}

	public void setSegList(ArrayList<String> segList) {
		this.segList = segList;
	}

	public Map<String, GroupInfoBean> getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(Map<String, GroupInfoBean> groupInfo) {
		this.groupInfo = groupInfo;
	}





}
