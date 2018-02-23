/**
 * 
 */
package com.hk.eureka.highavailability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <pre>
 * 	EnableEurekaServer 表示是一个 eureka 服务，启动 Eureka 服务注册中心的组件，对外提供服务注册和发现的功能
 * </pre>
 * @author huangkai
 * @author kally
 * @date 2018年2月23日下午15:47:30
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaHighAvailabilityServerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaHighAvailabilityServerApplication.class, args);
	}

}
