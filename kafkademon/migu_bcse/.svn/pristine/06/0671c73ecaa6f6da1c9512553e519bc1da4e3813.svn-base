package com.chinamobile.cmss.bcse.suggest.test;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.base.BaseUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SuggestTest extends BaseUnit {
	private String appId = "9e445583f16c44bb9d75eeb017eae96b";
	private String userId = "2bc9d5dbf18b4fae92d2055eb8448ced";
	
	/** 
	 * @Title: test001Post 
	 * @Description: TODO 
	 * @return: void
	 */
	@Test
	public void testSearchQuery() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("searchQuery", "c");
		jsonObject.put("sortConfig", "a");
	  //jsonObject.put("filterJson","");
		String json = jsonObject.toString();
		System.out.println(json);
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/suggestion", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testFilterJson() {
		JSONObject jsonObject = new JSONObject();
		JSONObject filterJson = new JSONObject();
		filterJson.put("id", "change.me");
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("searchQuery", "c");
		jsonObject.put("sortConfig", "a");
		jsonObject.put("filterJson",filterJson);
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/suggestion", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}	}
	
	
	@Test
	public void testAdvanceParamsQ() {
		JSONObject advanceParams = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("searchQuery", "q");
		jsonObject.put("sortConfig", "a");
		advanceParams.put("q", "change.me");
		jsonObject.put("advancedParams", advanceParams);
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/suggestion", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}