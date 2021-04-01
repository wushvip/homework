package com.chinamobile.cmss.bcse.search.querytype;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;

/** 
 * @ClassName: SearchQueryFactory 
 * @Description: TODO 生成查询逻辑实例
 * @author: chenmin
 * @date: 2016年5月25日 下午3:37:17  
 */
public class SearchQueryFactory {

	public static SearchQuery create(QueryBean queryBean) {
		SearchQuery searchQuery = null;

		// 智能提示
		if (queryBean.getIsSuggest().equals("1")) {
			searchQuery = new SuggestSearchQuery(queryBean);
			return searchQuery;
		}
		//当不是智能联想时，把所有字母转为小写(分词时会转为小写，出了单个大写字母之外的情况)
		if (queryBean.getSearchQuery() != null) {
			queryBean.setSearchQuery(queryBean.getSearchQuery().toLowerCase());
		}
		
		// 普通搜索
		String rankType = queryBean.getRankType();
		if (rankType.equals("-1")) {
			searchQuery = new CountNumSearchQuery(queryBean);
		} else {
			switch (rankType) {
			case "0":
				searchQuery = new FieldSearchQuery(queryBean);
				break;
			case "1":
				searchQuery = new RawSearchQuery(queryBean);
				break;
			case "2":
				searchQuery = new SeniorSearchQuery(queryBean);
				break;
			default:
				break;
			}
		}

		return searchQuery;
	}

}
