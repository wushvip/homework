package com.chinamobile.cmss.bcse.evaluate.bean;

import java.util.Map;

/**
 * http请求信息
 */
public class HttpRequestDataBean {

	private String searchWord;
	
	private Boolean isThirdPart;
	
	private String url;
	
	private String requestType; 

	private Map<String, Object> headers;
	
	private Map<String, String> params; //key -请求词  ; value - 请求参数

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}



	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Boolean getIsThirdPart() {
		return isThirdPart;
	}

	public void setIsThirdPart(Boolean isThirdPart) {
		this.isThirdPart = isThirdPart;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	
}
