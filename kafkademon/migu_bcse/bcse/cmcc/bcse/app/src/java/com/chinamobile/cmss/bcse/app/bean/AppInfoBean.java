package com.chinamobile.cmss.bcse.app.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: AppInfoBean
 * @Description: 应用的基本信息
 * @author: jinjing
 * @date: 2016年2月17日 上午9:13:38
 */
public class AppInfoBean implements Serializable{
	private static final long serialVersionUID = 1L;

	// 唯一标识
	private String appId;
	// 用户名
	private String userName;
	// 应用名
	private String appName;
	// 应用描述
	private String appDescribe;
	// 应用状态
	private String appStatus;
	// 创建时间 yyyy-MM-dd
	private String createDate;
	// 应用源文件存储路径
	private String sourceDir;
	// 用到的模板名
	private String tempName;
	// 处理类型，添加或者修改 add、update
	private String operaType;
	// 应用的数据量
	private int dataNum;
	// 昨日搜索次数
	private int searchNum;
	// 昨日请求量峰值
	private int maxSearchPerSec;

	private String userId;

	private String tempId;

	private String appTempFlag;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getOperaType() {
		return operaType;
	}

	public void setOperaType(String operaType) {
		this.operaType = operaType;
	}

	public int getDataNum() {
		return dataNum;
	}

	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}

	public int getSearchNum() {
		return searchNum;
	}

	public void setSearchNum(int searchNum) {
		this.searchNum = searchNum;
	}

	

	public int getMaxSearchPerSec() {
		return maxSearchPerSec;
	}

	public void setMaxSearchPerSec(int maxSearchPerSec) {
		this.maxSearchPerSec = maxSearchPerSec;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName == null ? null : appName.trim();
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus == null ? null : appStatus.trim();
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId == null ? null : tempId.trim();
	}

	public String getAppTempFlag() {
		return appTempFlag;
	}

	public void setAppTempFlag(String appTempFlag) {
		this.appTempFlag = appTempFlag == null ? null : appTempFlag.trim();
	}

	public String getAppDescribe() {
		return appDescribe;
	}

	public void setAppDescribe(String appDescribe) {
		this.appDescribe = appDescribe == null ? null : appDescribe.trim();
	}
}