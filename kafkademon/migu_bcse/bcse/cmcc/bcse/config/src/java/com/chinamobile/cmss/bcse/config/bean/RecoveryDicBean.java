package com.chinamobile.cmss.bcse.config.bean;

import java.util.Date;
/**
 * 纠错字典
 * @ClassName: RecoveryDicBean 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午4:49:46
 */
public class RecoveryDicBean {
	private String id;
	private String appId;
	private String userId;
	private String dicText;
	private String mode;
	private Date createDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDicText() {
		return dicText;
	}
	public void setDicText(String dicText) {
		this.dicText = dicText;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
}
