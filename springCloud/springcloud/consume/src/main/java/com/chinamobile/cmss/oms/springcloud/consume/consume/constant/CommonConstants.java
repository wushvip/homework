package com.chinamobile.cmss.oms.springcloud.consume.consume.constant;

import org.springframework.stereotype.Component;

/**
 * @author LIAOJIANYA
 * @date 2020/9/28
 */
@Component
public class CommonConstants {

    //一般设置为Runtime.getRuntime().availableProcessors() * 2 + 1;
    public static int HYSTRIX_COMMAND_CORE_SIZE = 5;

    public static int HYSTRIX_COMMAND_QUEUE_SIZE = 3;

    public static int HYSTRIX_COMMAND_THREAD_POOL_TIMEOUT = 3000;
}
