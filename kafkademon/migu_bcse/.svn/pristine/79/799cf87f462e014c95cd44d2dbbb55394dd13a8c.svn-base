package com.chinamobile.cmss.bcse.search.querytype;

import org.apache.solr.client.solrj.SolrQuery;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;

/**
 *
 * @author chenmin
 * @date   2015年11月18日
 *
 * TODO
 *
 */
@SuppressWarnings("serial")
public class SpellCheckQuery extends SolrQuery implements SearchQuery{
	
	/**
	 * 构造方法
	 * 
	 * @param queryBean
	 * @throws Exception 
	 * 
	 */
	public SpellCheckQuery(QueryBean queryBean) throws Exception {
		this.set("q", queryBean.getSearchQuery());
		this.set("qt", "/spell");
	}

	
	@Override
	public void setQuery(QueryBean queryBean) {
		// TODO Auto-generated method stub
		
	}
}
