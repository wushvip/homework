package com.chinamobile.cmss.bcse.search.handler;

import java.util.ArrayList;
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
import org.junit.Ignore;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.GroupInfoBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.search.cloudapi.ConnectSolrCloud;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;


/** 
 * @ClassName: SearchHandler 
 * @Description: TODO
 * @author: chenmin
 * @date: 2017年3月7日 下午2:01:10  
 */
public class SearchHandler {

	private static String userId = "0fa254e3-933a-4ccf-aa05-ef73e0598092";
	private static String appId = "2fc86871a6ea425f90aaea8e7e3d3f8e";

	private BCSEQuery bcseQuery;
	private QueryResponse queryResponse;
	
	private RedisService redisService=(RedisService) Config.ac.getBean("redisService");

	public SearchHandler(BCSEQuery bcseQuery) {
		this.bcseQuery = bcseQuery;
	}

	public static void main(String[] args){
		
		/* BCSEQuery bcseQuery=new BCSEQuery();
		 bcseQuery.setAppId(appId);
		 bcseQuery.setUserId(userId);
		 bcseQuery.setSearchQuery("*");
		 new SearchHandler(bcseQuery).search();*/
		 Object object=null;
		 ArrayList<String> list=(ArrayList<String>) object;
		 System.out.println(list);
	}
	
	/**
	 * @Title: search
	 * @Description: TODO
	 * @param bcseQuery
	 * @return: void
	 * 
	 */
	public void search() {
		
		try {
			prepare();
			process();
			decorate();
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.SEARCH_LOG, e.getMessage());
			Tool.operateExceptionInfo(bcseQuery.getResultBean(),e);
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
		bcseQuery.set("start", (bcseQuery.getPageIndex()-1)*bcseQuery.getPageNum());
		bcseQuery.set("rows", bcseQuery.getPageNum());
		bcseQuery.set("q", bcseQuery.getSearchQuery());
		bcseQuery.set("qt", bcseQuery.getSortConfig());
		bcseQuery.set("enableElevation", true);
		
		highlight();
		facetStatistics();
		sortByField();
		filterByClass();
		

		// advance grammar
		JSONObject advancedParams = bcseQuery.getAdvancedParams();
		if (advancedParams == null || advancedParams.size() == 0) {
			return;
		}
		for (String key : advancedParams.keySet()) {
			if (key != null && !key.equals("")) {
				if (advancedParams.get(key) != null)
					if ("group.field".equals(key)) {
						bcseQuery.set("group", true);
						bcseQuery.set("group.ngroups", true); 
//						bcseQuery.set("group.limit", 10); 
					}
				
					if (key.startsWith("_MULTI")) {
						String ky = key.replaceAll("_MULTI", "");
						String ve = advancedParams.get(key).toString();
						String[] sub =ve.split(";");
						for (String parm : sub) {
							bcseQuery.add(ky, parm);
						}
						
					}else{
						bcseQuery.set(key, (advancedParams.get(key).toString()));
					}
					
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
		ResultBean resultBean=new ResultBean();
		resultBean.setResult(bcseQuery.getSearchResult());
		bcseQuery.setResultBean(resultBean);

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
		
		GroupResponse groupResponse = queryResponse.getGroupResponse();

		if (groupResponse == null) {
			return;
		}
		SearchResultBean searchResultBean = bcseQuery.getSearchResult();
		processGroup(groupResponse,searchResultBean);
		
	}

	
	public void processGroup(GroupResponse groupResponse,SearchResultBean searchResultBean){
		List<GroupCommand> groupList = groupResponse.getValues();
		LinkedHashMap<String, GroupInfoBean> groupInfo = new LinkedHashMap<String, GroupInfoBean>();
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
		
		Map<String, Integer> queries = queryResponse.getFacetQuery();
		searchResultBean.setFacetQueries(queries);
		searchResultBean.setFacetList(tempArrayq);
	}

	
	/** 
	 * @Title: shieldKeyWords 
	 * @Description: TODO  屏蔽敏感词
	 * @param queryBean
	 * @return: void
	 */
	public void shieldKeyWords(){
			
	}



	/**
	 * @Title: sortByField
	 * @Description: TODO 指定字段排序
	 * @return: void
	 */
	public void sortByField() {

		if (bcseQuery.getSortRule() == null || bcseQuery.getSortRule().equals("")) {
			return;
		}
		String sortRule = bcseQuery.getSortRule();
	    bcseQuery.set("sort", sortRule);
	}

	

	/**
	 * @Title: filterByClass 分类筛选
	 * @Description: TODO
	 * @param queryBean
	 * @return: void
	 */
	public void filterByClass() {

		JSONObject filterJson=bcseQuery.getFilterJson();
		if(filterJson!=null){
			Set<String> iterator = filterJson.keySet();
			String value="";
			for(String key1:iterator){
		         value = filterJson.getString(key1);
		         bcseQuery.addFilterQuery(key1+":"+value);
			}
		}

	}

	/**
	 * @Title: groupStatistics
	 * @Description: TODO分组统计
	 * @param queryBean
	 * @return: void
	 */
	public void facetStatistics() {

		
		@SuppressWarnings("unchecked")
		String facetRules=bcseQuery.getFacetRule();
		if(facetRules==null||facetRules.equals("")){
			return;
		}else{
			try {
				ArrayList<String> facetList= (ArrayList<String>) redisService.get(bcseQuery.getAppId()+"FACET"+facetRules);
				if(facetList==null) return ;
				for(String facetField:facetList){
					bcseQuery.addFacetField(facetField);
				}
			} catch (Exception e) {
				LogUtil.loggerEntrance(userId, appId, Config.FAIL_SYS_CODE,LogUtil.CONFIG_LOG, e);
			}
			
		}
		
		bcseQuery.setFacetMinCount(1);
	}
	
	
	

	/** 
	 * 
	 * @Title: highlight 
	 * @Description: TODO
	 * @return: void
	 * 
	 */
	public void highlight() {
		
		if (bcseQuery.getHighLightFields() == null || bcseQuery.getHighLightFields().equals("")) {
			return;
		}
		String highLightFields = bcseQuery.getHighLightFields();
		bcseQuery.set("hl",true);
		bcseQuery.set("hl.fl", highLightFields);
	}

}
