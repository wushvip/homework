package com.chinamobile.cmss.oms.springcloud.consume.consume.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LIAOJIANYA
 * @date 2020/9/25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HystrixProductConsumeServiceImplTest {

    @Autowired
    HystrixProductConsumeService hystrixProductConsumeService;

    @Before
    public void setUp() {

    }

    @Test
    public void testConsumeHystrixProduct() {
        String name = "su-yan";
        String resultStr = hystrixProductConsumeService.consumeHystrixProduct(name);
        System.out.println("result: " + resultStr);
    }
}
