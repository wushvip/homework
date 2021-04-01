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
import com.chinamobile.cmss.bcse.datastatistics.controller.HotWordsController;
import com.chinamobile.cmss.bcse.datastatistics.service.IHotWordsService;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;


public class HotWordsControllerTest{
	@Mock
	private IHotWordsService hotWordsService;
	@Mock
	private BindingResult result;
	@Mock
	private RequestAuthorization authService;
	
	@InjectMocks
	@Resource
	private HotWordsController hotWordsController;
	
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	    Mockito.when(result.hasErrors()).thenReturn(false);
	}

	@Test
	public void testShowHotWord()throws Exception {
		LogReqBean logReqBean =new LogReqBean();
		ResultBean resultBean = new ResultBean();
		Mockito.doThrow(new NullPointerException()).when(hotWordsService).showHotWord(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		resultBean = hotWordsController.showHotWord(logReqBean, result);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
		
	}

	@Test
	public void testShowHotWordByWeek() throws Exception{
		LogReqBean logReqBean =new LogReqBean();
		ResultBean resultBean = new ResultBean();
		Mockito.doThrow(new NullPointerException()).when(hotWordsService).showHotWordLastWeek(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		hotWordsController.showHotWordByWeek(logReqBean, result);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
	}

	@Test
	public void testShowHotWordByMonth() throws Exception{
		LogReqBean logReqBean =new LogReqBean();
		Mockito.doThrow(new NullPointerException()).when(hotWordsService).showHotWordLastMonth(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		hotWordsController.showHotWordByMonth(logReqBean, result);
	}

	@Test
	public void testShowHotWordYesterday() throws Exception{
		LogReqBean logReqBean =new LogReqBean();
		Mockito.doThrow(new NullPointerException()).when(hotWordsService).showHotWordYesterday(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		hotWordsController.showHotWordYesterday(logReqBean, result);
	}

}
