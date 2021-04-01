package com.chinamobile.cmss.bcse.evaluate.bean;

import javax.ws.rs.PathParam;

public class EvaluateResultReqBean {

    private String appId;

    private String dataId;

    private Integer evaluateNumber;
    
    private String userId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Integer getEvaluateNumber() {
		return evaluateNumber;
	}

	public void setEvaluateNumber(Integer evaluateNumber) {
		this.evaluateNumber = evaluateNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
