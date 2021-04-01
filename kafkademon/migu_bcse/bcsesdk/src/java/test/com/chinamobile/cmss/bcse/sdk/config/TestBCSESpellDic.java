package com.chinamobile.cmss.bcse.sdk.config;



import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSESpellDic;



public class TestBCSESpellDic extends BaseUnit {

	
	
	@Test
	public void testUpload01() throws Exception {
		BCSESpellDic dic=new BCSESpellDic(client);
		String fileName="D:/cmcc_project/BCSE产品/1.3.0开发/备份/spell.txt";
		String mode="0";
		dic.setFileName(fileName);
		dic.setMode(mode);
		String result=dic.upload();
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}
	
	@Test
	public void testUpload02() throws Exception {
		BCSESpellDic dic=new BCSESpellDic(client);
		String fileName="D:/cmcc_project/BCSE产品/1.3.0开发/备份/spell.txt";
		String mode="0";
		Map<String,Object> opts=new HashMap<String,Object>();
		opts.put("fileName", fileName);
		opts.put("mode", mode);
		String result=dic.upload(opts);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}

}
