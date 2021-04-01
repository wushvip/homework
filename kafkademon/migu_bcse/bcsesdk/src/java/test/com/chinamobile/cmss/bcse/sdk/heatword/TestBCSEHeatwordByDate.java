package com.chinamobile.cmss.bcse.sdk.heatword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEHeatWordByDate;


public class TestBCSEHeatwordByDate extends BaseUnit {

	@Test
	public void testHeatwordDay() throws ClientProtocolException, IOException {
		try {
			
		BCSEHeatWordByDate heatWord=new BCSEHeatWordByDate(client);
		heatWord.setStartDate("2017-04-11");
		heatWord.setEndDate("2017-05-17");
		heatWord.setCt("ct");
		heatWord.setBookType("book");
		heatWord.setPageIndex(1);
		heatWord.setPageNum(10);
		String result= heatWord.heatWords();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHeatwordDay2() throws ClientProtocolException, IOException {
		try {
			
		BCSEHeatWordByDate heatWord=new BCSEHeatWordByDate(client);
		JSONObject obj1 = new JSONObject();
		obj1.put("startDate", "2017-04-11");
		obj1.put("endDate", "2017-05-17");
		obj1.put("ct", "ct");
		obj1.put("bookType", "book");
		String result= heatWord.heatWords(obj1);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
