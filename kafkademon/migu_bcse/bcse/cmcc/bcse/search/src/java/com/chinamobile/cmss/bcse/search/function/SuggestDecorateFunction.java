package com.chinamobile.cmss.bcse.search.function;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;

public class SuggestDecorateFunction extends DefaultDecorateFunction{

	public SuggestDecorateFunction(QueryBean queryBean, SearchResultBean searchResultBean) {
		super(queryBean, searchResultBean);
		// TODO Auto-generated constructor stub
	}
	
	public void process(QueryResponse response){
		this.setResponse(response);
		resultListDecorate();
	}

	public void resultListDecorate(){
		QueryResponse response=this.getResponse();
		SearchResultBean searchResultBean=this.getSearchResultBean();
		SolrDocumentList resultList = response.getResults();
		if (resultList == null || resultList.size() == 0) {
			SolrDocumentList solrDocumentList = new SolrDocumentList();
			searchResultBean.setSuggestions(solrDocumentList);
			return;
		} else {
			searchResultBean.setSuggestions(resultList);
		}
		searchResultBean.setTotalItems((int) response.getResults().getNumFound());
	}
}
