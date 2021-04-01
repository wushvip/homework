package com.chinamobile.cmss.bcse.datastatistics.bean;

import java.util.ArrayList;

import javax.validation.GroupSequence;
import javax.validation.Valid;

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


/** 
 * 
 * @ClassName: LogReqBean 
 * @Description: 日志分析模块请求参数 
 * @author: chenmin
 * @date: 2016年1月29日 下午5:44:24  
 *
 */
@Paging(pageIndex="pageIndex",pageNum="pageNum",groups={GroupB.class,GroupD.class})
@DateCompare(start="startDate",end="endDate",type=DateType._INT ,groups={GroupB.class,GroupA.class,GroupC.class})
public class LogReqBean {
	@GroupSequence({GroupD.class,GroupE.class,GroupF.class})
	public interface hotKeyGroup{
		
	}
	@NotEmpty(message="userId"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupB.class,GroupF.class,GroupA.class})
    private String userId;
	private String method;
	@NotEmpty(message = "appId"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupB.class,GroupE.class})
	private String appId;
	private Integer startDate;
	private String startDateStr;
	private Integer endDate;
	private String endDateStr;
	//bcse1.3.0变更
	/*@InInclude(rangeValue={"day","hour"},message="standardOrderBy"+ParamVerifyConf.PARAMS_NOT_IN_INCLUDE,groups={GroupA.class})
	@NotEmpty(message="standardOrderBy"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupA.class})
	private String standardOrderBy;*/
	
	@InInclude(rangeValue={"day","hour"},message="dimension"+ParamVerifyConf.PARAMS_NOT_IN_INCLUDE,groups={GroupA.class})
	@NotEmpty(message="dimension"+ParamVerifyConf.PARAMS_NOTEMPTY,groups={GroupA.class})
	private String dimension;
	
	@Valid
	private ArrayList<UserAppBean> userList;
    private int pageIndex = 1;
    private int pageNum = 10;
    private int startNum = 0;
    private String operDate;
    private String ct;
    private String bookType;
	
    
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	public Integer getStartDate() {
		return startDate;
	}
	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}
	public Integer getEndDate() {
		return endDate;
	}
	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
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
	public ArrayList<UserAppBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserAppBean> userList) {
		this.userList = userList;
	}
	/*public String getStandardOrderBy() {
		return standardOrderBy;
	}
	public void setStandardOrderBy(String standardOrderBy) {
		this.standardOrderBy = standardOrderBy;
	}*/
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
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
		this.appId = appId;
	}


	
	
}
