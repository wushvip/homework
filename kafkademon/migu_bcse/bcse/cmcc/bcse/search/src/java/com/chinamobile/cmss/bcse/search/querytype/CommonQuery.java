package com.chinamobile.cmss.bcse.search.querytype;

import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;

import com.chinamobile.cmss.bcse.app.bean.FieldInfoBean;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.tool.StringTool;

/**
 * 
 * @ClassName: CommonQuery
 * @Description: TODO 检索的具体逻辑类型，各种检索共有的功能
 * @author: chenmin
 * @date: 2016年1月29日 下午5:40:01
 * 
 */

public class CommonQuery extends SolrQuery {

	private static final long serialVersionUID = 1L;


	/**
	 * @Title:CommonQuery
	 * @Description:TODO
	 * @param queryBean
	 * @throws Exception
	 * 
	 */
	
	public CommonQuery(QueryBean queryBean) {

		// 推广设置
		spreadDocment(queryBean);
		// 筛选分类
		filterByClass(queryBean);
		// 指定字段排序
		sortByField(queryBean);
		// 分组统计
		groupStatistics(queryBean);
		// 显示字段
		showField(queryBean);
		// 分组折叠字段
		groupHide(queryBean);
        //屏蔽敏感词
		shieldKeyWords(queryBean);
	}

	/**
	 * @Title: groupHide
	 * @Description: TODO 分组折叠
	 * @param queryBean
	 * @return: void
	 */
	private void groupHide(QueryBean queryBean) {
		String hideField = queryBean.getGroupHide();
		if ((!hideField.equalsIgnoreCase(""))) {
			this.set("group", true);
			this.set("group.field", hideField);
		}
	}
	
	/** 
	 * @Title: shieldKeyWords 
	 * @Description: TODO  屏蔽敏感词
	 * @param queryBean
	 * @return: void
	 */
	private void shieldKeyWords(QueryBean queryBean){/*
		
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
				outputSearch=outputSearch+" -"+keyword;
			}
		}
		outputSearch=outputSearch +" "+ ")";
		//为每个索引字段添加屏蔽词
		ArrayList<FieldInfoBean>  fieldList = (ArrayList<FieldInfoBean>) queryBean.appInfoDataBean.get(RedisDataType.STRINGFIELDS);
		for(FieldInfoBean fieldInfoBean : fieldList){
			this.addFilterQuery(fieldInfoBean.getFieldName()+":"+outputSearch);
		}
		
	*/}

	/**
	 * @Title: spreadDocment
	 * @Description: TODO 竞价推广
	 * @param queryBean
	 * @return: void
	 */
	public void spreadDocment(QueryBean queryBean) {
		if (queryBean.getIsSpread().equals("0")) {
			this.set("enableElevation", false);
		} else {
			this.set("enableElevation", true);
		}
	}

	/**
	 * @Title: sortByField
	 * @Description: TODO 指定字段排序
	 * @return: void
	 */
	public void sortByField(QueryBean queryBean) {

		if (queryBean.getSortRule() == null || queryBean.getSortRule().equals("")) {
			return;
		}

		String sortRule = queryBean.getSortRule();
		sortRule = sortRule.replace("[", "");
		sortRule = sortRule.replace("]", "");
		String[] field = sortRule.split("#");

		if (field.length == 2) {
			this.addSort(field[0], ORDER.valueOf(field[1]));
		}

	}

	/**
	 * 
	 * @Title: showField
	 * @Description: TODO
	 * @param queryBean
	 *            需要展示的字段
	 * @return: void
	 * 
	 */
	public void showField(QueryBean queryBean) {

/*		Object ruleBean = queryBean.appInfoDataBean.get(RedisDataType.SHOWNABLEFIELDS);*/

		Object ruleBean = null;
		if (ruleBean == null) {
			return;
		}
		ArrayList<FieldInfoBean> fieldList = (ArrayList<FieldInfoBean>) ruleBean;
		String showFields = "";
		for (FieldInfoBean fieldInfoBean : fieldList) {
			showFields = showFields + "," + fieldInfoBean.getFieldName();
		}
		this.set("fl", showFields);
	}

	/**
	 * 
	 * @Title: filterByClass 分类筛选
	 * @Description: TODO
	 * @param queryBean
	 * @return: void
	 * 
	 */
	public void filterByClass(QueryBean queryBean) {

		// 筛选分类
		//使用solr的filter筛选
		String  filterRules=queryBean.getFilterRules();
		if(filterRules!=null&&!filterRules.equals("")){
			String[] filterArray=filterRules.split("@");
			for(String filter:filterArray){
				if(filter!=null){
					this.addFilterQuery(filter);
				}
			}
		}

	}

	/**
	 * @Title: groupStatistics
	 * @Description: TODO分组统计
	 * @param queryBean
	 * @return: void
	 */
	public void groupStatistics(QueryBean queryBean) {/*

		if (queryBean.getIsShow().equals("0")) {
			return;
		}
		String facetRules=queryBean.getFacetRule();
		if(facetRules==null||facetRules.equals("")){
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
							this.addFacetField(tempFacet);
						}
					}
		}else{
			String[] facetFields = facetRules.split("#");
			for (String tempFacet : facetFields) {
				if (tempFacet != null && !tempFacet.equals("")) {
					this.addFacetField(tempFacet);
				}
			}
		}
		
		this.setFacetMinCount(1);
	*/}

	public static void main(String[] args) {
		String test = "[meta1:value2:v1}";

	}

}
