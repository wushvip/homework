package com.chinamobile.cmss.bcse.sdk.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.entry.BCSESearch;


public class BaseUnit {
	

	 static String appId="2f8470d59c254c748a541307a1dc64b2"; 
	 static String userId="admin";
	 public static BCSEClient client;
	// static String ip="10.139.4.20";
//	 static String ip="172.16.208.225";
//	 static String ip="10.139.6.200";
	 static String ip="127.0.0.1";
	 static int port=8080;
	 static String secretAccessKey ="2968eb1e1f0f45e1bbfa2905b4f15c3e";
	 
	 
	/* static String userId="d2f93ebf533746f6af910eef7f45e7be";//admin
	 static String secretAccessKey ="de2763f0577c4c06b3851b17df7e3924";*/
	 
	 @Before
	 public void setUp(){
		try {
//			client=new BCSEClient(appId,userId,ip,port,secretAccessKey);
			//client=new BCSEClient(appId,ip,port,"bcse-1.3.0",secretAccessKey);
			Map<String, Object> opts =new HashMap<String, Object>();
			//client=new BCSEClient(appId,userId,ip,port,opts,"bcse",secretAccessKey);
			client=new BCSEClient(appId,userId,ip,port,opts,"migu-api-1.0.1-20170803014540",secretAccessKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 
	 public static void main(String[] args) throws Exception{
		 BCSEClient client = new BCSEClient(appId,userId,ip,port,secretAccessKey);
		 BCSESearch sea=new BCSESearch(client);
		 Map<String, Object> opts=new HashMap<String,Object>();
		 JSONObject filterJson=new JSONObject();
	     filterJson.put("OPERATOR_CODE", "700022098000");
		 opts.put("searchQuery", "游戏");
		 opts.put("rankType", 0);
		 opts.put("fieldSearch", "default");
		 opts.put("filterJson", filterJson);
		 //opts.put("sortRule","RESE0#asc");
		// opts.put("filterRules","VALID_DATE:[2013-02-26T00:00:00Z TO 2015-02-26T00:00:00Z]@RESE5:{-2 TO 7}");
		// opts.put("pageIndex",-1);
		 opts.put("pageNum",10);
		 //opts.put("facetRule","RESE3");
		 String result=sea.search(opts);
		 System.out.print(result);
	 }

}
