package com.chinamobile.cmss.bcse.search.handler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.GroupInfoBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.search.cloudapi.ConnectSolrCloud;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

public class SuggestHandler {
	private static String userId = "0fa254e3-933a-4ccf-aa05-ef73e0598092";
	private static String appId = "90918e0bce1d4bf3bcf9ee8c4992e5ac";

	private BCSEQuery bcseQuery;
	private QueryResponse queryResponse;

	public SuggestHandler(BCSEQuery bcseQuery) {
		this.bcseQuery = bcseQuery;
	}

	/**
	 * @Title: search
	 * @Description: TODO
	 * @param bcseQuery
	 * @return: void
	 * 
	 */
	public void suggest() {

		try {
			
			prepare();
			process();
			decorate();
			
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.SEARCH_LOG, e.getMessage());
		}

	}

	/**
	 * @Title: prepare
	 * @Description: TODO
	 * @return: void
	 */
	private void prepare() {

		if (bcseQuery == null) {
			return;
		}
		bcseQuery.set("start", 0);
		bcseQuery.set("rows", bcseQuery.getPageNum());
		bcseQuery.set("q", bcseQuery.getSearchQuery()+"*");
		bcseQuery.set("qt", bcseQuery.getSortConfig());
		bcseQuery.set("enableElevation", true);
	    filterByClass();
		// advance grammar
		JSONObject advancedParams = bcseQuery.getAdvancedParams();
		if (advancedParams == null || advancedParams.size() == 0) {
			return;
		}
		for (String key : advancedParams.keySet()) {
			if (key != null && !key.equals("")) {
				if (advancedParams.get(key) != null)
					bcseQuery.set(key, (advancedParams.get(key).toString()));
			}
		}

	}

	/**
	 * @Title: process
	 * @Description: TODO
	 * @return: void
	 */
	private void process() throws SearchException {

		CloudSolrClient solrClient = ConnectSolrCloud.getCloudSolrServers(Config.ZK_HOST, bcseQuery.getUserId(),
				bcseQuery.getAppId());
		try {
			this.queryResponse = solrClient.query((SolrParams) bcseQuery, METHOD.POST);
		} catch (Exception e) {
			LogUtil.loggerEntrance(bcseQuery.getUserId(), bcseQuery.getAppId(), ExceptionConstants.SearchError,
					LogUtil.SEARCH_LOG, e);
			throw new SearchException(ExceptionConstants.SearchError);
		}

	}

	/**
	 * @Title: decorate
	 * @Description: TODO
	 * @return: void
	 */
	private void decorate() {

		resultListDecorate();
		facetDecorate();
		hightLightDecorate();
		groupStaticDecorate();

	}

	/**
	 * @Title: resultListDecorate
	 * @Description: TODO
	 * @return: void
	 */
	public void resultListDecorate() {

		SearchResultBean searchResultBean = bcseQuery.getSearchResult();
		SolrDocumentList resultList = queryResponse.getResults();
		if (resultList == null) {
			SolrDocumentList solrDocumentList = new SolrDocumentList();
			searchResultBean.setResultList(solrDocumentList);
			return;
		} else {
			searchResultBean.setResultList(resultList);
		}
		searchResultBean.setTotalItems((int) queryResponse.getResults().getNumFound());
	}

	/**
	 * @Title: hightLightDecorate
	 * @Description: TODO
	 * @param response
	 * @param searchResultBean
	 * @return: void
	 */
	public void hightLightDecorate() {

		Map<String, Map<String, List<String>>> highLightResult = queryResponse.getHighlighting();
		SearchResultBean searchResultBean = bcseQuery.getSearchResult();
		if (highLightResult != null) {
			searchResultBean.setHighLightResult(queryResponse.getHighlighting());
		}

	}

	/**
	 * @Title: groupStaticDecorate
	 * @Description: TODO
	 * @param response
	 * @param searchResultBean
	 * @return: void
	 */
	public void groupStaticDecorate() {
		
		LinkedHashMap<String, GroupInfoBean> groupInfo = new LinkedHashMap<String, GroupInfoBean>();
		GroupResponse groupResponse = queryResponse.getGroupResponse();

		if (groupResponse == null) {
			return;
		}
		SearchResultBean searchResultBean = bcseQuery.getSearchResult();
		List<GroupCommand> groupList = groupResponse.getValues();
		GroupInfoBean bean = null;
		for (GroupCommand groupCommand : groupList) {
			List<Group> groups = groupCommand.getValues();
			for (Group group : groups) {
				if (group.getResult().getNumFound() > 0) {
					bean = new GroupInfoBean();
					bean.setNumFound(group.getResult().getNumFound());
					bean.setTypeName(groupCommand.getName());
					bean.setResult(group.getResult());
					groupInfo.put(group.getGroupValue(), bean);
				}
			}
		}
		if (groupList != null && groupList.size()>0 ) {
			searchResultBean.setTotalItems(groupList.get(0).getNGroups());
		}
		searchResultBean.setGroupInfo(groupInfo);
		
	}

	/**
	 * @Title: facetDecorate
	 * @Description: TODO
	 * @param response
	 * @param searchResultBean
	 * @return: void
	 */
	public void facetDecorate() {

		if (queryResponse.getFacetFields() == null) {
			return;
		}
		SearchResultBean searchResultBean = bcseQuery.getSearchResult();

		JSONArray tempArrayq = new JSONArray();
		for (FacetField facetField : queryResponse.getFacetFields()) {
			JSONArray tempArray = new JSONArray();
			JSONObject tempone = new JSONObject();
			List<Count> counts = facetField.getValues();
			for (Count count : counts) {
				JSONObject temp = new JSONObject();
				temp.put(count.getName(), count.getCount());
				tempArray.add(temp);
			}
			tempone.put(facetField.getName(), tempArray);
			tempArrayq.add(tempone);
		}
		searchResultBean.setFacetList(tempArrayq);
	}

	
	/**
	 * @Title: filterByClass 分类筛选
	 * @Description: TODO
	 * @param queryBean
	 * @return: void
	 */
	public void filterByClass() {

		JSONObject filterJson = bcseQuery.getFilterJson();
		if (filterJson != null) {
			Set<String> iterator = filterJson.keySet();
			String value = "";
			for (String key1 : iterator) {
				value = filterJson.getString(key1);
				bcseQuery.addFilterQuery(key1 + ":" + value);
			}
		}

	}

	

}
