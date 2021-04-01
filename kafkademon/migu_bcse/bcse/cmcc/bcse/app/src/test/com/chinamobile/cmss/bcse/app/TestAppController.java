package com.chinamobile.cmss.bcse.app;

import java.util.ArrayList;

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
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.app.controller.AppController;
import com.chinamobile.cmss.bcse.app.service.ApplicationManagerService;
import com.chinamobile.cmss.bcse.base.BaseUnit;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.user.controller.UserController;
import com.chinamobile.cmss.bcse.user.service.IUserService;

/**
 * 
 * @ClassName: TestUserController 
 * @Description: 应用模块Controller层单元测试
 * @author: lijingjing
 * @date: 2017年3月3日 下午4:12:01
 */
//按方法名顺序执行测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAppController extends BaseUnit{
	@Mock
	private ApplicationManagerService appService;
	@Resource
	@InjectMocks
	private AppController appController;
	

	
	@Before
	public void setUp() throws Exception {
		 // 初始化Mock
	    MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * 
	 * @Title: testShowList 
	 * @Description: 展示应用列表
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowList() throws Exception{
		Mockito.doNothing().when(appService).showAppList(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/apps")
				.param("userId", "admin")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(actual).value(expected));
		
	}

	/**
	 * 
	 * @Title: testDelete 
	 * @Description: 删除应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testDelete() throws Exception{
		Mockito.doNothing().when(appService).delete(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/apps/{appId}","appid")
				.param("userId", "userid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	/**
	 * 
	 * @Title: testDelete 
	 * @Description: 更新应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUpdate() throws Exception{
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		jsonObject.put("appStatus", "0");
		String json =jsonObject.toString();
		
		Mockito.doNothing().when(appService).update(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/apps/{appId}","appid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
	
	/**
	 * 
	 * @Title: testShowBasic 
	 * @Description: 展示应用基本信息
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testShowBasic() throws Exception{
	
		Mockito.doNothing().when(appService).showBasicInfo(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/apps/{appId}/basicInfo","appid")
				.param("userId", "userid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
	
	
	/**
	 * 
	 * @Title: testAddBasic 
	 * @Description: 添加应用基本信息
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testAddBasic() throws Exception{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		jsonObject.put("appStatus", "0");
		String json =jsonObject.toString();
		Mockito.doNothing().when(appService).addAppInfoDetail(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/apps/basicInfo")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
	
	
	/**
	 * 
	 * @Title: testUpBasic 
	 * @Description: 更新应用基本信息
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUpBasic() throws Exception{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		jsonObject.put("appName", "appname");
		String json =jsonObject.toString();
		Mockito.doNothing().when(appService).updateAppInfoDetail(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/apps/{appId}/basicInfo","appid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
	
	/**
	 * 
	 * @Title: testStructure 
	 * @Description: 获取应用结构
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testStructure() throws Exception{
		Mockito.doNothing().when(appService).showStructure(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/apps/{appId}/structure","appid")
				.param("userId", "userid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
	
	
	/**
	 * 
	 * @Title: testAddStructure 
	 * @Description: 添加应用结构
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testAddStructure() throws Exception{
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		ArrayList<AppTableMapBean> list = new ArrayList<AppTableMapBean>();
		jsonObject.put("appTableList", list);
		String json =jsonObject.toString();
		Mockito.doNothing().when(appService).saveAppStructure(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/apps/{appId}/structure","appid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
	
	

	
	
	/**
	 * 
	 * @Title: testCreate 
	 * @Description: 完成创建应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testCreate() throws Exception{
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId", "admin");
		String json =jsonObject.toString();
		Mockito.doNothing().when(appService).createApp(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/apps/{appId}/last","appid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(json.getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
	
	
	/**
	 * 
	 * @Title: testExist 
	 * @Description: 是否有创建中的应用
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testExist() throws Exception{
		Mockito.doNothing().when(appService).isExistCreatingApp(Mockito.any(AppReqBean.class), Mockito.any(ResultBean.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/apps/existFlag")
				.param("userId", "userid")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				;
		
	}
}
