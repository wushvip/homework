package com.chinamobile.cmss.bcselogAnalyse.bean;

import java.util.ArrayList;
import java.util.List;

public class NumFoundDay {

	private String userId;
	private String appId;
	private String date;
	private Range range;
	private int searchCount;

	public NumFoundDay() {
	}

	public NumFoundDay(String userId, String appId, String date, Range range) {
		this.userId = userId;
		this.appId = appId;
		this.date = date;
		this.range = range;
		this.searchCount = 0;
	}

	public NumFoundDay(String userId, String appId, String date, Range range, int searchCount) {
		this.userId = userId;
		this.appId = appId;
		this.date = date;
		this.range = range;
		this.searchCount = searchCount;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

	/**
	 * 初始化区间段
	 */
	public static final List<Range> RANGE_LIST = new ArrayList<Range>() {
		private static final long serialVersionUID = 1L;
		{
			this.add(new Range(0, 0));
			this.add(new Range(1, 10));
			this.add(new Range(11, 50));
			this.add(new Range(51, 100));
			this.add(new Range(101, 200));
			this.add(new Range(201, Integer.MAX_VALUE));
		}
	};

	/**
	 * 根据数值获取对应区间
	 * 
	 * @param num
	 * @return
	 */
	public static final Range getRangeByNum(int num) {
		for (Range range : RANGE_LIST) {
			if (range.getMin() <= num && num <= range.getMax()) {
				return range;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		for (Range r : NumFoundDay.RANGE_LIST) {
			System.out.println(r.toString());
		}
	}

}
