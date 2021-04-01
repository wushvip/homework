package com.chinamobile.cmss.bcse.config.test;



import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.chinamobile.cmss.bcse.base.BaseUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpellCheckControllerTest extends BaseUnit{
	private String appId="9a8e43ec1c304dfda3a8859292dfe82d";
	private String userId="2a66470321094386afbe314a79c651fe";
	
	
	
	
	//纠错
	@Test
	public void test002Recovery(){
		String isLoadDic="1";
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/recovery", "json")
					.param("appId", appId)
					.param("userId",userId)
					.param("isLoadDic", String.valueOf(isLoadDic))
					.param("searchQuery","sang")
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
	public void test002Reco(){
		String isLoadDic="0";
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/recovery", "json")
					.param("appId", appId)
					.param("userId",userId)
					.param("isLoadDic", String.valueOf(isLoadDic))
					.param("searchQuery","zw")
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
	public void test001Train(){
		String searchQuery="移动 植物 三国";
		MockMultipartFile file=new MockMultipartFile("file", searchQuery.getBytes());
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/recovery/dic")
					.file(file)
					.param("appId", appId)
					.param("userId", userId)
					.param("mode", "1")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
