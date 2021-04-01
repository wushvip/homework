package com.chinamobile.cmss.bcse.search.function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.FieldInfoBean;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.search.tool.StringTool;


public class DefaultPrepareFunction implements PrepareFunctionInterface{
	
	private QueryBean queryBean;
	private SearchResultBean searchResultBean;
	
	
	public DefaultPrepareFunction(QueryBean queryBean, SearchResultBean searchResultBean){
		this.setQueryBean(queryBean);
		this.setSearchResultBean(searchResultBean);
		escapeQuery();
	}
	
	public void escapeQuery(){
		queryBean.set("q",  ClientUtils.escapeQueryChars(queryBean.getSearchQuery()));
	}
	
	/**
	 * @Title: groupHide
	 * @Description: TODO 分组折叠
	 * @param queryBean
	 * @return: void
	 */
	public void groupHide() {
		String hideField = queryBean.getGroupHide();
		if ((!hideField.equalsIgnoreCase(""))) {
			queryBean.set("group", true);
			queryBean.set("group.field", hideField);
		}
	}
	
	/** 
	 * @Title: shieldKeyWords 
	 * @Description: TODO  屏蔽敏感词
	 * @param queryBean
	 * @return: void
	 */
	public void shieldKeyWords(){/*
		
		if (queryBean.getIsShield().equals("0")) {
			return;
		}
		
		//String inputSearch="";
		String outputSearch="(";
		if(inputSearch == null ||equals(inputSearch.equals(""))){
			return;
		}
		
		//组合需要屏蔽的词语
		@SuppressWarnings("unchecked")
		ArrayList<RuleBeanWithBLOBs> ruleBean = (ArrayList<RuleBeanWithBLOBs>)queryBean.appInfoDataBean.get(RedisDataType.SHIELDRULE);
		if (ruleBean == null||ruleBean.size()==0) {
			return ;
		}
		for(RuleBeanWithBLOBs bean:ruleBean){
			String keywords = bean.getIncludeKeywords();
			if (keywords == null) {
				continue ;
			}
			String[] keywordList = keywords.split(";");
			if(keywordList.length==0){
				continue ;
			}
			for (String keyword : keywordList) {
				//关键字全部屏蔽
				keyword = ClientUtils.escapeQueryChars(keyword);
				outputSearch=outputSearch+" -"+keyword;
			}
		}
		outputSearch=outputSearch +" "+ ")";
		//为每个索引字段添加屏蔽词
		ArrayList<FieldInfoBean>  fieldList = (ArrayList<FieldInfoBean>) queryBean.appInfoDataBean.get(RedisDataType.STRINGFIELDS);
		for(FieldInfoBean fieldInfoBean : fieldList){
			queryBean.addFilterQuery(fieldInfoBean.getFieldName()+":"+outputSearch);
		}
		
	*/}

	/**
	 * @Title: spreadDocment
	 * @Description: TODO 竞价推广
	 * @param queryBean
	 * @return: void
	 */
	public void spreadDocment() {
		if (queryBean.getIsSpread().equals("0")) {
			queryBean.set("enableElevation", false);
		} else {
			queryBean.set("enableElevation", true);
		}
	}

	/**
	 * @Title: sortByField
	 * @Description: TODO 指定字段排序
	 * @return: void
	 */
	public void sortByField() {

		if (queryBean.getSortRule() == null || queryBean.getSortRule().equals("")) {
			return;
		}

		String sortRule = queryBean.getSortRule();
		sortRule = sortRule.replace("[", "");
		sortRule = sortRule.replace("]", "");
		String[] field = sortRule.split("#");

		if (field.length == 2) {
			queryBean.addSort(field[0], ORDER.valueOf(field[1]));
		}

	}

