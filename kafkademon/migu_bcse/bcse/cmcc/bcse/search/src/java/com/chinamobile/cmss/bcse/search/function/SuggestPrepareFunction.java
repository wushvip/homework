package com.chinamobile.cmss.bcse.search.function;

import org.apache.solr.client.solrj.util.ClientUtils;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.tool.config.Config;

public class SuggestPrepareFunction extends DefaultPrepareFunction {

	
	public SuggestPrepareFunction(QueryBean queryBean, SearchResultBean searchResultBean) {
		super(queryBean, searchResultBean);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() {/*
		this.getQueryBean().setRows(this.getQueryBean().getPageNum());
	
		RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) this.getQueryBean().appInfoDataBean.get(RedisDataType.INTELASSOCIATERULE);
		if (ruleBean == null) {
			return;
		}
		if (ruleBean != null) {
			String handler="/"+Config.INTEL_ASSOCIATE_PREX+ruleBean.getId();
			this.getQueryBean().set("qt", handler);
			this.getQueryBean().set("q", this.getQueryBean().getOldInput()+"*");
			String[] feilds=ruleBean.getIncludeFields().split(";");
			if(feilds!= null){
				if(feilds.length>0)
					this.getQueryBean().set("fl", feilds[0]);
			}
			
		} 
	*/}

	@Override
	public void spreadDocment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByField() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void filterByClass() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void groupStatistics() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void highlight() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		String string=ClientUtils.escapeQueryChars("*");
		String ss=string;
		System.out.print(ss);
				
	}

}
