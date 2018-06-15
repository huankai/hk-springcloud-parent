package com.hk.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * <p>
 * 使用断路器，需要添加 @EnableCircuitBreaker 注解
 *
 * </p>
 * <p>
 *     通过源码可知，@SpringCloudApplication 注解是一个组合注解
 * </p>
 *
 * @author huangkai
 * @date 2018-6-3 15:00
 */
@EnableFeignClients
@SpringCloudApplication
public class HystrixFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixFeignApplication.class, args);
    }
}
