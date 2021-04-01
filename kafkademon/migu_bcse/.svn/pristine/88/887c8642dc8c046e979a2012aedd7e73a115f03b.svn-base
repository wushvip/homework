package com.chinamobile.cmss.bcse.base;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration({ "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })

public class BaseUnit {
	

	@Autowired
	public WebApplicationContext wac;
	public MockMvc mockMvc;
	//密码123456
	protected  String passwd="e10adc3949ba59abbe56e057f20f883e";
	protected  String actual="$.status";
	protected  String expected="SUCCESS";
	protected  String UtMsg="单元测试失败";
	protected ResultBean result;
	@Before
	public void setup()  {
		try {
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void test(){
		
	}
}

