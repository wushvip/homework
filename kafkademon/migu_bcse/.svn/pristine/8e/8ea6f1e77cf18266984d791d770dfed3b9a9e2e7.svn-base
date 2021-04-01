package com.chinamobile.cmss.bcse.search.test;



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
public class SearchTest extends BaseUnit {
	private String appId = "864a8159dc544ab59251c7474c828116";
	private String userId = "9e445583f16c44bb9d75eeb017eae96b";


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
		jsonObject.put("searchQuery", "*");
		jsonObject.put("sortConfig", "d");
		jsonObject.put("facetRule", "");
		//jsonObject.put("filterJson","");
		jsonObject.put("highLightFields","");
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/search", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFacetRule() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("searchQuery", "*");
		jsonObject.put("sortConfig", "chenmin");
		jsonObject.put("facetRule", "chenmin");
		jsonObject.put("highLightFields","");
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/search", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testHLightFields() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("searchQuery", "change.me");
		jsonObject.put("sortConfig", "chenmin");
		jsonObject.put("highLightFields","name");
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/search", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSortRule() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("searchQuery", "*");
		jsonObject.put("sortConfig", "chenmin");
		jsonObject.put("sortRule","id DESC");
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/search", "json").accept(MediaType.APPLICATION_JSON)
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
		jsonObject.put("searchQuery", "*");
		jsonObject.put("sortConfig", "chenmin");
		jsonObject.put("filterJson",filterJson);
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/search", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testAdvanceParamsQ() {
		JSONObject advanceParams = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("searchQuery", "*");
		jsonObject.put("sortConfig", "chenmin");
		advanceParams.put("q", "change.me");
		jsonObject.put("advancedParams", advanceParams);
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/search", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*	@Test
	public void testAdvanceParamsQT() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("q", "");
		jsonObject.put("qt", "");
		jsonObject.put("fq", "");
		jsonObject.put("fl","");
		jsonObject.put("hl","");
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/search", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}