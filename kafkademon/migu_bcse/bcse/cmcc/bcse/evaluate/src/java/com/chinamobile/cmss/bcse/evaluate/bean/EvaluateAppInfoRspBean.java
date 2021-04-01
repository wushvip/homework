package com.chinamobile.cmss.bcse.evaluate.bean;

import java.util.ArrayList;

public class EvaluateAppInfoRspBean {

	private ArrayList<EvaluateAppInfoBean> appInfoBeans;

	private String success;
	private String message;
	private String appId;




	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public ArrayList<EvaluateAppInfoBean> getAppInfoBeans() {
		return appInfoBeans;
	}

	public void setAppInfoBeans(ArrayList<EvaluateAppInfoBean> appInfoBeans) {
		this.appInfoBeans = appInfoBeans;
	}

}
