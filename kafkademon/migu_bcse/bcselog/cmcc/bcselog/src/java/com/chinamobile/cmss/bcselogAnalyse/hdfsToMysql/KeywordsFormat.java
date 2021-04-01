package com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql;


/** 
 * @ClassName: KeywordsFormat 
 * @Description: 关键词数据结构
 * @author: yangjing
 * @date: 2016年2月1日 上午9:41:41  
 */
public class KeywordsFormat {	
	/**
	 * @fieldName: user_id
	 * @fieldType: String
	 * @Description: 用户名
	 */
	private String user_id; 
	
	/**
	 * @fieldName: app_id
	 * @fieldType: String
	 * @Description: 应用名
	 */
	private String app_id;   
	
	/**
	 * @fieldName: oper_time
	 * @fieldType: String
	 * @Description: 操作时间
	 */
	private String oper_time;  
	
	/**
	 * @fieldName: keywords
	 * @fieldType: String
	 * @Description: 关键词
	 */
	private String keywords;   
	
	/**
	 * @fieldName: search_count
	 * @fieldType: String
	 * @Description: 关键词搜索次数
	 */
	private String search_count; 
	
	/**
	 * @fieldName: flag
	 * @fieldType: String
	 * @Description: 标识字段
	 */
	private String flag;        

	
	public KeywordsFormat(String user_id,String app_id,String oper_time,String keywords,String search_count,String flag) {
		super();
		this.setUser_id(user_id);
		this.setApp_id(app_id);
		this.setOper_time(oper_time);
		this.setKeywords(keywords);
		this.setSearch_count(search_count);
		this.setFlag(flag);
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getApp_id() {
		return app_id;
	}


	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}


	public String getOper_time() {
		return oper_time;
	}


	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public String getSearch_count() {
		return search_count;
	}


	public void setSearch_count(String search_count) {
		this.search_count = search_count;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}

	
}

