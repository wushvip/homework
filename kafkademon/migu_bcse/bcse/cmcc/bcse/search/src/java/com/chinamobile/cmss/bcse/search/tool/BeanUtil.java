package com.chinamobile.cmss.bcse.search.tool;

import org.apache.solr.client.solrj.util.ClientUtils;

import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchBean;
import com.chinamobile.cmss.bcse.search.bean.SearchInput;
import com.chinamobile.cmss.bcse.search.function.DefaultSearchFunction;
import com.chinamobile.cmss.bcse.search.function.SeniorSearchFunction;
import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 * @ClassName: BeanUtil
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年7月4日 下午3:54:26
 */
public class BeanUtil {

	/**
	 * @Title: InputToQuery
	 * @Description: TODO input parse query
	 * @param searchInput
	 * @param bcseQuery
	 * @return: void
	 */
	public static void inputToQuery(SearchInput searchInput, BCSEQuery bcseQuery) {

		bcseQuery.setAppId(searchInput.getAppId());
		if (searchInput.getSearchQuery() != null) {
			bcseQuery.setSearchQuery(searchInput.getSearchQuery());
		}

		bcseQuery.setSortRule(searchInput.getSortRule());
		bcseQuery.setPageIndex(searchInput.getPageIndex());
		bcseQuery.setPageNum(searchInput.getPageNum());
		bcseQuery.setHighLightFields(searchInput.getHighLightFields());
		bcseQuery.setAppId(searchInput.getAppId());
		bcseQuery.setFacetRule(searchInput.getFacetRule());
		bcseQuery.setUserId(searchInput.getUserId());
		bcseQuery.setHighLightFields(searchInput.getHighLightFields());
		bcseQuery.setFilterJson(searchInput.getFilterJson());
		bcseQuery.setAdvancedParams(searchInput.getAdvancedParams());
		bcseQuery.setParamMap(searchInput.getParamMap());
		bcseQuery.setLogMap(searchInput.getLogMap());
		if (searchInput.getSortConfig() == null || searchInput.getSortConfig().equals("")) {
			bcseQuery.setSortConfig("/" + Config.DEFAULT_HANDLER);
		} else {
			bcseQuery.setSortConfig("/" + Config.ROUGH_SORT_PREX + searchInput.getSortConfig());
		}

	}

	/**
	 * @Title: InputToQuery
	 * @Description: TODO input parse query
	 * @param searchInput
	 * @param bcseQuery
	 * @return: void
	 */
	public static void inputToSuggestQuery(SearchInput searchInput, BCSEQuery bcseQuery) {

		bcseQuery.setAppId(searchInput.getAppId());
		if (searchInput.getSearchQuery() != null) {
			bcseQuery.setSearchQuery(searchInput.getSearchQuery());
		}

		bcseQuery.setSortRule(searchInput.getSortRule());
		bcseQuery.setPageIndex(searchInput.getPageIndex());
		bcseQuery.setPageNum(searchInput.getPageNum());
		bcseQuery.setHighLightFields(searchInput.getHighLightFields());
		bcseQuery.setAppId(searchInput.getAppId());
		bcseQuery.setFacetRule(searchInput.getFacetRule());
		bcseQuery.setUserId(searchInput.getUserId());
		bcseQuery.setHighLightFields(searchInput.getHighLightFields());
		bcseQuery.setFilterJson(searchInput.getFilterJson());
		bcseQuery.setAdvancedParams(searchInput.getAdvancedParams());
		bcseQuery.setSortConfig("/" + Config.INTEL_ASSOCIATE_PREX + searchInput.getSortConfig());

	}

	public static void SearchToQuery(SearchBean searchBean, QueryBean queryBean) {

		queryBean.setAppId(searchBean.getAppId());
		if (searchBean.getSearchQuery() != null) {
			queryBean.setSearchQuery(searchBean.getSearchQuery());
			queryBean.setOldInput(ClientUtils.escapeQueryChars(searchBean.getSearchQuery()));
		}
		if (searchBean.getQueryType() == null || searchBean.getQueryType().equals("")) {
			if (searchBean.getRankType() != null && searchBean.getRankType().equals("2")) {
				SeniorSearchFunction searchFunction = new SeniorSearchFunction();
				queryBean.setQueryType(searchFunction.getClass().getName());
			} else {
				DefaultSearchFunction searchFunction = new DefaultSearchFunction();
				queryBean.setQueryType(searchFunction.getClass().getName());
			}
		} else {
			queryBean.setQueryType(searchBean.getQueryType());
		}
		queryBean.setSortRule(searchBean.getSortRule());
		queryBean.setFacetRule(searchBean.getFacetRule());
		queryBean.setFilterRules(searchBean.getFilterRules());
		queryBean.setPageIndex(searchBean.getPageIndex());
		queryBean.setPageNum(searchBean.getPageNum());
		queryBean.setHighLightFields(searchBean.getHighLightFields());
		queryBean.setClFilter(searchBean.getClFilter());
		queryBean.setNumFilter(searchBean.getNumFilter());
		queryBean.setGroupHide(searchBean.getGroupHide());
		queryBean.setIsAnalysis(searchBean.getIsAnalysis());
		queryBean.setIsLoadDic(searchBean.getIsLoadDic());
		queryBean.setIsShield(searchBean.getIsShield());
		queryBean.setIsShow(searchBean.getIsShow());
		queryBean.setIsSpread(searchBean.getIsSpread());
		queryBean.setIsSuggest(searchBean.getIsSuggest());
		queryBean.setUserId(searchBean.getUserId());
		queryBean.setEnableHighLight(searchBean.getEnableHighLight());
		queryBean.setRankType(searchBean.getRankType());
		queryBean.setFilterJson(searchBean.getFilterJson());

		if (searchBean.getFieldSearch() != null && !searchBean.getFieldSearch().equals("")) {
			queryBean.setFieldSearch(searchBean.getFieldSearch());
		}

	}

	/*
	 * public static void SuggestToQuery(SuggestBean suggestBean,QueryBean
	 * queryBean){ queryBean.setAppId(suggestBean.getAppId());
	 * queryBean.setSearchQuery(suggestBean.getSearchQuery());
	 * queryBean.setRankType(suggestBean.getRankType());
	 * if(suggestBean.getRankType()==null||suggestBean.getRankType().equals(""))
	 * { queryBean.setRankType(suggestBean.getRankType()); }else{
	 * queryBean.setRankType(
	 * "com.chinamobile.cmss.bcse.search.SuggestSearchFunction"); }
	 * queryBean.setSuggestType(suggestBean.getSuggestType());
	 * queryBean.setPageIndex(suggestBean.getPageIndex());
	 * queryBean.setPageNum(suggestBean.getPageNum()); }
	 */

}
