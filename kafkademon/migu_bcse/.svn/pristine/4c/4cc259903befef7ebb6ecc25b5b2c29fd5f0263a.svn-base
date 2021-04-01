package com.chinamobile.cmss.bcse.search.sort;

import java.util.ArrayList;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SortBean;

/**
 *
 * @author chenmin
 * @date 2015年10月12日
 *
 *       TODO 精确匹配规则
 *
 */
public class ExactSortRule extends SortRule {

	/**
	 * 
	 * @param sortRule
	 * @param queryBean
	 * 
	 */
	public ExactSortRule(SortRule sortRule, QueryBean queryBean, SortBean sortBean) {
		super(sortRule, queryBean, sortBean);
		filterRule();
	}

	public void filterRule() {
		// 数据范围列表
		SolrDocumentList tempFilterDocumentList = new SolrDocumentList();
		filterData(tempFilterDocumentList);
		sortFactor(tempFilterDocumentList);
		System.out.println("####--"+filterList.size());
		System.out.println("####--"+elementList.size());
	}

	public void filterData(SolrDocumentList tempFilterDocumentList) {

		String keyWord = this.queryBean.getSearchQuery();
		// 匹配的字段
		String fieldExt =  this.sortBean.getMatchField();
		// 数据范围列表装入
		for (int i = 0; i < this.elementList.size(); i++) {
			SolrDocument tempDocument = this.elementList.get(i);
			String fieldContent = (String) tempDocument.get(fieldExt);
			if (keyWord.equals(fieldContent)) {
				tempFilterDocumentList.add(tempDocument);
			}
		}
	}

	

}
