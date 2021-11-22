package com.chinamobile.cmss.oms.springcloud.product.product.controller;

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
public class HystrixProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void productHystrixTest() throws Exception{
        //访问短路径
        String uri = "/product/hystrix";

        //controller层的mock结果
        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                .param("name", "suyan"))
                .andReturn();

        //获取访问结果
        System.out.println("result: " + mvcResult.getResponse().getContentAsString());
    }


}
