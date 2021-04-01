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
import com.chinamobile.cmss.bcse.datastatistics.bean.LogMaxSearchTimesBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogSearchTimesBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.UserAppBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.SearchTimesDao;
import com.chinamobile.cmss.bcse.datastatistics.service.impl.ISearchTimesServiceImpl;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

public class ISearchTimesServiceImplTest{
	@Mock
	private SearchTimesDao searchTimesDao;
	
	private ISearchTimesServiceImpl iSearchTimesServiceImpl;
	
	
	@Before
	public void setUp() throws Exception {
		// 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShowSearchTimesByDay() throws Exception{
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
		LogSearchTimesBean logSearchTimesBean = new LogSearchTimesBean();
		logSearchTimesBean.setOper_date("2017.03.02");
		logSearchTimesBean.setSearch_count(100);
		LogSearchTimesBean logSearchTimesBean2 = new LogSearchTimesBean();
		logSearchTimesBean2.setOper_date("2017.03.01");
		logSearchTimesBean2.setSearch_count(50);
		ArrayList<LogSearchTimesBean> array = new ArrayList<LogSearchTimesBean>();
		array.add(logSearchTimesBean);
		array.add(logSearchTimesBean2);

		Mockito.when(searchTimesDao.selectSearchTimesAsDay(infoParam)).thenReturn(array);
		
		iSearchTimesServiceImpl = new ISearchTimesServiceImpl();
		Field testAField = iSearchTimesServiceImpl.getClass().getDeclaredField("searchTimesDao");
		testAField.setAccessible(true);
		testAField.set(iSearchTimesServiceImpl, searchTimesDao);
		iSearchTimesServiceImpl.showSearchTimesByDay(logReqBean, resultBean);
		System.out.println("正常测试");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
		
		arr.clear();
		logReqBean.setUserList(arr);
		iSearchTimesServiceImpl.showSearchTimesByDay(logReqBean, resultBean);
		System.out.println("异常测试：上传的用户列表信息不能为空");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
	}

	@Test
	public void testShowSearchTimesByHour() throws Exception{
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
		infoParam.put("OPER_DATE", Tool.getDayTime(-1));
		//结果构造
		LogMaxSearchTimesBean logMaxSearchTimesBean = new LogMaxSearchTimesBean();
		logMaxSearchTimesBean.setSearch_count(100);
		logMaxSearchTimesBean.setOper_hour(1);
		LogMaxSearchTimesBean logMaxSearchTimesBean2 = new LogMaxSearchTimesBean();
		logMaxSearchTimesBean2.setSearch_count(50);
		logMaxSearchTimesBean2.setOper_hour(2);
		ArrayList<LogMaxSearchTimesBean> array = new ArrayList<LogMaxSearchTimesBean>();
		array.add(logMaxSearchTimesBean);
		array.add(logMaxSearchTimesBean2);

		Mockito.when(searchTimesDao.selectSearchTimesPerHour(infoParam)).thenReturn(array);
		
		iSearchTimesServiceImpl = new ISearchTimesServiceImpl();
		Field testAField = iSearchTimesServiceImpl.getClass().getDeclaredField("searchTimesDao");
		testAField.setAccessible(true);
		testAField.set(iSearchTimesServiceImpl, searchTimesDao);
		iSearchTimesServiceImpl.showSearchTimesByHour(logReqBean, resultBean);
		System.out.println("正常测试");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
		
		arr.clear();
		logReqBean.setUserList(arr);
		iSearchTimesServiceImpl.showSearchTimesByHour(logReqBean, resultBean);
		System.out.println("异常测试：上传的用户列表信息不能为空");
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getResult());
	}

	@Test
	public void testYesterdaySearchTimes() throws Exception{
		//请求构造
		String userId = "userid";
		String userId_NG = "NG";
		String appId = "appid";
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", userId);
		infoParam.put("APP_ID", appId);
		infoParam.put("OPER_DATE", Tool.getDayTime(-1));
		HashMap<String, String> infoParam2 = new HashMap<String, String>();
		infoParam2.put("USER_ID", userId_NG);
		infoParam2.put("APP_ID", appId);
		infoParam2.put("OPER_DATE", Tool.getDayTime(-1));
		//结果构造
		LogSearchTimesBean logSearchTimesBean = new LogSearchTimesBean();
		LogSearchTimesBean logSearchTimesBean2 = null;
		logSearchTimesBean.setOper_date("2017");
		logSearchTimesBean.setSearch_count(9);
		Mockito.when(searchTimesDao.selectSearchTimes(infoParam)).thenReturn(logSearchTimesBean);
		Mockito.when(searchTimesDao.selectSearchTimes(infoParam2)).thenReturn(logSearchTimesBean2);

		iSearchTimesServiceImpl = new ISearchTimesServiceImpl();
		Field testAField = iSearchTimesServiceImpl.getClass().getDeclaredField("searchTimesDao");
		testAField.setAccessible(true);
		testAField.set(iSearchTimesServiceImpl, searchTimesDao);
		int a = iSearchTimesServiceImpl.yesterdaySearchTimes(userId, appId);
		System.out.println("正常测试");
		System.out.println(a);

		int b =iSearchTimesServiceImpl.yesterdaySearchTimes(userId_NG, appId);
		System.out.println("分支测试：数据库返回结果为空");
		System.out.println(b);
	}

	@Test
	public void testMaxSearchTimes() throws Exception{
		//请求构造
		String userId = "userid";
		String userId_NG = "NG";
		String appId = "appid";
		HashMap<String, String> infoParam = new HashMap<String, String>();
		infoParam.put("USER_ID", userId);
		infoParam.put("APP_ID", appId);
		infoParam.put("OPER_DATE_START", Tool.getDayTime(-1) + " 00:00:01");
		infoParam.put("OPER_DATE_END", Tool.getDayTime(-1) + " 23:59:59");
		//结果构造
		LogMaxSearchTimesBean logMaxSearchTimesBean = new LogMaxSearchTimesBean();
		logMaxSearchTimesBean.setSearch_count(1111);
		LogMaxSearchTimesBean logMaxSearchTimesBean2 = new LogMaxSearchTimesBean();
		Mockito.when(searchTimesDao.selectMaxSearchTimes(infoParam)).thenReturn(logMaxSearchTimesBean).thenReturn(logMaxSearchTimesBean2);

		iSearchTimesServiceImpl = new ISearchTimesServiceImpl();
		Field testAField = iSearchTimesServiceImpl.getClass().getDeclaredField("searchTimesDao");
		testAField.setAccessible(true);
		testAField.set(iSearchTimesServiceImpl, searchTimesDao);
		int a = iSearchTimesServiceImpl.maxSearchTimes(userId, appId);
		System.out.println("正常测试");
		System.out.println(a);
		
		int b = iSearchTimesServiceImpl.maxSearchTimes(userId_NG, appId);
		System.out.println("分支测试：数据库返回结果为空");
		System.out.println(b);
	}

}
