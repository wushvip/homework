package com.chinamobile.cmss.bcse.index.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })

public class IndexControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

//	@Test
	public void TestIndex() throws Exception {

		String result = mockMvc
				.perform(post("/apps/123/index").contentType(MediaType.APPLICATION_JSON).param("userId", "12")
						.param("mutiValueSplit", "|").param("fileSplit", ","))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(result);

		String result1 = mockMvc
				.perform(delete("/apps/123/index").contentType(MediaType.APPLICATION_JSON).param("userId", "12")
						.param("mutiValueSplit", "|").param("fileSplit", ","))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(result1);

	}

	@Test
	public void TestSample() throws Exception {
		
		/*String result = mockMvc
				.perform(post("/apps/111/sample","UT").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(result);*/

		String result1 = mockMvc
				.perform(get("/apps/123/sample").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("userId",
						"1234"))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(result1);

	}
}
