package com.chinamobile.cmss.bcse.sdk.heatword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEHeatWord;


public class TestBCSEHeatword extends BaseUnit {

	@Test
	public void testHeatwordDay() throws ClientProtocolException, IOException {
		try {
			

		/*BCSEHeatWord heatWord=new BCSEHeatWord(client);
		HashMap<String, Object> opts=new HashMap<>();
		opts.put("dateType" ,"WEEK");
		opts.put("pageIndex", 2);
		opts.put("pageNum", 10);
		String result= heatWord.heatWords(opts);
		System.out.println("result:"+result);*/
		BCSEHeatWord heatWord=new BCSEHeatWord(client);
		heatWord.setDateType("DAY");
		heatWord.setCt("ct");
		heatWord.setBookType("book");
		heatWord.setPageIndex(2);
		heatWord.setPageNum(1);
		String result= heatWord.heatWords();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHeatwordWeek() throws ClientProtocolException, IOException {
		
		BCSEHeatWord heatWord=new BCSEHeatWord(client);
		HashMap<String, Object> opts=new HashMap<>();
		opts.put("dateType" ,"WEEK");
		heatWord.setCt("ct");
		heatWord.setBookType("book");
		String result= heatWord.heatWords(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testHeatwordMonth() throws ClientProtocolException, IOException {
		
		BCSEHeatWord heatWord=new BCSEHeatWord(client);
		HashMap<String, Object> opts=new HashMap<>();
		opts.put("dateType" ,"MONTH");
		heatWord.setCt("ct");
		heatWord.setBookType("book");
		//opts.put("dateType" ,"DAY");
		String result= heatWord.heatWords(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}

}
