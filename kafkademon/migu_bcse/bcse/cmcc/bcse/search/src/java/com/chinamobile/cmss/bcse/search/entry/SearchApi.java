package com.chinamobile.cmss.bcse.search.entry;






import org.apache.solr.client.solrj.util.ClientUtils;

import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.search.function.SearchFunctionFactory;
import com.chinamobile.cmss.bcse.search.log.SaveSearchLog;
import com.chinamobile.cmss.bcse.search.querytype.SearchQuery;
import com.chinamobile.cmss.bcse.search.querytype.SearchQueryFactory;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 *
 * @author chenmin
 * @date TODO 搜索服务对外接口
 * 
 * 
 */
public class SearchApi {
	
	private static String userId = "0fa254e3-933a-4ccf-aa05-ef73e0598092";
	private static String appId = "90918e0bce1d4bf3bcf9ee8c4992e5ac";

	public static void main(String[] args) throws Exception {
		
		QueryBean queryBean = new QueryBean();
		queryBean.setSearchQuery("");
		
		/*queryBean.setPageId(0);
		queryBean.setNumPerPage(10);*/
		queryBean.setFieldSearch("text");
		SearchResultBean searchResultBean = new SearchResultBean();
		try {
		//	new SearchApi().searchEntrance(queryBean, searchResultBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(searchResultBean.getResultList());

	}
	
	/** 
	 * @Title: search 
	 * @Description: TODO 
	 * @param bcseQuery
	 * @return: void
	 */
	public void search(BCSEQuery bcseQuery){
	}

	/**
	 * 
	 * 搜索服务入口 使用工厂方法模式创建搜索实例以及查询逻辑
	 * 
	 * @param queryBean
	 * @param SearchResultBean
	 * @return
	 * @throws Exception
	 * 
	 * */

	/*public void searchEntrance(QueryBean queryBean,
			SearchResultBean searchResultBean) throws SearchException {
		
		long start = System.currentTimeMillis();
	    queryBean.setOldInput(queryBean.getSearchQuery());
		//构造查询函数实例
		SearchFunction searchFunction = null;
		
		//searchFunction输入词预处理
		searchFunction.searchPreDeal(queryBean);
		//构造检索逻辑实例
		try {
			queryBean.setSearchQuery(ClientUtils.escapeQueryChars(queryBean.getSearchQuery()));
		} catch (Exception e) {
			LogUtil.loggerEntrance(queryBean.getUserId(),queryBean.getAppId(),ExceptionConstants.ParametersError,LogUtil.SEARCH_LOG, e);
		}
		
		SearchQuery searchQuery = SearchQueryFactory.create(queryBean);
		//执行检索检索逻辑
		searchFunction.search(queryBean,
				searchResultBean, searchQuery);
		//封裝查詢結果
		searchFunction.decorate( queryBean, searchResultBean);
		long end = System.currentTimeMillis();
		searchResultBean.setCostTime(end - start);
		//写日志
		try {
			if(!queryBean.getRankType().equals("-1")&&!queryBean.getIsSuggest().equals("1")){
				new SaveSearchLog().saveLogInfo(searchResultBean, queryBean);
			}
		} catch (Exception e1) {
			LogUtil.loggerEntrance(queryBean.getUserId(),queryBean.getAppId(),ExceptionConstants.QueryLogError,LogUtil.SEARCH_LOG, e1);
			e1.printStackTrace();
		}
		
	}*/
}
