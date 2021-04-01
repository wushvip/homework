package com.chinamobile.cmss.bcse.app.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.chinamobile.cmss.bcse.index.bean.AppDataHistoryBean;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;

/**
 * 
 * @ClassName: AppRspBean
 * @Description: 应用管理返回信息的bean
 * @author: jinjing
 * @date: 2016年2月17日 上午9:14:06
 */
public class AppRspBean {

	private ArrayList<AppInfoBean> appInfoBeans;
	private ArrayList<AppTableMapBean> appTableList;
/*	private ArrayList<RuleBeanWithBLOBs> ruleBeanWithBLOBs;*/
	private ArrayList<AppDataHistoryBean> dataHistoryBeans;

	/**
	 * 用在sdk中获取应用的结构信息
	 */
	private ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
	private LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();

	private String success;
	private String message;
	private String operaType;
	private String appId;
	private String isExist;
	private String modifyFlag;
	private String createIndexFlag;

	public String getOperaType() {
		return operaType;
	}

	public void setOperaType(String operaType) {
		this.operaType = operaType;
	}



	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
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

	public ArrayList<AppDataHistoryBean> getDataHistoryBeans() {
		return dataHistoryBeans;
	}

	public void setDataHistoryBeans(ArrayList<AppDataHistoryBean> dataHistoryBeans) {
		this.dataHistoryBeans = dataHistoryBeans;
	}

	public ArrayList<AppTableMapBean> getAppTableList() {
		return appTableList;
	}

	public void setAppTableList(ArrayList<AppTableMapBean> appTableList) {
		this.appTableList = appTableList;
	}

/*	public ArrayList<RuleBeanWithBLOBs> getRuleBeanWithBLOBs() {
		return ruleBeanWithBLOBs;
	}

	public void setRuleBeanWithBLOBs(ArrayList<RuleBeanWithBLOBs> ruleBeanWithBLOBs) {
		this.ruleBeanWithBLOBs = ruleBeanWithBLOBs;
	}*/

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getCreateIndexFlag() {
		return createIndexFlag;
	}

	public void setCreateIndexFlag(String createIndexFlag) {
		this.createIndexFlag = createIndexFlag;
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

	public void setTablePropertiesList(ArrayList<TableProperty> tablePropertiesList) {
		this.tablePropertiesList = tablePropertiesList;
	}

}
