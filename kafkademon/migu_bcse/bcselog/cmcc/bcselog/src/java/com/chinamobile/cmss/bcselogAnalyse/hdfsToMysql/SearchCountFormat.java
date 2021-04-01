package com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql;

/**
 * @ClassName: SearchCountFormat
 * @Description: 搜索次数数据结构
 * @author: yangjing
 * @date: 2016年2月1日 上午9:54:27
 */
public class SearchCountFormat implements Comparable<SearchCountFormat>{

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

	public String getSEARCH_COUNT() {
		return SEARCH_COUNT;
	}

	public void setSEARCH_COUNT(String sEARCH_COUNT) {
		SEARCH_COUNT = sEARCH_COUNT;
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
	private String SEARCH_COUNT;
	private String FLAG;

	public SearchCountFormat(String USER_ID, String APP_ID, String OPER_DATE, String OPER_HOUR, String SEARCH_COUNT,
			String FLAG) {
		this.USER_ID = USER_ID;
		this.APP_ID = APP_ID;
		this.OPER_DATE = OPER_DATE;
		this.OPER_HOUR = OPER_HOUR;
		this.SEARCH_COUNT = SEARCH_COUNT;
		this.FLAG = FLAG;
	}

	

	@Override
	public int compareTo(SearchCountFormat o) {
		// TODO Auto-generated method stub
		return (Integer.parseInt(this.getSEARCH_COUNT())-Integer.parseInt(o.getSEARCH_COUNT()));
	}
	
	@Override
	public String toString(){
		return this.USER_ID+" "+this.APP_ID+" "+this.OPER_DATE+" "+this.SEARCH_COUNT;
	}
	
	

}
