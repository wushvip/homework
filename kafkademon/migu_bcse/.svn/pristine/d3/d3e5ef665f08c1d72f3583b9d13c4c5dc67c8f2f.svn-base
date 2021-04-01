package com.chinamobile.cmss.bcse.search.function;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;

public class SeniorDecorateFunction extends DefaultDecorateFunction{

	public SeniorDecorateFunction(QueryBean queryBean, SearchResultBean searchResultBean) {
		super(queryBean, searchResultBean);
		// TODO Auto-generated constructor stub
	}
	

	public void resultListDecorate(){
		SearchResultBean searchResultBean=this.getSearchResultBean();
		QueryResponse response=this.getResponse();
		if(searchResultBean.getResultList()!=null&&searchResultBean.getResultList().size()>0){
			return;
		}
		SolrDocumentList resultList = response.getResults();
		if (resultList == null) {
			SolrDocumentList solrDocumentList = new SolrDocumentList();
			searchResultBean.setResultList(solrDocumentList);
			return;
		} else {
			searchResultBean.setResultList(resultList);
		}
		searchResultBean.setTotalItems((int) response.getResults().getNumFound());
	}
}
