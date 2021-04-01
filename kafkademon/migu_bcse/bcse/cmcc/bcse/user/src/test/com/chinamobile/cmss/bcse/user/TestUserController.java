package com.chinamobile.cmss.bcse.user;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.user.controller.UserController;
import com.chinamobile.cmss.bcse.user.service.IUserService;

/**
 * 
 * @ClassName: TestUserController 
 * @Description: 用户模块Controller层单元测试
 * @author: lijingjing
 * @date: 2017年3月3日 下午4:12:01
 */
//按方法名顺序执行测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserController extends BaseUnit{
	@Mock
	private IUserService iuService;
	@Resource
	@InjectMocks
	private UserController userController;
	

	
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * 
	 * @Title: testAdd 
	 * @Description: 添加用户
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testAdd() throws Exception{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		jsonObject.put("userName", "admin");
		jsonObject.put("userStatus", "1");
		String json =jsonObject.toString();
		Mockito.doNothing().when(iuService).addUser(Mockito.any(UserBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/users","UT")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(actual).value(expected));
		
	}
	/**
	 * 
	 * @Title: testShowUsers 
	 * @Description: 展示用户列表
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowUsers() throws Exception{
		
		Mockito.doNothing().when(iuService).showUserList(Mockito.any(UserBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.param("userId", "admin")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(actual).value(expected));
		
	}
	
	/**
	 * 
	 * @Title: testLogin 
	 * @Description: 用户登录
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testLogin() throws Exception{
		
		Mockito.doNothing().when(iuService).userLogin(Mockito.any(UserBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/login")
				.param("userName", "admin")
				.param("password", "1234567890")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(actual).value(expected));
		
	}
	
	/**
	 * 
	 * @Title: testRestPwd 
	 * @Description: 重置密码
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testRestPwd() throws Exception{
		
		Mockito.doNothing().when(iuService).resetPassword(Mockito.any(UserBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}/password","UT")
				.param("userId", "admin")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(actual).value(expected));
		
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
		
		Mockito.doNothing().when(iuService).updatePwd(Mockito.any(UserBean.class), Mockito.any(ResultBean.class));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		jsonObject.put("password", "admin");
		jsonObject.put("updatePassword", "1");
		String json =jsonObject.toString();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}/password","UT")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(actual).value(expected));
		
	}
	
	/**
	 * 
	 * @Title: testUpdate 
	 * @Description: 更新用户
	 * @throws Exception
	 * @return: void
	 */
/*	@Test
	public void testUpdate() throws Exception{
		
		Mockito.doNothing().when(iuService).updateUser(Mockito.any(UserBean.class), Mockito.any(ResultBean.class));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		jsonObject.put("userStatus", "1");
		String json =jsonObject.toString();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}","UT")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(actual).value(expected));
		
	}*/
}
