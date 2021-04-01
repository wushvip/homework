package com.chinamobile.cmss.bcse.sdk.searchnum;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSESearchNum;


public class TestBCSESearchNum extends BaseUnit{
	@Test
	public void testSearchNum1() throws ClientProtocolException, IOException {
		BCSESearchNum searchNum = new BCSESearchNum(client);
		searchNum.setEndDate(-1);
		searchNum.setStartDate(-7);
		String s = searchNum.searchcost();
		System.out.println(s);
	}
	
	@Test
	public void testSearchNum2() throws ClientProtocolException, IOException {
		BCSESearchNum searchNum = new BCSESearchNum(client);
		JSONObject obj1 = new JSONObject();
		obj1.put("startDate", -10);
		obj1.put("endDate", -1);
		String s = searchNum.searchcost(obj1);
		System.out.println(s);
	}
}
