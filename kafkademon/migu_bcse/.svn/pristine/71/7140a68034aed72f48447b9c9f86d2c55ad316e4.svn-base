package com.chinamobile.cmss.bcse.sdk.suggest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.entry.BCSESuggest;


public class TestBCSESuggest extends BaseUnit {
	/*@Mock
	BCSEClient client;
	
	@Before
	public void setUp(){
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}*/
	@Test
	public void testBCSESuggest() throws Exception{
		BCSESuggest suggest1=new BCSESuggest(client);
		HashMap<String, Object> opts=new HashMap<>();
		JSONObject  json = new JSONObject();
		opts.put("sortConfig", "zhineng");
		opts.put("searchQuery", "yingguo");
		//opts.put("filterJson", json);
		String result= suggest1.suggest(opts);
		System.out.println(result);
		
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}

}
