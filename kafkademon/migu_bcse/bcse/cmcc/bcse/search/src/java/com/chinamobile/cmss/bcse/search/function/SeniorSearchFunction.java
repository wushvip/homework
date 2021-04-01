package com.chinamobile.cmss.bcse.search.function;

import java.util.ArrayList;


import org.apache.solr.common.SolrDocumentList;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;
import com.chinamobile.cmss.bcse.search.bean.SortBean;
import com.chinamobile.cmss.bcse.search.cloudapi.QuerySolrCloud;
import com.chinamobile.cmss.bcse.search.sort.ExactSortRule;
import com.chinamobile.cmss.bcse.search.sort.SortRule;
import com.chinamobile.cmss.bcse.search.sort.TextRelevalSortRule;
import com.chinamobile.cmss.bcse.search.tool.StringTool;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;

/**
 * @ClassName: SeniorSearchFunction
 * @Description: TODO 精排检索逻辑
 * @author: chenmin
 * @date: 2016年1月29日 下午5:31:03
 */

public class SeniorSearchFunction extends DefaultSearchFunction {
	
	private ArrayList<SortBean> sortRuleList=new ArrayList<SortBean>();
	
	public SeniorSearchFunction(){
	}

	public SeniorSearchFunction(QueryBean queryBean, SearchResultBean searchResultBean) {
		super(queryBean, searchResultBean);
		// TODO Auto-generated constructor stub
	}
	/**
	

	private ArrayList<SortBean> sortRuleList = new ArrayList<SortBean>();

	/*
	 * (non Javadoc)
	 * 
	 * @Title: search
	 * @Description: TODO
	 * @param queryBean
	 * @param searchResultBean
	 * @param solrQuery
	 * @throws Exception
	 * 
	 * @see com.bcse.search.function.SearchFunction#search(com.bcse.search.bean.
	 * QueryBean, com.bcse.search.bean.SearchResultBean,
	 * com.bcse.search.querytype.SearchQuery)
	 */
	public void search() throws SearchException {
		// solr底层获取结果
		QueryBean queryBean=this.getQueryBean();
		SearchResultBean searchResultBean=this.getSearchResultBean();
		int start = queryBean.getPageIndex() * queryBean.getPageNum();
		if(Config.MAX_NUM<=start){
			this.setResponse(QuerySolrCloud.query(queryBean, searchResultBean));
		}else{
			queryBean.setStart(0);
			queryBean.setRows(Config.MAX_NUM);
			this.setResponse(QuerySolrCloud.query(queryBean, searchResultBean));
			SortRule sortRule = new SortRule();
			// 上层排序
			carefulSort( sortRule);
			// 二次查全补齐结果
			secondSearch( sortRule);
		}
	}

	/**
	 * @Title: carefulSort
	 * @Description: TODO 精排逻辑,对前100条结果进行人工排序
	 * @param queryBean
	 * @param searchResultBean
	 * @param solrQuery
	 * @param sortRule
	 * @return: void
	 * @throws SearchException 
	 */
	@SuppressWarnings("unused")
	public void carefulSort(
			SortRule sortRule) throws SearchException {/*
		// 用户名和应用名
		QueryBean queryBean=this.getQueryBean();
		SearchResultBean searchResultBean=this.getSearchResultBean();
		String userId = queryBean.getUserId();
		String appId = queryBean.getAppId();

		@SuppressWarnings("unchecked")
		RuleBeanWithBLOBs ruleBean = (RuleBeanWithBLOBs) queryBean.appInfoDataBean.get(RedisDataType.CAREFULSORTRULE);

		if (ruleBean == null ) {
			sortRule.filterList = searchResultBean.getResultList();
		} else {
			if (ruleBean.getRuleName().equals("default")) {
				sortRule.filterList = searchResultBean.getResultList();
				return;
			}
			sortRule.elementList = searchResultBean.getResultList();
			String expresstion = ruleBean.getExpression();
			if (expresstion != null) {
				StringTool.ParseExpression(expresstion, this.sortRuleList);
				humanSort(queryBean, sortRule);
				
			}

		}
	*/}

	/**
	 * @Title: secondSearch
	 * @Description: TODO 二次查询补全结果 MAX_NUM = Config.MAX_NUM;
	 *               start=queryBean.getPageId()*queryBean.getNumPerPage()+1
	 *               end=(queryBean.getPageId()+1)*queryBean.getNumPerPage()
	 *               MAX_NUM<start : 结果全部在二次查询中获取 start<MAX_NUM<end :
	 *               结果由两次查询组合而成 MAX_NUM>=end : 结果全部在第一次查询中获取
	 * @param queryBean
	 * @param searchResultBean
	 * @param solrQuery
	 * @param sortRule
	 * @throws Exception
	 * @return: void
	 */
	public void secondSearch(
			SortRule sortRule) throws SearchException {
		QueryBean queryBean=this.getQueryBean();
		SearchResultBean searchResultBean=this.getSearchResultBean();
		// 重新组装结果
		int MAX_NUM = Config.MAX_NUM;
		if (queryBean.getPageNum() == 0) {
			queryBean.setPageNum(10);
		}
		int start = queryBean.getPageIndex() * queryBean.getPageNum();
		int end = (queryBean.getPageIndex() + 1) * queryBean.getPageNum();

		if (MAX_NUM >= end) {
			SolrDocumentList newResultList = new SolrDocumentList();
			for (int i = start; i < end&&i<sortRule.filterList.size(); i++) {
				newResultList.add(sortRule.filterList.get(i));
			}
			searchResultBean.setResultList(newResultList);
		} else {
			SolrDocumentList newResultList = new SolrDocumentList();
			for (int i = start; i < MAX_NUM&&i<sortRule.filterList.size(); i++) {
				newResultList.add(sortRule.filterList.get(i));
			}
			searchResultBean.setResultList(newResultList);
			queryBean.setStart(Config.MAX_NUM);
			queryBean.setRows((queryBean.getPageIndex() + 1) * queryBean.getPageNum() - Config.MAX_NUM);
			QuerySolrCloud.query(queryBean, searchResultBean);
		}
	}

	/**
	 * @Title: humanSort
	 * @Description: TODO 人工排序
	 * @param queryBean
	 * @param sortRule
	 * @throws Exception
	 * @return: void
	 */
	private void humanSort(QueryBean queryBean, SortRule sortRule) throws SearchException {
		SortRule temp = sortRule;
		for (SortBean o : this.sortRuleList) {
			System.out.println(o.name);
			if (o.name.equals("exactmatch")) {
				ExactSortRule exactSortRule = new ExactSortRule(temp, queryBean, o);
				temp = exactSortRule;
			} else if (o.name.equals("textRelLevel")) {
				TextRelevalSortRule textRelevalSortRule = new TextRelevalSortRule(temp, queryBean, o);
				temp = textRelevalSortRule;
			}
		}
		sortRule.filterList.addAll(sortRule.elementList);
		sortRule.elementList = null;

	}

	public static void main(String[] args) {
		/*
		 * String expresstion =
		 * "exactmatch(name) & docWeight:des+publicationDate:des;textRelLevel(title)>2 & docWeight:des+publicationDate:des"
		 * ; SeniorSearchFunction searchFunction = new SeniorSearchFunction();
		 * searchFunction.ParseExpression(expresstion); for (SortBean o :
		 * searchFunction.sortRuleList) { System.out.println(o.name); if
		 * (o.name.equals("exactmatch")) {
		 * 
		 * } }
		 */
	}
}
