package com.chinamobile.cmss.bcse.search.querytype;



import java.util.ArrayList;

import com.chinamobile.cmss.bcse.app.bean.FieldInfoBean;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 * @ClassName: FieldSearchQuery
 * @Description: TODO按域检索逻辑
 * @author: chenmin
 * @date: 2016年1月29日 下午5:41:13
 */
public class FieldSearchQuery extends CommonQuery implements SearchQuery {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 构造方法
	 * 
	 * @param queryBean
	 * @throws Exception
	 * 
	 */
	public FieldSearchQuery(QueryBean queryBean)  {
		super(queryBean);
		setQuery(queryBean);
	}

	/**
	 * 设置相关检索逻辑
	 * 
	 * @param queryBean
	 * @return SolrQuery
	 * 
	 */
	public void setQuery(QueryBean queryBean) {

		// 域字段 需要判断传进来的是建表字段还是索引字段
		String fieldString = queryBean.getFieldSearch();
		boolean flag = false;
		
		
		Object ruleBean =  null;
		if (ruleBean == null) {
			defaultQuery(queryBean);
			return;
		}
		@SuppressWarnings("unchecked")
		ArrayList<FieldInfoBean> fieldList=(ArrayList<FieldInfoBean>) ruleBean;
		for(FieldInfoBean fieldInfoBean : fieldList){
			if(fieldInfoBean.getFieldName().equals(fieldString)){
				flag = true;
				break;
			}
		}
		//设置具体的查询handler
		setQueryDetail(queryBean, flag);
	}

	/**
	 * @Title: setQueryDetail
	 * @Description: TODO 按域检索具体细节设置
	 * @param queryBean
	 * @return: void
	 */
	public void setQueryDetail(QueryBean queryBean, boolean flag) {

		if (flag) {
			this.set("q", queryBean.getFieldSearch() + ":" + queryBean.getSearchQuery());
		} else {
			if (queryBean.getFieldSearch().equals(Config.DEFAULT_HANDLER)) {
				this.set("qt", "/" + Config.INDEX_FIELD_PREX + Config.DEFAULT_HANDLER);
			} else {
				this.set("qt", "/" + Config.INDEX_FIELD_PREX + queryBean.getFieldSearch());
			}
			this.set("q", queryBean.getSearchQuery());
		}
		this.setStart(queryBean.getPageIndex() * queryBean.getPageNum());
		this.setRows(queryBean.getPageNum());
	}

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
