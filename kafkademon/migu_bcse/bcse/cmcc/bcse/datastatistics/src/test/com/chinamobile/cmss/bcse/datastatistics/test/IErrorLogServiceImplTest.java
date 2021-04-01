package com.chinamobile.cmss.bcse.datastatistics.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chinamobile.cmss.bcse.datastatistics.bean.ErrorLogBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.ErrorLogDao;
import com.chinamobile.cmss.bcse.datastatistics.service.impl.IErrorLogServiceImpl;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

/**
 * @ClassName: IErrorLogServiceImplTest
 * @Description: TODO 
 * @author: zhuxiang
 * @date: 2017年3月10日
 */
public class IErrorLogServiceImplTest {
	@Mock
	private ErrorLogDao errorLogDao;
	
	@InjectMocks
	//@Resource
	private IErrorLogServiceImpl errorLogServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		// 初始化Mock
	    MockitoAnnotations.initMocks(this);
	   /* //注入Mock
	    errorLogServiceImpl = new IErrorLogServiceImpl();
	    Field testAField = errorLogServiceImpl.getClass().getDeclaredField("errorLogDao");
		testAField.setAccessible(true);
		testAField.set(errorLogServiceImpl, errorLogDao);*/
	}

	@Test
	public void testShowErrorLogs()throws Exception {
		//请求构造
		LogReqBean logReqBean = new LogReqBean();
		logReqBean.setAppId("appid");
		logReqBean.setUserId("userid");
		logReqBean.setPageIndex(1);
		logReqBean.setPageNum(10);
		logReqBean.setStartDate(-7);
		logReqBean.setEndDate(-1);
		ResultBean resultBean = new ResultBean();
		Mockito.when(errorLogDao.selectErrorLogAsDays(Mockito.any(LogReqBean.class))).thenReturn(null);
		Mockito.when(errorLogDao.getTotalItemsErrorLog(Mockito.any(LogReqBean.class))).thenReturn(9);
		errorLogServiceImpl.showErrorLogs(logReqBean, resultBean);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
	}

	@Test
	public void testInsertErrorLogs()throws Exception {
		//请求构造
		ErrorLogBean errorLogReqBean = new ErrorLogBean();
		errorLogReqBean.setAppId("appid");
		errorLogReqBean.setCode(30000);
		errorLogReqBean.setDetail("errorDetails");
		errorLogReqBean.setFlag(11);
		errorLogReqBean.setId(00000001);
		errorLogReqBean.setCreateDate("2017.03.10");
		errorLogReqBean.setModule("module");
		errorLogReqBean.setUserId("userId");
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", errorLogReqBean.getUserId());
		infoParam.put("APP_ID", errorLogReqBean.getAppId());
		infoParam.put("LOG_TIME", errorLogReqBean.getCreateDate().toString());
		infoParam.put("FLAG", errorLogReqBean.getFlag().toString());
		infoParam.put("MODULE", errorLogReqBean.getModule());
		infoParam.put("ERROR_CODE", errorLogReqBean.getCode().toString());
		infoParam.put("ERROR_DETAILS", errorLogReqBean.getDetail());
		//结果构造
		Mockito.when(errorLogDao.insertErrorLog(infoParam)).thenReturn(8);
		System.out.println("testInsertErrorLogs:"+errorLogServiceImpl.insertErrorLogs(errorLogReqBean));
	}

}
