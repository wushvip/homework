package com.chinamobile.cmss.bcse.sdk.index.service;

import java.util.HashMap;

import com.chinamobile.cmss.bcse.sdk.constant.Constant;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;

/**
 * 发送请求设置数据量
 * 
 * @author Administrator
 * 
 */
public class DataApi {

	public void setNumToDataBase(BCSEClient client) {
		HashMap<String, Object> opts = new HashMap<String, Object>();
		// opts.put("method", "setDataApi");
		try {
			client.call(Constant.APP_POST_URL + "/sdk/updateDataApi", opts, Constant.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
