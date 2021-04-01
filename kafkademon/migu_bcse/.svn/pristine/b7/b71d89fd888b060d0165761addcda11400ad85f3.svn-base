package com.chinamobile.cmss.bcse.sdk.searchtimes;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSESearchTimes;

public class TestBCSESearchTimes extends BaseUnit{

	@Test
	public void testSearchTimes1() throws ClientProtocolException, IOException {
		BCSESearchTimes searchTimes = new BCSESearchTimes(client);
		searchTimes.setDimension("day");
		searchTimes.setEndDate(-1);
		searchTimes.setStartDate(-7);
		String s = searchTimes.searchcost();
		System.out.println(s);
	}
	
	@Test
	public void testSearchTimes2() throws ClientProtocolException, IOException {
		BCSESearchTimes searchTimes = new BCSESearchTimes(client);
		JSONObject obj1 = new JSONObject();
		obj1.put("dimension", "hour");
		obj1.put("startDate", -2);
		obj1.put("endDate", -1);
		String s = searchTimes.searchcost(obj1);
		System.out.println(s);
	}
}
