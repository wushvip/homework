package com.chinamobile.cmss.bcse.search.function;

import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.search.cloudapi.ConnectSolrCloud;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;


/**
 * @ClassName: SearchFunction
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年7月4日 下午3:17:41
 */
public  class DefaultSearchFunction implements SearchFunctionInterface{
	
	private QueryBean queryBean;
	private SearchResultBean searchResultBean;
	private QueryResponse response;
	private PrepareFunctionInterface prepareFunction;
	private DecorateFunctionInterface decorateFunction;
	
	
	public DefaultSearchFunction(QueryBean queryBean, SearchResultBean searchResultBean) {
		this.queryBean = queryBean;
		this.searchResultBean = searchResultBean;
	}

	public  DefaultSearchFunction(){
		
	}
	public void prepare() {
		prepareFunction.process();
	}

	public void search() throws SearchException {
		CloudSolrClient cloudSolrServer = null;
		String collectionName = queryBean.getAppId();

		try {
			cloudSolrServer = ConnectSolrCloud.getCloudSolrServers(Config.ZK_HOST, collectionName);
			this.response = cloudSolrServer.query((SolrParams) queryBean,METHOD.POST);
			if(this.response==null){
				this.response=new QueryResponse();
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(queryBean.getUserId(), queryBean.getAppId(), ExceptionConstants.SearchError, LogUtil.SEARCH_LOG, e);
		    throw new SearchException(ExceptionConstants.SearchError);
		}
	}

	public void decorate() {
		decorateFunction.process(this.response);
	}

	@Override
	public void process() throws SearchException {
		prepare();
		search();
		decorate();
		
	}
	public DecorateFunctionInterface getDecorateFunction() {
		return decorateFunction;
	}


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

	
	public PrepareFunctionInterface getPrepareFunction() {
		return prepareFunction;
	}

	public void setPrepareFunction(PrepareFunctionInterface prepareFunction) {
		this.prepareFunction = prepareFunction;
	}


	@Override
	public void setDecorateFunction(DecorateFunctionInterface decorateFunction) {
		this.decorateFunction=decorateFunction;
		
	}

	public QueryResponse getResponse() {
		return response;
	}

	public void setResponse(QueryResponse response) {
		this.response = response;
	}

	


	

}
