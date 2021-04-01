package com.chinamobile.cmss.bcse.search.querytype;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 *
 * @author chenmin
 * @date 2015年10月19日
 *
 *       TODO 初級排序（依賴edismax）
 *
 */
public class RawSearchQuery extends CommonQuery implements SearchQuery {

	private static final long serialVersionUID = 1L;

	public RawSearchQuery(QueryBean queryBean) {
		super(queryBean);
		setQuery(queryBean);
		// TODO Auto-generated constructor stub
	}

	
	
	/*
	 * (non Javadoc)
	 * 
	 * @Title: setQuery
	 * 
	 * @Description: TODO
	 * 
	 * @param queryBean
	 * 
	 * @throws Exception
	 * 
	 * @see com.bcse.search.querytype.SearchQuery#setQuery(com.bcse.search.bean.
	 * QueryBean)
	 */
	public void setQuery(QueryBean queryBean) {/*

		RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) queryBean.appInfoDataBean.get(RedisDataType.ROUGHSORTRULE);
		if (ruleBean == null) {
			defaultQuery(queryBean);
			return;
		}
		if (ruleBean.getRuleName().equals("default")) {
			this.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
		} else {
			String rawHandler = "/" + Config.ROUGH_SORT_PREX + ruleBean.getId();
			this.set("qt", rawHandler);
		}
		this.set("q", queryBean.getSearchQuery());
		this.setStart(queryBean.getPageIndex() * queryBean.getPageNum());
		this.setRows(queryBean.getPageNum());

	*/}

	
	
	
	/**
	 * @Title: defaultQuery
	 * @Description: TODO 默认配置
	 * @param queryBean
	 * @return: void
	 */
	public void defaultQuery(QueryBean queryBean) {
		// 默认配置
		this.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
		this.set("q", queryBean.getSearchQuery());
		this.setStart(queryBean.getPageIndex() * queryBean.getPageNum());
		this.setRows(queryBean.getPageNum());
	}

}
