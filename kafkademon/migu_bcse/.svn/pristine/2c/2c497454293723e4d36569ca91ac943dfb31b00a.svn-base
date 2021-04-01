package com.chinamobile.cmss.bcse.config.test;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.chinamobile.cmss.bcse.base.BaseUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnalysisControllerTest extends BaseUnit{
	private String appId="f72369d00d18475b927998d5c480c193s";
	private String userId="2a66470321094386afbe314a79c651fe";
	
	private String fieldSearch="ANSJ";
	private String searchQuery="保卫萝卜";
	//获取
	@Test
	public void test004Get(){
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/analyzer", "json")
					.param("appId", appId)
					.param("userId",userId)
					.param("fieldSearch", fieldSearch)
					.param("searchQuery",searchQuery)
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
