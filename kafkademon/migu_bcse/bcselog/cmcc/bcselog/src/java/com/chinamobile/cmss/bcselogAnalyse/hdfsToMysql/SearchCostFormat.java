package com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql;

/**
 * @ClassName: SearchCostFormat
 * @Description: 搜索耗时数据结构
 * @author: yangjing
 * @date: 2016年2月1日 上午9:51:26
 */
public class SearchCostFormat {

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}

	public String getOPER_DATE() {
		return OPER_DATE;
	}

	public void setOPER_DATE(String oPER_DATE) {
		OPER_DATE = oPER_DATE;
	}

	public String getOPER_HOUR() {
		return OPER_HOUR;
	}

	public void setOPER_HOUR(String oPER_HOUR) {
		OPER_HOUR = oPER_HOUR;
	}

	public String getAVG_COST() {
		return AVG_COST;
	}

	public void setAVG_COST(String aVG_COST) {
		AVG_COST = aVG_COST;
	}

	public String getMIN_COST() {
		return MIN_COST;
	}

	public void setMIN_COST(String mIN_COST) {
		MIN_COST = mIN_COST;
	}

	public String getMAX_COST() {
		return MAX_COST;
	}

	public void setMAX_COST(String mAX_COST) {
		MAX_COST = mAX_COST;
	}

	public String getFLAG() {
		return FLAG;
	}

	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}

	private String USER_ID;
	private String APP_ID;
	private String OPER_DATE;
	private String OPER_HOUR;
	private String AVG_COST;
	private String MIN_COST;
	private String MAX_COST;
	private String FLAG;

	public SearchCostFormat(String USER_ID, String APP_ID, String OPER_DATE, String OPER_HOUR, String AVG_COST,
			String MIN_COST, String MAX_COST, String FLAG) {
		this.APP_ID = APP_ID;
		this.USER_ID = USER_ID;
		this.OPER_DATE = OPER_DATE;
		this.OPER_HOUR = OPER_HOUR;
		this.AVG_COST = AVG_COST;
		this.MAX_COST = MAX_COST;
		this.MIN_COST = MIN_COST;
		this.FLAG = FLAG;

	}

}
