package com.chinamobile.cmss.bcse.evaluate.bean;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class EvaluateAppInfoReqBean {
	
    private String appId;

    private String userId;
    
    private String isThirdPart;

    private String requestUrl;

    private String requestType;

    private String requestContentType;

    private String requestContent;

    private String requestWord;
    
    private String sortConfig;

    @Range(min=0,message="pageIndex无效范围;") 
    private Integer pageIndex;

    @Range(min=0,max=100,message="pageNum无效范围;") 
    private Integer pageNum;

    @NotEmpty(message = "evaluateField不能为空;")
    private String evaluateField;

    private String dataType;

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getIsThirdPart() {
        return isThirdPart;
    }

    public void setIsThirdPart(String isThirdPart) {
        this.isThirdPart = isThirdPart;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

     
    public String getSortConfig() {
		return sortConfig;
	}

	public void setSortConfig(String sortConfig) {
		this.sortConfig = sortConfig;
	}

	public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public String getRequestContentType() {
        return requestContentType;
    }

    public void setRequestContentType(String requestContentType) {
        this.requestContentType = requestContentType == null ? null : requestContentType.trim();
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent == null ? null : requestContent.trim();
    }

    public String getRequestWord() {
        return requestWord;
    }

    public void setRequestWord(String requestWord) {
        this.requestWord = requestWord == null ? null : requestWord.trim();
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getEvaluateField() {
        return evaluateField;
    }

    public void setEvaluateField(String evaluateField) {
        this.evaluateField = evaluateField == null ? null : evaluateField.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
}