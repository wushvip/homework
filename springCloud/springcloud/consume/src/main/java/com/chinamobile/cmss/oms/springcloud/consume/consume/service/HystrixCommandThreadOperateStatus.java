package com.chinamobile.cmss.oms.springcloud.consume.consume.service;

import com.chinamobile.cmss.oms.springcloud.consume.consume.constant.CommonConstants;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author LIAOJIANYA
 * @date 2020/9/28
 */
@Slf4j
public class HystrixCommandThreadOperateStatus extends HystrixCommand<String> {


    private String operateStatus;

    public HystrixCommandThreadOperateStatus(String operateStatus) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixCommandThreadOperateStatus"))
        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                //设置线程池大小
        .withCoreSize(CommonConstants.HYSTRIX_COMMAND_CORE_SIZE)
                //设置最大等待队列大小
        .withMaxQueueSize(CommonConstants.HYSTRIX_COMMAND_QUEUE_SIZE))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                //设置超时时间，默认是1000ms，
        .withExecutionTimeoutInMilliseconds(CommonConstants.HYSTRIX_COMMAND_THREAD_POOL_TIMEOUT)));

        log.info("operateStatus: " + operateStatus);

        this.operateStatus = operateStatus;
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(1000);
        return operateStatus;
    }

    @Override
    protected String getFallback() {
        return "hystrix thread pool: fall-back";
    }
}
