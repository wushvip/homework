package com.chinamobile.cmss.bcse.datastatistics.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alibaba.fastjson.JSON;
import com.chinamobile.cmss.bcse.config.service.impl.ListRedisService;
import com.chinamobile.cmss.bcse.datastatistics.bean.HotWordsBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.HotWordsDao;
import com.chinamobile.cmss.bcse.datastatistics.service.impl.IHotWordsServiceImpl;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

public class IHotWordsServiceImplTest{
	@Mock
	private HotWordsDao hotWordsDao;
	@Mock
	private ListRedisService listRedisService;
	@InjectMocks
	private IHotWordsServiceImpl hotWordsServiceImpl;
	LogReqBean logReqBean;
	ResultBean resultBean;
	
	@Before
	public void setUp() throws Exception {
		// 初始化Mock
	    MockitoAnnotations.initMocks(this);
	    //注入Mock
	   /* hotWordsServiceImpl = new IHotWordsServiceImpl();
	    Field testAField = hotWordsServiceImpl.getClass().getDeclaredField("hotWordsDao");
		testAField.setAccessible(true);
		testAField.set(hotWordsServiceImpl, hotWordsDao);*/
	    resultBean = new ResultBean();
	  //请求构造
	  logReqBean = new LogReqBean();
	  logReqBean.setAppId("appid");
	  logReqBean.setStartDate(-4);
	  logReqBean.setEndDate(-1);
	  logReqBean.setUserId("userid");
	  logReqBean.setPageIndex(1);
	  logReqBean.setPageNum(10);
	}

	@Test
	public void testShowHotWord() throws Exception{
		//结果构造
		ArrayList<HotWordsBean> list = new ArrayList<HotWordsBean>();
		HotWordsBean hb = new HotWordsBean();
		hb.setKeywords("keywords");
		hb.setOperTime("2017.03.12");
		hb.setSearchCount(9999);
		list.add(hb);
		Mockito.when(hotWordsDao.getTotalItemsHotWord(logReqBean)).thenReturn(100);
		Mockito.when(hotWordsDao.selectHotSearchWordAsDays(logReqBean)).thenReturn(list);
		hotWordsServiceImpl.showHotWord(logReqBean, resultBean);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
		
		
	}
	
	//以前代码未实现
	/*@Test
	public void testInsertWord() {
		
	}*/

	@Test
	public void testShowHotWordLastWeek() throws Exception{
		//结果构造
		ArrayList DataList = new ArrayList();
		HotWordsBean hb = new HotWordsBean();
		hb.setKeywords("keywordsforweek");
		hb.setOperTime("2017.03.11");
		hb.setSearchCount(888);
		HotWordsBean hb1 = new HotWordsBean();
		hb1.setKeywords("keywordsforweek1");
		hb1.setOperTime("2017.03.11");
		hb1.setSearchCount(8881);
		DataList.add(JSON.toJSONString(hb1));
		DataList.add(JSON.toJSONString(hb));
		Mockito.when(listRedisService.get(Mockito.any(String.class),Mockito.anyLong() , Mockito.anyLong())).thenReturn(DataList);
		Mockito.when(hotWordsDao.getTotalItemsHotWordAsWeek(logReqBean)).thenReturn(11);
		Mockito.when(hotWordsDao.selectHotSearchWordAsWeek(logReqBean)).thenReturn(new ArrayList());
		hotWordsServiceImpl.showHotWordLastWeek(logReqBean, resultBean);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
		
	}

	@Test
	public void testShowHotWordLastMonth() throws Exception{
		//结果构造
		ArrayList DataList = new ArrayList();
		HotWordsBean hb = new HotWordsBean();
		hb.setKeywords("keywordsforMonth");
		hb.setOperTime("2017.03.11");
		hb.setSearchCount(888);
		HotWordsBean hb1 = new HotWordsBean();
		hb1.setKeywords("keywordsforMonth1");
		hb1.setOperTime("2017.03.11");
		hb1.setSearchCount(8881);
		DataList.add(hb1);
		DataList.add(hb);
		Mockito.when(listRedisService.get(Mockito.any(String.class),Mockito.anyLong() , Mockito.anyLong())).thenThrow(new RuntimeException());
		Mockito.when(hotWordsDao.getTotalItemsHotWordAsMonth(logReqBean)).thenReturn(11);
		Mockito.when(hotWordsDao.selectHotSearchWordAsMonth(logReqBean)).thenReturn(DataList);
		hotWordsServiceImpl.showHotWordLastMonth(logReqBean, resultBean);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
	}

	@Test
	public void testShowHotWordYesterday() throws Exception{
		//结果构造
		ArrayList DataList = new ArrayList();
		HotWordsBean hb = new HotWordsBean();
		hb.setKeywords("keywordsforYesterday");
		hb.setOperTime("2017.03.11");
		hb.setSearchCount(888);
		HotWordsBean hb1 = new HotWordsBean();
		hb1.setKeywords("keywordsforYesterday1");
		hb1.setOperTime("2017.03.11");
		hb1.setSearchCount(8881);
		DataList.add(hb1);
		DataList.add(hb);
		Mockito.when(listRedisService.get(Mockito.any(String.class),Mockito.anyLong() , Mockito.anyLong())).thenThrow(new RuntimeException());
		Mockito.when(hotWordsDao.getTotalItemsHotWordYesterday(logReqBean)).thenReturn(11);
		Mockito.when(hotWordsDao.selectHotSearchWordYesterday(logReqBean)).thenReturn(DataList);
		hotWordsServiceImpl.showHotWordYesterday(logReqBean, resultBean);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
	}

	@Test
	public void testGetDataFromRedis() throws Exception{
		//结果构造
		ArrayList DataList = new ArrayList();
		HotWordsBean hb = new HotWordsBean();
		hb.setKeywords("keywordsforDB");
		hb.setOperTime("2017.03.11");
		hb.setSearchCount(888);
		HotWordsBean hb1 = new HotWordsBean();
		hb1.setKeywords("keywordsforDB1");
		hb1.setOperTime("2017.03.11");
		hb1.setSearchCount(8881);
		DataList.add(hb1);
		DataList.add(hb);
		Mockito.when(listRedisService.get(Mockito.any(String.class),Mockito.anyLong() , Mockito.anyLong())).thenThrow(new RuntimeException());
		Mockito.when(hotWordsDao.getTotalItemsHotWordAsWeek(logReqBean)).thenReturn(11);
		Mockito.when(hotWordsDao.selectHotSearchWordAsWeek(logReqBean)).thenReturn(DataList);
		hotWordsServiceImpl.showHotWordLastWeek(logReqBean, resultBean);
		System.out.println(resultBean.getMessage());
		System.out.println(resultBean.getStatus());
		System.out.println(resultBean.getResult());
	}

}
