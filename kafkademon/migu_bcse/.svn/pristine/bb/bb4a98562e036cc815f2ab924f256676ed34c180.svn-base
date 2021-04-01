package com.chinamobile.cmss.bcse.sdk.searchcost;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSESearchCost;


public class TestBCSESearchCost extends BaseUnit{
	@Test
	public void testSearchCost1() throws ClientProtocolException, IOException {
		BCSESearchCost SearchCost = new BCSESearchCost(client);
		SearchCost.setDimension("day");
		SearchCost.setEndDate(-1);
		SearchCost.setStartDate(-7);
		SearchCost.setCt("ct");
		SearchCost.setBookType("book");
		String result = SearchCost.searchcost();
		System.out.println(result);
	}
	
	@Test
	public void testSearchCost2() throws ClientProtocolException, IOException {
		BCSESearchCost SearchCost = new BCSESearchCost(client);
		JSONObject obj1 = new JSONObject();
		obj1.put("dimension", "hour");
		obj1.put("startDate", -2);
		obj1.put("endDate", -1);
		obj1.put("ct", "ct");
		obj1.put("bookType", "book");
		String result =SearchCost.searchcost(obj1);
		System.out.println(result);
	}
	
		

}
