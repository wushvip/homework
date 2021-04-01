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
import com.chinamobile.cmss.bcse.sdk.entry.BCSEElevate;


public class TestBCSEElevate extends BaseUnit {

	@Test
	public void testGet() throws ClientProtocolException, IOException {
		BCSEElevate elevate = new BCSEElevate(client);
		String result=elevate.get();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testInsert01() throws ClientProtocolException, IOException {
		BCSEElevate elevate = new BCSEElevate(client);
		Map<String,Object> opts=new HashMap<String,Object>();
		opts.put("ruleName", "test01");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试");
		opts.put("includeKeywords", includeKeywords);
		
		List<String> spreadIds=new ArrayList<String>();
		spreadIds.add("001");
		opts.put("spreadIds", spreadIds);
		
		String result=elevate.insert(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testInsert02() throws ClientProtocolException, IOException {
		BCSEElevate elevate = new BCSEElevate(client);
		elevate.setRuleName("test02");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试");
		elevate.setIncludeKeywords(includeKeywords);
		
		List<String> spreadIds=new ArrayList<String>();
		spreadIds.add("001");
		elevate.setSpreadIds(spreadIds);
		
		String result=elevate.insert();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testUpdate01() throws ClientProtocolException, IOException {
		BCSEElevate elevate = new BCSEElevate(client);
		elevate.setRuleName("test");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试");
		elevate.setIncludeKeywords(includeKeywords);
		
		List<String> spreadIds=new ArrayList<String>();
		spreadIds.add("001");
		elevate.setSpreadIds(spreadIds);
		
		String result=elevate.update();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testUpdate02() throws ClientProtocolException, IOException {
		BCSEElevate elevate = new BCSEElevate(client);
		
		Map<String,Object> opts=new HashMap<String,Object>();
		opts.put("ruleName", "test");
		
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("测试1");
		opts.put("includeKeywords", includeKeywords);
		
		List<String> spreadIds=new ArrayList<String>();
		spreadIds.add("001");
		opts.put("spreadIds", spreadIds);
		
		String result=elevate.update(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testDelete() throws ClientProtocolException, IOException {
		BCSEElevate elevate = new BCSEElevate(client);
		
		String ruleName="test";
		String result=elevate.delete(ruleName);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testBashDelete() throws ClientProtocolException, IOException {
		BCSEElevate elevate = new BCSEElevate(client);
		
		List<String> ruleNames=new ArrayList<String>();
		ruleNames.add("test01");
		ruleNames.add("test02");
		String result=elevate.bashDelete(ruleNames);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}


}
