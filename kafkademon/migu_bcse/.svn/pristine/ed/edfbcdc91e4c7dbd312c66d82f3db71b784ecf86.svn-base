package com.chinamobile.cmss.bcse.search.function;

import java.lang.reflect.Constructor;

import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.bean.SearchResultBean;


public class SearchFunctionFactory {

	/**
	 * @Title: create
	 * @Description: TODO 生产检索实例
	 * @param queryBean
	 * @param searchResultBean
	 * @return
	 * @throws Exception
	 * @return: SearchFunction
	 * 
	 */
	public static SearchFunctionInterface create(QueryBean queryBean, SearchResultBean searchResultBean) {
		
		SearchFunctionInterface searchFunction = null;
		String rankType = queryBean.getQueryType();
		try {
			Class<?> functionFactory = Class.forName(rankType);
			Class[] paramTypes = { QueryBean.class, SearchResultBean.class };
			Object[] params = {queryBean, searchResultBean}; // 方法传入的参数
			Constructor con = functionFactory.getConstructor(paramTypes);     //主要就是这句了
			searchFunction=  (SearchFunctionInterface) con.newInstance(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchFunction;

	}
	
}
