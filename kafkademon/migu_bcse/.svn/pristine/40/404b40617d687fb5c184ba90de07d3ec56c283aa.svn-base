package com.chinamobile.cmss.bcse.app.bean;

import java.util.ArrayList;

import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.alibaba.fastjson.JSONArray;
import com.chinamobile.cmss.bcse.tool.config.ParamVerifyConf;
import com.chinamobile.cmss.bcse.validate.annotation.DateCompare;
import com.chinamobile.cmss.bcse.validate.annotation.DateType;
import com.chinamobile.cmss.bcse.validate.annotation.InInclude;
import com.chinamobile.cmss.bcse.validate.annotation.Paging;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupB;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupC;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupD;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupE;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupF;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupG;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupH;

/**
 * 
 * @ClassName: AppReqBean
 * @Description: 应用管理的请求参数bean
 * @author: jinjing
 * @date: 2016年2月17日 上午9:13:56
 */
@Paging(pageIndex = "pageIndex", pageNum = "pageNum")
@DateCompare(start = "startDate", end = "endDate", type = DateType._STRING, groups = GroupG.class)
public class AppReqBean {
	@GroupSequence({ GroupA.class, GroupD.class })
	public interface defaultGroup {

	}

	@GroupSequence({ GroupB.class, GroupC.class, GroupD.class })
	public interface desAddGroup {

	}

	@GroupSequence({ GroupG.class, GroupA.class, GroupH.class })
	public interface operaShowGroup {

	}

	private String method;
	private String userName;
	private String searchText;
	@NotEmpty(message = "role" + ParamVerifyConf.PARAMS_NOTEMPTY, groups = GroupE.class)
	@InInclude(rangeValue = { "0", "1" }, message = "role"
			+ ParamVerifyConf.PARAMS_NOT_IN_INCLUDE, groups = GroupE.class)
	private String role;
	@NotEmpty(message = "appId" + ParamVerifyConf.PARAMS_NOTEMPTY, groups = GroupA.class)
	private String appId;
	@Size(min = 1, max = 15, message = "应用名" + ParamVerifyConf.PARAMS_NOT_IN_LENGTH, groups = GroupB.class)
	private String appName;

	@NotEmpty(message = "appStatus" + ParamVerifyConf.PARAMS_NOTEMPTY, groups = GroupF.class)
	@InInclude(rangeValue = { "0", "1", "2", "3" }, message = "appStatus"
			+ ParamVerifyConf.PARAMS_NOT_IN_INCLUDE, groups = GroupF.class)
	private String appStatus;

	@Size(min = 0, max = 600, message = "应用描述" + ParamVerifyConf.PARAMS_NOT_IN_LENGTH, groups = GroupC.class)
	private String appDescribe;
	private String dataTabName;
	private String fileName;
	private String ftpUserName;
	private String ftpPassword;
	private String ftpHost;
	private String ftpPort;
	private String sourceDir;
	@InInclude(rangeValue = { "1", "2", "4" }, message = "operaType"
			+ ParamVerifyConf.PARAMS_NOT_IN_INCLUDE, groups = GroupH.class)
	@NotEmpty(message = "operaType" + ParamVerifyConf.PARAMS_NOTEMPTY, groups = GroupH.class)
	private String operaType;
	private String startDate;
	private String endDate;
	private ArrayList<AppTableMapBean> appTableList;
	// private ArrayList<RuleBeanWithBLOBs> ruleBeanList;
	@NotEmpty(message = "userId" + ParamVerifyConf.PARAMS_NOTEMPTY, groups = GroupD.class)
	private String userId;
	private String createDate;
	private String uuid;
	private String tempId;
	private String appTempFlag;
	private String historyId;
	private String operaStatus;
	private String detail;
	private int pageIndex = 1;
	private int pageNum = 10;
	private int startNum = 0;
	private String mutiValueSplit;
	private String fileSplit;
	private String type;
	private JSONArray datas;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getMutiValueSplit() {
		return mutiValueSplit;
	}

	public void setMutiValueSplit(String mutiValueSplit) {
		this.mutiValueSplit = mutiValueSplit;
	}

	public String getFileSplit() {
		return fileSplit;
	}

	public void setFileSplit(String fileSplit) {
		this.fileSplit = fileSplit;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	/*
	 * public ArrayList<RuleBeanWithBLOBs> getRuleBeanList() { return
	 * ruleBeanList; }
	 * 
	 * public void setRuleBeanList(ArrayList<RuleBeanWithBLOBs> ruleBeanList) {
	 * this.ruleBeanList = ruleBeanList; }
	 */

	public String getAppTempFlag() {
		return appTempFlag;
	}

	public void setAppTempFlag(String appTempFlag) {
		this.appTempFlag = appTempFlag;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getDataTabName() {
		return dataTabName;
	}

	public void setDataTabName(String dataTabName) {
		this.dataTabName = dataTabName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFtpUserName() {
		return ftpUserName;
	}

	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpHost() {
		return ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getOperaType() {
		return operaType;
	}

	public void setOperaType(String operaType) {
		this.operaType = operaType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAppDescribe() {
		return appDescribe;
	}

	public void setAppDescribe(String appDescribe) {
		this.appDescribe = appDescribe;
	}

	public ArrayList<AppTableMapBean> getAppTableList() {
		return appTableList;
	}

	public void setAppTableList(ArrayList<AppTableMapBean> appTableList) {
		this.appTableList = appTableList;
	}

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getOperaStatus() {
		return operaStatus;
	}

	public void setOperaStatus(String operaStatus) {
		this.operaStatus = operaStatus;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public JSONArray getDatas() {
		return datas;
	}

	public void setDatas(JSONArray datas) {
		this.datas = datas;
	}

}
