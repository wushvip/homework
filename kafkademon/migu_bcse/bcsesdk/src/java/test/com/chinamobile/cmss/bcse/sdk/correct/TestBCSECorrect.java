package com.chinamobile.cmss.bcse.sdk.correct;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSECorrect;


public class TestBCSECorrect extends BaseUnit {

	@Test
	public void testCorrect() throws ClientProtocolException, IOException {
		String query="yg";
		BCSECorrect correct=new BCSECorrect(client);
		correct.setSearchQuery(query); 
		correct.setIsLoadDic("1"); 
		String result= correct.recovery();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}
	
	@Test
	public void testCorrect2() throws ClientProtocolException, IOException {
		BCSECorrect correct=new BCSECorrect(client);
		HashMap<String, Object> opts=new HashMap<>();
		opts.put("searchQuery" ,"jc");
		opts.put("isLoadDic" ,"1");
		String result= correct.recovery(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}

}
