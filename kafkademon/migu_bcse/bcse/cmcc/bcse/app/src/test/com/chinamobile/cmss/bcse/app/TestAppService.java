package com.chinamobile.cmss.bcse.app;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chinamobile.cmss.bcse.app.bean.AppFieldBean;
import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.app.dao.AppFieldBeanDao;
import com.chinamobile.cmss.bcse.app.dao.AppInfoBeanDao;
import com.chinamobile.cmss.bcse.app.dao.AppTableMapBeanDao;
import com.chinamobile.cmss.bcse.app.service.ApplicationManagerService;
import com.chinamobile.cmss.bcse.app.service.impl.ApplicationManagerServiceImpl;
import com.chinamobile.cmss.bcse.app.service.impl.IndexEntry;
import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.config.service.impl.FieldsServiceImpl;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.datastatistics.bean.DataInfoBean;
import com.chinamobile.cmss.bcse.datastatistics.service.IDataInfoService;
import com.chinamobile.cmss.bcse.datastatistics.service.ISearchTimesService;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.user.dao.UserDao;
import com.chinamobile.cmss.bcse.user.service.IUserService;
import com.chinamobile.cmss.bcse.user.service.impl.IUserServiceImpl;
import com.chinamobile.cmss.bcse.validate.authority.ValidatorApi;
import com.sun.tools.javac.util.List;

/**
 * 
 * @ClassName: TestUserService
 * @Description: 用户管理模块service层单元测试
 * @author: lijingjing
 * @date: 2017年3月3日 下午4:14:31
 */
