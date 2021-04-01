package com.chinamobile.cmss.bcse.index.bean;

import java.util.HashMap;

/**
 * 
 * @ClassName: SolrConfProperty
 * @Description: solrConfig的相关属性
 * @author: jinjing
 * @date: 2016年2月16日 下午2:50:03
 */
public class SolrConfProperty {

	// 索引字段(域名)
	private String requestHeaderName; // RequestHeader的名字
	// 包含的字段与其对应的权重(现在默认写1)
	private HashMap<String, Integer> requestField; // 该RequestHeader的搜索字段以及权重

	final public String getRequestHeaderName() {
		return requestHeaderName;
	}

	final public void setRequestHeaderName(String requestHeaderName) {
		this.requestHeaderName = requestHeaderName;
	}

	final public HashMap<String, Integer> getRequestField() {
		return requestField;
	}

	final public void setRequestField(HashMap<String, Integer> requestField) {
		this.requestField = requestField;
	}

}
