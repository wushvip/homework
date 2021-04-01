package com.chinamobile.cmss.bcse.search.cloudapi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

import com.chinamobile.cmss.bcse.search.bean.GroupInfoBean;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.search.querytype.SearchQuery;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.ExcuteQueryException;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * @ClassName: QuerySolrCloud
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年2月14日 下午5:01:38
 */
public class QuerySolrCloud {

	/**
	 * 
	 * solr 底层查询接口
	 * 
	 * @param queryBean
	 * @param searchResultBean
	 * @param solrQuery
	 * @throws SearchException 
	 * 
	 */

	public static QueryResponse  query(QueryBean queryBean, SearchResultBean searchResultBean) throws SearchException
			 {

		CloudSolrClient cloudSolrServer = null;
		QueryResponse response = null;
		SolrDocumentList resultList = null;
		String collectionName = queryBean.getAppId();

		try {
			cloudSolrServer = ConnectSolrCloud.getCloudSolrServers(Config.ZK_HOST, collectionName);
			response = cloudSolrServer.query(queryBean,METHOD.POST);
		} catch (Exception e) {
			LogUtil.loggerEntrance(queryBean.getUserId(), queryBean.getAppId(), ExceptionConstants.SearchError, LogUtil.SEARCH_LOG, e);
		    throw new SearchException(ExceptionConstants.SearchError);
		}
		
		// 判断是不是有分组折叠
		GroupResponse groupResponse = response.getGroupResponse();
		LinkedHashMap<String, GroupInfoBean> groupInfo = new LinkedHashMap<String, GroupInfoBean>();
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
		searchResultBean.setGroupInfo(groupInfo);
		
		resultList = response.getResults();

		
		if (resultList == null || resultList.size() == 0) {
			SolrDocumentList solrDocumentList = new SolrDocumentList();
			searchResultBean.setResultList(solrDocumentList);
			return response;
		}

		searchResultBean.setTotalItems((int) response.getResults().getNumFound());
		SolrDocumentList solrDocumentList = searchResultBean.getResultList();
		// 如果有二次搜索补全搜索结果
		if (solrDocumentList != null) {
			solrDocumentList.addAll(resultList);
			searchResultBean.setResultList(solrDocumentList);
		} else {
			searchResultBean.setResultList(resultList);
		}
		return response;
		//searchResultBean.setFacetFields(response.getFacetFields());
	}

	public static void main(String[] args) {

	}

}
