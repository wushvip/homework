package com.chinamobile.cmss.bcse.config.test;

import java.util.ArrayList;
import java.util.List;

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
public class ShieldRuleControllerTest extends BaseUnit{
	private String appId="f72369d00d18475b927998d5c480c193";
	private String userId="2a66470321094386afbe314a79c651fe";
	
//	@Test
	public void test001Post(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId",userId);
		jsonObject.put("ruleName","shield001");
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("屏蔽词1");
		includeKeywords.add("屏蔽词1");
		jsonObject.put("includeKeywords",includeKeywords);
		String json =jsonObject.toString();
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.post("/config/shield", "json")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).
					characterEncoding("UTF-8").content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test002Post(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId",userId);
		jsonObject.put("ruleName","shield002");
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("name");
		jsonObject.put("includeKeywords",includeKeywords);
		String json =jsonObject.toString();
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.post("/config/shield", "json")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).
					characterEncoding("UTF-8").content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//更新
	@Test
	public void test003Put(){
		String ruleName="shield001";
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId",userId);
		List<String> includeKeywords=new ArrayList<String>();
		includeKeywords.add("age");
		jsonObject.put("includeKeywords",includeKeywords);
		String json =jsonObject.toString();
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.put("/config/shield/"+ruleName, "json")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).
					characterEncoding("UTF-8").content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取
	@Test
	public void test004Get(){
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/config/shield", "json")
					.param("appId", appId)
					.param("userId",userId)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void test005Del(){
		String ruleName="shield001";
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.delete("/config/shield/"+ruleName, "json")
					.param("appId", appId)
					.param("userId",userId)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test006BashDel(){
		String ruleName="shield002";
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.delete("/config/shield/bash", "json")
					.param("appId", appId)
					.param("userId",userId)
					.param("ruleNames", ruleName)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
	
}
