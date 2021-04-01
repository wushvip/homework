package com.chinamobile.cmss.bcse.search.function;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.tool.config.Config;

public class SeniorPrepareFunction extends DefaultPrepareFunction {

	public SeniorPrepareFunction(QueryBean queryBean, SearchResultBean searchResultBean) {
		super(queryBean, searchResultBean);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: defaultQuery
	 * @Description: TODO 默认配置
	 * @param queryBean
	 * @return: void
	 */
	public void defaultQuery(QueryBean queryBean) {
		// 默认配置
		queryBean.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
		queryBean.setStart(queryBean.getPageIndex() * queryBean.getPageNum());
		queryBean.setRows(queryBean.getPageNum());
	}
	
	public void process(){/*
		
		QueryBean queryBean=this.getQueryBean();	
		RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) queryBean.appInfoDataBean.get(RedisDataType.ROUGHSORTRULE);
		
		if (ruleBean == null) {
			defaultQuery(queryBean);
			return;
		}
		if (ruleBean.getRuleName().equals("default")) {
			queryBean.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
		} else {
			String rawHandler = "/" + Config.ROUGH_SORT_PREX + ruleBean.getId();
			queryBean.set("qt", rawHandler);
		}
		queryBean.setStart(queryBean.getPageIndex() * queryBean.getPageNum());
		queryBean.setRows(queryBean.getPageNum());
		spreadDocment();
		sortByField();
		filterByClass();
		groupStatistics();
		highlight();
		groupHide();
		shieldKeyWords();
		showField();
		
	*/}
}

