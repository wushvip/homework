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
public class DicUploadControllerTest extends BaseUnit{
	private String appId="f72369d00d18475b927998d5c480c193";
	private String userId="2a66470321094386afbe314a79c651fe";
	
	//@Test
	public void testUploadStop(){
		String words="{";
		MockMultipartFile file=new MockMultipartFile("file", words.getBytes());
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/config/dic/stop")
					.file(file)
					.param("appId", appId)
					.param("userId", userId)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUploadExt(){
		String words="开心消消乐";
		MockMultipartFile file=new MockMultipartFile("file", words.getBytes());
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/config/dic/extention")
					.file(file)
					.param("appId", appId)
					.param("userId", userId)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUploadSyno(){
		String words="开心,高兴";
		MockMultipartFile file=new MockMultipartFile("file", words.getBytes());
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/config/dic/synonym")
					.file(file)
					.param("appId", appId)
					.param("userId", userId)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.characterEncoding("UTF-8"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//@Test
	public void testUploadAmbiguity(){
		String words="小鸟迷情	小鸟	n	迷情	n";
		MockMultipartFile file=new MockMultipartFile("file", words.getBytes());
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/config/dic/ambiguity")
					.file(file)
					.param("appId", appId)
					.param("userId", userId)
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
