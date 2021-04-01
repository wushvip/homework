package com.chinamobile.cmss.bcse.datastatistics.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Assert;
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
import com.chinamobile.cmss.bcse.datastatistics.controller.DataInfoController;
import com.chinamobile.cmss.bcse.datastatistics.service.IDataInfoService;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;


public class DataInfoControllerTest{
	@Mock
	private IDataInfoService dataInfoService;
	@Mock
	private BindingResult result;
	@Mock
	private RequestAuthorization authService;
	

	@InjectMocks
	@Resource
	private DataInfoController dataInfoController;
	
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShowDataInfo()throws Exception {
		LogReqBean logReqBean = new LogReqBean();
		ResultBean resultBean =new ResultBean();
		//Mockito.doNothing().when(dataInfoService).showDataInfo(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.doThrow(new RuntimeException()).when(dataInfoService).showDataInfo(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(result.hasErrors()).thenReturn(false);
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		resultBean = dataInfoController.showDataInfo(logReqBean, result);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
		Assert.assertEquals("预期返回FAIL", "FAIL", resultBean.getStatus());
		
	}

}
