package com.chinamobile.cmss.bcse.sdk.index.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 
 * @ClassName: AppRspBean
 * @Description: 应用管理返回信息的bean
 * @author: jinjing
 * @date: 2016年2月17日 上午9:14:06
 */
public class AppRspBean {

	private ArrayList<AppInfoBean> appInfoBeans;

	/**
	 * 用在sdk中获取应用的结构信息
	 */
	private ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
	private LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();

	private boolean success;
	private String message;
	private String operaType;
	private String appId;
	private boolean isExist;
	private boolean modifyFlag;
	private boolean createIndexFlag;

	public String getOperaType() {
		return operaType;
	}

	public void setOperaType(String operaType) {
		this.operaType = operaType;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<AppInfoBean> getAppInfoBeans() {
		return appInfoBeans;
	}

	public void setAppInfoBeans(ArrayList<AppInfoBean> appInfoBeans) {
		this.appInfoBeans = appInfoBeans;
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

	public boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(boolean isExist) {
		this.isExist = isExist;
	}

	public Boolean getCreateIndexFlag() {
		return createIndexFlag;
	}

	public void setCreateIndexFlag(Boolean createIndexFlag) {
		this.createIndexFlag = createIndexFlag;
	}

	public Boolean getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(Boolean modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public LinkedHashMap<String, String> getSourceFileMap() {
		return sourceFileMap;
	}

	public void setSourceFileMap(LinkedHashMap<String, String> sourceFileMap) {
		this.sourceFileMap = sourceFileMap;
	}

	public ArrayList<TableProperty> getTablePropertiesList() {
		return tablePropertiesList;
	}

	public void setTablePropertiesList(
			ArrayList<TableProperty> tablePropertiesList) {
		this.tablePropertiesList = tablePropertiesList;
	}

}
