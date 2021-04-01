package com.chinamobile.cmss.bcse.sdk.config;



import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.base.BaseUnit;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEAmbiguityDic;



public class TestBCSEAmbiguityDic extends BaseUnit {

	
	
	@Test
	public void testUpload() throws Exception {
		BCSEAmbiguityDic dic=new BCSEAmbiguityDic(client);
		String fileName="D:/cmcc_project/BCSE产品/1.3.0开发/备份/q3.txt";
		String result=dic.upload(fileName);
		System.out.println(result);
		JSONObject object =JSONObject.parseObject(result);
		Assert.assertEquals(object.get("status"), "SUCCESS");
	}

}
