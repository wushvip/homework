package com.chinamobile.cmss.bcse.user;

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

import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.dao.AppInfoBeanDao;
import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
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
//按方法名顺序执行测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserService extends BaseUnit{
	
	@Mock
	private UserDao userDao;
	@Mock
	private AppInfoBeanDao appInfoBeanDao;
	@Mock
	private RedisService redisService;
	@Mock
	private ValidatorApi uImpl;
	@InjectMocks
	@Resource
	private IUserService iuService;
	

	
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	    result = new ResultBean();
	    //配置dao
		iuService = new IUserServiceImpl();
		Field testDao = iuService.getClass().getDeclaredField("userDao");
		Field ul = iuService.getClass().getDeclaredField("uImpl");
		Field redis = iuService.getClass().getDeclaredField("redisService");
		Field app = iuService.getClass().getDeclaredField("appInfoBeanDao");
		testDao.setAccessible(true);
		testDao.set(iuService, userDao);
		
		ul.setAccessible(true);
		ul.set(iuService, uImpl);
		
		redis.setAccessible(true);
		redis.set(iuService, redisService);
		app.setAccessible(true);
		app.set(iuService, appInfoBeanDao);
	}
	
			/**
	        * @Title: testAdd
	        * @Description: 添加
	        * @throws Exception
	        * @return: void
	        */
//	       @Test
	       public void testAdd() throws Exception{
	               UserBean userBean = new UserBean();
	               userBean.setUserMail("18817596152@139.com");
	               Mockito.when(userDao.getUserByName(Mockito.any(String.class))).thenReturn(null);
	               Mockito.when(userDao.insertUser(Mockito.any(UserBean.class))).thenReturn(1);
	               Mockito.when(uImpl.authorityValidate(Mockito.any(UserBean.class))).thenReturn(true);
	       			Mockito.doNothing().when(redisService).saveOrUpdate(Mockito.any(String.class), Mockito.any(String.class));
	               iuService.addUser(userBean,result);
	               Assert.assertEquals(UtMsg, expected, result.getStatus());
	
	       }
	

	/**
	 * 
	 * @Title: testLogin 
	 * @Description: 登录
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testLogin() throws Exception{
		
		UserBean userBean = new UserBean();
		userBean.setUserId("admin");
		userBean.setUserStatus("0");
		Mockito.when(userDao.checkPwd(Mockito.any(UserBean.class))).thenReturn(userBean);
		iuService.userLogin(userBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());
		
	}
	
	/**
	 * 
	 * @Title: testUsers 
	 * @Description: 用户列表
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUsers() throws Exception{
		
		UserBean userBean = new UserBean();
		userBean.setUserId("admin");
		userBean.setUserStatus("0");

		ArrayList<UserBean> list = new ArrayList<UserBean>();
		list.add(userBean);
		Mockito.when(userDao.selectUserList(Mockito.any(UserBean.class))).thenReturn(list);
		Mockito.when(userDao.getTotalItems(Mockito.any(UserBean.class))).thenReturn(1);
		Mockito.when(uImpl.authorityValidate(Mockito.any(UserBean.class))).thenReturn(true);
		iuService.showUserList(userBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());
		
	}
	
	/**
	 * 
	 * @Title: testDel 
	 * @Description: 删除
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testDel() throws Exception{
		UserBean userBean = new UserBean();
		Mockito.when(userDao.deleUser(Mockito.any(UserBean.class))).thenReturn(1);
		ArrayList<AppInfoBean> list = new ArrayList<AppInfoBean>();
		Mockito.when(appInfoBeanDao.getAppList(Mockito.any(AppReqBean.class))).thenReturn(list);
		Mockito.when(uImpl.authorityValidate(Mockito.any(UserBean.class))).thenReturn(true);
		Mockito.doNothing().when(redisService).delete(Mockito.any(String.class));
		Mockito.doNothing().when(redisService).delete(Mockito.any(ArrayList.class));
		
		iuService.delUser(userBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());
		
	}
	
	/**
	 * 
	 * @Title: testUpPwd 
	 * @Description: 更新密码
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUpPwd() throws Exception{
		UserBean userBean = new UserBean();
		userBean.setUserId("UT");
		userBean.setId("UT");
		
		Mockito.when(userDao.checkPwdId(Mockito.any(UserBean.class))).thenReturn(userBean);
		Mockito.when(userDao.updateUser(Mockito.any(UserBean.class))).thenReturn(1);
		Mockito.when(uImpl.existValidate(Mockito.any(String.class))).thenReturn(true);
		iuService.updatePwd(userBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());
		
	}
	
	/**
	 * 
	 * @Title: testRestPwd 
	 * @Description: 重置密码
	 * @throws Exception
	 * @return: void
	 */
//	@Test
	public void testRestPwd() throws Exception{
		UserBean userBean = new UserBean();
		userBean.setUserId("UT");
		userBean.setId("UT");
		userBean.setUserMail("18817596152@139.com");
		Mockito.when(userDao.getUserById(Mockito.any(String.class))).thenReturn(userBean);
		Mockito.when(userDao.updateUser(Mockito.any(UserBean.class))).thenReturn(1);
		Mockito.when(uImpl.authorityValidate(Mockito.any(String.class))).thenReturn(true);
		iuService.resetPassword(userBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());
		
	}
	
	/**
	 * 
	 * @Title: testUpdate 
	 * @Description: 
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUpdate() throws Exception{
		UserBean userBean = new UserBean();
		userBean.setUserId("UT");
		userBean.setId("UT");
		
		Mockito.when(userDao.checkPwdId(Mockito.any(UserBean.class))).thenReturn(userBean);
		Mockito.when(userDao.updateUser(Mockito.any(UserBean.class))).thenReturn(1);
		Mockito.when(uImpl.existValidate(Mockito.any(String.class))).thenReturn(true);
		Mockito.when(uImpl.authorityValidate(Mockito.any(String.class))).thenReturn(true);
		iuService.updateUser(userBean,result);
		Assert.assertEquals(UtMsg, expected, result.getStatus());
		
	}
	
}
