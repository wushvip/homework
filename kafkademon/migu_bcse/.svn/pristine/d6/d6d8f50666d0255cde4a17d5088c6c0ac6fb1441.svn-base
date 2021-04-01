package com.chinamobile.cmss.bcse.search.querytype;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 *
 * @author chenmin
 * @date 2015年10月19日
 *
 *       TODO 精排逻辑
 *
 */
public class SeniorSearchQuery extends CommonQuery implements SearchQuery {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param queryBean
	 * @throws Exception
	 * 
	 */
	public SeniorSearchQuery(QueryBean queryBean) {
		super(queryBean);
		setQuery(queryBean);
	}

	/**
	 * 设置相关检索逻辑
	 * 
	 * @param queryBean
	 * @return void
	 * 
	 */
	public void setQuery(QueryBean queryBean) {/*
		
		@SuppressWarnings("unchecked")
		RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) queryBean.appInfoDataBean
				.get(RedisDataType.ROUGHSORTRULE);

		if (ruleBean.getRuleName().equals("default")) {
			this.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
		} else {
			String rawHandler = "/" + Config.ROUGH_SORT_PREX + ruleBean.getId();
			this.set("qt", rawHandler);
		}

		this.set("q", queryBean.getSearchQuery());
		if (!queryBean.isSecondSearch()) {
			int start = queryBean.getPageIndex() * queryBean.getPageNum();
			int end = (queryBean.getPageIndex() + 1) * queryBean.getPageNum();
			if(start>=Config.MAX_NUM){
				this.setStart(start);
				this.setRows(end);
			}else {
				this.setStart(0);
				this.setRows(Config.MAX_NUM);
			}
			
		} else {
			this.setStart(Config.MAX_NUM);
			this.setRows((queryBean.getPageIndex() + 1) * queryBean.getPageNum() - Config.MAX_NUM);
		}
	*/}

}
