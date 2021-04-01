package com.chinamobile.cmss.bcse.datastatistics.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.controller.SearchCostController;
import com.chinamobile.cmss.bcse.datastatistics.service.ISearchCostService;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;

public class SearchCostControllerTest{
	private String URL = "";
	private final String JSON="json";
	private final String userId="0c9b6d9580274fbf96b2accf3facf58c";
	private final String appId="d5f7efb0f039404db37fd23b80088a8c";
	JSONObject jsonObject=new JSONObject();
	@Mock
	private ISearchCostService iSearchCostService;
	@Mock
	private BindingResult result;
	@Mock
	private RequestAuthorization authService;
	
	@InjectMocks
	@Resource
	private SearchCostController searchCostController;
	
	
	@Before
	public void setUp() throws Exception {
		jsonObject.clear();
		URL="";
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}
	/**
	 * 
	 * @Title: testShowSearchCost 
	 * @Description: 搜索耗时controller层
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowSearchCost() throws Exception{
		LogReqBean logReqBean = new LogReqBean();
		logReqBean.setDimension("day");
		ResultBean resultBean =new ResultBean();
		Mockito.doNothing().when(iSearchCostService).showSearchCostByDay(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.doNothing().when(iSearchCostService).showSearchCostByHour(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(result.hasErrors()).thenReturn(false);
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		/*URL = "/statistics/cost?dimension=day&startDate=-7&endDate=-1&userList[]=[{appId:\"911cb3e7b1304934a709435d2ee2c2ef\",userId:\"de6ddb496e354247a1ad92da37089764\"]";
		//jsonObject.put(key, value);
		String json =jsonObject.toString();
		//json = "dimension=day&startDate=-7&endDate=-1&userList[]=[{appId:\"911cb3e7b1304934a709435d2ee2c2ef\",userId:\"de6ddb496e354247a1ad92da37089764\"]";
		this.mockMvc.perform(MockMvcRequestBuilders.get(URL, "json")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());*/
		searchCostController.showSearchCost(logReqBean, result);
		logReqBean.setDimension("hour");
		searchCostController.showSearchCost(logReqBean, result);
		Mockito.verify(iSearchCostService,Mockito.times(1)).showSearchCostByDay(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.verify(iSearchCostService,Mockito.times(1)).showSearchCostByHour(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
	}
}
