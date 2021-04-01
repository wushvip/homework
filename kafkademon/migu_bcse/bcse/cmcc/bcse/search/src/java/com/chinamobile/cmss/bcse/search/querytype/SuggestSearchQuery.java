package com.chinamobile.cmss.bcse.search.querytype;

import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.tool.config.Config;


/** 
 * @ClassName: SuggestSearchQuery 
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年2月29日 上午9:42:43  
 */

public class SuggestSearchQuery extends SolrQuery implements SearchQuery {

	private static final long serialVersionUID = 1L;

	/** 
	 * @Title:SuggestSearchQuery
	 * @Description:TODO 
	 * @param queryBean
	 * @throws Exception 
	 */
	public SuggestSearchQuery(QueryBean queryBean){

		
		this.setStart(0);
		this.setRows(queryBean.getPageNum());
		setQuery(queryBean);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bcse.search.querytype.SearchQuery#setQuery(com.bcse.search.bean.
	 * QueryBean)
	 */
	@Override
	public void setQuery(QueryBean queryBean) {/*
		
		RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) queryBean.appInfoDataBean.get(RedisDataType.INTELASSOCIATERULE);
		if (ruleBean == null) {
			return;
		}
		
		//RuleBeanWithBLOBs ruleBean = ruleBeans.get(0);
		if (ruleBean != null) {
			String handler="/"+Config.INTEL_ASSOCIATE_PREX+ruleBean.getId();
			//String handler="/suggest_0485ea33-2eb1-4c27-b6c5-2b9b1066e1bc";
			this.set("qt", handler);
			this.set("q", queryBean.getSearchQuery()+"*");
			//this.set("shards.tolerant", true);
			String[] feilds=ruleBean.getIncludeFields().split(";");
			if(feilds!= null){
				if(feilds.length>0)
				this.set("fl", feilds[0]);
			}
			
		} 
	*/}

	
}
