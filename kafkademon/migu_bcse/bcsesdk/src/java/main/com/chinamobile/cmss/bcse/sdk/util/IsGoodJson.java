package com.chinamobile.cmss.bcse.sdk.util;

import com.alibaba.fastjson.JSON;

public class IsGoodJson {
	public static boolean isGoodJson(String json) {    
		   try {    
		       JSON.parseObject(json);  
		       return true;    
		   } catch (Exception e) {    
		       System.out.println("bad json: " + json);    
		       return false;    
		   }    
		}
}
