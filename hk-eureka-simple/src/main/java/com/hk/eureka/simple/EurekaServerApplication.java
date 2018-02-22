/**
 * 
 */
package com.hk.eureka.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <pre>
 * 	EnableEurekaServer 表示是一个 eureka 服务，启动 Eureka 服务注册中心的组件，对外提供服务注册和发现的功能
 * </pre>
 * @author huangkai
 * @author kally
 * @date 2018年2月6日下午5:45:04
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
