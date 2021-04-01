package com.chinamobile.cmss.bcse.evaluate.test;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.base.BaseUnit;

public class AppInfoTest  extends BaseUnit {
	private String appId = "9e445583f16c44bb9d75eeb017eae96b";
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
		jsonObject.put("isThirdPart", "0");
		jsonObject.put("evaluateField", "name");
		jsonObject.put("dataType", "0");
		jsonObject.put("sortConfig", "chenmin");
		//jsonObject.put("userId", "chenmin");
		//jsonObject.put("filterJson","");
		//jsonObject.put("highLightFields","");
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/evaluation/app", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUploadDic(){
		String words="test";
		MockMultipartFile file=new MockMultipartFile("file", words.getBytes());
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/evaluation/dic")
					.file(file)
					.param("userId", "admin")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void testExcute(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", appId);
		jsonObject.put("userId", userId);
		jsonObject.put("dataId","");
		jsonObject.put("num", "10");
		
		//jsonObject.put("userId", "chenmin");
		//jsonObject.put("filterJson","");
		//jsonObject.put("highLightFields","");
		String json = jsonObject.toString();
		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/evaluation/result", "json").accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
							.content(json.getBytes()))
					.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testGetResult(){
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/evaluation/result", "json")
					.param("appId", appId)
					.param("userId",userId)
					.param("dataId", "")
					.param("num","10")
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
