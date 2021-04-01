package com.chinamobile.cmss.bcse.search.function;

import java.util.ArrayList;

import com.chinamobile.cmss.bcse.app.bean.FieldInfoBean;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.tool.config.Config;

public class FieldPrepareFunction extends DefaultPrepareFunction {

	public FieldPrepareFunction(QueryBean queryBean, SearchResultBean searchResultBean) {
		super(queryBean, searchResultBean);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置相关检索逻辑
	 * 
	 * @param queryBean
	 * @return SolrQuery
	 * 
	 */
	public void setQuery() {

		QueryBean queryBean = this.getQueryBean();

		// 域字段 需要判断传进来的是建表字段还是索引字段
		String fieldString = queryBean.getFieldSearch();
		boolean flag = false;

//		Object ruleBean = queryBean.appInfoDataBean.get(RedisDataType.INDEXEDFIELDS);
		Object ruleBean = null;
		if (ruleBean == null) {
			defaultQuery();
			return;
		}
		@SuppressWarnings("unchecked")
		ArrayList<FieldInfoBean> fieldList = (ArrayList<FieldInfoBean>) ruleBean;
		for (FieldInfoBean fieldInfoBean : fieldList) {
			if (fieldInfoBean.getFieldName().equals(fieldString)) {
				flag = true;
				break;
			}
		}
		// 设置具体的查询handler
		setQueryDetail(flag);
	}

	/**
	 * @Title: setQueryDetail
	 * @Description: TODO 按域检索具体细节设置
	 * @param queryBean
	 * @return: void
	 */
	public void setQueryDetail(boolean flag) {
		QueryBean queryBean = this.getQueryBean();
		String qString=queryBean.get("q");
		if (flag) {
			queryBean.set("q", qString);
			queryBean.set("qf", queryBean.getFieldSearch());
			queryBean.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
		} else {
			if (queryBean.getFieldSearch().equals(Config.DEFAULT_HANDLER)) {
				queryBean.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
			} else {
				queryBean.set("qt", "/" + Config.INDEX_FIELD_PREX + queryBean.getFieldSearch());
			}
		}
		queryBean.setStart(queryBean.getPageIndex() * queryBean.getPageNum());
		queryBean.setRows(queryBean.getPageNum());
	}

	/**
	 * @Title: defaultQuery
	 * @Description: TODO 默认配置
	 * @param queryBean
	 * @return: void
	 */
	public void defaultQuery() {
		QueryBean queryBean = this.getQueryBean();
		String qString=queryBean.get("q");
		// 默认配置
		queryBean.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
		queryBean.set("q", qString);
		queryBean.setStart(queryBean.getPageIndex() * queryBean.getPageNum());
		queryBean.setRows(queryBean.getPageNum());
	}

	public void process() {
		setQuery();
		spreadDocment();
		sortByField();
		filterByClass();
		groupStatistics();
		highlight();
		groupHide();
		shieldKeyWords();
		showField();

	}
	
	
}
