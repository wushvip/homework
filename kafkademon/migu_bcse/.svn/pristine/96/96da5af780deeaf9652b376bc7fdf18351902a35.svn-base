package com.chinamobile.cmss.bcse.sdk.config;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEShield;


public class TestBCSEShield extends BaseUnit {

	@Test
	public void testGet() throws ClientProtocolException, IOException {
		BCSEShield shield = new BCSEShield(client);
		String result=shield.get();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testInsert01() throws ClientProtocolException, IOException {
		BCSEShield shield = new BCSEShield(client);
		Map<String,Object> opts=new HashMap<String,Object>();
		opts.put("ruleName", "test03");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试");
		opts.put("includeKeywords", includeKeywords);
		
		String result=shield.insert(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testInsert02() throws ClientProtocolException, IOException {
		BCSEShield shield = new BCSEShield(client);
		shield.setRuleName("test02");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试");
		shield.setIncludeKeywords(includeKeywords);
		
		
		String result=shield.insert();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testUpdate01() throws ClientProtocolException, IOException {
		BCSEShield shield = new BCSEShield(client);
		shield.setRuleName("test01");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试1");
		shield.setIncludeKeywords(includeKeywords);
	
		
		String result=shield.update();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testUpdate02() throws ClientProtocolException, IOException {
		BCSEShield shield = new BCSEShield(client);
		
		Map<String,Object> opts=new HashMap<String,Object>();
		opts.put("ruleName", "test");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试1");
		opts.put("includeKeywords", includeKeywords);
		
		
		String result=shield.update(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testDelete() throws ClientProtocolException, IOException {
		BCSEShield shield = new BCSEShield(client);
		
		String ruleName="test01";
		String result=shield.delete(ruleName);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testBashDelete() throws ClientProtocolException, IOException {
		BCSEShield shield = new BCSEShield(client);
		
		List<String> ruleNames=new ArrayList<String>();
		ruleNames.add("test02");
		ruleNames.add("test03");
		String result=shield.bashDelete(ruleNames);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}


}
