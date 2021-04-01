package com.chinamobile.cmss.bcse.search.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SortBean;
import com.chinamobile.cmss.bcse.search.tool.LevenshteinTool;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SearchException;

/**
 *
 * @author chenmin
 * @date 2015年10月12日
 *
 *       TODO 文本相关性规则 对所有结果按照编辑距离排序，再根据用户的表达式获取
 *
 */
public class TextRelevalSortRule extends SortRule {

	/**
	 * 
	 * @param sortRule
	 * @param queryBean
	 * @throws Exception
	 */
	public TextRelevalSortRule(SortRule sortRule, QueryBean queryBean, SortBean sortBean) throws SearchException {
		super(sortRule, queryBean, sortBean);
		filterRule();
	}

	public void filterRule() throws SearchException {

		// 数据范围列表
		SolrDocumentList tempFilterDocumentList = new SolrDocumentList();
		//数据范围
		filterData(tempFilterDocumentList);
		//排序因子
		sortFactor(tempFilterDocumentList);
		System.out.println(filterList.size());
		System.out.println(elementList.size());

	}

	

	public void filterData(SolrDocumentList tempSolrDocumentList) throws SearchException{
		// 范围过滤
		List<SortData> list = caculateScore(this.elementList);
		Collections.sort(list);
		int start = 0;
		int end = 0;
		String operator = this.sortBean.getOperator();
		int standard = this.sortBean.getStandard();
		int avg = this.elementList.size() / 4;
		if (!operator.equals("=")) {
			throw new SearchException(ExceptionConstants.SearchError);
		}
		switch (standard) {
		case 1:
			start = 0;
			end = avg;
			break;
		case 2:
			start = avg;
			end = 2 * avg;
			break;
		case 3:
			start = 2 * avg;
			end = 3 * avg;
			break;
		case 4:
			start = 3 * avg;
			end = this.elementList.size();
			break;
		default:
			break;
		}

		for (int i = start; i < end; i++) {
			tempSolrDocumentList.add(this.elementList.get(list.get(i).id));
		}

	}
	
	

	/**
	 * 计算得分
	 * 
	 * @param solrDocumentList
	 * @return List<SortData>
	 * 
	 */
	private List<SortData> caculateScore(SolrDocumentList solrDocumentList) {
		List<SortData> list = new ArrayList<SortData>();
		for (int i = 0; i < solrDocumentList.size(); i++) {
			SortData sortData = new SortData();
			String textString = sortBean.getMatchField();
			String word = (String) solrDocumentList.get(i).get(textString);
			/*
			 * word = word.replaceAll("<em>", ""); word =
			 * word.replaceAll("<em/>", "");
			 */
			sortData.score = LevenshteinTool.levenshtein(word, queryBean.getSearchQuery());
			sortData.id = i;
			list.add(sortData);
		}
		return list;

	}

	/**
	 * 编辑距离排序
	 * 
	 */
	class SortData implements Comparable<SortData> {
		public int id;
		public double score;

		@Override
		public int compareTo(SortData o) {
			if (score < o.score)
				return 1;
			if (score > o.score)
				return -1;
			return 0;
		}
	}

}
