package com.chinamobile.cmss.bcse.sdk.analysis;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEAnalysis;


public class TestBCSEAnalysis extends BaseUnit {

	@Test
	public void testAnalysis() throws ClientProtocolException, IOException {
		
		
		BCSEAnalysis analysis=new BCSEAnalysis(client);
		analysis.setSearchQuery("中国移动");
		analysis.setFieldSearch("ANSJ");
		String result= analysis. analysis();
		
		System.out.print(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}
	
	@Test
	public void testAnalysis2() throws ClientProtocolException, IOException {
		BCSEAnalysis analysis=new BCSEAnalysis(client);
		HashMap<String, Object> opts=new HashMap<>();
		opts.put("searchQuery", "保卫萝卜");
		opts.put("fieldSearch","ANSJ" );
		String result= analysis. analysis (opts);
		
		System.out.print(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
		
	}

}
