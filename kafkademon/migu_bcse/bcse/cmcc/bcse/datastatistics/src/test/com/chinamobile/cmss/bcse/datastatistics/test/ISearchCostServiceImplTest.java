package com.chinamobile.cmss.bcse.datastatistics.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogSearchCostTimeBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.UserAppBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.SearchCostDao;
import com.chinamobile.cmss.bcse.datastatistics.service.impl.ISearchCostServiceImpl;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

public class ISearchCostServiceImplTest{
	@Mock
	private SearchCostDao searchCostDao;
	
	/*@InjectMocks
	@Resource*/
	private  ISearchCostServiceImpl iSearchCostServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * 
	 * @Title: testShowSearchCostByDay
	 * @Description: 搜索耗时（获取应用以天为单位的搜索耗时数据量）
	 * @throws Exception
	 * @return: void
	 */
	
	@Test
	public void testShowSearchCostByDay() throws Exception{
		UserAppBean userAppBean = new UserAppBean();
		userAppBean.setAppId("appid");
		userAppBean.setUserId("userid");
		ArrayList arr = new ArrayList();
		arr.add(userAppBean);
		LogReqBean logReqBean = new LogReqBean();
		logReqBean.setAppId("appid");
		logReqBean.setUserId("userid");
		logReqBean.setUserList(arr);
		logReqBean.setStartDate(-7);
		logReqBean.setEndDate(-1);
		ResultBean resultBean = new ResultBean();
		LogSearchCostTimeBean logSearchCostTimeBean = new LogSearchCostTimeBean();
		logSearchCostTimeBean.setAvg_cost(5);
		logSearchCostTimeBean.setMax_cost(10);
		logSearchCostTimeBean.setMin_cost(0);
		logSearchCostTimeBean.setOper_date("2017");
		logSearchCostTimeBean.setOper_hour(1);
		LogSearchCostTimeBean logSearchCostTimeBean2 = new LogSearchCostTimeBean();
		logSearchCostTimeBean2.setAvg_cost(50);
		logSearchCostTimeBean2.setMax_cost(100);
		logSearchCostTimeBean2.setMin_cost(2);
		logSearchCostTimeBean2.setOper_date("2017.03.01");
		logSearchCostTimeBean2.setOper_hour(10);
		ArrayList<LogSearchCostTimeBean> array = new ArrayList<LogSearchCostTimeBean>();
		array.add(logSearchCostTimeBean);
		array.add(logSearchCostTimeBean2);
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", logReqBean.getUserId());
		infoParam.put("APP_ID", logReqBean.getAppId());
		infoParam.put("OPER_DATE_START", Tool.getDayTime(logReqBean.getStartDate()));
		infoParam.put("OPER_DATE_END", Tool.getDayTime(logReqBean.getEndDate() + 1));

		Mockito.when(searchCostDao.selectSearchCostPerDay(infoParam)).thenReturn(array);
		//Mockito.when(searchCostDao.selectSearchCostPerDay(infoParam)).thenThrow(new NullPointerException());
		//Mockito.when(searchCostDao.selectSearchCostPerDay((HashMap)Mockito.anyMapOf(String.class, String.class))).thenReturn(array);
		//Mockito.when(searchCostDao.selectSearchCostPerDay((HashMap)Mockito.anyMapOf(String.class, String.class))).thenThrow(new NullPointerException());
		
		iSearchCostServiceImpl = new ISearchCostServiceImpl();
		Field testAField = iSearchCostServiceImpl.getClass().getDeclaredField("searchCostDao");
		testAField.setAccessible(true);
		testAField.set(iSearchCostServiceImpl, searchCostDao);
		iSearchCostServiceImpl.showSearchCostByDay(logReqBean, resultBean);
		System.out.println("正常测试");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
		
		arr.clear();
		logReqBean.setUserList(arr);
		iSearchCostServiceImpl.showSearchCostByDay(logReqBean, resultBean);
		System.out.println("异常测试：上传的用户列表信息不能为空");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
		
		
		
	}

	/**
	 * 
	 * @Title: testShowSearchCostByHour
	 * @Description: 搜索耗时（获取应用以小时为单位的搜索耗时数据量）
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowSearchCostByHour() throws Exception{
		UserAppBean userAppBean = new UserAppBean();
		userAppBean.setAppId("appid");
		userAppBean.setUserId("userid");
		ArrayList arr = new ArrayList();
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
		
		
		LogSearchCostTimeBean logSearchCostTimeBean = new LogSearchCostTimeBean();
		logSearchCostTimeBean.setAvg_cost(5);
		logSearchCostTimeBean.setMax_cost(10);
		logSearchCostTimeBean.setMin_cost(0);
		logSearchCostTimeBean.setOper_date("2017");
		logSearchCostTimeBean.setOper_hour(1);
		LogSearchCostTimeBean logSearchCostTimeBean2 = new LogSearchCostTimeBean();
		logSearchCostTimeBean2.setAvg_cost(50);
		logSearchCostTimeBean2.setMax_cost(100);
		logSearchCostTimeBean2.setMin_cost(2);
		logSearchCostTimeBean2.setOper_date("2017.03.01");
		logSearchCostTimeBean2.setOper_hour(10);
		ArrayList<LogSearchCostTimeBean> array = new ArrayList<LogSearchCostTimeBean>();
		array.add(logSearchCostTimeBean);
		array.add(logSearchCostTimeBean2);

		Mockito.when(searchCostDao.selectSearchCostPerHour(infoParam)).thenReturn(array);
		//Mockito.when(searchCostDao.selectSearchCostPerDay(infoParam)).thenThrow(new NullPointerException());
		//Mockito.when(searchCostDao.selectSearchCostPerDay((HashMap)Mockito.anyMapOf(String.class, String.class))).thenReturn(array);
		//Mockito.when(searchCostDao.selectSearchCostPerDay((HashMap)Mockito.anyMapOf(String.class, String.class))).thenThrow(new NullPointerException());
		
		iSearchCostServiceImpl = new ISearchCostServiceImpl();
		Field testAField = iSearchCostServiceImpl.getClass().getDeclaredField("searchCostDao");
		testAField.setAccessible(true);
		testAField.set(iSearchCostServiceImpl, searchCostDao);
		iSearchCostServiceImpl.showSearchCostByHour(logReqBean, resultBean);
		System.out.println("正常测试");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
		
		arr.clear();
		logReqBean.setUserList(arr);
		iSearchCostServiceImpl.showSearchCostByHour(logReqBean, resultBean);
		System.out.println("异常测试：上传的用户列表信息不能为空");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
	}

}
