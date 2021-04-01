package com.chinamobile.cmss.bcse.datastatistics.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.datastatistics.bean.DataInfoBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.UserAppBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.DataInfoDao;
import com.chinamobile.cmss.bcse.datastatistics.service.impl.IDataInfoServiceImpl;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;


public class IDataInfoServiceImplTest{
	
	@Mock
	private DataInfoDao dataInfoDao;
	
	private IDataInfoServiceImpl iDataInfoServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		// 初始化Mock
	    MockitoAnnotations.initMocks(this);
	    //注入Mock
	    iDataInfoServiceImpl = new IDataInfoServiceImpl();
		Field testAField = iDataInfoServiceImpl.getClass().getDeclaredField("dataInfoDao");
		testAField.setAccessible(true);
		testAField.set(iDataInfoServiceImpl, dataInfoDao);
	}

	@Test
	public void testShowDataInfo() throws Exception {
		//请求构造
		UserAppBean userAppBean = new UserAppBean();
		userAppBean.setAppId("appid");
		userAppBean.setUserId("userid");
		ArrayList<UserAppBean> arr = new ArrayList<UserAppBean>();
		arr.add(userAppBean);
		LogReqBean logReqBean = new LogReqBean();
		logReqBean.setAppId("appid");
		logReqBean.setUserId("userid");
		logReqBean.setUserList(arr);
		logReqBean.setStartDate(-7);
		logReqBean.setEndDate(-1);
		ResultBean resultBean = new ResultBean();
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", logReqBean.getUserId());
		infoParam.put("APP_ID", logReqBean.getAppId());
		infoParam.put("OPER_DATE_START", Tool.getDayTime(logReqBean.getStartDate()));
		infoParam.put("OPER_DATE_END", Tool.getDayTime(logReqBean.getEndDate() + 1));
		//结果构造
		DataInfoBean dataInfoBean = new DataInfoBean();
		dataInfoBean.setDateTime("2017-03-12");
		dataInfoBean.setSize(3);
		DataInfoBean dataInfoBean2 = new DataInfoBean();
		dataInfoBean2.setDateTime("2017-03-10");
		dataInfoBean2.setSize(12);
		DataInfoBean dataInfoBean3 = new DataInfoBean();//此日期为开始日期前
		dataInfoBean3.setDateTime("2016-12-24");
		dataInfoBean3.setSize(999);
		ArrayList<DataInfoBean> array = new ArrayList<DataInfoBean>();
		array.add(dataInfoBean);
		array.add(dataInfoBean2);
		ArrayList<DataInfoBean> arraybefore = new ArrayList<DataInfoBean>();
		arraybefore.add(dataInfoBean3);
		Mockito.when(dataInfoDao.selectdataNumberByDay(infoParam)).thenReturn(null);
		Mockito.when(dataInfoDao.selectdataNumberBefore(infoParam)).thenReturn(arraybefore).thenReturn(null);
		Mockito.when(dataInfoDao.selectdataNumberRecently(infoParam)).thenReturn(array);

		iDataInfoServiceImpl.showDataInfo(logReqBean, resultBean);
		System.out.println("正常测试1:开始日期没有对应的值");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
		
		iDataInfoServiceImpl.showDataInfo(logReqBean, resultBean);
		System.out.println("正常测试2:开始日期和之前日期都没有对应的值");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());

		arr.clear();
		logReqBean.setUserList(arr);
		iDataInfoServiceImpl.showDataInfo(logReqBean, resultBean);
		System.out.println("分支测试：上传的用户列表信息不能为空");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
		
	}

	@Test
	public void testDataNumberNew() throws Exception{
		//参数构造
		String userId = "userid";
		String appId ="appid";
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", userId);
		infoParam.put("APP_ID", appId);
		infoParam.put("OPER_DATE", Tool.getDayTime(-1));
		//结果构造
		DataInfoBean dataInfoBean = new DataInfoBean();
		dataInfoBean.setAppId("testAppId");
		dataInfoBean.setUserId("testUserId");
		Mockito.when(dataInfoDao.selectdataNumberNew(infoParam)).thenReturn(dataInfoBean);
		
		iDataInfoServiceImpl.dataNumberNew(userId, appId);
		assertEquals("预期结果一致",dataInfoBean,dataInfoDao.selectdataNumberNew(infoParam));
	}

	@Test
	public void testInsertDataLatest() throws Exception{
		DataInfoBean dataInfoBean = new DataInfoBean();
		Mockito.when(dataInfoDao.insertdataNumberLatest(Mockito.any(DataInfoBean.class))).thenReturn(5);
		assertEquals("预期结果是5",5,iDataInfoServiceImpl.insertDataLatest(dataInfoBean));
	}

}
