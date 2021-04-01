package com.chinamobile.cmss.bcse.sdk.tools;

import com.alibaba.fastjson.JSONObject;

public class ResultDecorate {

	/**
	 * @Title: decorate
	 * @Description: TODO 按固定格式封装结果
	 * @param jsonObject
	 * @param status
	 * @param message
	 * @param code
	 * @return
	 * @return: String
	 */
	public static String decorate(String jsonObject, String status, String message, String code) {

		JSONObject object = new JSONObject();
		object.put("status", status);
		object.put("code", code);
		object.put("message", message);
		object.put("result", jsonObject);

		return object.toString();

	}

	/**
	 * 重载方法
	 * 
	 * @param status
	 * @return
	 */
	public static String decorate(Boolean status) {

		if (status) {
			return decorate("", "SUCCESS", "", "");
		} else {
			return decorate("", "FAIL", "", "");
		}

	}

	public static String decorate(Boolean status, String msg) {

		if (status) {
			return decorate("", "SUCCESS", msg, "");
		} else {
			return decorate("", "FAIL", msg, "");
		}

	}

}