// 按方法名顺序执行测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAppService extends BaseUnit {


	@Mock
	private AppInfoBeanDao appInfoBeanDao;
	@Mock
	private RedisService redisService;
	@Mock
	private ValidatorApi uImpl;
	@Mock
	private ValidatorApi appImpl;
	@Mock
	private IndexEntry indexEntry;
	@Mock
	private AppTableMapBeanDao appTableMapBeanDao;
	@Mock
	private AppFieldBeanDao appFieldBeanDao;
	@Mock
	private ISearchTimesService searchTimesService;
	@Mock
	private IDataInfoService dataInfoService;
	@Mock
	private FieldsServiceImpl fieldsService;
	
	@InjectMocks
	@Resource
	private ApplicationManagerService appService;

	@Before
	public void setUp() throws Exception {
		// 初始化Mock
		MockitoAnnotations.initMocks(this);
		result = new ResultBean();

		appService = new ApplicationManagerServiceImpl();
		Field dataService = appService.getClass().getDeclaredField("dataInfoService");
		dataService.setAccessible(true);
		dataService.set(appService, dataInfoService);
		
		Field ul = appService.getClass().getDeclaredField("uImpl");
		ul.setAccessible(true);
		ul.set(appService, uImpl);
		
		Field appuml = appService.getClass().getDeclaredField("appImpl");
		appuml.setAccessible(true);
		appuml.set(appService, appImpl);
		
		Field redis = appService.getClass().getDeclaredField("redisService");
		redis.setAccessible(true);
		redis.set(appService, redisService);
		
		Field app = appService.getClass().getDeclaredField("appInfoBeanDao");
		app.setAccessible(true);
		app.set(appService, appInfoBeanDao);
		
		
		Field index = appService.getClass().getDeclaredField("indexEntry");
		index.setAccessible(true);
		index.set(appService, indexEntry);
		
		Field appTab = appService.getClass().getDeclaredField("appTableMapBeanDao");
		appTab.setAccessible(true);
		appTab.set(appService, appTableMapBeanDao);
		
		Field appField = appService.getClass().getDeclaredField("appFieldBeanDao");
		appField.setAccessible(true);
		appField.set(appService, appFieldBeanDao);
		
		Field search = appService.getClass().getDeclaredField("searchTimesService");
		search.setAccessible(true);
		search.set(appService, searchTimesService);
		
		Field field = appService.getClass().getDeclaredField("fieldsService");
		field.setAccessible(true);
		field.set(appService, fieldsService);
		
		
		ArrayList<AppInfoBean> appList = new ArrayList<AppInfoBean>();
		ArrayList<AppTableMapBean> appTableMapBeans = new ArrayList<AppTableMapBean>();
		ArrayList<AppFieldBean> appFieldBean = new ArrayList<AppFieldBean>();
		AppInfoBean bean = new AppInfoBean();
		bean.setAppId("admin");
		DataInfoBean data = new DataInfoBean();
		data.setSize(1);
		
		Mockito.when(uImpl.authorityValidate(Mockito.any(UserBean.class))).thenReturn(true);
		Mockito.when(appInfoBeanDao.getTotalItemsNum(Mockito.any(AppReqBean.class))).thenReturn(1);
		Mockito.when(appInfoBeanDao.StopOrOpenAppStatus(Mockito.any(AppReqBean.class))).thenReturn(1);
		Mockito.when(appInfoBeanDao.getAppList(Mockito.any(AppReqBean.class))).thenReturn(appList);
		Mockito.when(appInfoBeanDao.DeleteAppDetail(Mockito.any(AppReqBean.class))).thenReturn(1);
		Mockito.when(appInfoBeanDao.modifyAppStatus(Mockito.any(AppReqBean.class))).thenReturn(1);
		Mockito.when(appInfoBeanDao.showSignAppDetail(Mockito.any(AppReqBean.class))).thenReturn(new AppInfoBean());
		Mockito.when(appInfoBeanDao.isExistApp(Mockito.any(AppReqBean.class))).thenReturn(appList);
		Mockito.when(appInfoBeanDao.addAppInfoDetail(Mockito.any(AppReqBean.class))).thenReturn(1);
		Mockito.when(appInfoBeanDao.updateAppDetail(Mockito.any(AppReqBean.class))).thenReturn(1);
		Mockito.when(appInfoBeanDao.getAppListByStatus(Mockito.any(AppReqBean.class))).thenReturn(appList);
		
		Mockito.when(appTableMapBeanDao.selectByAppId(Mockito.any(String.class))).thenReturn(appTableMapBeans);
		Mockito.when(appTableMapBeanDao.deleteByAppId(Mockito.any(String.class))).thenReturn(1);
		Mockito.when(appTableMapBeanDao.insert(Mockito.any(AppTableMapBean.class))).thenReturn(1);
		Mockito.when(appFieldBeanDao.selectByTableId(Mockito.any(String.class))).thenReturn(appFieldBean);
		
		Mockito.when(searchTimesService.yesterdaySearchTimes(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(1);
		Mockito.when(searchTimesService.maxSearchTimes(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(1);
		Mockito.when(dataInfoService.dataNumberNew(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(data);
		
		Mockito.when(indexEntry.deleteIndex(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class))).thenReturn(true);
		Mockito.when(indexEntry.createIndex(Mockito.any(AppReqBean.class))).thenReturn(true);
		
		Mockito.doNothing().when(redisService).delete(Mockito.any(String.class));
		Mockito.doNothing().when(redisService).delete(Mockito.any(ArrayList.class));
		Mockito.doNothing().when(redisService).saveOrUpdate(Mockito.any(String.class), Mockito.any(String.class));
	
		Mockito.doNothing().when(fieldsService).addSelectHandler(Mockito.any(String.class), Mockito.any(String.class));
		

	}


	/**
	 * 
	 * @Title: testStopOrOpenApp 
	 * @Description: 
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testStopOrOpenApp() throws Exception {
//		ArrayList<AppInfoBean> appList = new ArrayList<AppInfoBean>();
//		Mockito.when(appInfoBeanDao.StopOrOpenAppStatus(Mockito.any(AppReqBean.class))).thenReturn(1);
//		Mockito.when(appInfoBeanDao.getAppList(Mockito.any(AppReqBean.class))).thenReturn(appList);
//		Mockito.doNothing().when(redisService).delete(Mockito.any(ArrayList.class));
//		Mockito.doNothing().when(redisService).saveOrUpdate(Mockito.any(String.class), Mockito.any(String.class));
		AppReqBean appReqBean = new AppReqBean();
		appService.stopOrOpenApp(appReqBean);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	
	
	/**
	 * 
	 * @Title: testShowList 
	 * @Description: 获取应用列表
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowList() throws Exception {
//		ArrayList<AppInfoBean> appList = new ArrayList<AppInfoBean>();
//		Mockito.when(uImpl.authorityValidate(Mockito.any(UserBean.class))).thenReturn(true);
//		Mockito.when(appInfoBeanDao.getTotalItemsNum(Mockito.any(AppReqBean.class))).thenReturn(1);
//		Mockito.when(appInfoBeanDao.getAppList(Mockito.any(AppReqBean.class))).thenReturn(appList);
//		Mockito.when(searchTimesService.yesterdaySearchTimes(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(1);
//		Mockito.when(searchTimesService.maxSearchTimes(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(1);
		AppReqBean appReqBean = new AppReqBean();
		appService.showAppList(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}

	
	/**
	 * 
	 * @Title: testShowList 
	 * @Description: 删除应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testDelete() throws Exception {
//		Mockito.when(indexEntry.deleteIndex(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class))).thenReturn(true);
//		Mockito.when(appInfoBeanDao.DeleteAppDetail(Mockito.any(AppReqBean.class))).thenReturn(1);
//		Mockito.doNothing().when(redisService).delete(Mockito.any(String.class));
		AppReqBean appReqBean = new AppReqBean();
		appService.delete(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	
	/**
	 * 
	 * @Title: testUpdate 
	 * @Description: 更新应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUpdate() throws Exception {
//		Mockito.when(appInfoBeanDao.modifyAppStatus(Mockito.any(AppReqBean.class))).thenReturn(1);
//		Mockito.when(appInfoBeanDao.showSignAppDetail(Mockito.any(AppReqBean.class))).thenReturn(new AppInfoBean());
//		Mockito.doNothing().when(redisService).saveOrUpdate(Mockito.any(String.class), Mockito.any(String.class));
		AppReqBean appReqBean = new AppReqBean();
		appService.update(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	/**
	 * 
	 * @Title: testShowBasic 
	 * @Description: 展示应用基本信息
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowBasic() throws Exception {
//		AppInfoBean bean = new AppInfoBean();
//		bean.setAppId("admin");
//		DataInfoBean data = new DataInfoBean();
//		data.setSize(1);
//		Mockito.when(appInfoBeanDao.showSignAppDetail(Mockito.any(AppReqBean.class))).thenReturn(bean);
//		Mockito.when(dataInfoService.dataNumberNew(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(data);
//		Mockito.when(searchTimesService.yesterdaySearchTimes(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(1);
//		Mockito.when(searchTimesService.maxSearchTimes(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(1);

		AppReqBean appReqBean = new AppReqBean();
		appService.showBasicInfo(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	
	/**
	 * 
	 * @Title: testAddBasic 
	 * @Description: 添加应用基本信息
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testAddBasic() throws Exception {
//		ArrayList<AppInfoBean> appList = new ArrayList<AppInfoBean>();
//		Mockito.when(appInfoBeanDao.isExistApp(Mockito.any(AppReqBean.class))).thenReturn(appList);
//		Mockito.when(appInfoBeanDao.addAppInfoDetail(Mockito.any(AppReqBean.class))).thenReturn(1);

		AppReqBean appReqBean = new AppReqBean();
		appService.addAppInfoDetail(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	
	/**
	 * 
	 * @Title: testUpBasic 
	 * @Description: 更新应用基本信息
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUpBasic() throws Exception {
//		ArrayList<AppInfoBean> appList = new ArrayList<AppInfoBean>();
//		Mockito.when(appInfoBeanDao.isExistApp(Mockito.any(AppReqBean.class))).thenReturn(appList);
//		Mockito.when(appInfoBeanDao.updateAppDetail(Mockito.any(AppReqBean.class))).thenReturn(1);

		AppReqBean appReqBean = new AppReqBean();
		appService.addAppInfoDetail(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	/**
	 * 
	 * @Title: testShowBasic 
	 * @Description: 展示应用结构
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowStructure() throws Exception {
//		AppInfoBean bean = new AppInfoBean();
//		ArrayList<AppTableMapBean> appTableMapBeans = new ArrayList<AppTableMapBean>();
//		ArrayList<AppFieldBean> appFieldBean = new ArrayList<AppFieldBean>();
//	
//		Mockito.when(appInfoBeanDao.showSignAppDetail(Mockito.any(AppReqBean.class))).thenReturn(bean);
//		Mockito.when(appTableMapBeanDao.selectByAppId(Mockito.any(String.class))).thenReturn(appTableMapBeans);
//		Mockito.when(appFieldBeanDao.selectByTableId(Mockito.any(String.class))).thenReturn(appFieldBean);

		AppReqBean appReqBean = new AppReqBean();
		appService.showStructure(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	
	/**
	 * 
	 * @Title: testSaveStructure 
	 * @Description: 保存应用结构
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testSaveStructure() throws Exception {
//		AppInfoBean bean = new AppInfoBean();
//		ArrayList<AppTableMapBean> appTableMapBeans = new ArrayList<AppTableMapBean>();
//		ArrayList<AppFieldBean> appFieldBean = new ArrayList<AppFieldBean>();
//	
//		Mockito.when(appInfoBeanDao.showSignAppDetail(Mockito.any(AppReqBean.class))).thenReturn(bean);
//		Mockito.when(appTableMapBeanDao.selectByAppId(Mockito.any(String.class))).thenReturn(appTableMapBeans);
//		Mockito.when(appFieldBeanDao.selectByTableId(Mockito.any(String.class))).thenReturn(appFieldBean);

		AppReqBean appReqBean = new AppReqBean();
		ArrayList<AppTableMapBean> appTableList = new ArrayList<AppTableMapBean>();
		AppTableMapBean mapBean = new AppTableMapBean();
		ArrayList<AppFieldBean> fields = new ArrayList<AppFieldBean>();
		fields.add(new AppFieldBean());
		mapBean.setFields(fields);
		appTableList.add(mapBean);
		appReqBean.setAppTableList(appTableList);
		
		
		appService.saveAppStructure(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
	
	
	/**
	 * 
	 * @Title: testCreate 
	 * @Description: 创建应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testCreate() throws Exception {

		AppReqBean appReqBean = new AppReqBean();
		appService.createApp(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}

	
	/**
	 * 
	 * @Title: testisCreating 
	 * @Description: 是否存在创建中的应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testisCreating() throws Exception {

		AppReqBean appReqBean = new AppReqBean();
		appService.isExistCreatingApp(appReqBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());

	}
}
