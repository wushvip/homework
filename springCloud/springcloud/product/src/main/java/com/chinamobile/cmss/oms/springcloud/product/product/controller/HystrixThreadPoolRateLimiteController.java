package com.chinamobile.cmss.oms.springcloud.product.product.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author LIAOJIANYA
 * @date 2020/10/9
 */
@RestController
@Slf4j
public class HystrixThreadPoolRateLimiteController {

    @HystrixCommand(
            //默认值为注解方法的名称
            commandKey = "thread-key",
            //定制断路器行为，接受@HystrixProperty对象数组
            commandProperties = {
                    //隔离策略：线程池隔离策略，默认THREAD
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    //超时时间，默认1s
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    //开启熔断器，默认true
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    //断路器跳闸前，窗口时间内必须达到的请求数，默认20
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    //断路器跳闸前，滚动窗口内必须达到的故障百分比，默认50
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    //断路器跳闸后，熔断器进入半开状态，允许放行一个试探请求前的等待时间，默认5000ms
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            },
            //线程池名称
            threadPoolKey = "threadPool-key",
            //线程池属性
            threadPoolProperties = {
                    //核心线程数
                    @HystrixProperty(name = "coreSize", value = "5"),
                    //最大队列数
                    @HystrixProperty(name = "maxQueueSize", value = "2"),
                    //使用THREAD隔离技术后，设置的拒绝请求阈值
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "3")
            },
            //降级方法名称
            fallbackMethod = "hystrixThreadFallback"
    )
    @RequestMapping(value = "/hystrix/threadPool", method = RequestMethod.GET)
    public String hystrixThreadPool(@RequestParam String name) {
        try {
            Thread.sleep(2000);
            log.info("thread pool: {}", name);
        } catch (InterruptedException e) {
            log.error("thread timeout");
        }
//        randomlyRunLong();
        return "thread pool:" + name;
    }

    /**
     * 接口调用失败后的服务降级方法（方法签名要和主方法保持一致）
     * @param name
     * @return
     */
    public String hystrixThreadFallback(@RequestParam String name) {
        log.warn("hystrix-thread fallback");
        return "thread pool: fallback!";
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
