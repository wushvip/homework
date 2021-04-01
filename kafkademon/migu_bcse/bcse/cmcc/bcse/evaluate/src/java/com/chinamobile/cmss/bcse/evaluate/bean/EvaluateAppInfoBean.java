package com.chinamobile.cmss.bcse.evaluate.bean;

public class EvaluateAppInfoBean {
    private String id;

    private Boolean isThirdPart;

    private String requestUrl;

    private String requestType;

    private String requestContentType;

    private String requestContent;

    private String requestWord;

    private Integer pageIndex;

    private Integer pageNum;

    private String evaluateField;

    private String dataType;
    
    private String sortConfig;

    
    
    public String getSortConfig() {
		return sortConfig;
	}

	public void setSortConfig(String sortConfig) {
		this.sortConfig = sortConfig;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Boolean getIsThirdPart() {
        return isThirdPart;
    }

    public void setIsThirdPart(Boolean isThirdPart) {
        this.isThirdPart = isThirdPart;
    }

    public String getRequestUrl() {
        return requestUrl;
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