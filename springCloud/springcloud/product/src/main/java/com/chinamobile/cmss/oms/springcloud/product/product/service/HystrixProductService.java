package com.chinamobile.cmss.oms.springcloud.product.product.service;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
public interface HystrixProductService {

    /**
     * 生产服务数据
     * @param name
     * @return
     */
    String productHystrix(String name);
}
