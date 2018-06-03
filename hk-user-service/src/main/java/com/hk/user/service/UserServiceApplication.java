package com.hk.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户中心(提供者)
 *
 * @author kally
 * @EnableEurekaClient 只是对 Eureka 可以，如果你不使用 Eureka，
 * 可以使用 {@link org.springframework.cloud.client.discovery.EnableDiscoveryClient} 注解
 * @date 2018年2月22日下午5:31:57
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
