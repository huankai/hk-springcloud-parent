package com.hk.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author huangkai
 * @date 2018-6-3 15:00
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClients(value = @RibbonClient(name = "user-service",configuration = CustomUserServiceConfiguration.class))
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class))

//@RibbonClients(value = @RibbonClient(name = "user-service",configuration = CustomUserServiceConfiguration.class))
public class MovieApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