	/**
	 * @Title: showField
	 * @Description: TODO
	 * @param queryBean
	 *            需要展示的字段
	 * @return: void
	 */
	public void showField() {/*

		Object ruleBean = queryBean.appInfoDataBean.get(RedisDataType.SHOWNABLEFIELDS);

		if (ruleBean == null) {
			return;
		}
		ArrayList<FieldInfoBean> fieldList = (ArrayList<FieldInfoBean>) ruleBean;
		String showFields = "";
		for (FieldInfoBean fieldInfoBean : fieldList) {
			showFields = showFields + "," + fieldInfoBean.getFieldName();
		}
		queryBean.set("fl", showFields);
	*/}

	/**
	 * @Title: filterByClass 分类筛选
	 * @Description: TODO
	 * @param queryBean
	 * @return: void
	 */
	public void filterByClass() {

		// 筛选分类
		String cfilter = queryBean.getClFilter();
		String qString=queryBean.get("q");
		String cfilterString = "";

		if (cfilter != null && !cfilter.equals("")) {
			cfilterString = StringTool.parseClFilter(cfilter);
		}
		if (!cfilterString.equals("")) {
			qString = qString + " AND " + cfilterString;
		}
		queryBean.set("q", qString);
		//使用solr的filter筛选
		String  filterRules=queryBean.getFilterRules();
		if(filterRules!=null&&!filterRules.equals("")){
			String[] filterArray=filterRules.split("@");
			for(String filter:filterArray){
				if(filter!=null){
					queryBean.addFilterQuery(filter);
				}
			}
		}
		JSONObject filterJson=queryBean.getFilterJson();
		if(filterJson!=null){
			Set<String> iterator = filterJson.keySet();
			String value="";
			for(String key1:iterator){
		         value = filterJson.getString(key1);
				 queryBean.addFilterQuery(key1+":"+value);
			}
		}

	}

	/**
	 * @Title: groupStatistics
	 * @Description: TODO分组统计
	 * @param queryBean
	 * @return: void
	 */
	public void groupStatistics() {/*

		if (queryBean.getIsShow().equals("0")) {
			return;
		}
		@SuppressWarnings("unchecked")
		String facetRules=queryBean.getFacetRule();
		if(facetRules==null||facetRules.equals("")){
			@SuppressWarnings("unchecked")
			RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) 
					queryBean.appInfoDataBean.get(RedisDataType.GROUPSTATISTICSRULE);
					if (ruleBean == null) {
						return;
					}
					String facetField = ruleBean.getIncludeFields();
					if (facetField == null || facetField.equals("")) {
						return;
					}
					String[] facetFields = facetField.split(";");
					for (String tempFacet : facetFields) {
						if (tempFacet != null && !tempFacet.equals("")) {
							queryBean.addFacetField(tempFacet);
						}
					}
		}else{
			String[] facetFields = facetRules.split("#");
			for (String tempFacet : facetFields) {
				if (tempFacet != null && !tempFacet.equals("")) {
					queryBean.addFacetField(tempFacet);
				}
			}
		}
		
		this.getQueryBean().setFacetMinCount(1);
	*/}
	

	public QueryBean getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(QueryBean queryBean) {
		this.queryBean = queryBean;
	}

	public SearchResultBean getSearchResultBean() {
		return searchResultBean;
	}

	public void setSearchResultBean(SearchResultBean searchResultBean) {
		this.searchResultBean = searchResultBean;
	}


	@Override
	public void process() {
		queryBean.set("start", (queryBean.getPageIndex()-1)*queryBean.getPageNum());
		queryBean.set("rows", queryBean.getPageNum());
		spreadDocment();
		sortByField();
		filterByClass();
		groupStatistics();
		highlight();
		groupHide();
		shieldKeyWords();
		showField();
	}

	@Override
	public void highlight() {
		if (queryBean.getHighLightFields() == null || queryBean.getHighLightFields().equals("")) {
			return;
		}
		String sortRule = queryBean.getHighLightFields();
		String[] fields = sortRule.split(";");
		queryBean.setHighlight(true);
		for (String field : fields) {
			queryBean.addHighlightField(field);
		}
	}


		

	
}
