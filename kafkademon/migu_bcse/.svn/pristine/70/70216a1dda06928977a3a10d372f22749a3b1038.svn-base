package com.chinamobile.cmss.bcse.datastatistics.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.controller.SearchTimesController;
import com.chinamobile.cmss.bcse.datastatistics.service.ISearchTimesService;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;

public class SearchTimesControllerTest{
	@Mock
	private ISearchTimesService isearchTimesService;
	@Mock
	private BindingResult result;
	@Mock
	private RequestAuthorization authService;
	
	@InjectMocks
	@Resource
	private SearchTimesController searchTimesController;
	
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void testGetSearchTimes() {
		LogReqBean logReqBean = new LogReqBean();
		logReqBean.setDimension("day");
		ResultBean resultBean =new ResultBean();
		Mockito.doNothing().when(isearchTimesService).showSearchTimesByDay(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.doNothing().when(isearchTimesService).showSearchTimesByHour(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(result.hasErrors()).thenReturn(false);
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		searchTimesController.getSearchTimes(logReqBean, result);
		logReqBean.setDimension("hour");
		searchTimesController.getSearchTimes(logReqBean, result);
		Mockito.verify(isearchTimesService,Mockito.times(1)).showSearchTimesByDay(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.verify(isearchTimesService,Mockito.times(1)).showSearchTimesByHour(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
	}

}
