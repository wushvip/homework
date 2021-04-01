package com.chinamobile.cmss.bcse.search.querytype;

import org.apache.solr.client.solrj.SolrQuery;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;


/** 
 * @ClassName: CountNumSearchQuery 
 * @Description: TODO 获取数量的接口
 * @author: chenmin
 * @date: 2016年1月29日 下午5:40:30  
 */
public class CountNumSearchQuery extends SolrQuery implements SearchQuery{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;


	
	/** 
	 * @Title:CountNumSearchQuery
	 * @Description:TODO 
	 * @param queryBean
	 * @throws Exception 
	 */
	public CountNumSearchQuery(QueryBean queryBean){
		this.set("q", "*:*");
		queryBean.setSearchQuery("*:*");
	}

	
	
	/* (non Javadoc) 
	 * @Title: setQuery
	 * @Description: TODO
	 * @param queryBean 
	 * @see com.bcse.search.querytype.SearchQuery#setQuery(com.bcse.search.bean.QueryBean) 
	 */
	public void setQuery(QueryBean queryBean) {
		// TODO Auto-generated method stub
		
	}
}
