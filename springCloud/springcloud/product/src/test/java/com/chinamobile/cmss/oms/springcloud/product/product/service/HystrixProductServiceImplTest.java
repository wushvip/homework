package com.chinamobile.cmss.oms.springcloud.product.product.service;

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
public class HystrixProductServiceImplTest {

    @Autowired
    HystrixProductService hystrixProductService;

    @Before
    public void setUp() {

    }

    @Test
    public void testProductHystrix () {
        String name = "product-suyan";
        String resultStr = hystrixProductService.productHystrix(name);
        System.out.println("result: " + resultStr);
    }
}
