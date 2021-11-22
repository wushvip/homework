package com.chinamobile.cmss.oms.springcloud.consume.consume.service;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
public interface HystrixProductConsumeService {


    /**
     * 对生产服务的api进行消费
     * @param name
     * @return
     */
    String consumeHystrixProduct(String name);
}
