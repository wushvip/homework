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
import com.chinamobile.cmss.bcse.datastatistics.controller.ErrorLogController;
import com.chinamobile.cmss.bcse.datastatistics.service.IErrorLogService;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;

/**
 * @ClassName: ErrorLogControllerTest
 * @Description: TODO 获取错误日志单元测试（controller）
 * @author: zhuxiang
 * @date: 2017年3月9日
 */
public class ErrorLogControllerTest{
	@Mock
	private IErrorLogService errorLogService;
	@Mock
	private BindingResult result;
	@Mock
	private RequestAuthorization authService;
	

	@InjectMocks
	@Resource
	private ErrorLogController errorLogController;
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetErrorLog() throws Exception {
		LogReqBean logReqBean = new LogReqBean();
		ResultBean resultBean =new ResultBean();
		Mockito.doThrow(new RuntimeException()).when(errorLogService).showErrorLogs(Mockito.any(LogReqBean.class), Mockito.any(ResultBean.class));
		Mockito.when(result.hasErrors()).thenReturn(false);
		Mockito.when(authService.paramCheck(Mockito.any(JSONObject.class),Mockito.anyBoolean())).thenReturn(true);
		resultBean = errorLogController.getErrorLog(logReqBean, result);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
		Assert.assertEquals("预期返回FAIL", "FAIL", resultBean.getStatus());
	}

}
