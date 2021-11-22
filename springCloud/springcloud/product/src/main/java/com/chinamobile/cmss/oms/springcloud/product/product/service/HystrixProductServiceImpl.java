package com.chinamobile.cmss.oms.springcloud.product.product.service;

import com.chinamobile.cmss.oms.springcloud.commonbean.entity.HystrixServiceBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
@Service
@Slf4j
public class HystrixProductServiceImpl implements HystrixProductService {
    @Override
    public String productHystrix(String name) {
        log.info("name={} : product hystrix ... ...", name);
        HystrixServiceBean hystrixServiceBean = new HystrixServiceBean();
        hystrixServiceBean.setName(name);
        return hystrixServiceBean.toString();
    }
}
