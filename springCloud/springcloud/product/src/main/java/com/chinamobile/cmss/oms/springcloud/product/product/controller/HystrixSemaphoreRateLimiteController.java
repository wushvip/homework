package com.chinamobile.cmss.oms.springcloud.product.product.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author LIAOJIANYA
 * @date 2020/10/10
 */
@RestController
@Slf4j
public class HystrixSemaphoreRateLimiteController {

    @HystrixCommand(
            //默认值为注解方法的名称
            commandKey = "semaphore-key",
            //定制断路器行为，接受@HystrixProperty对象数组
            commandProperties = {
                    //隔离策略：信号量
                    @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"),
                    //最大并发请求数限制，默认值10
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "12"),
                    //超时时间，默认1s
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            //降级方法名称
            fallbackMethod = "hystrixSemaphoreFallback"
    )
    @RequestMapping(value = "/hystrix/semaphore", method = RequestMethod.GET)
    public String hystrixSemaphore(@RequestParam String name) {


        try {
            Thread.sleep(2000);
            log.info("semaphore: {}", name);
        } catch (InterruptedException e) {
            log.error("thread timeout");
        }
//        randomlyRunLong();

        return "semaphore: " + name;
    }


    /**
     * 接口调用失败后的服务降级方法（方法签名要和主方法保持一致）
     * @param name
     * @return
     */
    public String hystrixSemaphoreFallback(@RequestParam String name) {
        log.warn("hystrix-semaphore fallback");
        return "semaphore: fallback!";
    }

    /**
     * 随机睡眠
     */
    private void randomlyRunLong() {
        Random random = new Random();
        int randomNum = random.nextInt((3-1) + 1) + 1;
        //三分之一的概率超时
        if (randomNum == 3) {
            sleepTwoSeconds();
        }
    }

    /**
     * 睡眠2s
     */
    private void sleepTwoSeconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("thread timeout");
        }
    }



}
