package com.hk.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>
 * Eureka 高可用(high availability)配置
 *
 * @author kally
 * @date 2018年2月6日下午5:45:04
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaHAServerApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaHAServerApplication.class, args);
    }

}
