package com.chinamobile.cmss.oms.springcloud.consume.consume.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HystrixConsumeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testConsumeHystrix() throws Exception {
        //短路径
        String uri = "/consume/hystrix";

        //mock url获取访问结果
        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                .param("name", "suyan-01"))
                .andReturn();

        //访问结果必定是fallback，由于依赖于product服务，测试时，它不生效，所以会触发回退机制
        System.out.println("result: " + mvcResult.getResponse().getContentAsString());
    }


}
