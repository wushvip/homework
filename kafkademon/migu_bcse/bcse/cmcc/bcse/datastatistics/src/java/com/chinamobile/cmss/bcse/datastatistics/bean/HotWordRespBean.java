package com.chinamobile.cmss.bcse.datastatistics.bean;

import java.util.ArrayList;

public class HotWordRespBean { 
	
	private String success;
	private String message;
	private ArrayList<HotWordsBean> heatWordsList;
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
	public ArrayList<HotWordsBean> getHeatWordsList() {
		return heatWordsList;
	}
	public void setHeatWordsList(ArrayList<HotWordsBean> heatWordsList) {
		this.heatWordsList = heatWordsList;
	}
	
	
	

}
