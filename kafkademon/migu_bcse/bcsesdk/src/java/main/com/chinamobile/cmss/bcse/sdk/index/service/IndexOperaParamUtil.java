package com.chinamobile.cmss.bcse.sdk.index.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.chinamobile.cmss.bcse.sdk.constant.Constant;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.index.bean.AppRspBean;
import com.chinamobile.cmss.bcse.sdk.index.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.sdk.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.sdk.tools.AssembleAppResult;
import com.chinamobile.cmss.bcse.sdk.tools.LogUtil;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;
import com.chinamobile.cmss.bcse.sdk.util.IsGoodJson;


public class IndexOperaParamUtil {

	/**
	 * 发送http请求，获取索引时需要的数据表的数据
	 * 
	 * @param userId
	 * @param appId
	 * @param tablePropertiesList
	 * @param sourceFileMap
	 * @throws Exception
	 */
	/*public static void getDataTableParam(
			ArrayList<TableProperty> tablePropertiesList,
			LinkedHashMap<String, String> sourceFileMap, BCSEClient client)
			throws Exception {

		HashMap<String, Object> opts = new HashMap<String, Object>();
		opts.put("method", "getStructure");

		String result = client.call(Constant.APP_POST_URL+"/sdk/getStructure", opts, Constant.POST);

		JSONObject jsonObject = AssembleAppResult.assembleResult(result);
		AppRspBean appRspBean = AssembleAppResult.assembleResultBean( jsonObject.get("result").toString());
		sourceFileMap.putAll(appRspBean.getSourceFileMap());
		tablePropertiesList.addAll(appRspBean.getTablePropertiesList());
		return;
	}*/
	
	
	public static String getAppTableId(
			 BCSEClient client)
			throws Exception {
		LogUtil.loggerEntrance(LogUtil.INDEX_LOG,"getAppTableId start");
		HashMap<String, Object> opts = new HashMap<String, Object>();
		opts.put("userId",client.getUserId() );

		String result = client.call(Constant.APP_POST_URL+"/"+client.getAppId()+"/structure", opts, Constant.GET);
		if(!IsGoodJson.isGoodJson(result)){return "";}
		JSONObject jsonObject = JSONObject.parseObject(result);
		if(jsonObject.get("result")==null){return "";}
		JSONObject resultJson = JSONObject.parseObject(jsonObject.get("result").toString());
		JSONArray appTableListJson = resultJson.getJSONArray("appTableList");
		if(appTableListJson==null){return "";}
		AppTableMapBean appTableMapBean = JSONObject.parseObject(appTableListJson.get(0).toString(),AppTableMapBean.class);
		System.out.println("tableId:"+appTableMapBean.getTableId());
		return appTableMapBean.getTableId();
	}
	
}
